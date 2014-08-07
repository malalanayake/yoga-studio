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
                    <table>
                        &nbsp;&nbsp;&nbsp;
                    <h2>
                        Product List
                    </h2>
                        &nbsp;
                    <table class="tg">
                        <tr>
                            <th width="80">Product ID</th>
                            <th width="120">Product Name</th>
                            <th width="120">Product Type</th>
                            <th width="120">Price</th>
                            <th width="120">Available Quantity</th>
                            <th width="120">Description</th>
                            <th width="100">Option</th>
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
                                    <td><a href="<c:url value='/view-products' />">ADD To Cart</a></td></td>
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
