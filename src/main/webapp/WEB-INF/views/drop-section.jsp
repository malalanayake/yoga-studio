<%-- 
    Document   : drop-the-section
    Created on : Aug 8, 2014, 10:58:59 AM
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
                    <h2>
                        Drop Section
                    </h2>
                    <c:if test="${not empty error}">
                        <div class="error">${error}</div>
                    </c:if>
                    <c:if test="${not empty msg}">
                        <div class="msg">${msg}</div>
                    </c:if>
                    <c:choose>
                        <c:when test="${empty enrolledSections}">
                            <p>You are currently not enrolled in any classes.</p>
                        </c:when>
                        <c:otherwise>
                            <table class="tg">
                                <tr>
                                    <th>Semester ID</th>
                                    <th>Class Name</th>
                                    <th>Section ID</th>
                                    <th>Prerequisites</th>
                                    <th>Price</th>
                                    <th>Schedule</th>
                                    <th>Location</th>
                                    <th>Capacity</th>
                                    <th>Enrollment Date</th>
                                    <th>Drop</th>
                                </tr>
                                <c:forEach items="${enrolledSections}" var="enrolled">
                                    <tr>
                                        <td>${enrolled.section.semester.id}</td>
                                        <td>${enrolled.section.yogaClass.name}</td>
                                        <td>${enrolled.section.id}</td>
                                        <td>
                                            <ul>
                                                <c:forEach items="${enrolled.section.yogaClass.setOfPrerequisites}" var="prereq">
                                                    <li>${prereq.name}</li>
                                                </c:forEach>                                             
                                            </ul>
                                        </td>
                                        <td>$${enrolled.section.yogaClass.price}</td>
                                        <td>${enrolled.section.schedule} ${enrolled.section.start}-${enrolled.section.end}</td>
                                        <td>${enrolled.section.location}</td>
                                        <td>${enrolled.section.maxStudents}</td>
                                        <td>${enrolled.date}</td>
                                        <td><a href="<c:url value='/drop-section/${enrolled.id}' />">Click to drop</a></td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:otherwise>
                    </c:choose>
                </div>
                <!--<%@include file="template/right-side.jsp"%>-->
                <%@include file="template/footer.jsp"%>
            </div>
        </div>
    </body>
</html>
