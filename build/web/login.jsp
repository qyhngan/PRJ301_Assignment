<%-- 
    Document   : login
    Created on : 15-07-2022, 23:19:08
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login Page</h1>
        <font color="red">
        ${requestScope.ADD_ERROR}
        </font><br/>
        <form action="loginController" method="POST">
            Username <input type="text" name="txtUsername" value="" /><br/>
            Password <input type="password" name="txtPassword" value="" /><br/>
            <input type="submit" value="Login" name="btAction">
            <input type="reset" value="Reset"/>
        </form><br/>
        <a href="createAccount">Click here to Sign Up</a><br/>
        <a href="showProductsController">Click here to buy product</a>  
    </body>
</html>
