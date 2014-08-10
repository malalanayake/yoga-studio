<%-- 
    Document   : reset-password
    Created on : Aug 9, 2014, 3:37:17 PM
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
        <title>Reset Password</title>
    </head>
    <body>
        <div id="container">
            <%@include file="template/header.jsp"%>
            <div id="content-container">
                <%@include file="template/sidebar.jsp"%>
                <div id="content">

                    <c:if test="${not empty error}">
                        <div class="error">${error}</div>
                    </c:if>
                    <c:if test="${not empty msg}">
                        <div class="msg">${msg}</div>
                    </c:if>

                    <h2>Please introduce your new password</h2>
                    <c:url var="resetpassword" value="/reset-password"></c:url>
                    <form:form action="${resetpassword}" commandName="user">

                        <table>
                            <tr>
                                <td><form:label path="username">
                                        <spring:message text="Username" />
                                    </form:label></td>
                                <td><form:input id="username" name="username" path="username" readonly="" disabled="true" /> <form:hidden path="username"/></td>
                            </tr>
                            <tr>
                                <td><form:label path="password">
                                        <spring:message text="Password" />
                                    </form:label></td>
                                <td><form:password id="password" name="password" path="password" /></td>
                            </tr>
                         

                            <tr>
                                <td colspan="2">
                                    <input type="submit"
                                           value="<spring:message text="Submit"/>" />
                                </td>
                            </tr>
                        </table>
                    </form:form>
                </div>
                <!--<%@include file="template/right-side.jsp"%>-->
                <%@include file="template/footer.jsp"%>
            </div>
        </div>
    </body>
</html>
