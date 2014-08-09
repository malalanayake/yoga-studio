<%-- 
    Document   : waiver
    Created on : Aug 5, 2014, 10:01:25 PM
    Author     : Yen
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="com.app.studio.model.WaiverRequest.Constants" %>
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
                    <h2>
                        Manage Waiver Requests
                    </h2>
                    <c:if test="${not empty error}">
                        <div class="error">${error}</div>
                    </c:if>
                    <c:if test="${not empty msg}">
                        <div class="msg">${msg}</div>
                    </c:if>
                    <c:choose>
                        <c:when test="${empty listWaivers}">
                            <p>No waiver requests have been submitted.</p>
                        </c:when>
                        <c:otherwise>
                            <table class="tg">
                                <tr>
                                    <th>Waiver Request ID</th>
                                    <th>Customer</th>
                                    <th>Yoga Class</th>
                                    <th>Status</th>
                                    <th>Approve</th>
                                    <th>Reject</th>
                                </tr>
                                <c:set var="pending" value="<%= Constants.STATUS_PENDING%>" />
                                <c:forEach items="${listWaivers}" var="waiver">
                                    <tr>
                                        <td>${waiver.id}</td>
                                        <td>${waiver.customer.user.firstName} ${waiver.customer.user.lastName}</td>
                                        <td>${waiver.yogaClass.name}</td>
                                        <td>${waiver.status}</td>
                                        <c:choose>
                                            <c:when test="${waiver.status == pending}">
                                                <td><a href="<c:url value='/waivers/approve/${waiver.id}' />">Approve</a></td>
                                                <td><a href="<c:url value='/waivers/reject/${waiver.id}' />">Reject</a></td>
                                            </c:when>
                                            <c:otherwise>
                                                <td></td>
                                                <td></td>
                                            </c:otherwise>
                                        </c:choose>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:otherwise>
                    </c:choose>
                </div>
                <!--<%@include file="template/right-side.jsp"%>-->
                <%@include file="template/footer.jsp"%>
            </div>
        </div>
    </body>
</html>
