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
                    <c:if test="${not empty error}">
                        <div class="error">${error}</div>
                    </c:if>
                    <c:if test="${not empty msg}">
                        <div class="msg">${msg}</div>
                    </c:if>
                    <h2>
                        Manage Faculty
                    </h2>
                    <c:url var="addAction" value="/faculties/add"></c:url>
                    <form:form action="${addAction}" commandName="user">

                        <table>
                            <c:if test="${user.id > 0}">
                                <tr>
                                    <td><form:label path="id">
                                            <spring:message text="ID" />
                                        </form:label></td>
                                    <td><form:input path="id" readonly="true" 
                                                disabled="true" /><form:hidden path="id" /></td>
                                </tr> 
                            </c:if>

                            <c:if test="${user.id > 0}">
                                <tr>
                                    <td><form:label path="username">
                                            <spring:message text="Username" />
                                        </form:label></td>
                                    <td><form:input path="username" readonly="true" 
                                                disabled="true"/><form:hidden path="username" /></td>
                                </tr>
                            </c:if>

                            <c:if test="${user.id == 0}">
                                <tr>
                                    <td><form:label path="username">
                                            <spring:message text="Username" />
                                        </form:label></td>
                                    <td><form:input path="username" /></td>
                                </tr>
                            </c:if>

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
                                <td colspan='2'>
                                    <c:if test="${user.id gt 0}">
                                        <input type="submit" value="<spring:message text="Edit Semester"/>" />
                                    </c:if>
                                    <c:if test="${user.id == 0}">
                                        <input type="submit" value="<spring:message text="Add New Semeter"/>" />
                                    </c:if></td>
                            </tr>
                        </table>
                    </form:form>

                    <table class="tg">
                        <tr>
                            <th width="80">ID</th>
                            <th width="120">User Name</th>
                            <th width="120">First Name</th>
                            <th width="120">Last Name</th>
                            <th width="200">Security Question</th>
                            <th width="60">Answer</th>
                            <th width="60">Edit</th>
                            <th width="60">Delete</th>
                        </tr>
                        <c:if test="${!empty listFaculties}">
                            <c:forEach items="${listFaculties}" var="faculty">
                                <tr>
                                    <td>${faculty.id}</td>
                                    <td>${faculty.user.username}</td>
                                    <td>${faculty.user.firstName}</td>
                                    <td>${faculty.user.lastName}</td>
                                    <td>${faculty.user.sequrityQuestion}</td>
                                    <td>${faculty.user.answer}</td>
                                    <td><a href="<c:url value='/faculties/edit/${faculty.id}' />">Edit</a></td>
                                    <td><a href="<c:url value='/faculties/remove/${faculty.id}' />">Delete</a></td>
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
