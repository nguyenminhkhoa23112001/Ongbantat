/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Entity.*;
import dao.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Random;

/**
 *
 * @author ADMIN
 */
public class VerifyCode extends HttpServlet {

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
            out.println("<title>Servlet VerifyCode</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VerifyCode at " + request.getContextPath() + "</h1>");
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
        long currentTime = System.currentTimeMillis();
        long lastSentTime = 0;
        PrintWriter out = response.getWriter();
        int code = GenOTP();
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        
        if (session.getAttribute("lastSentTime") != null) {
            lastSentTime = (Long) session.getAttribute("lastSentTime");
            long elapsedTime = currentTime - lastSentTime;

            // Set a time threshold (e.g., 1 minute = 60000 milliseconds)
            long timeThreshold = 60000;

            if (elapsedTime < timeThreshold) {
                // Notify the user that they need to wait before requesting another OTP
                out.println("Please wait before requesting another OTP.");

                // You may want to handle this case gracefully in your front-end/UI
            } else {
                // Send the new OTP
                SendEmail sm = new SendEmail();
                sm.Send(email, code);
                session.setAttribute("otp", code);
                session.setAttribute("lastSentTime", currentTime);
            }
        } else {
            // If it's the first time, set the last sent time and send the OTP
            SendEmail sm = new SendEmail();
            sm.Send(email, code);
            session.setAttribute("otp", code);
            session.setAttribute("lastSentTime", currentTime);
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
        DAO dao = new DAO();
        //processRequest(request, response);
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        PrintWriter out = response.getWriter();
        String code = request.getParameter("otp_code");
        String mess = "";

        int code_give = (int) session.getAttribute("otp");
        try {
            int code_1 = Integer.parseInt(code);
            if (code_1 == code_give) {
                dao.setVerifyTrue(email);
                 session.invalidate();
                response.getWriter().write("success");
            } else {
                response.getWriter().write("error");
            }
        } catch (Exception e) {
//            mess += "OTP error, input again!!!";
//            request.setAttribute("messError", mess);
//            request.getRequestDispatcher("verify.jsp").forward(request, response);
            response.getWriter().write("error");
        }

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
