<%-- 
    Document   : checkout
    Created on : 01-07-2022, 15:04:32
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Check out</title>
    </head>
    <body>
        <c:set var="error" value="${requestScope.CHECKOUT_ERROR}" />
        <c:if test="${not empty error}">
            <h3>${error}<br/>Sorry, we will auto remove error product form your cart.<br/></h3>
            <font color="red">Your order has not been checked out successfully.</font><br/>
            <a href="viewCartPage">Click here to return your cart.</a>    or   
        </c:if>
        <c:if test="${empty error}">
            <c:set var="cart" value="${sessionScope.CART.items}" />
            <c:set var="totalBill" value="${requestScope.TOTAL_BILL}" />
            <c:set var="date" value="${requestScope.DATE}" />
            <c:set var="id" value="${requestScope.ORDER_ID}" />
            <font color="blue">Your Order has been checked out successfully</font><br/>
            <h1>Your Bill: </h1>
            Your order id: ${id} <br/>
            Date: ${date} <br/>

            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Title</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Total</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${cart}" varStatus="counter">
                        <c:set var="productId" value="${item.key}" />
                        <c:set var="quantity" value="${item.value}" />
                        <jsp:useBean id="productDAO" class="ngannq.product.ProductDAO"
                                     />
                        <c:set var="product" value="${productDAO.getProduct(productId)}" />
                        <tr>
                            <td>
                                ${counter.count}
                                .</td>
                            <td>
                                ${product.title}
                            </td>
                            <td>
                                ${quantity}
                            </td>
                            <td>
                                ${product.price}
                            </td>
                            <td>
                                ${product.price*quantity}
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="5" style="text-align:center;">
                            <font color="blue" >
                            PAID: $${totalBill}
                            </font><br/>  
                        </td>
                    </tr>
                </tbody>
            </table> 
            <c:remove scope="session" var="CART"/>
        </c:if>
        <a href="showProductsController">Click here to buy more</a> 

    </body>
</html>
