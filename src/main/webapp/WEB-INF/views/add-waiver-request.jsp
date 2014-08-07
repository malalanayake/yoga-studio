<%-- 
    Document   : add-waiver-request
    Created on : Aug 7, 2014, 10:10:12 AM
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
        <title>Request to Waive a Prerequisite </title>
    </head>
    <body>
        <div id="container">
            <%@include file="template/header.jsp"%>
            <div id="content-container">
                <%@include file="template/sidebar.jsp"%>
                <div id="content">
                    <h1>
                        Please select the Yoga Class that you want to take
                    </h1>
                    

             <c:if test="${not empty error}">
                        <div class="error">${error}</div>
                    </c:if>
                    <c:if test="${not empty msg}">
                        <div class="msg">${msg}</div>
                    </c:if>
                    <table class="tg">
                        <tr>
                            <th>Yoga Class ID</th>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Submit</th>
                   
                        </tr>
                        <c:if test="${!empty listYogaClasses}">
                            <c:forEach items="${listYogaClasses}" var="yogaClass">
                                <tr>
                                    <td>${yogaClass.id}</td>
                                    <td>${yogaClass.name}</td>
                                    <td>${yogaClass.price}</td>
                                    <td><a href="<c:url value='/add-waiver-request/${yogaClass.id}/${pageContext.request.userPrincipal.name}' />">Submit</a></td>
         
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
