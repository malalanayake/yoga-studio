<%-- 
    Document   : available-sections
    Created on : Aug 9, 2014, 9:15:07 AM
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
                        Available Sections
                    </h2>
                    <c:choose>
                        <c:when test="${empty availableSections}">
                            <p>There are currently no available sections.</p>
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
                                </tr>
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
                                        <td>${section.schedule}</td>
                                        <td>${section.location}</td>
                                        <td>${section.maxStudents}</td>
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
