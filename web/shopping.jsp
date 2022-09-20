<%-- 
    Document   : shopping
    Created on : 30-06-2022, 13:14:49
    Author     : User
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Store</title>
    </head>
    <body>
        <h1>Store</h1>
        <c:set var="list" value="${requestScope.PRODUCT_LIST}" />
        <c:if test="${not empty list}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Title</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${list}" varStatus="counter">
                        <tr>
                            <td>
                                ${counter.count}
                            .</td>
                            <td>
                                ${dto.title}
                            </td>
                            <td>
                                ${dto.quantity}
                            </td>
                            <td>
                                ${dto.price}
                            </td>
                            <td>
                                <form action="addProductToCartController">
                                    <input type="hidden" name="productId" value="${dto.id}" />
                                    <input type="submit" value="Add Product to Your Cart" name="btAction"/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty list}">
            <h2>
                Empty!!!
            </h2>
        </c:if>
        <form action="viewCartController">
            <input type="submit" value="View Your Cart" name="btAction"/>
        </form>
        <form action="searchPage">
            <input type="submit" value="Return Search Page" name="btAction"/>
        </form>
    </body>
</html>
