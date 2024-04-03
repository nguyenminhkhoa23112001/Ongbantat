/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Entity.Cart;
import Entity.Product;
import Entity.ReportOrderProduct;
import Entity.Report;
import Entity.User;
import Entity.intermediateOrders;
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
import org.apache.jasper.tagplugins.jstl.core.ForEach;

/**
 *
 * @author Nguyen Minh Khoa
 */
@WebServlet(name = "ReportServlet", urlPatterns = {"/ReportServlet"})
public class ReportServlet extends HttpServlet {

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
        //response.setContentType("text/html;charset=UTF-8");
        //DAO dao = new DAO();
        //List<Report> list = dao.getAllReport();
        //request.setAttribute("listR", list);
        //intermediateOrders orderx = dao.getOrderByID(list.forEach(getOrderID));
        //request.getRequestDispatcher("Report.jsp").forward(request, response);
        
        response.setContentType("text/html;charset=UTF-8");       
        DAO dao = new DAO();
        List<Report> listR = dao.getAllReport();
        List<ReportOrderProduct> reportOrderProductAll = new ArrayList<>();
        for (Report report : listR) {
            intermediateOrders order = dao.getOrderByID(report.getOrderID());
            Product product = dao.getProductByID(order.getProductId());
            reportOrderProductAll.add(new ReportOrderProduct(report,  order, product));
        }
        /*
        for (ReportOrderProduct c : reportOrderProductAll) {            
            //response.getWriter().print(c.getOrder().getId());
            c.getReport().getType_report()
        }
        */
        request.setAttribute("combo3", reportOrderProductAll);
        request.getRequestDispatcher("Report.jsp").forward(request, response);
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
