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
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class homeServ extends HttpServlet {

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
            out.println("<title>Servlet Searchproduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Searchproduct at " + request.getContextPath() + "</h1>");
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
        List<Product> listProduct = null;
        List<Product> listProductPage = null;

        List<Category> listCategory = dao.getAllCategory();
        String page = request.getParameter("page");
        int pageSize = 8;
        int Count = 0;

        if (page == null) {
            page = "1";
        }

        String pramCategoryId = request.getParameter("categoryId");
        String search = request.getParameter("searchproductname");
        if (pramCategoryId != null && !pramCategoryId.equals("all")) {
            listProduct = dao.getProductbyCategoryID(pramCategoryId);
        } else if (search != null && !search.isEmpty()) {
            listProduct = dao.getAllProductbyName(search);
        } else {
            listProduct = dao.getAllProduct();
        }
        int start = (Integer.parseInt(page) - 1) * pageSize;
        int end = Math.min(start + pageSize, listProduct.size());

        if ((listProduct.size() % pageSize) == 0) {
            Count = listProduct.size() / pageSize;
        } else {
            Count = listProduct.size() / pageSize + 1;
        }
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        if (u != null) {
            List<Report> listReport = dao.getTopNext3Report(u.getId(), 0);
            request.setAttribute("listR", listReport);
            
            session.setAttribute("balance", dao.getWallet(u.getId()).getBalance());
        }

        request.setAttribute("dao", dao);
        int quantity = 0;
        if (u != null) {
            quantity = dao.getQuantityProductInCart(u.getId());
        } else {
            //
        }
        request.setAttribute("quantity", quantity);
        listProductPage = listProduct.subList(start, end);
        boolean isAjax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
        if (isAjax) {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            out.println("<div id=\"Listproduct\">"); // Opening container for AJAX response

            // Dynamically generate the product listing for AJAX response
            for (Product p : listProductPage) {
                out.println("<div class=\"col-md-3\">");
                out.println("    <!-- product -->");
                out.println("    <div class=\"product\">");
                out.println("        <div class=\"product-img\">");
                out.println("            <img src=\"imagesUpload/" + p.image1 + "\" alt=\"\" style=\"height: 150px;\">");
                out.println("        </div>");
                out.println("        <div class=\"product-body\">");
                out.println("           <p class=\"product-category\">Danh mục</p>");
                out.println("            <h3 class=\"product-name\"><a href=\"ProductDetail?id=" + p.id + "\">" + p.name + "</a></h3>");

                // Formatting the price
                String formattedPrice = String.format("%,.0f", p.price);
                String fmTotalPaid = String.format("%,.0f", dao.getOrderByProductID(p.id).getTotal_paid_amount());
                out.println("            <h4 class=\"product-price\">" + formattedPrice + "</h4>");

                out.println("            <div class=\"product-btns\">");
                out.println("                <button class=\"add-to-wishlist\"><i class=\"fa fa-heart-o\"></i><span class=\"tooltipp\">add to wishlist</span></button>");
                out.println("                <button class=\"add-to-compare\"><i class=\"fa fa-exchange\"></i><span class=\"tooltipp\">add to compare</span></button>");
                out.println("                <button class=\"quick-view\"><i class=\"fa fa-eye\"></i><span class=\"tooltipp\">quick view</span></button>");
                out.println("            </div>");
                out.println("        </div>");
                out.println("        <div class=\"add-to-cart\">");
                out.println("            <button class=\"add-to-cart-btn buy-button1\" data-target=\"cookiesPopup\">");
                out.println("                <i class=\"fa fa-shopping-cart\"></i>Mua");
                out.println("            </button>");
                out.println("            <button class=\"add-to-cart-btn buy-button\" onclick=\"addToCart(" + p.id + ")\">");
                out.println("                <i class=\"fa fa-shopping-cart\"></i>Thêm");
                out.println("            </button>");
                out.println("        </div>");
                out.println("    </div>");
                out.println("    <div class=\"container-2\">");
                out.println("        <div class=\"cookiesContent cookiesPopup\">");
                out.println("            <button class=\"close\">✖</button>");
                out.println("            <img src=\"https://dichthuatmientrung.com.vn/wp-content/uploads/2022/06/important-sticky-note.jpg\" alt=\"cookies-img\" style=\"width: 50%;\">");
                out.println("              <p style=\"color:red; margin-top: 5%;\">Bạn sẽ phải trả tổng số tiền là: " + fmTotalPaid + " cho sản phẩm này!</p>\n");
                out.println("<p style=\"color:red;\">Bấm mua nếu bạn chấp nhận hệ thống giữ tiền trung gian !!!</p>");
                out.println("<p style=\"color:red; font-weight: bold;\">Hãy quay video từ lúc bấm mua sản phẩm để làm bằng chứng sau này !!!</p>");
                out.println("            <button class=\"button-buy\" data-id=\"" + p.id + "\">BUY</button>");
                out.println("        </div>");
                out.println("    </div>");
                out.println("    <!-- /product -->");
                out.println("</div>");
            }

            out.println("</div>"); // Closing container for AJAX response

        } else {
            
            request.setAttribute("Count", Count);
            request.setAttribute("page", page);
            request.setAttribute("dao", dao);
            request.setAttribute("listProductPage", listProductPage);
            
            request.setAttribute("listCategory", listCategory);
            
            request.getRequestDispatcher("home.jsp").forward(request, response);
            
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
