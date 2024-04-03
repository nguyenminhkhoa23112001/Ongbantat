/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Entity.Product;
import Entity.intermediateOrders;
import dao.DAO;
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
@WebServlet(name = "OrderBuyerDetailServ", urlPatterns = {"/orderBuyerDetail"})
public class OrderBuyerDetailServ extends HttpServlet {

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
            out.println("<title>Servlet OrderBuyerDetailServ</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderBuyerDetailServ at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/html;charset=UTF-8");
        DAO dao = new DAO();
        String code = request.getParameter("code").trim();
        intermediateOrders o = dao.getOrderByCode(code);
        String s = "";
        if (dao.getProductByID(o.getProductId()).isTransaction_fee()) {
            s = "Người bán";
        } else {
            s = "Người mua";
        }
        if (o.getStatus().equals("Người mua đang kiểm tra đơn hàng")) {
            response.getWriter().print("<h2 style=\"text-align: center;\">Chi tiết đơn hàng</h2>\n"
                    + "                            <input type=\"text\" id=\"order_id\" name=\"order_id\" readonly=\"\" hidden=\"\" value=\"" + o.getId() + "\"><br>\n"
                    + "                            <label for=\"order_code\">Mã đơn hàng trung gian</label><br>\n"
                    + "                            <input type=\"text\" id=\"order_code\" name=\"code\" value=\"" + o.getCode() + "\" readonly><br>\n"
                    + "                            <label for=\"order_code\">Tên sản phẩm</label><br>\n"
                    + "                            <input type=\"text\" id=\"productName1\"  value=\"" + dao.getProductByID(o.getProductId()).getName() + "\" readonly><br>\n"
                    + "                            <label for=\"order_code\">Giá sản phẩm</label><br>\n"
                    + "                            <input type=\"text\" id=\"Price\"  value=\"" + dao.getProductByID(o.getProductId()).getPrice() + "\" readonly><br>\n"
                    + "                            <label for=\"order_code\">Phí trung gian</label><br>\n"
                    + "                            <input type=\"text\" id=\"inter\"  value=\"" + o.getIntermediary_fee() + "\" readonly><br>\n"
                    + "                            <label for=\"order_code\">Bên chịu phí</label><br>\n"
                    + "                            <input type=\"text\" id=\"party1\"  value=\"" + s + "\" readonly><br>\n"
                    + "                            <label for=\"order_code\">Tổng tiền trả</label><br>\n"
                    + "                            <input type=\"text\" id=\"totalPaid1\"  value=\"" + o.getTotal_paid_amount() + "\" readonly><br>\n"
                    + "                            <label for=\"order_code\">Ảnh mô tả</label><br>\n"
                    + "                            <img style=\"max-width: 150px; max-height: 150px\" id=\"img1\" src=\"" + dao.getProductByID(o.getProductId()).getImage1() + "\" >\n"
                    + "                            <label for=\"order_code\">Mô tả sản phẩm</label><br>\n"
                    + "                            <input type=\"text\" id=\"des\"  value=\"" + dao.getProductByID(o.getProductId()).getDescription() + "\" readonly><br>\n"
                    + "                            <label for=\"order_code\">Thông tin ẩn</label><br>\n"
                    + "                            <input type=\"text\" id=\"hidden_info\"  value=\"" + dao.getProductByID(o.getProductId()).getHidden_content() + "\" readonly><br>\n"
                    + "                            <label for=\"order_code\">Liên hệ</label><br>\n"
                    + "                            <input type=\"text\" id=\"contact\"  value=\"" + dao.getProductByID(o.getProductId()).getContact_Method() + "\" readonly><br>\n"
                    + "                            <label for=\"order_code\">Trạng thái</label><br>\n"
                    + "                            <input type=\"text\" id=\"status1\"  value=\"" + o.getStatus() + "\" readonly><br>\n"
                    + "                            <label for=\"order_code\">Người bán</label><br>\n"
                    + "                            <input type=\"text\" id=\"buyer1\"  value=\"" + dao.getUserById(o.getCreate_by()).getDisplay_name() + "\" readonly><br>\n"
                    + "                            <label for=\"hidden_info\">Thời gian tạo</label><br>\n"
                    + "                            <input type=\"text\" id=\"create\" name=\"\" value=\"" + o.getCreate_at() + "\" readonly><br><br><br>\n"
                    + "                            <button type=\"submit\" class=\"complainOrder\" style=\"float: left; color: white; background-color: red; border: 1px solid red; padding: 10px; border-radius: 3px;\" data-orderi=\"1\">Khiếu nại đơn hàng không đúng mô tả</button>\n"
                    + "                            <button type=\"submit\" class=\"confirmOrder\" style=\"float: right; color: white; background-color: #4CAF50; border: 1px solid #4CAF50; padding: 10px; border-radius: 3px;\" data-orderi=\"2\">Xác nhận đơn hàng đúng mô tả</button>");
        } else if (o.getStatus().equals("Người mua khiếu nại đơn hàng")) {
            response.getWriter().print("<h2 style=\"text-align: center;\">Chi tiết đơn hàng</h2>\n"
                    + "                            <button class=\"requestAdmin\" type=\"submit\" style=\"float: right; color: white; background-color: #007bff; border: 1px solid; padding: 10px; border-radius: 3px; \" data-orderi=\"3\">Yêu cầu admin tham gia giải quyết</button>\n"
                    + "                            <input type=\"text\" id=\"order_id\" name=\"order_id\" readonly=\"\" hidden=\"\" value=\"" + o.getId() + "\"><br>\n"
                    + "                            <label for=\"order_code\">Mã đơn hàng trung gian</label><br>\n"
                    + "                            <input type=\"text\" id=\"order_code\" name=\"code\" value=\"" + o.getCode() + "\" readonly><br>\n"
                    + "                            <label for=\"order_code\">Tên sản phẩm</label><br>\n"
                    + "                            <input type=\"text\" id=\"productName1\"  value=\"" + dao.getProductByID(o.getProductId()).getName() + "\" readonly><br>\n"
                    + "                            <label for=\"order_code\">Giá sản phẩm</label><br>\n"
                    + "                            <input type=\"text\" id=\"Price\"  value=\"" + dao.getProductByID(o.getProductId()).getPrice() + "\" readonly><br>\n"
                    + "                            <label for=\"order_code\">Phí trung gian</label><br>\n"
                    + "                            <input type=\"text\" id=\"inter\"  value=\"" + o.getIntermediary_fee() + "\" readonly><br>\n"
                    + "                            <label for=\"order_code\">Bên chịu phí</label><br>\n"
                    + "                            <input type=\"text\" id=\"party1\"  value=\"" + s + "\" readonly><br>\n"
                    + "                            <label for=\"order_code\">Tổng tiền trả</label><br>\n"
                    + "                            <input type=\"text\" id=\"totalPaid1\"  value=\"" + o.getTotal_paid_amount() + "\" readonly><br>\n"
                    + "                            <label for=\"order_code\">Ảnh mô tả</label><br>\n"
                    + "                            <img style=\"max-width: 150px; max-height: 150px\" id=\"img1\" src=\"" + dao.getProductByID(o.getProductId()).getImage1() + "\" >\n"
                    + "                            <label for=\"order_code\">Mô tả sản phẩm</label><br>\n"
                    + "                            <input type=\"text\" id=\"des\"  value=\"" + dao.getProductByID(o.getProductId()).getDescription() + "\" readonly><br>\n"
                    + "                            <label for=\"order_code\">Thông tin ẩn</label><br>\n"
                    + "                            <input type=\"text\" id=\"hidden_info\"  value=\"" + dao.getProductByID(o.getProductId()).getHidden_content() + "\" readonly><br>\n"
                    + "                            <label for=\"order_code\">Liên hệ</label><br>\n"
                    + "                            <input type=\"text\" id=\"contact\"  value=\"" + dao.getProductByID(o.getProductId()).getContact_Method() + "\" readonly><br>\n"
                    + "                            <label for=\"order_code\">Trạng thái</label><br>\n"
                    + "                            <input type=\"text\" id=\"status1\"  value=\"" + o.getStatus() + "\" readonly><br>\n"
                    + "                            <label for=\"order_code\">Người bán</label><br>\n"
                    + "                            <input type=\"text\" id=\"buyer1\"  value=\"" + dao.getUserById(o.getCreate_by()).getDisplay_name() + "\" readonly><br>\n"
                    + "                            <label for=\"hidden_info\">Thời gian tạo</label><br>\n"
                    + "                            <input type=\"text\" id=\"create\" name=\"\" value=\"" + o.getCreate_at() + "\" readonly><br><br><br>\n");
        } else if (o.getStatus().equals("Chờ người mua xác nhận")) {
            response.getWriter().print("<h2 style=\"text-align: center;\">Chi tiết đơn hàng</h2>\n"
                    + "                            <button class=\"requestAdmin\" type=\"submit\" style=\"float: right; color: white; background-color: #007bff; border: 1px solid; padding: 10px; border-radius: 3px; \" data-orderi=\"3\">Yêu cầu admin tham gia giải quyết</button>\n"
                    + "                            <input type=\"text\" id=\"order_id\" name=\"order_id\" readonly=\"\" hidden=\"\" value=\"" + o.getId() + "\"><br>\n"
                    + "                            <label for=\"order_code\">Mã đơn hàng trung gian</label><br>\n"
                    + "                            <input type=\"text\" id=\"order_code\" name=\"code\" value=\"" + o.getCode() + "\" readonly><br>\n"
                    + "                            <label for=\"order_code\">Tên sản phẩm</label><br>\n"
                    + "                            <input type=\"text\" id=\"productName1\"  value=\"" + dao.getProductByID(o.getProductId()).getName() + "\" readonly><br>\n"
                    + "                            <label for=\"order_code\">Giá sản phẩm</label><br>\n"
                    + "                            <input type=\"text\" id=\"Price\"  value=\"" + dao.getProductByID(o.getProductId()).getPrice() + "\" readonly><br>\n"
                    + "                            <label for=\"order_code\">Phí trung gian</label><br>\n"
                    + "                            <input type=\"text\" id=\"inter\"  value=\"" + o.getIntermediary_fee() + "\" readonly><br>\n"
                    + "                            <label for=\"order_code\">Bên chịu phí</label><br>\n"
                    + "                            <input type=\"text\" id=\"party1\"  value=\"" + s + "\" readonly><br>\n"
                    + "                            <label for=\"order_code\">Tổng tiền trả</label><br>\n"
                    + "                            <input type=\"text\" id=\"totalPaid1\"  value=\"" + o.getTotal_paid_amount() + "\" readonly><br>\n"
                    + "                            <label for=\"order_code\">Ảnh mô tả</label><br>\n"
                    + "                            <img style=\"max-width: 150px; max-height: 150px\" id=\"img1\" src=\"" + dao.getProductByID(o.getProductId()).getImage1() + "\" >\n"
                    + "                            <label for=\"order_code\">Mô tả sản phẩm</label><br>\n"
                    + "                            <input type=\"text\" id=\"des\"  value=\"" + dao.getProductByID(o.getProductId()).getDescription() + "\" readonly><br>\n"
                    + "                            <label for=\"order_code\">Thông tin ẩn</label><br>\n"
                    + "                            <input type=\"text\" id=\"hidden_info\"  value=\"" + dao.getProductByID(o.getProductId()).getHidden_content() + "\" readonly><br>\n"
                    + "                            <label for=\"order_code\">Liên hệ</label><br>\n"
                    + "                            <input type=\"text\" id=\"contact\"  value=\"" + dao.getProductByID(o.getProductId()).getContact_Method() + "\" readonly><br>\n"
                    + "                            <label for=\"order_code\">Trạng thái</label><br>\n"
                    + "                            <input type=\"text\" id=\"status1\"  value=\"" + o.getStatus() + "\" readonly><br>\n"
                    + "                            <label for=\"order_code\">Người bán</label><br>\n"
                    + "                            <input type=\"text\" id=\"buyer1\"  value=\"" + dao.getUserById(o.getCreate_by()).getDisplay_name() + "\" readonly><br>\n"
                    + "                            <label for=\"hidden_info\">Thời gian tạo</label><br>\n"
                    + "                            <input type=\"text\" id=\"create\" name=\"\" value=\"" + o.getCreate_at() + "\" readonly><br><br><br>\n"
                    + "                            <button type=\"submit\" class=\"confirmOrder\" style=\"float: right; color: white; background-color: #4CAF50; border: 1px solid #4CAF50; padding: 10px; border-radius: 3px;\" data-orderi=\"2\">Xác nhận đơn hàng đúng mô tả</button>");

        } else if (o.getStatus().equals("Yêu cầu admin giải quyết")) {
            response.getWriter().print("<h2 style=\"text-align: center;\">Chi tiết đơn hàng</h2>\n"
                    + "                            <input type=\"text\" id=\"order_id\" name=\"order_id\" readonly=\"\" hidden=\"\" value=\"" + o.getId() + "\"><br>\n"
                    + "                            <label for=\"order_code\">Mã đơn hàng trung gian</label><br>\n"
                    + "                            <input type=\"text\" id=\"order_code\" name=\"code\" value=\"" + o.getCode() + "\" readonly><br>\n"
                    + "                            <label for=\"order_code\">Tên sản phẩm</label><br>\n"
                    + "                            <input type=\"text\" id=\"productName1\"  value=\"" + dao.getProductByID(o.getProductId()).getName() + "\" readonly><br>\n"
                    + "                            <label for=\"order_code\">Giá sản phẩm</label><br>\n"
                    + "                            <input type=\"text\" id=\"Price\"  value=\"" + dao.getProductByID(o.getProductId()).getPrice() + "\" readonly><br>\n"
                    + "                            <label for=\"order_code\">Phí trung gian</label><br>\n"
                    + "                            <input type=\"text\" id=\"inter\"  value=\"" + o.getIntermediary_fee() + "\" readonly><br>\n"
                    + "                            <label for=\"order_code\">Bên chịu phí</label><br>\n"
                    + "                            <input type=\"text\" id=\"party1\"  value=\"" + s + "\" readonly><br>\n"
                    + "                            <label for=\"order_code\">Tổng tiền trả</label><br>\n"
                    + "                            <input type=\"text\" id=\"totalPaid1\"  value=\"" + o.getTotal_paid_amount() + "\" readonly><br>\n"
                    + "                            <label for=\"order_code\">Ảnh mô tả</label><br>\n"
                    + "                            <img style=\"max-width: 150px; max-height: 150px\" id=\"img1\" src=\"" + dao.getProductByID(o.getProductId()).getImage1() + "\" >\n"
                    + "                            <label for=\"order_code\">Mô tả sản phẩm</label><br>\n"
                    + "                            <input type=\"text\" id=\"des\"  value=\"" + dao.getProductByID(o.getProductId()).getDescription() + "\" readonly><br>\n"
                    + "                            <label for=\"order_code\">Thông tin ẩn</label><br>\n"
                    + "                            <input type=\"text\" id=\"hidden_info\"  value=\"" + dao.getProductByID(o.getProductId()).getHidden_content() + "\" readonly><br>\n"
                    + "                            <label for=\"order_code\">Liên hệ</label><br>\n"
                    + "                            <input type=\"text\" id=\"contact\"  value=\"" + dao.getProductByID(o.getProductId()).getContact_Method() + "\" readonly><br>\n"
                    + "                            <label for=\"order_code\">Trạng thái</label><br>\n"
                    + "                            <input type=\"text\" id=\"status1\"  value=\"" + o.getStatus() + "\" readonly><br>\n"
                    + "                            <label for=\"order_code\">Người bán</label><br>\n"
                    + "                            <input type=\"text\" id=\"buyer1\"  value=\"" + dao.getUserById(o.getCreate_by()).getDisplay_name() + "\" readonly><br>\n"
                    + "                            <label for=\"hidden_info\">Thời gian tạo</label><br>\n"
                    + "                            <input type=\"text\" id=\"create\" name=\"\" value=\"" + o.getCreate_at() + "\" readonly><br><br><br>\n");
            
        }

        // HttpSession session = request.getSession();
        //  User u = (User) session.getAttribute("user");
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
