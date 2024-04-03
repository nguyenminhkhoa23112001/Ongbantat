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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author acer
 */
@WebServlet(name="Feedback", urlPatterns={"/Feedback"})
public class Feedback extends HttpServlet {
   
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
            out.println("<title>Servlet Feedback</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Feedback at " + request.getContextPath () + "</h1>");
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
//        HttpSession session = request.getSession();     
//        User u = (User) session.getAttribute("user");
//        DAO fdao = new DAO();
//        
//        ArrayList<Entity.Feedback> listF =fdao.getFeedbackList();
//        request.setAttribute("listF", listF);
//        request.getRequestDispatcher("Feedback.jsp").forward(request, response);
 HttpSession session = request.getSession();
    User u = (User) session.getAttribute("user");
    DAO fdao = new DAO();
    
    // Set the number of items per page
    int itemsPerPage = 5;
    
    // Get the current page from the request parameter
    int currentPage = 1;
    String pageParam = request.getParameter("page");
    if (pageParam != null) {
        currentPage = Integer.parseInt(pageParam);
    }
    
    // Get the total number of feedback items
    int totalFeedback = fdao.getTotalFeedbackCount();
    
    // Calculate the total number of pages
    int totalPages = (int) Math.ceil((double) totalFeedback / itemsPerPage);
    
    // Get the feedback list for the current page
    ArrayList<Entity.Feedback> listF = fdao.getFeedbackList(currentPage, itemsPerPage);
    
    // Set attributes for JSP
    request.setAttribute("listF", listF);
    request.setAttribute("currentPage", currentPage);
    request.setAttribute("totalPages", totalPages);
    
    request.getRequestDispatcher("Feedback.jsp").forward(request, response);
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
        String action = request.getParameter("action");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        HttpSession session = request.getSession();
        
        User u = (User) session.getAttribute("user"); //Lay ra tai khoan dang su dung
        DAO fdao = new DAO();
        
        if(action.equalsIgnoreCase("create")){
            fdao.createFeedback(u.getId(), title, content);
        } else if (action.equalsIgnoreCase("update")) {
            int id = Integer.parseInt(request.getParameter("id"));
            fdao.updateFeedback(id, u.getId(), title, content);
        } else if(action.equalsIgnoreCase("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            fdao.deleteFeedback(id);
             response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"success\": true}");
        return;
        }
        response.sendRedirect("Feedback");
   
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
