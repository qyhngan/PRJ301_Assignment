<%-- 
    Document   : createAccount
    Created on : 30-06-2022, 12:39:56
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account</title>
    </head>
    <body>
        <h1>Create Account</h1>
        <form action="createAccountController" method="POST">
            <c:set var="errors" value="${requestScope.CREATE_ERR}" />
            Username* <input type="text" name="txtUsername" 
                             value="${param.txtUsername}" /> (e.g 6 - 20 characters)<br/>
            <c:if test="${not empty errors.usernameLengthErr}">
                <font color="red">
                ${errors.usernameLengthErr}
                </font><br/>
            </c:if>
            <c:if test="${not empty errors.usernameIsExisted}">
                <font color="red"> 
                ${errors.usernameIsExisted}
                </font><br/>
            </c:if>
            Password* <input type="password" name="txtPassword" 
                             value="${param.txtPassword}" /> (e.g 6 - 30 characters)<br/>
            <c:if test="${not empty errors.passwordLengthErr}">
                <font color="red"> 
                ${errors.passwordLengthErr}
                </font><br/>
            </c:if>
            Confirm* <input type="password" name="txtConfirm" 
                            value="${param.txtConfirm}" /><br/>
            <c:if test="${not empty errors.confirmNoMatched}">
                <font color="red"> 
                ${errors.confirmNoMatched}
                </font><br/>
            </c:if>
            Full name* <input type="text" name="txtFullname" 
                              value="${param.txtFullname}" />(e.g 2 - 40 characters)<br/>
            <c:if test="${not empty errors.fullnameLengthErr}">
                <font color="red"> 
                ${errors.fullnameLengthErr}
                </font><br/>
            </c:if>
            <input type="submit" value="Create New Account" name="btAction" />
            <input type="reset" value="Reset"/>
        </form>
    </body>
</html>
