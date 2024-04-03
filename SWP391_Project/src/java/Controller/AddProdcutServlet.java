/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Entity.Product;
import Entity.User;
import Entity.Wallet;
import Entity.intermediateOrders;
import dao.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 *
 * @author tudo7
 */
@MultipartConfig
@WebServlet(name = "AddProdcutServlet", urlPatterns = {"/addProduct"})
public class AddProdcutServlet extends HttpServlet {

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
            out.println("<title>Servlet AddProdcutServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddProdcutServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("InserCategory.jsp").forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");

        Product product = new Product();

        try {
            product.setName(request.getParameter("nameProduct"));
            String price = request.getParameter("priceProduct");
            String priceWithoutCommas = price.replace(",", "");
            product.setPrice(Double.parseDouble(priceWithoutCommas));
            if (product.getPrice() <= 0) {
                throw new NumberFormatException();
            }
            product.setCategoryID(Integer.parseInt(request.getParameter("categoryID")));
            product.setDescription(request.getParameter("Description"));

            Collection<Part> fileParts = request.getParts().stream().filter(part -> part.getName().startsWith("images")).collect(Collectors.toList());

            int i = 1;
            for (Part filePart : fileParts) {
                if (filePart != null) { // Kiểm tra nếu có dữ liệu gửi lên
                    String imageFileName = filePart.getSubmittedFileName(); // Lấy tên của file
                    String uploadPath = "D:/Project_SSWP/SWP391_Project/web/imagesUpload/" + imageFileName; // Đường dẫn lưu trữ file
                    try (FileOutputStream fos = new FileOutputStream(uploadPath)) {
                        InputStream is = filePart.getInputStream();
                        byte[] data = new byte[is.available()];
                        is.read(data); // Đọc dữ liệu từ InputStream vào mảng byte data
                        fos.write(data); // Ghi dữ liệu từ mảng byte data vào FileOutputStream
                    } catch (Exception e) {
                        response.getWriter().write("loianh");
                    }
                    if (i == 1) {
                        product.setImage1(imageFileName);
                    } else if (i == 2) {
                        product.setImage2(imageFileName);
                    } else if (i == 3) {
                        product.setImage3(imageFileName);
                    } else if (i == 4) {
                        product.setImage4(imageFileName);
                    }
                    i++;
                }
            }
            if (request.getParameter("Transaction_fee").equals("seller")) {
                product.setTransaction_fee(true);
            } else {
                product.setTransaction_fee(false);
            }
            product.setContact_Method(request.getParameter("Contact_Method"));
            product.setHidden_content(request.getParameter("hidden_content"));
            if (product.getName().equals("") || product.getDescription().equals("") || product.getContact_Method().equals("") || product.getHidden_content().equals("")) {
                throw new Exception();
            }
            HttpSession session = request.getSession();
            User u = (User) session.getAttribute("user");
            product.setCreate_by(u.getId());
            DAO dal = new DAO();
            Wallet walet = dal.getWallet(u.getId());
            if (walet.getBalance() >= 500) {
                walet.setBalance(walet.getBalance() - 500);
                dal.insertProduct(product);
                intermediateOrders order = new intermediateOrders();
                int codeP = dal.getIdProduct() + 1;
                order.setCode("SP00" + codeP);
                order.setProductId(dal.getIdProduct());
                if (product.isTransaction_fee() == true) {
                    order.setTotal_paid_amount(product.getPrice());
                    order.setTotal_received_amount((product.getPrice() * 95) / 100);

                } else {
                    order.setTotal_paid_amount(product.getPrice() * 105 / 100);
                    order.setTotal_received_amount(product.getPrice());
                }
                order.setIntermediary_fee(product.getPrice() * 5 / 100);
                order.setStatus("Sẵn sàng giao dịch");
                order.setCreate_by(u.getId());
                order.setUpdate_by(u.getId());
                dal.insertOrder(order);
                int orderID = dal.getOrderByCode(order.getCode()).getId();
                dal.updateAmount(walet.getBalance(), u.getId());
                session.setAttribute("balance", dal.getWallet(u.getId()).getBalance());

                //binh them vao day
                dal.insertReport(3, orderID, u.getId(), true, "Bạn vừa đăng sản phẩm với mã code: " + order.getCode(), u.getId(), false);
                dal.insertOrderHistory(orderID, "Sẵn sàng giao dịch", "Người bán đăng bán thành công sản phẩm", order.getCreate_by());
                dal.InsertHistory_Transaction(500, "-", true, "Thu phí tạo yêu cầu trung gian mã số:" + order.getCode(), u.id, u.id);
                response.getWriter().write("success");
            } else {
                response.getWriter().write("Insufficient_balance");
            }
        } catch (NumberFormatException e) {
            response.getWriter().write("price");
        } catch (Exception e2) {
            response.getWriter().write("null");
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
