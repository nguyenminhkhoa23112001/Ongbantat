/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import dao.DAO;
import Entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import Validate.validate;
import util.Encryption;

/**
 *
 * @author acer
 */
@WebServlet(name = "ChangePassword", urlPatterns = {"/ChangePassword"})
public class ChangePassword extends HttpServlet {

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
            out.println("<title>Servlet ChangePassword</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePassword at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("password.jsp").forward(request, response);
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
        String user = request.getParameter("username");
        String oldPass = request.getParameter("oldPassword");
        String passEn = Encryption.toSHA1(oldPass);
        String newPass = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        validate s = new validate();
        DAO c = new DAO();
        User u = c.getUser(user);
        if (passEn.equals(u.getPassword())) {
            if (s.checkInput(newPass, "^(?=.*[!@#$%^&*(),.?\":{}|<>]).*$", 6, 15)) {
                if (confirmPassword.equals(newPass)) {
                    String newPassEn = Encryption.toSHA1(newPass);
                    c.changePassword(newPassEn, user);
                    String mess = "Change password success";
                    request.setAttribute("done1", mess);
                    request.getRequestDispatcher("editprofile.jsp").forward(request, response);
                } else {
                    String mess = "New pass and confirm new pass must be the same";
                    request.setAttribute("fail", mess);
                    request.getRequestDispatcher("editprofile.jsp").forward(request, response);
                }

            } else{
                String mess = "New pass not correct form!";
                request.setAttribute("fail", mess);
                request.getRequestDispatcher("editprofile.jsp").forward(request, response);
            }
        } else {
            String mess = "Old password not correct";
            request.setAttribute("fail", mess);
            request.getRequestDispatcher("editprofile.jsp").forward(request, response);
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
