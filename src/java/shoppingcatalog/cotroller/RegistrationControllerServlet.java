/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingcatalog.cotroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import shoppingcatalog.DAO.RegistrationDAO;
import shoppingcatalog.dto.UserDTO;

/**
 *
 * @author Paaty
 */
public class RegistrationControllerServlet extends HttpServlet {

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
       String uname=request.getParameter("username");
       String pwd=request.getParameter("password");
       RequestDispatcher rd=null;
       
        try {
            boolean ans=RegistrationDAO.searchUser(uname);
            boolean result=false;
            if(!ans)
            {
            UserDTO user=new UserDTO();
       user.setUserName(uname);
       user.setPassword(pwd);
       user.setUserType("CUSTOMER");
       result=RegistrationDAO.registerUser(user);
            }
            request.setAttribute("result",result);
            request.setAttribute("userFound",ans);
       rd=request.getRequestDispatcher("registrationresponse.jsp");
            
      } 
     catch(Exception e){
   request.setAttribute("Exception",e);
   rd=request.getRequestDispatcher("showexception.jsp");
   
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
