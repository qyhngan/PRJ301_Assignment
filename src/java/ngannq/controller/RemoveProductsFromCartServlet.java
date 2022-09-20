/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ngannq.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ngannq.cart.CartObject;

/**
 *
 * @author User
 */
@WebServlet(name = "RemoveProductsFromCartServlet", urlPatterns = {"/RemoveProductsFromCartServlet"})
public class RemoveProductsFromCartServlet extends HttpServlet {

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
        
        
        
        try {
            //1. Customer goes to cart place
            HttpSession session = request.getSession(false);
            if (session != null) {
                //2. Custoomer takes his/her cart
                CartObject cart = (CartObject)session.getAttribute("CART");
                if (cart != null) {
                    //3. Check items existed
                    Map<String, Integer> items = cart.getItems();;
                    if (items != null) {
                        //4. get all selected book
                        String[] selectedItems = request.getParameterValues("chkItem");
                        if (selectedItems != null) {
                            //5. remove one by one item from items 
                            for (String item : selectedItems) {
                                cart.removeProductFromCart(item);
                            }//end remove items from cart
                            session.setAttribute("CART", cart);
                        }//selected items have existed
                    }//items have existed
                }// end cart has existed
            }// session has existed
        } finally {
            String urlRewriting = "viewCartPage";
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
