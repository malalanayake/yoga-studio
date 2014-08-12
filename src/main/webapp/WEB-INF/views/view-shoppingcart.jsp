<%-- 
    Document   : main
    Created on : Aug 5, 2014, 10:01:25 PM
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
                            Shopping Cart List
                        </h2>
                        <tr><p>UserName: ${username.username}</p>&nbsp; 
                        </tr>
                    </table>
                    <table class="tg">
                        <tr>
                            <th width="80">Product Name</th>
                            <th width="120">Product Image</th>
                            <th width="80">Number Of Quantity</th>
                            <th width="80">Actual Price</th>
                            <th width="80">Delete</th>
                            <th width="100">Option</th>
                        </tr>
                        <c:if test="${!empty listShoppingCarts}">
                            <c:forEach items="${listShoppingCarts}" var="shoppingcartitem">
                                <tr>
                                    <td>${shoppingcartitem.product.name}</td>
                                    <td><img src=${shoppingcartitem.product.imageSrc} height="100" width="100"></td>
                                    <td>${shoppingcartitem.quantity}</td>
                                    <td>${shoppingcartitem.product.price}</td>
                                    <td><a
                                            href="<c:url value='/view-shoppingcart/remove/${shoppingcartitem.id}' />">Delete</a></td>
                                    <td><a href="<c:url value='/view-shoppingcart/remove/${shoppingcartitem.id}' />">Check Out</a></td></td>
                                </tr>
                            </c:forEach>
                        </c:if>
                    </table>
                    <table>
                        <tr>
                            <td>Total Price: ${calculatePrice} </td>
                        </tr>
                        &nbsp;
                        <tr> <td> &nbsp;</td> </tr>
                        <tr>
                            <td>&nbsp;

                                <a href="<c:url value='/view-shoppingcart/removeall' />">Check Out All</a></td>

                        </tr> 
                    </table>
                </div>
                <!--<%@include file="template/right-side.jsp"%>-->
                <%@include file="template/footer.jsp"%>
            </div>
        </div>
    </body>
</html>
