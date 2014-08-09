<%-- 
    Document   : manage-profile
    Created on : Aug 8, 2014, 12:04:23 PM
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
        <title>Manage Profile</title>
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


                    <h2>Manage Profile</h2>
                    <c:url var="manageProfile" value="/manage-profile/update"></c:url>
                    <form:form method="POST" action="${manageProfile}" commandName="user">

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
                                <td><form:label path="firstName">
                                        <spring:message text="First Name" />
                                    </form:label></td>
                                <td><form:input id="firstName" name="firstName" path="firstName" /></td>
                            </tr>
                            <tr>
                                <td><form:label path="lastName">
                                        <spring:message text="Last name" />
                                    </form:label></td>
                                <td><form:input id="lastName" name="lastName" path="lastName" /></td>
                            </tr>
                            <tr>
                                <td><form:label path="sequrityQuestion">
                                        <spring:message text="Security Question" />
                                    </form:label></td>
                                <td><form:input id="sequrityQuestion" name="sequrityQuestion" path="sequrityQuestion" /></td>
                            </tr>
                            <tr>
                                <td><form:label path="answer">
                                        <spring:message text="Answer" />
                                    </form:label></td>
                                <td><form:input id="answer" name="answer" path="answer" /></td>
                            </tr>

                            <tr>
                                <td colspan="2">
                                    <input type="submit"
                                           value="<spring:message text="Update Profile"/>" />
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
