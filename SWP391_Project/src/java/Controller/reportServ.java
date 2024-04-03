/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Entity.Report;
import Entity.Transaction;
import Entity.TransactionQueue;
import Entity.User;
import dao.DAO;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "reportServ", urlPatterns = {"/report"})
public class reportServ extends HttpServlet {

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
            out.println("<title>Servlet reportServ</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet reportServ at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private TransactionQueue transactionQueue;
    private DAO dao;

    public reportServ() {
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
        String xid = request.getParameter("code").trim();
        String xindex = request.getParameter("datax").trim();
        int index = Integer.parseInt(xindex);
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        DAO dao = new DAO();
        int id = dao.getOrderByCode(xid).getId();
        if (index == 1) {
            if (dao.getOrderByID(id).getStatus().equals("Người mua khiếu nại đơn hàng") || dao.getOrderByID(id).getStatus().equals("Yêu cầu admin giải quyết")) {
                dao.insertReport(8, id, dao.getOrderByID(id).getBuyer_id(), true, "Có xác nhận từ người mua rằng đơn hàng " + dao.getOrderByID(id).getCode() + "sai thông tin và huỷ đơn ", u.getId(), false);
                dao.updateOrderStatus(u.getId(), "Người bán huỷ đơn", id);
                dao.insertOrderHistory(id, "Người bán huỷ đơn", "Người bán huỷ đơn do đơn hàng sai thông tin", dao.getOrderByID(id).getCreate_by());
                dao.InsertHistory_Transaction(dao.getOrderByID(id).getTotal_paid_amount(), "+", true, "Hoàn tiền đơn hàng mã số:"  + dao.getOrderByID(id).getCode(), 1, dao.getOrderByID(id).getBuyer_id());
                dao.updateAmount(dao.getOrderByID(id).getTotal_paid_amount() + dao.getWallet(dao.getOrderByID(id).getBuyer_id()).getBalance(), dao.getUserById(dao.getOrderByID(id).getBuyer_id()).getId());
                response.getWriter().print("Bạn vừa huỷ đơn hàng do sai thông tin! Hãy chú ý hơn cho lần giao dịch sau");
            } else {
                response.getWriter().print("Không thực hiện được, vui lòng kiểm tra lại!");
            }
        } else if (index == 2) {
            if (dao.getOrderByID(id).getStatus().equals("Người mua khiếu nại đơn hàng")) {
                dao.insertReport(9, id, dao.getOrderByID(id).getBuyer_id(), true, "Người bán xác nhận đơn hàng đúng thông tin, chờ người mua xác nhận đơn hàng với mã sản code: " + dao.getOrderByID(id).getCode(), u.getId(), false);
                dao.updateOrderStatus(u.getId(), "Chờ người mua xác nhận", id);
                dao.insertOrderHistory(id, "Chờ người mua xác nhận", "Người bán xác nhận đơn hàng đúng thông tin, chờ người mua xác nhận đơn hàng", u.getId());
                response.getWriter().print("Bạn đã xác nhận đơn hàng đúng và chờ người mua xác nhận lại!");

            } else {
                response.getWriter().print("Không thực hiện được, vui lòng kiểm tra lại!");
            }
        } else {
            if (dao.getWallet(u.getId()).getBalance() > 10000) {
                if (!dao.getOrderByID(id).getStatus().equals("Yêu cầu admin giải quyết") && !dao.getOrderByID(id).getStatus().equals("Người mua đang kiểm tra đơn hàng")) {
                    if (!dao.getOrderByID(id).getStatus().equals("Đơn hàng đã hoàn thành")) {
                        dao.insertReport(7, id, 1, false, "2 bên không tự giải quyết được yêu cầu admin tham gia giải quyết đơn hàng mã code: " + dao.getOrderByID(id).getCode() + ", liên hệ người mua: " + dao.getUserById(dao.getOrderByID(id).getBuyer_id()).getEmail(), u.getId(), false);
                        dao.updateOrderStatus(u.getId(), "Yêu cầu admin giải quyết", id);
                        dao.insertOrderHistory(id, "Chờ Admin giải quyết", "Giao dịch được khiếu nại và chờ Admin giải quyết", dao.getOrderByID(id).getCreate_by());
                        dao.updateAmount(dao.getWallet(u.getId()).getBalance() - 10000, u.getId());
                        // binh them vao day
                        dao.InsertHistory_Transaction(10000, "-", true, "Yêu cầu tạo khiếu nại đơn hàng mã số:"  + dao.getOrderByID(id).getCode(), u.id, u.id);
                        
                        response.getWriter().print("Yêu cầu admin giải quyết thành công. Chờ giải quyết nhé!");
                    } else {
                        response.getWriter().print("Không thực hiện được, vui lòng kiểm tra lại!");
                    }

                } else {
                    response.getWriter().print("Không thực hiện được, vui lòng kiểm tra lại!");
                }

            } else {
                response.getWriter().print("Không đủ số dư để yêu cầu admin giải quyết đơn hàng! Hãy nạp thêm tiền tối thiếu 10.000đ nhé.");
            }
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
        String xid = request.getParameter("id").trim();
        int id = Integer.parseInt(xid);
        String xindex = request.getParameter("datax").trim();
        int index = Integer.parseInt(xindex);
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        DAO dao = new DAO();

        if (index == 1) {
            if (dao.getOrderByID(id).getStatus().equals("Người mua đang kiểm tra đơn hàng")) {
                dao.insertReport(1, id, dao.getOrderByID(id).getCreate_by(), false, "Có khiếu nại từ người mua rằng đơn hàng " + dao.getOrderByID(id).getCode() + " không đúng mô tả ", u.getId(), false);
                dao.updateOrderStatus(u.getId(), "Người mua khiếu nại đơn hàng", id);
                dao.insertOrderHistory(id, "Bên mua khiếu nại", "Người mua khiếu nại sản phẩm không đúng miêu tả", dao.getOrderByID(id).getBuyer_id());
                response.getWriter().print("Bạn vừa khiếu nại đơn hàng. Chờ giải quyết nhé!");
            } else {
                response.getWriter().print("Không thực hiện được, vui lòng kiểm tra lại!");
            }
        } else if (index == 2) {
            if (dao.getOrderByID(id).getStatus().equals("Đơn hàng đã hoàn thành")) {
                response.getWriter().print("Không thực hiện được, vui lòng kiểm tra lại!");
            } else {

                dao.insertReport(4, id, dao.getOrderByID(id).getCreate_by(), true, "Người mua đã hoàn tất mua đơn hàng với mã sản code: " + dao.getOrderByID(id).getCode(), u.getId(), false);
                dao.updateOrderStatus(u.getId(), "Đơn hàng đã hoàn thành", id);
                int transactionId = dao.insertTransactionBuy(dao.getOrderByID(id).getCreate_by(), dao.getOrderByID(id).getProductId(), "Pending"); // Insert transaction with pending status

                if (transactionId != -1) {
                    transactionQueue.addTransaction(new Transaction(transactionId, dao.getOrderByID(id).getCreate_by(), id, dao.getOrderByID(id).getTotal_received_amount()));
                    dao.insertOrderHistory(id, "Hoàn thành giao dịch", "Người mua xác nhận hoàn thành đơn hàng", dao.getOrderByID(id).getBuyer_id());
                    dao.InsertHistory_Transaction(dao.getOrderByID(id).getTotal_received_amount(), "+", true, "Hoàn thành đơn hàng mã số:"  + dao.getOrderByID(id).getCode(), u.id, dao.getOrderByID(id).getCreate_by());
                    response.getWriter().print("Bạn đã xác thực đơn hàng thành công. Xin cảm ơn!");
                   


                    //binh them vao day
                    new Thread(() -> transactionQueue.processTransactionsComlete()).start();
                    
                } else {
                    response.getWriter().print("Failed to process transaction. Please try again later!");
                }

            }
        } else {
            if (dao.getWallet(u.getId()).getBalance() > 10000) {
                if (!dao.getOrderByID(id).getStatus().equals("Yêu cầu admin giải quyết")) {
                    if (!dao.getOrderByID(id).getStatus().equals("Đơn hàng đã hoàn thành")) {
                        dao.insertReport(7, id, 1, false, "2 bên không tự giải quyết được yêu cầu admin tham gia giải quyết đơn hàng mã code: " + dao.getOrderByID(id).getCode() + ", liên hệ người mua: " + dao.getUserById(dao.getOrderByID(id).getBuyer_id()).getEmail(), u.getId(), false);
                        dao.updateOrderStatus(u.getId(), "Yêu cầu admin giải quyết", id);
                        dao.updateAmount(dao.getWallet(u.getId()).getBalance() - 10000, u.getId());
                        dao.insertOrderHistory(id, "Chờ Admin giải quyết", "Giao dịch được khiếu nại và chờ Admin giải quyết", dao.getOrderByID(id).getBuyer_id());
                    
                        dao.InsertHistory_Transaction(10000, "-", true, "Yêu cầu tạo khiếu nại đơn hàng mã số:"  + dao.getOrderByID(id).getCode(), u.id, u.id);

                        // bình them vao day
                        response.getWriter().print("Yêu cầu admin giải quyết thành công. Chờ giải quyết nhé!");
                    } else {
                        response.getWriter().print("Không thực hiện được, vui lòng kiểm tra lại!");
                    }

                } else {
                    response.getWriter().print("Không thực hiện được, vui lòng kiểm tra lại!");
                }

            } else {
                response.getWriter().print("Không đủ số dư để yêu cầu admin giải quyết đơn hàng! Hãy nạp thêm tiền tối thiếu 10.000đ nhé.");
            }
        }

        //1 là Người mua khiếu nại đơn hàng
        //2 là Người mua đang kiểm tra đơn hàng
        //3 là Sẵn sàng giao dịch
        //4 là Đơn hàng đã hoàn tất
        //5 là nạp tiền
        //6 là rút tiền
        //7 Yêu cầu admin giải quyết
        //8 Người bán huỷ đơn
        //9 Chờ người mua xác nhận
        //10 hoàn tiền từ admin
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
