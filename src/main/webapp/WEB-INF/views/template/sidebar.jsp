<%-- 
    Document   : sidebar
    Created on : Aug 4, 2014, 10:59:13 PM
    Author     : malalanayake
--%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
    <div id="section-navigation">
        <ul>
            <sec:authorize ifAnyGranted="ROLE_ADMIN">  
                <li><a href="<c:url value='/semesters' />">Manage Semesters</a></li>
                <li><a href="<c:url value='/yogaclasses' />">Manage Yoga Classes</a></li>
                <li><a href="<c:url value='/sections' />">Manage Sections</a></li>
                <li><a href="<c:url value='/products' />">Manage Products</a></li>
                <li><a href="<c:url value='/faculties' />">Manage Faculties</a></li>
                <li><a href="<c:url value='/view-customers' />">View Customers</a></li>
                </sec:authorize>
                <sec:authorize ifAnyGranted="ROLE_FACULTY">  
                <li><a href="<c:url value='/waivers' />">Manage Waiver Requests</a></li>
                <li><a href="<c:url value='/advisees' />">View Advisees</a></li>
                <li><a href="<c:url value='/assigned-sections' />">View Assigned Sections</a></li>
                <li><a href="<c:url value='/students' />">View Students</a></li>
                </sec:authorize>
                <sec:authorize ifAnyGranted="ROLE_CUSTOMER">
                <li><a href="<c:url value='/enroll' />">Enroll</a></li>
                <li><a href="<c:url value='/add-waiver-request' />">Add Waiver Request</a></li>
                <li><a href="<c:url value='/drop-section' />">Drop Section</a></li>
                <li><a href="<c:url value='/enrolled-sections' />">View Enrolled Sections</a></li>
                <li><a href="<c:url value='/available-sections' />">View Available Sections</a></li>
                <li><a href="<c:url value='/view-products' />">View Products</a></li>
                </sec:authorize>

            <sec:authorize ifNotGranted="ROLE_ANONYMOUS">
                <li><a href="<c:url value='/manage-profile' />">Manage My Profile</a></li>
                </sec:authorize>

        </ul>
    </div>
</html>
