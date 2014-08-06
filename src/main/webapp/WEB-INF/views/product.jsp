<%-- 
    Document   : main
    Created on : Aug 4, 2014, 10:01:25 PM
    Author     : aTabibi
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
                    <c:url var="addProduct" value="/products/add"></c:url>
                    <form:form action="${addProduct}" commandName="product">
                        <table>
                            <c:if test="${product.id > 0}">
                                <tr>
                                    <td><form:label path="id">
                                            <spring:message text="ID" />
                                        </form:label></td>
                                    <td><form:input path="id" readonly="true" size="8"
                                                disabled="true" /> <form:hidden path="id" /></td>
                                </tr> 
                            </c:if>
                            <tr>
                                <td><form:label path="name">
                                        <spring:message text="Product Name" />
                                    </form:label></td>
                                <td><form:input path="name" /></td>
                            </tr>
                            <tr>
                                <td><form:label path="type">
                                        <spring:message text="Product Type" />
                                    </form:label></td>
                                <td><form:input path="type" /></td>
                            </tr>
                            <tr>
                                <td><form:label path="price">
                                        <spring:message text="Price" />
                                    </form:label></td>
                                <td><form:input path="price" /></td>
                            </tr>
                            <tr>
                                <td><form:label path="availableQuantity">
                                        <spring:message text="Available Quantity" />
                                    </form:label></td>
                                <td><form:input path="availableQuantity" /></td>
                            </tr>
                            <tr>
                                <td><form:label path="description">
                                        <spring:message text="Description" />
                                    </form:label></td>
                                <td><form:input path="description" /></td>
                            </tr>
                            
                            <tr>
                                <td colspan="2">
                                    <c:if test="${product.id gt 0}">
                                        <input type="submit"
                                               value="<spring:message text="Edit Product"/>" />
                                    </c:if>
                                    <c:if test="${product.id == 0}">
                                        <input type="submit" value="<spring:message text="Add New Product"/>" />
                                    </c:if></td>
                            </tr>
                        </table>
                    </form:form>

                    <h2>
                        Product List
                    </h2>
                    <table class="tg">
                        <tr>
                            <th width="80">Product ID</th>
                            <th width="120">Product Name</th>
                            <th width="120">Product Type</th>
                            <th width="120">Price</th>
                            <th width="120">Available Quantity</th>
                            <th width="120">Description</th>
                            <th width="60">Edit</th>
                            <th width="60">Delete</th>
                        </tr>
                        <c:if test="${!empty listProducts}">
                            <c:forEach items="${listProducts}" var="product">
                                <tr>
                                    <td>${product.id}</td>
                                    <td>${product.name}</td>
                                    <td>${product.type}</td>
                                    <td>${product.price}</td>
                                    <td>${product.availableQuantity}</td>
                                    <td>${product.description}</td>
                                    <td><a href="<c:url value='/products/edit/${product.id}' />">Edit</a></td>
                                    <td><a
                                            href="<c:url value='/products/remove/${product.id}' />">Delete</a></td>
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
