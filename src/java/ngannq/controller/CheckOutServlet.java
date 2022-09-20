/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ngannq.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ngannq.cart.CartObject;
import ngannq.order.OrderDAO;
import ngannq.orderDetail.OrderDetailDAO;
import ngannq.product.ProductDAO;
import ngannq.registration.RegistrationDTO;
import ngannq.utils.MyApplicationConstant;

/**
 *
 * @author User
 */
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {

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

        Date date = new Date(System.currentTimeMillis());
        String url = siteMaps.getProperty(MyApplicationConstant.CheckOutFeatures.CHECKOUT_PAGE);

        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart != null) {
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
                        OrderDAO dao = new OrderDAO();
                        RegistrationDTO user = (RegistrationDTO) session.getAttribute("ACCOUNT");
                        int orderId = dao.createOrder(date, 0, user.getUsername());
                        boolean success = false;
                        if (orderId > 0) {
                            double sum = 0;
                            for (String productId : items.keySet()) {
                                ProductDAO proDao = new ProductDAO();
                                double price = proDao.getProduct(productId).getPrice();
                                int quantity = proDao.getProduct(productId).getQuantity();
                                int reqQuan = items.get(productId);
                                if (quantity >= reqQuan) {
                                    OrderDetailDAO detailDao = new OrderDetailDAO();
                                    double total = detailDao.createOrderDetail(productId,
                                            reqQuan, price, orderId);
                                    proDao.updateQuantity(productId, quantity, reqQuan);
                                    sum += total;
                                } else {
                                    cart.removeProductFromCart(productId);
                                    request.setAttribute("CHECKOUT_ERROR", "THERE IS NOT ENOUGH QUANTITY TO SOME PRODUCT.");
                                    dao.removeOrder(orderId);
                                }
                            }
                            if (sum > 0) {
                                success = dao.updateTotalOrder(orderId, sum);
                                if (success) {
                                    request.setAttribute("ORDER_ID", orderId);
                                    request.setAttribute("DATE", date);
                                    request.setAttribute("TOTAL_BILL", sum);
                                    session.setAttribute("CART", cart);
                                    url = siteMaps.getProperty(MyApplicationConstant.CheckOutFeatures.CHECKOUT_PAGE);
                                }
                            } 
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            log("Check Out Servlet _ SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log("Check Out Servlet _ Naming " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
