
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
        <title>Yoga Studio</title>

        <sec:authorize ifNotGranted="ROLE_ANONYMOUS">
            <c:redirect url="/main"/>
        </sec:authorize>
    </head>
    <body>
        <div id="container">
            <%@include file="template/header.jsp"%>
            <div id="content-container">

                <body onload='document.loginForm.username.focus();'>
                    <div id="login-box">

                        <h3>Login with Username and Password</h3>
                        <br>
                        <c:if test="${not empty error}">
                            <div class="error">${error}</div>
                        </c:if>
                        <c:if test="${not empty msg}">
                            <div class="msg">${msg}</div>
                        </c:if>

                        <form name='loginForm'
                              action="<c:url value='j_spring_security_check' />" method='POST'>

                            <table>
                                <tr>
                                    <td>User:</td>
                                    <td><input type='text' name='username' value=''></td>
                                </tr>
                                <tr>
                                    <td>Password:</td>
                                    <td><input type='password' name='password' /></td>
                                    <td><a href="<c:url value='/forgot-password' />">Forgot Password</a></td>
                                </tr>
                                <tr>
                                    <td><input name="submit" type="submit"
                                               value="submit" /></td>
                                    <td><a href="<c:url value='/signup' />">SignUp</a></td>
                                </tr>
                            </table>

                            <input type="hidden" name="${_csrf.parameterName}"
                                   value="${_csrf.token}" />
                        </form>
                    </div>

                </body>

                <%@include file="template/footer.jsp"%>
            </div>
        </div>
    </body>
</html>