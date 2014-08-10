<%-- 
    Document   : forgotpassword
    Created on : Aug 9, 2014, 3:26:31 PM
    Author     : jCalles
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ page session="true"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <c:url var="cssUrl" value='/css/style.css'></c:url>
        <link rel="stylesheet" type="text/css" href=${cssUrl}>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <sec:authorize ifNotGranted="ROLE_ANONYMOUS">
            <c:redirect url="/main"/>
        </sec:authorize>
        <title>Forgot Password</title>
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


                    <c:url var="forgotpasswordanswer" value="/forgot-password/answer"></c:url>
                    <form:form action="${forgotpasswordanswer}" commandName="user">
                        <c:if test="${user.id == 0}">
                            <h2>Please introduce your username</h2>
                        </c:if>

                        <c:if test="${user.id > 0}">
                            <h2>Please introduce your answer</h2>
                        </c:if>
                        <table>
                            <c:if test="${user.id == 0}">
                                <tr>
                                    <td><form:label path="username">
                                            <spring:message text="Username" />
                                        </form:label></td>
                                    <td><form:input id="username" name="username" path="username" /></td>
                                </tr>
                            </c:if>

                            <c:if test="${user.id > 0}">
                                <tr>
                                    <td><form:label path="username">
                                            <spring:message text="Username" />
                                        </form:label></td>
                                    <td><form:input id="username" name="username" path="username" readonly="" disabled="true" /> <form:hidden path="username"/></td>
                                </tr>
                                <tr>
                                    <td><form:label path="sequrityQuestion">
                                            <spring:message text="Security Question" />
                                        </form:label></td>
                                    <td><form:input id="sequrityQuestion" name="sequrityQuestion" path="sequrityQuestion" readonly="" disabled="true" /> <form:hidden path="sequrityQuestion"/></td>
                                </tr>

                                <tr>
                                    <td><form:label path="answer">
                                            <spring:message text="Answer" />
                                        </form:label></td>
                                    <td><form:password id="answer" name="answer" path="answer" /></td>
                                </tr>
                            </c:if>
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
