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
        <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.0/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" /> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
        <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
        <c:url var="jsUrl" value='/js/jquery.timePicker.js'></c:url>
        <c:url var="jsCssUrl" value='/js/jquery.timepicker.css'></c:url>
        <link rel="stylesheet" type="text/css" href=${jsCssUrl}>
        <script src=${jsUrl}></script>
        <script type="text/javascript">
            jQuery(document).ready(function() {
                $("#ui-datepicker").datepicker({
                    showOn: 'focus',
                    dateFormat: ''
                }).css({'font-size': 'smaller'});

                $("#start").timepicker({
                    showOn: 'focus'
                });

            });
        </script>
        <style type="text/css">
            .ui-datepicker {
                width: 216px;
                height: auto;
                margin: 5px auto 0;
                font: 9pt Arial, sans-serif;
                -webkit-box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, .5);
                -moz-box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, .5);
                box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, .5);
                background-color: #FFF;
            }
        </style>
        <title>Yoga Studio</title>
    </head>
    <body>
        <div id="container">
            <%@include file="template/header.jsp"%>
            <div id="content-container">
                <%@include file="template/sidebar.jsp"%>
                <div id="content">
                    <h2>
                        Manage Sections
                    </h2>
                    <c:url var="addAction" value="/sections/add"></c:url>
                    <form:form action="${addAction}" commandName="section">

                        <table>
                            <c:if test="${section.id > 0}">
                                <tr>
                                    <td><form:label path="id">
                                            <spring:message text="ID" />
                                        </form:label></td>
                                    <td><form:input path="id" readonly="true" 
                                                disabled="true" /><form:hidden path="id" /></td>
                                </tr> 
                            </c:if>

                            <tr>
                                <td><form:label path="location">
                                        <spring:message text="Location" />
                                    </form:label></td>
                                <td><form:input path="location" /></td>
                            </tr>
                            <tr>
                                <td><form:label path="schedule">
                                        <spring:message text="Schedule Date" />
                                    </form:label></td>
                                <td><form:input id="ui-datepicker" name="ui-datepicker" path="schedule" /></td>
                            </tr>

                            <tr>
                                <td><form:label path="start">
                                        <spring:message text="Start Time" />
                                    </form:label></td>
                                <td><form:input id="start" name="start" path="start" /></td>
                            </tr>
                            <tr>
                                <td><form:label path="end">
                                        <spring:message text="End Time" />
                                    </form:label></td>
                                <td><form:input id="end" name="end" path="end" /></td>
                            </tr>
                            
                            <tr>
                                <td colspan='2'>
                                    <c:if test="${section.id gt 0}">
                                        <input type="submit" value="<spring:message text="Done Editing"/>" />
                                    </c:if>
                                    <c:if test="${section.id == 0}">
                                        <input type="submit" value="<spring:message text="Add New Section"/>" />
                                    </c:if></td>
                            </tr>
                        </table>
                    </form:form>


                    <table class="tg">
                        <tr>
                            <th width="80">ID</th>
                            <th width="120">Yoga Class</th>
                            <th width="120">Faculty</th>
                            <th width="120">Location</th>
                            <th width="60">Day</th>
                            <th width="60">Start</th>
                            <th width="60">End</th>
                            <th width="60">Edit</th>
                            <th width="60">Delete</th>
                        </tr>
                        <c:if test="${!empty listSections}">
                            <c:forEach items="${listSections}" var="section">
                                <tr>
                                    <td>${section.id}</td>
                                    <td>${section.yogaClass.name}</td>
                                    <td>${section.faculty.user.firstName}</td>
                                    <td>${section.location}</td>
                                    <td>${section.schedule}</td>
                                    <td>${section.start}</td>
                                    <td>${section.end}</td>
                                    <td><a href="<c:url value='/sections/edit/${section.id}' />">Edit</a></td>
                                    <td><a href="<c:url value='/sections/remove/${section.id}' />">Delete</a></td>
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