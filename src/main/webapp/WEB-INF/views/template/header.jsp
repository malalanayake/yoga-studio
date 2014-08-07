<%-- 
    Document   : header
    Created on : Aug 4, 2014, 10:36:06 PM
    Author     : malalanayake
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="true"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="Expires" content="-1">
    </head>
    <div id="header">
        <h1>
            Yoga Studio
        </h1>
    </div>
    <div id="navigation">
        <c:url value="/j_spring_security_logout" var="logoutUrl" />
        <!-- csrt for log out-->
        <form action="${logoutUrl}" method="post" id="logoutForm">
            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}" />
        </form>

        <script>
            function formSubmit() {
                document.getElementById("logoutForm").submit();
            }
        </script>
        <ul>
            <sec:authorize ifAnyGranted="ROLE_ADMIN"></sec:authorize>
            <li><a href="<c:url value='/main' />">Home</a></li>
            <li><a href="<c:url value='/' />">About</a></li>
            <li><a href="<c:url value='/' />">Services</a></li>
            <li><a href="<c:url value='/' />">Contact us</a></li>
                <c:if test="${pageContext.request.userPrincipal.name != null}">
                <li> <a href="javascript:formSubmit()"> Logout</a> </li>
                </c:if>
                
                <c:if test="${pageContext.request.userPrincipal.name == null}">
                <li> <a href="<c:url value='/login' />"> Login</a> </li>
                </c:if>
        </ul>
    </div>
</html>
