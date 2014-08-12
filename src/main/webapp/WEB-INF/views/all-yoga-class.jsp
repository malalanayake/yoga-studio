<%-- 
    Document   : all-yoga-class
    Created on : Aug 11, 2014, 10:00:58 AM
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
                        View Classes
                    </h2>
                    <c:choose>
                        <c:when test="${empty listSemesters}">
                            <p>There are currently no available classes.</p>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${listSemesters}" var="semester">
                                <div id="content-inner">
                                    <h3>Semester: ${semester.startdate} - ${semester.enddate}</h3>
                                    <p>Sign Up Date: ${semester.signUpDate}</p>
                                    <c:choose>
                                        <c:when test="${empty semester.setOfSections}">
                                            <p>There are no available classes for this semester.</p>
                                        </c:when>
                                        <c:otherwise>
                                            <table class="tg">
                                                <tr>
                                                    <th>Class Name</th>
                                                    <th>Section ID</th>
                                                    <th>Prerequisites</th>
                                                    <th>Price</th>
                                                    <th>Schedule</th>
                                                    <th>Location</th>
                                                    <th>Capacity</th>
                                                </tr>
                                                <c:forEach items="${semester.setOfSections}" var="section">
                                                    <tr>
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
