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
    <head>
        <meta charset='utf-8'>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <c:url var="cssMenuUrl" value='/css/styles-side-menu.css'></c:url>
        <link rel="stylesheet" href="${cssMenuUrl}">
        <c:url var="jsMenuUrl" value='/js/script-side-menu.js'></c:url>
        <script src="${jsMenuUrl}"></script>
    </head>
    <div id="section-navigation">
        <div id='cssmenu'>
            <ul>
                <sec:authorize ifAnyGranted="ROLE_ADMIN">  
                    <li><a href="<c:url value='/semesters' />"><span>Manage Semesters</span></a></li>
                    <li><a href="<c:url value='/yogaclasses' />"><span>Manage Yoga Classes</span></a></li>
                    <li><a href="<c:url value='/faculties' />"><span>Manage Faculties</span></a></li>
                    <li><a href="<c:url value='/sections' />"><span>Manage Sections</span></a></li>
                    <li><a href="<c:url value='/products' />"><span>Manage Products</span></a></li>
                    <li><a href="<c:url value='/view-customers' />"><span>View Customers</span></a></li>
                    <li><a href="<c:url value='/view-orders' />"><span>View Orders</span></a></li>
                    <li><a href="<c:url value='/manage-profile' />"><span>Manage My Profile</span></a></li>
                </sec:authorize>
                <sec:authorize ifAnyGranted="ROLE_FACULTY">  
                    <li><a href="<c:url value='/waivers' />"><span>Manage Waiver Requests</span></a></li>
                    <li><a href="<c:url value='/advisees' />"><span>View Advisees</span></a></li>
                    <li><a href="<c:url value='/assigned-sections' />"><span>View Assigned Sections</span></a></li>
                    <li><a href="<c:url value='/students' />"><span>View Students</span></a></li>
                    <li><a href="<c:url value='/manage-profile' />"><span>Manage My Profile</span></a></li>
                </sec:authorize>
                <sec:authorize ifAnyGranted="ROLE_CUSTOMER">
                    <li><a href="<c:url value='/enroll' />"><span>Enroll</span></a></li>
                    <li><a href="<c:url value='/add-waiver-request' />"><span>Add Waiver Request</span></a></li>
                    <li><a href="<c:url value='/drop-section' />"><span>Drop Section</span></a></li>
                    <li><a href="<c:url value='/enrolled-sections' />"><span>View Enrolled Sections</span></a></li>
                    <li><a href="<c:url value='/view-products' />"><span>View Products</span></a></li>
                    <li><a href="<c:url value='/view-shoppingcart' />"><span>View Shopping Cart</span></a></li>
                    <li><a href="<c:url value='/view-orders-customer' />"><span>View My Orders</span></a></li>
                    <li><a href="<c:url value='/manage-profile' />"><span>Manage My Profile</span></a></li>
                </sec:authorize>
            </ul>
        </div>
    </div>
</html>
