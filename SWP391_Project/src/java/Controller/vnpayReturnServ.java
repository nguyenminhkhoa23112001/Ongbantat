/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Entity.Transaction;
import Entity.TransactionQueue;
import Entity.User;
import Entity.Wallet;
import Entity.vnpay_Transaction;
import com.vnpay.common.Config;
import dao.DAO;
import dao.VNPAYDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "vnpayReturnServ", urlPatterns = {"/vnpay_return"})
public class vnpayReturnServ extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet vnpayReturnServ</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet vnpayReturnServ at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private TransactionQueue transactionQueue;
    private DAO dao;

    public vnpayReturnServ() {
        this.transactionQueue = new TransactionQueue();
        this.dao = new DAO(); // Initialize DAO instance
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Map fields = new HashMap();
        for (Enumeration params = request.getParameterNames(); params.hasMoreElements();) {
            String fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
            String fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                fields.put(fieldName, fieldValue);
            }
        }
        //trc khi chuyen sang cho vnpay thi status mac dinh la pending, neu successed thi doi status sang thanh cong va soat lai 1 lan nua thi dau den cuoi xem co giao dich nao con pending thi xu li not (trong bang vnpay_transaction, nghia la cu thuc hien giao dich la luu vao db la pending r neu ma xu li xong thi cap nhat xem successed hay failed neu failed thi cap nhat vao trong bang wallet la account bang bao nhieu do ben vnpay tra ve status thi cap nhat status trong bang vnpay_transaction sau do neu thanh cong thi lai bat dau do lai xem co cai nao van con pending thi xu li not (neu van chua xu li dc truong hop 2 accout cung an thanh toan 1 luc thi phai dung queue(FIFO) de luu giao dich de xu li tung cai 1) neu thanh cong thi luu vao 1 cai queue r thuc hien tung cai 1 tranh reload lai trang thi lien tuc cong tien
        String vnp_SecureHash = request.getParameter("vnp_SecureHash");
        if (fields.containsKey("vnp_SecureHashType")) {
            fields.remove("vnp_SecureHashType");
        }
        if (fields.containsKey("vnp_SecureHash")) {
            fields.remove("vnp_SecureHash");
        }
        String signValue = Config.hashAllFields(fields);

        DAO dao = new DAO();
        VNPAYDAO vnpayDao = new VNPAYDAO();
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        Wallet w = dao.getWallet(u.getId());
        double amount = Double.parseDouble(request.getParameter("vnp_Amount")) / 100;
        boolean paid = false;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date dateTime = null;
        try {
            dateTime = dateFormat.parse(request.getParameter("vnp_PayDate"));
        } catch (ParseException ex) {
            Logger.getLogger(vnpayReturnServ.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        vnpay_Transaction vnpay = new vnpay_Transaction("Pending", w.getId(), request.getParameter("vnp_TxnRef"), request.getParameter("vnp_PayDate"), request.getParameter("vnp_OrderInfo"), request.getParameter("vnp_BankCode"));
        if (signValue.equals(vnp_SecureHash)) {
            List<vnpay_Transaction> vnpayList = vnpayDao.getListTransaction(w);
            if ("00".equals(request.getParameter("vnp_TransactionStatus"))) {
                for (vnpay_Transaction transaction : vnpayList) {
                    if (transaction != null && transaction.getTime() != null && transaction.getPayment_Code() != null) {
                        if (transaction.getWallet_id() == w.getId() && transaction.getTime().equals(request.getParameter("vnp_PayDate"))) {
                            paid = true;
                        }
                    }
                }
                if (paid == true) {
                    response.getWriter().print("Giao dịch của bạn đã hoàn thành trước đó rồi!");
                } else {
                    vnpayDao.setTransactionVnpay(vnpay);
                    int transactionId = dao.insertTransactionVnpay(u.getId(), request.getParameter("vnp_TxnRef"), "Pending");
                    if (transactionId != -1) {
                        transactionQueue.addTransaction(new Transaction(transactionId, u.getId(), request.getParameter("vnp_TxnRef"), amount));
                        transactionQueue.processTransactionsvnpay();
                        
                        //binh them vao day
                        dao.InsertHistory_Transaction(amount, "+", true, "Nạp tiền thành công", 1, u.id);
                        dao.insertReport(5, vnpayDao.getStatus(request.getParameter("vnp_TxnRef")).getID(), u.getId(), true, "Bạn đã nạp tiền thành công vào lúc: " + dateTime + " với số tiền là: " + amount + ". Hãy kiểm tra số dư tài khoản!", u.getId(), false);
                        session.setAttribute("balance", w.getBalance());
                        request.setAttribute("transactionCode", vnpay.getPayment_Code());
                        request.setAttribute("money", amount);
                        request.setAttribute("description", vnpay.getDescription());
                        request.setAttribute("bank", vnpay.getBankCode());
                        request.setAttribute("time", dateTime);
                        request.setAttribute("status", vnpayDao.getStatus(request.getParameter("vnp_TxnRef")).getStatus());
                        request.getRequestDispatcher("vnpayReturn.jsp").forward(request, response);
                        
                    }

                }
            } else {
                response.getWriter().print("Số tiền phải lớn hơn 10.000đ");
            }
        } else {
            response.getWriter().print("Invalid signature");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
