/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Entity.Product;
import Entity.ProductOrderPair;
import Entity.User;
import Entity.intermediateOrders;
import com.google.gson.Gson;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "orderBuyChecking", urlPatterns = {"/orderChecking"})
public class orderBuyChecking extends HttpServlet {

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
            out.println("<title>Servlet orderBuyChecking</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet orderBuyChecking at " + request.getContextPath() + "</h1>");
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
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        //processRequest(request, response);
//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//        DAO dao = new DAO();
//        HttpSession session = request.getSession();
//        User u = (User) session.getAttribute("user");
//        List<intermediateOrders> listOrderBuy = dao.getOrderBuy(u.getId());
//        List<ProductOrderPair> productOrderPairs = new ArrayList<>();
//        for (intermediateOrders o : listOrderBuy) {
//            Product product = dao.getProductByID(o.getProductId());
//            productOrderPairs.add(new ProductOrderPair(product, o));
//
//        }
//        String s = "";
//        for (ProductOrderPair o : productOrderPairs) {
//            if (o.getOrder().getStatus().equals("Người mua đang kiểm tra đơn hàng") || o.getOrder().getStatus().equals("Người mua khiếu nại đơn hàng")
//                    || o.getOrder().getStatus().equals("Yêu cầu admin giải quyết")) {
//                if (o.getProduct().isTransaction_fee() == true) {
//                    s = "Người bán";
//                } else {
//                    s = "Người mua";
//                }
//                out.print("\n"
//                        + "<tr class=\"cell-1\">\n"
//                        + "                                                    <td>" + o.getOrder().getCode() + "</td>\n"
//                        + "                                                    <td>" + o.getOrder().getStatus() + "</td>\n"
//                        + "                                                    <td>" + dao.getUserById(o.getProduct().getCreate_by()).getDisplay_name() + "</td>\n"
//                        + "                                                    <td>" + dao.getCategoryById(o.getProduct().getCategoryID()).getName() + "</td>\n"
//                        + "                                                    <td>" + o.getProduct().getContact_Method() + "</td>\n"
//                        + "                                                    <td>" + o.getProduct().getPrice() + " VND" + "</td>\n"
//                        + "                                                    <td>" + o.getOrder().getIntermediary_fee() + " VND" + "</td>\n"
//                        + "                                                    <td><span class=\"badge badge-success\">" + s + "</span></td>\n"
//                        + "                                                    <td>" + o.getOrder().getTotal_paid_amount() + " VND" + "</td>\n"
//                        + "                                                    <td>\n"
//                        + "  <div class=\"buttonContainer\">\n"
//                        + "    <a class=\"reportButton\" data-orderid=\"" + o.getOrder().getId() + "\" data-ordercode=\"" + o.getOrder().getCode() + "\" data-productname=\"" + o.getProduct().getName() + "\" data-price = \"" + o.getProduct().getPrice() + "\" data-inter = \"" + o.getOrder().getIntermediary_fee() + "\" data-party = \"" + s + "\" data-totalpaids = \"" + o.getOrder().getTotal_paid_amount() + "\" data-proimg = \"" + o.getProduct().getImage1() + "\" data-des = \"" + o.getProduct().getDescription() + "\" data-hiddeninfo = \"" + o.getProduct().getHidden_content() + "\" data-contact = \"" + o.getProduct().getContact_Method() + "\" data-status1 = \"" + o.getOrder().getStatus() + "\" data-buyers = \"" + dao.getUserById(o.getProduct().getCreate_by()).getDisplay_name() + "\" data-create = \"" + o.getProduct().getCreate_At() + "\">\n"
//                        + "      <i style=\"color: #0061f2\" class=\"fa fa-info-circle\"></i>\n"
//                        + "    </a>\n"
//                        + "  </div>\n"
//                        + "</td>\n"
//                        + " </tr>");
//            }
//        }
//    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        List<Map<String, String>> jsonList = new ArrayList<>();
        PrintWriter out = response.getWriter();
        DAO dao = new DAO();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<intermediateOrders> listOrderBuy = dao.getOrderBuy(user.getId());
        Gson gson = new Gson();

        for (intermediateOrders intermediateOrder : listOrderBuy) {
            Product product = dao.getProductByID(intermediateOrder.getProductId());

            if (intermediateOrder.getStatus().equals("Người mua đang kiểm tra đơn hàng")
                    || intermediateOrder.getStatus().equals("Người mua khiếu nại đơn hàng")
                    || intermediateOrder.getStatus().equals("Yêu cầu admin giải quyết") || intermediateOrder.getStatus().equals("Chờ người mua xác nhận")) {

                ProductOrderPair productOrderPair = new ProductOrderPair(product, intermediateOrder);
                String s = product.isTransaction_fee() ? "Người bán" : "Người mua";

                Map<String, String> orderInfo = new HashMap<>();
                
                orderInfo.put("orderCode", productOrderPair.getOrder().getCode());
                orderInfo.put("orderStatus", productOrderPair.getOrder().getStatus());
                orderInfo.put("sellerName", dao.getUserById(product.getCreate_by()).getDisplay_name());
                orderInfo.put("categoryName", dao.getCategoryById(product.getCategoryID()).getName());
                orderInfo.put("contactMethod", product.getContact_Method());
                orderInfo.put("Price", product.getPrice() + " VND");
                orderInfo.put("Intermediary_fee", intermediateOrder.getIntermediary_fee() + " VND");
                orderInfo.put("isTransaction_fee", s);
                orderInfo.put("totalPaid", intermediateOrder.getTotal_paid_amount() + " VND");
                
                jsonList.add(orderInfo);
            }
        }

        String jsonData = gson.toJson(jsonList);
        out.print(jsonData);
        out.flush();

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

        response.setContentType("application/json");
        List<Map<String, String>> jsonList = new ArrayList<>();
        PrintWriter out = response.getWriter();
        DAO dao = new DAO();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<intermediateOrders> listOrderBuy = dao.getOrderBuy(user.getId());
        Gson gson = new Gson();

        for (intermediateOrders intermediateOrder : listOrderBuy) {
            Product product = dao.getProductByID(intermediateOrder.getProductId());

            if (intermediateOrder.getStatus().equals("Đơn hàng đã hoàn thành")) {

                ProductOrderPair productOrderPair = new ProductOrderPair(product, intermediateOrder);
                String s = product.isTransaction_fee() ? "Người bán" : "Người mua";

                Map<String, String> orderInfo = new HashMap<>();
                orderInfo.put("orderCode", productOrderPair.getOrder().getCode());
                orderInfo.put("orderStatus", productOrderPair.getOrder().getStatus());
                orderInfo.put("sellerName", dao.getUserById(product.getCreate_by()).getDisplay_name());
                orderInfo.put("categoryName", dao.getCategoryById(product.getCategoryID()).getName());
                orderInfo.put("contactMethod", product.getContact_Method());
                orderInfo.put("Price", product.getPrice() + " VND");
                orderInfo.put("Intermediary_fee", intermediateOrder.getIntermediary_fee() + " VND");
                orderInfo.put("isTransaction_fee", s);
                orderInfo.put("totalPaid", intermediateOrder.getTotal_paid_amount() + " VND");
                
                jsonList.add(orderInfo);
            }
        }

        String jsonData = gson.toJson(jsonList);
        out.print(jsonData);
        out.flush();


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
