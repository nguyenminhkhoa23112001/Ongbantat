/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Entity.User;
import dao.DAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Random;
import Validate.validate;

/**
 *
 * @author acer
 */
@WebServlet(name = "EditProfile", urlPatterns = {"/EditProfile"})
public class EditProfile extends HttpServlet {

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
            out.println("<title>Servlet EditProfile</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditProfile at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("editprofile.jsp").forward(request, response);
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
        Integer id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        // String password = request.getParameter("password"); 
        String displayName = request.getParameter("display");
        String email = request.getParameter("email");
        //   Integer isAdmin =  Integer.parseInt(request.getParameter("Admin"));

        if (username == null || username.trim().isEmpty()) {
            request.setAttribute("errorMsg1", "Username is required.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("editprofile.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // Validate Password
//    if (password == null || password.trim().isEmpty()) {
//        request.setAttribute("errorMsg2", "Password is required.");
//        RequestDispatcher dispatcher = request.getRequestDispatcher("editprofile.jsp");
//        dispatcher.forward(request, response);
//        return;
//    }
        //Validate Display Name
//        if (displayName == null || displayName.trim().isEmpty()) {
//            request.setAttribute("errorMsg3", "Display Name is required.");
//            RequestDispatcher dispatcher = request.getRequestDispatcher("editprofile.jsp");
//            dispatcher.forward(request, response);
//            return;
//        }
        // Validate Email
        if (email == null || email.trim().isEmpty() || isEmailAlreadyExists(email, id)) {
            request.setAttribute("errorMsg4", "Invalid or empty email address.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("editprofile.jsp");
            dispatcher.forward(request, response);
            return;
        }

        validate s = new validate();

        DAO d = new DAO();
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        if (email.equals(u.getEmail()) && u.isIs_verify()) {
            d.updateProfile(email, displayName, u.getId());
            u.setDisplay_name(displayName);
            String mess = "Edit profile success";
            request.setAttribute("done", mess);
            request.getRequestDispatcher("editprofile.jsp").forward(request, response);
        } else if (s.checkInput(email, "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", 0, 50)) {
            int code = GenOTP();
            d.updateProfile(u.getEmail(), displayName, u.getId());
            session.setAttribute("otp", code);
            session.setAttribute("email", email);
            response.sendRedirect("VerifyCodeUpdate");
            SendEmail sm = new SendEmail();
            new Thread(() -> sm.Send(email, code)).start();
        } else {
            String mess = "Email not correct form";
            request.setAttribute("errorMsg4", mess);
            request.getRequestDispatcher("editprofile.jsp").forward(request, response);
        }

    }

    private boolean isValidEmail(String email) {
        // Implement your email validation logic here
        // For a simple example, you can use a regular expression
        // Replace this with a more robust email validation if needed
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    private boolean isEmailAlreadyExists(String email, int id) {
        try {
            DAO d = new DAO();
            // Assuming you have a method in DAO to check if the email exists for a different user
            return d.isEmailAlreadyExists(email, id);
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public int GenOTP() {
        int min = 10_000; // Số nguyên tối thiểu (bao gồm)
        int max = 99_999; // Số nguyên tối đa (bao gồm)
        Random random = new Random();
        int randomNumber = random.nextInt(max - min + 1) + min;

        return randomNumber;
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
