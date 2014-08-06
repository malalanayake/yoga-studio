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
                <li><a href="<c:url value='/view-customers' />">View Customers</a></li>
                <li><a href="<c:url value='/products' />">Manage Products</a></li>
            </sec:authorize>
            <sec:authorize ifAnyGranted="ROLE_FACULTY">  
                <li><a href="<c:url value='/waivers' />">Manage Waiver Requests</a></li>
                <li><a href="<c:url value='/advisees' />">View Advisees</a></li>
                <li><a href="<c:url value='/assignedsections' />">View Assigned Sections</a></li>
            </sec:authorize>
        </ul>
    </div>
</html>
