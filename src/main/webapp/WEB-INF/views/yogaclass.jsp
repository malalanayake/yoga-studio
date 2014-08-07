<%-- 
    Document   : main
    Created on : Aug 4, 2014, 10:01:25 PM
    Author     : malalanayake
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page session="true"%>
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
                    <c:if test="${not empty error}">
                        <div class="error">${error}</div>
                    </c:if>
                    <c:if test="${not empty msg}">
                        <div class="msg">${msg}</div>
                    </c:if>
                    <h2>
                        Manage Yoga Class
                    </h2>
                    <c:url var="addAction" value="/yogaclasses/add"></c:url>
                    <form:form action="${addAction}" commandName="yogaClass">

                        <table>
                            <c:if test="${yogaClass.id > 0}">
                                <tr>
                                    <td><form:label path="id">
                                            <spring:message text="ID" />
                                        </form:label></td>
                                    <td><form:input path="id" readonly="true" 
                                                disabled="true" /><form:hidden path="id" /></td>
                                </tr> 
                            </c:if>

                            <c:if test="${yogaClass.id > 0}">
                                <tr>
                                    <td><form:label path="name">
                                            <spring:message text="Yoga Class Name" />
                                        </form:label></td>
                                    <td><form:input path="name" readonly="true" 
                                                disabled="true"/><form:hidden path="name" /></td>
                                </tr>
                            </c:if>


                            <c:if test="${yogaClass.id == 0}">
                                <tr>
                                    <td><form:label path="name">
                                            <spring:message text="Yoga Class Name" />
                                        </form:label></td>
                                    <td><form:input path="name"/></td>
                                </tr>
                            </c:if>

                            <tr>
                                <td><form:label path="price">
                                        <spring:message text="Price" />
                                    </form:label></td>
                                <td><form:input path="price" /></td>
                            </tr>

                            <tr>
                                <td colspan='2'>
                                    <c:if test="${yogaClass.id gt 0}">
                                        <input type="submit" value="<spring:message text="Edit Yoga Class"/>" />
                                    </c:if>
                                    <c:if test="${yogaClass.id == 0}">
                                        <input type="submit" value="<spring:message text="Add New Yoga Class"/>" />
                                    </c:if></td>
                            </tr>
                        </table>
                    </form:form>

                    <table class="tg">
                        <tr>
                            <th width="80">ID</th>
                            <th width="120">Name</th>
                            <th width="120">Price</th>
                            <th width="60">Edit</th>
                            <th width="60">Delete</th>
                        </tr>
                        <c:if test="${!empty listYogaClasses}">
                            <c:forEach items="${listYogaClasses}" var="yogaClass">
                                <tr>
                                    <td>${yogaClass.id}</td>
                                    <td>${yogaClass.name}</td>
                                    <td>${yogaClass.price}</td>
                                    <td><a href="<c:url value='/yogaclasses/edit/${yogaClass.id}' />">Edit</a></td>
                                    <td><a href="<c:url value='/yogaclasses/remove/${yogaClass.id}' />">Delete</a></td>
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
