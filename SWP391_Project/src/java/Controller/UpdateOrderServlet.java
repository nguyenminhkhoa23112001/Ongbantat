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
 * @author tudo7
 */
@WebServlet(name = "UpdateOrderServlet", urlPatterns = {"/updateOrder"})
public class UpdateOrderServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateOrderServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateOrderServlet at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        String code = request.getParameter("code").trim();
        String productName = request.getParameter("productName").trim();
        String pricepro = request.getParameter("price").trim();
        String priceWithoutCommas = pricepro.replace(",", "");
        Double price = Double.valueOf(priceWithoutCommas);
        String party = request.getParameter("party").trim();
        String description = request.getParameter("description");
        String hiddenContent = request.getParameter("hiddenContent");
        String contactMethod = request.getParameter("contactMethod");
        DAO dao = new DAO();
            
        intermediateOrders order = dao.getOrderByCode(code);
        Product product = dao.getProductByID(order.getProductId());
        
        if (order.getStatus().equals("Sẵn sàng giao dịch")) {
            product.setName(productName);
            product.setPrice(price);
            if (party.equals("seller")) {
                product.setTransaction_fee(true);
            } else {
                product.setTransaction_fee(false);
            }
            product.setDescription(description);
            product.setContact_Method(contactMethod);
            product.setHidden_content(hiddenContent);
            dao.UpdateProductByProductID(product);
            order.setIntermediary_fee((price * 5) / 100);
            if (product.isTransaction_fee() == true) {
                order.setTotal_received_amount(price - order.getIntermediary_fee());
                order.setTotal_paid_amount(price);
            } else {
                order.setTotal_paid_amount(price + order.getIntermediary_fee());
                order.setTotal_received_amount(price);
            }
            dao.UpdateOrdersByID(order.getId(), order);
//       request.getRequestDispatcher("manageMyOrder").forward(request, response);
            response.getWriter().write("success");
        } else {
            response.getWriter().print("Không thể cập nhật đơn hàng!");
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
