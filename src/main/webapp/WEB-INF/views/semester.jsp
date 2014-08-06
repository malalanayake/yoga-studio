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
                    <c:url var="addSemester" value="/semesters/add"></c:url>
                    <form:form action="${addSemester}" commandName="semester">
                        <table>
                            <c:if test="${semester.id > 0}">
                                <tr>
                                    <td><form:label path="id">
                                            <spring:message text="ID" />
                                        </form:label></td>
                                    <td><form:input path="id" readonly="true" size="8"
                                                disabled="true" /> <form:hidden path="id" /></td>
                                </tr> 
                            </c:if>
                            <tr>
                                <td><form:label path="startdate">
                                        <spring:message text="Start Date" />
                                    </form:label></td>
                                <td><form:input path="startdate" /></td>
                            </tr>
                            <tr>
                                <td><form:label path="enddate">
                                        <spring:message text="End Date" />
                                    </form:label></td>
                                <td><form:input path="enddate" /></td>
                            </tr>
                            <tr>
                                <td><form:label path="signUpDate">
                                        <spring:message text="SignUp Date" />
                                    </form:label></td>
                                <td><form:input path="signUpDate" /></td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <c:if test="${semester.id gt 0}">
                                        <input type="submit"
                                               value="<spring:message text="Edit Semester"/>" />
                                    </c:if>
                                    <c:if test="${semester.id == 0}">
                                        <input type="submit" value="<spring:message text="Add New Semeter"/>" />
                                    </c:if></td>
                            </tr>
                        </table>
                    </form:form>

                    
                    <table class="tg">
                        <tr>
                            <th width="80">Semester ID</th>
                            <th width="120">Start Date</th>
                            <th width="120">End Date</th>
                            <th width="120">Signup Date</th>
                            <th width="60">Edit</th>
                            <th width="60">Delete</th>
                        </tr>
                        <c:if test="${!empty listSemetsters}">
                            <c:forEach items="${listSemetsters}" var="semester">
                                <tr>
                                    <td>${semester.id}</td>
                                    <td>${semester.startdate}</td>
                                    <td>${semester.enddate}</td>
                                    <td>${semester.signUpDate}</td>
                                    <td><a href="<c:url value='/semesters/edit/${semester.id}' />">Edit</a></td>
                                    <td><a
                                            href="<c:url value='/semesters/remove/${semester.id}' />">Delete</a></td>
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
