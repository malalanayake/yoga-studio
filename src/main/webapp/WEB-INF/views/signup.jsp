<%-- 
    Document   : SignUp
    Created on : Aug 5, 2014, 3:18:37 PM
    Author     : jCalles
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <c:url var="cssUrl" value='/css/style.css'></c:url>
    <link rel="stylesheet" type="text/css" href=${cssUrl}>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Yoga Studio</title>
</head>
<body>
    <div id="container">
        <%@include file="template/header.jsp"%>
        <div id="content-container">
            <%@include file="template/sidebar.jsp"%>
            <div id="content">
                
                <h1>Sign Up </h1>
                <h2>Please fill all the mandatory fields </h2>

                    <c:url var="addAction" value="/customer/add"></c:url>
                    <form name='SignUForm' form action="${addAction}" commandName="customer"
                    Username: <input type="text" name="username"><br>
                    Password: <input type="text" name="password"><br>
                    First Name: <input type="text" name="firstName"><br>
                    Last Name: <input type="text" name="lastName"><br>
                    Security Question: <input type="text" name="securityQuestion"><br>
                    Answer: <input type="text" name="answer"><br>
                    <input type="submit" value="Submit">
                    </form>

                    <c:if test="${not empty error}">
                        <div class="error">${error}</div>
                    </c:if>
                    <c:if test="${not empty msg}">
                        <div class="msg">${msg}</div>
                    </c:if>

            </div>
            <!--<%@include file="template/right-side.jsp"%>-->
            <%@include file="template/footer.jsp"%>
        </div>
    </div>
</body>
</html>
