/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import Entity.User;
import Entity.Wallet;
 import Entity.Withdrawal;
import dao.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author tudo7
 */
@WebServlet(name="WithdrawalProcessingServlet", urlPatterns={"/withdrawalprocessing"})
public class WithdrawalProcessingServlet extends HttpServlet {
   
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
   //   processRequest(request, response);
         DAO dao = new DAO();
        List<Withdrawal> listWithdrawal = dao.getAllWithdrawal();     
        request.setAttribute("listWithdrawal", listWithdrawal);
        request.setAttribute("dao", dao);
 
        request.getRequestDispatcher("withdrawalProcessing.jsp").forward(request, response);
    
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
      //  processRequest(request, response);
      String wid_st = request.getParameter("lwids");
      String action = request.getParameter("actions");
      DAO dao = new DAO();
        try {
            int wid = Integer.parseInt(wid_st);
             Withdrawal w = dao.getWithdrawalByID(wid);
          switch (action) {
              case "complete" -> {
                  dao.UpdateWithdrawal("Hoàn thành","Đã chuyển khoản!", wid);
                  response.getWriter().write("success");
              }
              case "error" ->  {
                      dao.UpdateWithdrawal("Bị lỗi", "Vui lòng xem lại thông tin !", wid);
                      Wallet wa = dao.getWallet(w.getCreated_by());
                      dao.updateAmount(wa.getBalance()+(w.getAmount()*9/10), w.getCreated_by());
                      response.getWriter().write("success");
                  }
              case "accept" -> {
                  dao.UpdateWithdrawal("Chờ chuyển khoản", "Đã xác nhận, chờ chuyển khoản!", wid);
                  response.getWriter().write("success");
              }
              default ->                   {
                      dao.UpdateWithdrawal("Bị từ chối", w.getResponse(), wid);
                      Wallet wa = dao.getWallet(w.getCreated_by());
                      dao.updateAmount(wa.getBalance()+(w.getAmount()*9/10), w.getCreated_by());
                      response.getWriter().write("success");
                  }
          }
        } catch (IOException | NumberFormatException e) {
            response.getWriter().write("error");
        }
     
      
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
