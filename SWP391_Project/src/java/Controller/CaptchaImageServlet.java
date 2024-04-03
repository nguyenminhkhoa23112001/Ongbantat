/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 *
 * @author tudo7
 */
@WebServlet(name = "CaptchaImageServlet", urlPatterns = {"/captchaimage"})
public class CaptchaImageServlet extends HttpServlet {

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
            out.println("<title>Servlet CaptchaImageServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CaptchaImageServlet at " + request.getContextPath() + "</h1>");
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
        //  processRequest(request, response);
        Random random = new Random();
        String capchaString = generateCapchaString();
        BufferedImage img = new BufferedImage(120, 40, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2D = img.createGraphics();
        //Ve chuoi so len anh
        g2D.setFont(new Font("SansSerif", Font.PLAIN, 25));
        g2D.setColor(Color.WHITE);
        g2D.drawString(capchaString, 5 + random.nextInt(10), 25 + random.nextInt(10));

        //Luu chuoi so vao session de kiem tra sau
        request.getSession().setAttribute("captcha", capchaString);
        // vẽ các đường gạch ngang
        // Đặt kích thước của đường gạch
        float kichThuocDuongGach = 1.5f;  // Đặt kích thước của đường gạch ở đây
        g2D.setStroke(new BasicStroke(kichThuocDuongGach));

        for (int i = 0; i < 3; i++) {
            g2D.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            int x = (int) (10 + random.nextInt(10));
            int y = (int) (8 + random.nextInt(15));
            int width = 55 + random.nextInt(30);
            int height = random.nextInt(15); // Độ cao của đường cong
            int startAngle = 180;
            int arcAngle = 150 + random.nextInt(30);

            g2D.drawArc(x, y, width, height, startAngle, arcAngle);
        }

        ImageIO.write(img, "png", response.getOutputStream());

    }

    public static String generateCapchaString() {
        Random random = new Random();
        int captcha = random.nextInt(9000) + 1000;
        return String.valueOf(captcha);
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
