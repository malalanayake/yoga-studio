<%-- 
    Document   : enroll
    Created on : Aug 7, 2014, 11:04:27 AM
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
                        Enroll
                    </h2>
                    <c:if test="${not empty error}">
                        <div class="error">${error}</div>
                    </c:if>
                    <c:if test="${not empty msg}">
                        <div class="msg">${msg}</div>
                    </c:if>
                    <c:if test="${(!empty enrolledSections) || (!empty availableSections)}">
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
                                <th>Enrollment Status</th>
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
                                    <td>${enrolled.status}</td>
                                </tr>
                            </c:forEach>
                            <c:forEach items="${availableSections}" var="section">
                                <tr>
                                    <td>${section.semester.id}</td>
                                    <td>${section.yogaClass.name}</td>
                                    <td>${section.id}</td>
                                    <td>
                                        <ul>
                                            <c:forEach items="${section.yogaClass.setOfPrerequisites}" var="prereq">
                                                <li>${prereq.name}</li>
                                            </c:forEach>                                             
                                        </ul>
                                    </td>
                                    <td>$${section.yogaClass.price}</td>
                                    <td>${section.schedule} ${section.start}-${section.end}</td>
                                    <td>${section.location}</td>
                                    <td>${section.maxStudents}</td>
                                    <td></td>
                                    <td><a href="<c:url value='/enroll/${section.id}' />">Click to enroll</a></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:if>
                    <c:if test="${empty enrolledSections}">
                        <p>You are currently not enrolled in any classes.</p>
                    </c:if>
                    <c:if test="${empty availableSections}">
                        <p>There are currently no available sections for you to enroll in.</p>
                    </c:if>
                </div>
                <!--<%@include file="template/right-side.jsp"%>-->
                <%@include file="template/footer.jsp"%>
            </div>
        </div>
    </body>
</html>
