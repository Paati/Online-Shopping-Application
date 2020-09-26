/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingcatalog.cotroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import shoppingcatalog.DAO.StoreDAO;
import shoppingcatalog.dto.ItemInfoDTO;
import shoppingcatalog.dto.itemDTO;

/**
 *
 * @author Paaty
 */
public class StoreControllerServlet extends HttpServlet {

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
    HttpSession ht=request.getSession();
    String username=(String)ht.getAttribute("username");
   
    RequestDispatcher rd=null;
        try {
    if(username==null)
    {
    response.sendRedirect("accessdenied.html");
    }
    else{
     String itemType=request.getParameter("itemType");
    String id=request.getParameter("itemId");
    
     if(itemType==null && id==null){
         
        
                List<String> categories=StoreDAO.getItemTypes();
                request.setAttribute("categories",categories);
                rd=request.getRequestDispatcher("seestore.jsp");
   }
     else if(itemType!=null){
     List <ItemInfoDTO>itemList=StoreDAO.getItemsByType(itemType);
     request.setAttribute("itemList",itemList);
     rd=request.getRequestDispatcher("showitemsbytype.jsp");
     }
     else{
     itemDTO item=StoreDAO.getItemDetails(Integer.parseInt(id));
     request.setAttribute("item",item);
     rd=request.getRequestDispatcher("showitemdetails.jsp");
     }
     
    }     
            }catch (SQLException ex) {
                   ex.printStackTrace();
            }
            finally{
            
            rd.forward(request,response);
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
        processRequest(request, response);
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
