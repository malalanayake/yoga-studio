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

                    <h1>Please edit the parameters that you want to change </h1>


                    <c:if test="${not empty error}">
                        <div class="error">${error}</div>
                    </c:if>
                    <c:if test="${not empty msg}">
                        <div class="msg">${msg}</div>
                    </c:if>

                    <c:url var="manage-profile" value="/manage-profile"></c:url>
                    <form:form action="${manage-profile}" commandName="user">

                        <table>
                            <tr>
                                <td><form:label path="password">
                                        <spring:message text="Password" />
                                    </form:label></td>
                                <td><form:input id="password" name="password" path="password" /></td>
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
                                <td><a href="<c:url value='/manage-profile/${pageContext.request.userPrincipal.name}/${user.password}/${user.firstName}/${user.lastName}/${user.sequrityQuestion}/${user.answer}'/>">Submit</a></td>
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
