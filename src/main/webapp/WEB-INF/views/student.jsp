<%-- 
    Document   : student
    Created on : Aug 9, 2014, 11:48:06 AM
    Author     : Yen
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
                    <h2>
                        View Students
                    </h2>
                    <c:choose>
                        <c:when test="${empty listSections}">
                            <p>You are currently not assigned to teach any classes.</p>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${listSections}" var="section">
                                <div id="content-inner">
                                    <h3>Semester ${section.semester.startdate} - ${section.semester.enddate}: ${section.yogaClass.name} - Section ${section.id}</h3>
                                    <c:choose>
                                        <c:when test="${empty section.setOfEnrolledSections}">
                                            <p>There are currently no students enrolled in this class.</p>
                                        </c:when>
                                        <c:otherwise>
                                            <table class="tg">
                                                <tr>
                                                    <th>Customer ID</th>
                                                    <th>Name</th>
                                                    <th>Enrollment Date</th>
                                                    <th>Enrollment Status</th>
                                                </tr>
                                                <c:forEach items="${section.setOfEnrolledSections}" var="enrolled">
                                                    <tr>
                                                        <td>${enrolled.customer.id}</td>
                                                        <td>${enrolled.customer.user.firstName} ${enrolled.customer.user.lastName}</td>
                                                        <td>${enrolled.date}</td>
                                                        <td>${enrolled.status}</td>
                                                    </tr>
                                                </c:forEach>
                                            </table>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>
                <!--<%@include file="template/right-side.jsp"%>-->
                <%@include file="template/footer.jsp"%>
            </div>
        </div>
    </body>
</html>
