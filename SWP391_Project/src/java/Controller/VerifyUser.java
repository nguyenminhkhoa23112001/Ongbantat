/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Validate.validate;
import dao.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.Encryption;
import java.util.Random;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "VerifyUser", urlPatterns = {"/VerifyUser"})
public class VerifyUser extends HttpServlet {

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

    }

    public int GenOTP() {
        int min = 10_000; // Số nguyên tối thiểu (bao gồm)
        int max = 99_999; // Số nguyên tối đa (bao gồm)
        Random random = new Random();
        int randomNumber = random.nextInt(max - min + 1) + min;

        return randomNumber;
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
       request.getRequestDispatcher("signup.jsp").forward(request, response);
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
        //processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");
        validate validate = new validate();
        DAO dao = new DAO();
        String mess = "";
        PrintWriter out = response.getWriter();

        String username = request.getParameter("user");
        String password = request.getParameter("pass");
        String confirmPass = request.getParameter("confirmPass");
        String email = request.getParameter("email");
        String captcha = request.getParameter("capchaRespone");
        String sessionCaptcha = (String) request.getSession().getAttribute("captcha");
        if (captcha != null && captcha.equals(sessionCaptcha)) {
            if (validate.checkInput(username, "^[^@,!#$%&*]*$", 5, 20)
                    && dao.getUsername(username) == null) {
                if (validate.checkInput(password, "^(?=.*[!@#$%^&*(),.?\":{}|<>]).*$", 6, 15) && password.equals(confirmPass)) {
                    if (validate.checkInput(email, "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", 0, 50)
                            && dao.getEmail(email) == null) {
                        int code = GenOTP();
                        HttpSession session = request.getSession();
                        session.setAttribute("otp", code);
                        session.setAttribute("email", email);
                        password = Encryption.toSHA1(password);
                        dao.signup(username, password, email);
                        
                        dao.insertWallet(0, dao.getUser(username).getId());
                        response.getWriter().write("success");
                        SendEmail sm = new SendEmail();
                        new Thread(() -> sm.Send(email, code)).start();
                    } else {
                        mess = "Email: abc@xyz.com and CAN NOT DUPLICATE EMAIL!";
                        out.print(mess);
                    }
                } else {
                    mess = "Password: must CONTAIN special CHARACTERS and DIGIT\nConfirm password must be the same password!";
                    out.print(mess);
                }
            } else {
                mess = "Username can not duplicate and not cantain CHARACTERS!";
                out.print(mess);
            }
        } else {
            mess = "Invalid Captcha";
            out.print(mess);
        }
    }

    // Phương thức kiểm tra đăng nhập đơn giản, bạn có thể thay thế bằng logic phức tạp hơn
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
