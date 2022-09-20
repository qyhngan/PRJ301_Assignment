/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ngannq.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ngannq.order.OrderDAO;
import ngannq.orderDetail.OrderDetailDAO;
import ngannq.registration.RegistrationDAO;
import ngannq.registration.RegistrationDTO;
import ngannq.utils.MyApplicationConstant;

/**
 *
 * @author User
 */
@WebServlet(name = "DeleteAccountServlet", urlPatterns = {"/DeleteAccountServlet"})
public class DeleteAccountServlet extends HttpServlet {

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
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");

        String urlRewriting = siteMaps.getProperty(MyApplicationConstant.DeleteFeatures.ERROR_PAGE);
        String username = request.getParameter("pk");
        String searchValue = request.getParameter("lastSearchValue");

        try {
            //1. call DAO
            RegistrationDAO dao = new RegistrationDAO();
            HttpSession session = request.getSession(false);
            RegistrationDTO account = (RegistrationDTO) session.getAttribute("ACCOUNT");
            session.removeAttribute("UPDATE_ERR");

            if (username.equals(account.getUsername())) {
                String error = "You cannot delete the account that is logged in.";
                session.setAttribute("DELETE_ERR", error);
                urlRewriting = "searchLastNameController"
                        + "?txtSearchValue=" + searchValue;
            } else {
                session.removeAttribute("DELETE_ERR");
                OrderDAO orderDao = new OrderDAO();
                OrderDetailDAO detailDao = new OrderDetailDAO();
                ArrayList<Integer> orderIDs = orderDao.getOrderIDByAcc(username);
                if (orderIDs != null) {
                    for (int orderID : orderIDs) {
                        detailDao.removeOrderDetail(orderID);
                    }
                    orderDao.removeOrderByAcc(username);

                }
                boolean result = dao.deleteAccount(username);

                //2. process result
                if (result) {
                    urlRewriting = "searchLastNameController"
                            + "?txtSearchValue=" + searchValue;
                }
            }
        } catch (NamingException ex) {
            log("DeleteAccountServlet _ Naming " + ex.getMessage());
        } catch (SQLException ex) {
            log("DeleteAccountServlet _ SQL " + ex.getMessage());
        } finally {
            response.sendRedirect(urlRewriting);
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
