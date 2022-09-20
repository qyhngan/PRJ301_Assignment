/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ngannq.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ngannq.registration.RegistrationCreateError;
import ngannq.registration.RegistrationDAO;
import ngannq.utils.MyApplicationConstant;

/**
 *
 * @author User
 */
@WebServlet(name = "UpdateAccountServlet", urlPatterns = {"/UpdateAccountServlet"})
public class UpdateAccountServlet extends HttpServlet {

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

        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties)context.getAttribute("SITEMAPS");
        
        String url = siteMaps.getProperty(MyApplicationConstant.UpdateFeatures.ERROR_PAGE);
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String admin = request.getParameter("chkRole");
        String searchValue = request.getParameter("lastSearchValue");
        boolean isAdmin = false;
        RegistrationCreateError errors = new RegistrationCreateError();

        try {
            if (admin != null && admin.equals("ON")) {
                isAdmin = true;
            }
            //1. call DAO
            RegistrationDAO dao = new RegistrationDAO();
            HttpSession session = request.getSession(false);
            
            session.removeAttribute("DELETE_ERR");
            if (password.trim().length() < 6 || password.trim().length() > 30) {
                errors.setPasswordLengthErr("Password is required with 6 - 30 characters");
                session.setAttribute("UPDATE_ERR", errors);
                url = "searchLastNameController"
                        + "?txtSearchValue=" + searchValue;
            } else {
                session.removeAttribute("UPDATE_ERR");
                boolean result = dao.updateAccount(username, password, isAdmin);
                //2. process result
                if (result) {
                    //call function again to refresh
                    url = "searchLastNameController"
                            + "?txtSearchValue=" + searchValue;
                }
            }//end update is succeeded
        } catch (SQLException ex) {
            log("UpdateAccountServlet _ SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log("UpdateAccountServlet _ Naming " + ex.getMessage());
        } finally {
            response.sendRedirect(url);
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
