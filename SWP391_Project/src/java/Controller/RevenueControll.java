/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import Entity.User;
import dao.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashMap;

/**
 *
 * @author acer
 */
@WebServlet(name="RevenueControll", urlPatterns={"/Revenue"})
public class RevenueControll extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet RevenueControll</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RevenueControll at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();

            User u = (User) session.getAttribute("user"); //Lay ra tai khoan dang su dung
            System.out.println("Revenue Page:");

            LocalDate currentDate = LocalDate.now();
            Calendar calendar = Calendar.getInstance();
            DAO dao = new DAO();
            
           //HashMap<Integer, Double> revenue = dao.getDataForChart(u.getId(), calendar.get(Calendar.YEAR));
           HashMap<Integer, Double> revenue = dao.getDataForChart(u.getId(), calendar.get(Calendar.YEAR));
        double totalMoney1 = dao.totalMoneyDay(1,u.getId());
        double totalMoney2 = dao.totalMoneyDay(2,u.getId());
        double totalMoney3 = dao.totalMoneyDay(3,u.getId());
        double totalMoney4 = dao.totalMoneyDay(4,u.getId());
        double totalMoney5 = dao.totalMoneyDay(5,u.getId());
        double totalMoney6 = dao.totalMoneyDay(6,u.getId());
        double totalMoney7 = dao.totalMoneyDay(7,u.getId());      
        request.setAttribute("totalMoney1", totalMoney1);
        request.setAttribute("totalMoney2", totalMoney2);
        request.setAttribute("totalMoney3", totalMoney3);
        request.setAttribute("totalMoney4", totalMoney4);
        request.setAttribute("totalMoney5", totalMoney5);
        request.setAttribute("totalMoney6", totalMoney6);
        request.setAttribute("totalMoney7", totalMoney7);
           request.setAttribute("chartData", revenue);
           request.setAttribute("revenueData",dao.getRevenueData(u.getId()));
           request.setAttribute("quantityAll",dao.getQuantityAll(u.getId()));
            request.getRequestDispatcher("Revenue.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e);
        }
    } 
    public static void main(String[] args) {

        Calendar calendar = Calendar.getInstance();
        DAO dao = new DAO();
        System.out.println(calendar.get(Calendar.MONTH) + 1);
        HashMap<Integer, Double> revenue = dao.getDataForChart(2, 2024);
        System.out.println(revenue);
        System.out.println(dao.getQuantityAll(2));
        System.out.println(dao.getRevenueData(2));
        double totalMoney1 = dao.totalMoneyDay(1,2);
         System.out.println(totalMoney1);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
