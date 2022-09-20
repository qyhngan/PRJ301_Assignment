<%-- 
    Document   : viewCart
    Created on : 30-06-2022, 22:51:25
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Store</title>
    </head>
    <body>
        <h1>Your Cart include:</h1>
        <c:set var="cart" value="${sessionScope.CART.items}" />
        <c:set var="listProduct" value="${sessionScope.LIST_PRODUCT}" />
        <c:if test="${not empty cart}">
            <form action="removeProductFromCartController">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Title</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Total</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${cart}" varStatus="counter">
                            <c:set var="productId" value="${item.key}" />
                            <c:set var="quantity" value="${item.value}" />
                            <c:forEach var="product" items="${listProduct}">
                                <c:if test="${product.id eq productId}">
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
                                        <td>
                                            <input type="checkbox" name="chkItem" 
                                                   value="${productId}" />
                                        </td>
                                    </tr>
                                </c:if>

                            </c:forEach>
                        </c:forEach>
                        <tr>
                            <td colspan="5">
                                <a href="showProductsController">Add More Books to Your Cart.</a>  
                            </td>
                            <td>
                                <input type="submit" value="Remove Selected Products" name="btAction" />
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
            <form action="checkOutController">
                <input type="submit" value="Check Out" name="btAction" />
            </form>
        </c:if>
        <c:if test="${empty cart}">
            <h2>
                No cart is existed!!!!! 
            </h2>
            <a href="showProductsController">Click here to buy more</a>  
        </c:if>


    </body>
</html>
