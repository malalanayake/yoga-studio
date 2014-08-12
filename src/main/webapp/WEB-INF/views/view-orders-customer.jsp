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
                    <h2>
                        View Customer Orders
                    </h2>

                    <table class="tg">
                        <tr>


                        </tr>
                        <c:if test="${!empty listCustomerOrders}">
                            <tr>
                                <th width="80">ID</th>
                                <th width="120">Status</th>
                                <th width="120">Total Price</th>
                                <th width="60">View</th>
                            </tr>

                            <c:forEach items="${listCustomerOrders}" var="order">
                                <tr>
                                    <td>${order.id}</td>
                                    <td>${order.status}</td>
                                    <td>${order.totalPrice}</td>
                                    <td><a href="<c:url value='/view-orders-customer/${order.id}' />">View</a></td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        <c:if test="${!empty listOrderItemsCustomer}">
                            <tr>
                                <th width="80">ID</th>
                                <th width="120">Product Name</th>
                                <th width="120">Type</th>
                                <th width="120">Price</th>
                                <th width="80">Quantity</th>
                            </tr>

                            <c:forEach items="${listOrderItemsCustomer}" var="item">
                                <tr>
                                    <td>${item.id}</td>
                                    <td>${item.product.name}</td>
                                    <td>${item.product.type}</td>
                                    <td>${item.product.price}</td>
                                    <td>${item.quantity}</td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td><a href="<c:url value='/view-orders-customer' />">Back</a></td>
                            </tr>
                        </c:if>
                    </table>

                </div>
                <!--<%@include file="template/right-side.jsp"%>-->
                <%@include file="template/footer.jsp"%>
            </div>
        </div>
    </body>
</html>
