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

                    <h1>Sign Up  </h1>
                    <h2>Please fill all the mandatory fields  </h2>

                    <c:url var="addAction" value="/signup/add"></c:url>
                    <form:form action="${addAction}" commandName="user">

                        <table>
                            <tr>
                                <td><form:label path="username">
                                        <spring:message text="Username" />
                                    </form:label></td>
                                <td><form:input path="username" /></td>
                            </tr>

                            <tr>
                                <td><form:label path="password">
                                        <spring:message text="Password" />
                                    </form:label></td>
                                <td><form:password path="password" /></td>
                            </tr>

                            <tr>
                                <td><form:label path="firstName">
                                        <spring:message text="First Name" />
                                    </form:label></td>
                                <td><form:input path="firstName" /></td>
                            </tr>


                            <tr>
                                <td><form:label path="lastName">
                                        <spring:message text="Lastname" />
                                    </form:label></td>
                                <td><form:input path="lastName" /></td>
                            </tr>

                            <tr>
                                <td><form:label path="sequrityQuestion">
                                        <spring:message text="Security Question" />
                                    </form:label></td>
                                <td><form:input path="sequrityQuestion" /></td>
                            </tr>

                            <tr>
                                <td><form:label path="answer">
                                        <spring:message text="Answer" />
                                    </form:label></td>
                                <td><form:input path="answer" /></td>
                            </tr>

                            <tr>
                                <td><input name="submit" type="submit"
                                                       value="submit" /></td>
                                <td><a href="<c:url value='/login' />">Go to Login</a></td>
                            </tr>
                        </table>


                    </form:form>

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
