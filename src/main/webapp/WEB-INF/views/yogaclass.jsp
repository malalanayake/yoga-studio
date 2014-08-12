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
                        Manage Yoga Classes
                    </h2>
                    <table>
                        <c:url var="addAction" value="/yogaclasses/add"></c:url>
                        <form:form action="${addAction}" commandName="yogaClass">


                            <c:if test="${yogaClass.id > 0}">
                                <tr>
                                    <td><form:label path="id">
                                            <spring:message text="YogaClass ID" />
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

                            </tr>
                            <tr>
                                <td></td>
                                <td>
                                    <c:if test="${yogaClass.id gt 0}">
                                        <input type="submit" value="<spring:message text="Done Editing"/>" />
                                    </c:if>
                                    <c:if test="${yogaClass.id == 0}">
                                        <input type="submit" value="<spring:message text="Add New Yoga Class"/>" />
                                    </c:if>
                                </td>
                            </tr>

                        </form:form>

                        <c:url var="addPreAction" value="/yogaclasses/add/pre"></c:url>
                        <form:form action="${addPreAction}" commandName="yogaClass">

                            <c:if test="${yogaClass.id > 0}">
                                <c:if test="${!empty listYogaClasses}">

                                    <tr>
                                        <td><form:label path="id">
                                                <spring:message text="YogaClass ID" />
                                            </form:label></td>
                                        <td><form:input path="id" readonly="true" 
                                                    disabled="true" /><form:hidden path="id" /></td>
                                    </tr> 
                                    <tr>
                                        <td>
                                            <select  name="preRequesits">
                                                <c:forEach items="${listYogaClasses}" var="yoga">

                                                    <c:if test="${yoga.id != yogaClass.id}">
                                                        <option value="${yoga.id}">${yoga}</option>
                                                    </c:if>

                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td>
                                            <input type="submit" value="<spring:message text="Add Prerequisite"/>" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2"></td>

                                    </tr>
                                    <tr>
                                        <td colspan="2">List of Yoga Classes</td>

                                    </tr>

                                </c:if>
                            </c:if>                
                        </form:form>
                    </table>

                    <c:if test="${!empty yogaClass.setOfPrerequisites}">
                        <table class="tg">
                            <tr>
                                <th width="80">ID</th>
                                <th width="200">Prerequisites Name</th>
                                <th width="120">Price</th>
                                <th width="60">Delete</th>
                            </tr>

                            <c:forEach items="${yogaClass.setOfPrerequisites}" var="pre">
                                <tr>
                                    <td>${pre.id}</td>
                                    <td>${pre.name}</td>
                                    <td>${pre.price}</td>
                                    <td><a href="<c:url value='/yogaclasses/remove/pre/${yogaClass.id}/${pre.id}' />">Delete</a></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:if>

                    <br>

                    <c:if test="${yogaClass.id == 0}">
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
                    </c:if>


                </div>
                <!--<%@include file="template/right-side.jsp"%>-->
                <%@include file="template/footer.jsp"%>
            </div>
        </div>
    </body>
</html>
