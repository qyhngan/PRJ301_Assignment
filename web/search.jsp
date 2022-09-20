<%-- 
    Document   : search
    Created on : 28-06-2022, 22:20:35
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <c:set var="account" value="${sessionScope.ACCOUNT}"/>
        <c:if test="${not empty account}">
            <font color="blue">
            Welcome, ${account.fullName}
            <form action="LogoutServlet">
                <input type="submit" value="Logout" name="btAction" /><br/>
            </form>
            </font>

            <a href="showProductsController">Click here to buy product</a>
            <h1>Search Account</h1>
            <form action="SearchLastNameServlet">
                Search Value <input type="text" name="txtSearchValue" 
                                    value="${param.txtSearchValue}" /><br/>
                <input type="submit" value="Search" name="btAction" />
            </form><br/>

            <c:set var="searchValue" value="${param.txtSearchValue}"/>
            <c:set var="delError" value="${sessionScope.DELETE_ERR}"/>
            <c:set var="updateError" value="${sessionScope.UPDATE_ERR}" />

            <c:if test="${not empty searchValue}">
                <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
                <c:if test="${not empty result}">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Username</th>
                                <th>Password</th>
                                <th>Full name</th>
                                <th>Role</th>
                                <th>Delete</th>
                                <th>Update</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="dto" items="${result}" varStatus="counter">
                            <form action="UpdateAccountServlet">
                                <tr>
                                    <td>
                                        ${counter.count}
                                        .</td>
                                    <td>
                                        ${dto.username}
                                        <input type="hidden" name="txtUsername" value="${dto.username}" />
                                    </td>
                                    <td>
                                        <input type="text" name="txtPassword" value="${dto.password}" />
                                    </td>
                                    <td>
                                        ${dto.fullName}
                                    </td>
                                    <td>
                                        <input type="checkbox" name="chkRole" value="ON" 
                                               <c:if test="${dto.role}">
                                                   checked="checked"
                                               </c:if>
                                               />
                                    </td>
                                    <td>
                                        <c:url var="deleteLink" value="deleteController">
                                            <c:param name="btAction" value="delete"/>
                                            <c:param name="pk" value="${dto.username}"/>
                                            <c:param name="lastSearchValue" value="${searchValue}"/>
                                        </c:url>
                                        <a href="${deleteLink}">Delete</a>
                                    </td>
                                    <td>
                                        <input type="submit" value="update" name="btAction" />
                                        <input type="hidden" name="lastSearchValue" value="${searchValue}" />
                                    </td>
                                </tr>
                            </form>
                        </c:forEach>
                    </tbody>
                </table>

            </c:if>
            <c:if test="${not empty delError}">
                <font color="red">
                ${delError}
                </font><br/>
            </c:if>
            <c:if test="${not empty updateError.passwordLengthErr}">
                <font color="red"> 
                ${updateError.passwordLengthErr}
                </font><br/>
            </c:if>
            <c:if test="${empty result}">
                <h2>
                    No record is matched!!!
                </h2>
            </c:if>    
        </c:if>     
    </c:if>
    <c:if test="${empty account}">
        <c:redirect url=""/>
    </c:if>


    <!--không được search ra chính mình
        log in xong mới được check out
        chuỗi [a-z0-9]
        back lại
    -->
</body>
</html>
