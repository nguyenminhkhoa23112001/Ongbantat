/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Entity.Category;
import Entity.Product;
import Entity.Transaction;
import Entity.TransactionQueue;
import Entity.User;
import Entity.intermediateOrders;
import dao.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "buyServ", urlPatterns = {"/buy"})
public class buyServ extends HttpServlet {

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
            out.println("<title>Servlet buyServ</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet buyServ at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        //processRequest(request, response);
        request.getRequestDispatcher("verify.jsp").forward(request, response);
    }

    private TransactionQueue transactionQueue;
    private DAO dao;

    public buyServ() {
        this.transactionQueue = new TransactionQueue();
        this.dao = new DAO(); // Initialize DAO instance
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
        DAO dao = new DAO();
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        String id = request.getParameter("id");
        int idx = Integer.parseInt(id);
        Product p = dao.getProductByID(idx);
        intermediateOrders o = dao.getOrderByProductID(p.id);
        double price = 0;
        if(u != null) {
            if(p.Transaction_fee == true) {
            price = p.getPrice();
        } else {
            price = p.getPrice() + o.getIntermediary_fee();
        }
        if (dao.getWallet(u.getId()).getBalance() < price) {
            response.getWriter().print("Số dư của bạn không đủ để mua sản phẩm!");
            return;
        }

        if (p.getCreate_by() != u.getId()) {
            if (!o.getStatus().equals("Sẵn sàng giao dịch")) {
                response.getWriter().print("Sản phẩm đã được bán");
            } else {
                int transactionId = dao.insertTransactionBuy(u.getId(), idx, "Pending"); // Insert transaction with pending status
                if (transactionId != -1) {
                    // Add the purchase transaction to the queue
                    transactionQueue.addTransaction(new Transaction(transactionId, u.getId(), idx, price));
                    dao.updateOrder(u.id, "Người mua đang kiểm tra đơn hàng", idx);
                    dao.InsertHistory_Transaction(price, "-", true, "Thanh toán đơn hàng mã số :"+dao.getOrderByProductID(idx).getCode()+".", u.id,u.id);
                    dao.insertReport(2, dao.getOrderByProductID(idx).getId(), dao.getOrderByProductID(idx).getCreate_by(), true, "Bên mua đã thanh toán đơn hàng có mã sản phẩm là: " + dao.getOrderByProductID(idx).getCode() + ". Hãy kiểm tra thông tin đơn hàng!", u.getId(), false);
                    dao.insertOrderHistory(dao.getOrderByProductID(idx).getId(), "Bên mua đang kiểm tra hàng", "Người mua đã thanh toán và đang kiểm tra hàng", u.getId());
                    dao.deleteProductCartAfterBuy(idx);
                    response.getWriter().print("Bạn vừa mua sản phẩm, hãy kiểm tra đơn hàng!");
                    new Thread(() -> transactionQueue.processTransactions()).start();
                } else {
                    response.getWriter().print("Failed to process transaction. Please try again later!");
                }
            }
        } else {
            response.getWriter().print("Không thể mua sản phẩm của chính mình!");
        }
        } else {
            response.getWriter().print("Đăng nhập để mua sản phẩm");
        }
        
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
