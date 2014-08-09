<%-- 
    Document   : waitlist
    Created on : Aug 8, 2014, 10:09:42 PM
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
                    <c:url var="waitlist" value="/waitlist"></c:url>
                    <form:form action="${waitlist}" commandName="section">
                        <p>${section.yogaClass.name} - Section ${section.id} for Semester ${section.semester.id}
                        is full. Would you like to be waitlisted for this class?</p>
                        <input type="submit" name="waitlist" value="<spring:message text="Yes"/>" />
                        <input type="submit" name="waitlist" value="<spring:message text="No"/>" />
                        <form:hidden path="id" />
                    </form:form>
                </div>
                <!--<%@include file="template/right-side.jsp"%>-->
                <%@include file="template/footer.jsp"%>
            </div>
        </div>
    </body>
</html>
