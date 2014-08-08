<%-- 
    Document   : main
    Created on : Aug 4, 2014, 10:01:25 PM
    Author     : malalanayake
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
                    <h2>View Customers</h2>
                    <table class="tg">
                        <tr>
                            <th width="80">ID</th>
                            <th width="120">First Name</th>
                            <th width="120">Last Name</th>
                            <th width="120">User Name</th>
                            <th width="120">SignUp Date</th>
                            <th width="60">Delete</th>
                        </tr>
                        <c:if test="${!empty listCustomers}">
                            <c:forEach items="${listCustomers}" var="customer">
                                <tr>
                                    <td>${customer.id}</td>
                                    <td>${customer.user.firstName}</td>
                                    <td>${customer.user.lastName}</td>
                                    <td>${customer.user.username}</td>
                                    <td>${customer.signUpDate}</td>
                                    <td><a
                                            href="<c:url value='/view-customers/remove/${customer.id}' />">Delete</a></td>
                                </tr>
                            </c:forEach>
                        </c:if>
                    </table>

                </div>
                <!--<%@include file="template/right-side.jsp"%>-->
                <%@include file="template/footer.jsp"%>
            </div>
        </div>
    </body>
</html>
