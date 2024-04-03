/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Entity.Category;
import Entity.Product;
import Entity.ProductOrderPair;
import Entity.Report;
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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tudo7
 */
@WebServlet(name = "ManageMyOrder", urlPatterns = {"/manageMyOrder"})
public class ManageMyOrder extends HttpServlet {

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
            out.println("<title>Servlet ManageMyOrder</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManageMyOrder at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        String complete = "Đơn hàng đã hoàn thành";
        List<Category> category = dao.getAllCategory();

        // Get All Order
        List<Product> listProduct = dao.getProductByUserID(u.getId());
        List<ProductOrderPair> productOrderPairsAll = new ArrayList<>();
        for (Product product : listProduct) {
            intermediateOrders order = dao.getOrderByProductID(product.getId());
            productOrderPairsAll.add(new ProductOrderPair(product, order));
        }
        // Get Complete Order
        List<ProductOrderPair> productOrderPairsComplete = new ArrayList<>();
        List<intermediateOrders> orderCom = dao.getOrderByStatus(complete, u.getId());
        for (intermediateOrders orders : orderCom) {
            Product product = dao.getProductByID(orders.getProductId());
            productOrderPairsComplete.add(new ProductOrderPair(product, orders));
        }
        List<intermediateOrders> orderDel = dao.getOrderByStatus("Người bán huỷ đơn", u.getId());
        for (intermediateOrders orders : orderDel) {
            Product product = dao.getProductByID(orders.getProductId());
            productOrderPairsComplete.add(new ProductOrderPair(product, orders));
        }
        //Get Processing Order  
        List<ProductOrderPair> productOrderPairsProcess = new ArrayList<>();
        List<intermediateOrders> orderReady = dao.getOrderByStatus("Sẵn sàng giao dịch", u.getId());
        for (intermediateOrders orders : orderReady) {
            Product product = dao.getProductByID(orders.getProductId());
            productOrderPairsProcess.add(new ProductOrderPair(product, orders));
        }
        List<intermediateOrders> orderComplain = dao.getOrderByStatus("Người mua khiếu nại đơn hàng", u.getId());
        for (intermediateOrders orders : orderComplain) {
            Product product = dao.getProductByID(orders.getProductId());
            productOrderPairsProcess.add(new ProductOrderPair(product, orders));
        }
        List<intermediateOrders> orderAdmin = dao.getOrderByStatus("Yêu cầu admin giải quyết", u.getId());
        for (intermediateOrders orders : orderAdmin) {
            Product product = dao.getProductByID(orders.getProductId());
            productOrderPairsProcess.add(new ProductOrderPair(product, orders));
        }
        List<intermediateOrders> orderAcept = dao.getOrderByStatus("Chờ người mua xác nhận", u.getId());
        for (intermediateOrders orders : orderAcept) {
            Product product = dao.getProductByID(orders.getProductId());
            productOrderPairsProcess.add(new ProductOrderPair(product, orders));
        }
        List<intermediateOrders> orderCheck = dao.getOrderByStatus("Người mua đang kiểm tra đơn hàng", u.getId());
        for (intermediateOrders orders : orderCheck) {
            Product product = dao.getProductByID(orders.getProductId());
            productOrderPairsProcess.add(new ProductOrderPair(product, orders));
        }

        request.setAttribute("category", category);
        request.setAttribute("productOrderPairs", productOrderPairsAll);
        request.setAttribute("productOrderPairsComplete", productOrderPairsComplete);
        request.setAttribute("productOrderPairsProcess", productOrderPairsProcess);
        session.removeAttribute("balance");
        session.setAttribute("balance", dao.getWallet(u.getId()).getBalance());
        List<Report> listReport = dao.getTopNext3Report(u.getId(), 0);
        request.setAttribute("listR", listReport);
        request.getRequestDispatcher("MyOrder.jsp").forward(request, response);
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
        // processRequest(request, response);

        DAO dao = new DAO();
        int id = Integer.parseInt(request.getParameter("pid").trim());
        intermediateOrders order = dao.getOrderByID(id);
        // HttpSession session = request.getSession();
        //  User u = (User) session.getAttribute("user");

        Product product = dao.getProductByID(order.getProductId());
        String buyerName = null;
        ProductOrderPair productOrderPair = new ProductOrderPair(product, order);
        if (dao.getUserById(productOrderPair.getOrder().getBuyer_id()) != null) {
            buyerName = dao.getUserById(productOrderPair.getOrder().getBuyer_id()).display_name;
        } else {
            buyerName = "Chưa xác định";
        }
        String data = productOrderPair.getOrder().getCode() + ";"
                + productOrderPair.getProduct().getName() + ";"
                + String.format("%,.0f", (double) productOrderPair.getProduct().getPrice()) + ";"
                + String.format("%,.0f", (double) productOrderPair.getOrder().getIntermediary_fee())  + ";"
                + (productOrderPair.getProduct().isTransaction_fee() ? "Bên Bán" : "Bên Mua") + ";"
                + String.format("%,.0f", (double) productOrderPair.getOrder().getTotal_received_amount()) + ";"
                + String.format("%,.0f", (double) productOrderPair.getOrder().getTotal_paid_amount())+ ";"
                +"imagesUpload/" + productOrderPair.getProduct().getImage1() + ";"
                +"imagesUpload/" + productOrderPair.getProduct().getImage2() + ";"
                +"imagesUpload/" + productOrderPair.getProduct().getImage3() + ";"
                +"imagesUpload/" + productOrderPair.getProduct().getImage4() + ";"
                + productOrderPair.getProduct().getDescription() + ";"
                + productOrderPair.getProduct().getHidden_content() + ";"
                + productOrderPair.getProduct().getContact_Method() + ";"
                + productOrderPair.getOrder().getStatus() + ";"
                + buyerName + ";"
                + productOrderPair.getOrder().getCreate_at() + ";"
                + productOrderPair.getOrder().getUpdate_at();
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(data);

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
