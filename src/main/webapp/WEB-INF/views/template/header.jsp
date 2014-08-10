<%-- 
    Document   : header
    Created on : Aug 4, 2014, 10:36:06 PM
    Author     : malalanayake
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="true"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="Expires" content="-1">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <c:url var="cssUrl" value='/css/style-slider.css'></c:url>
        <link rel="stylesheet" type="text/css" media="screen" href="${cssUrl}"/>
        <link href='http://fonts.googleapis.com/css?family=Open+Sans:300,300italic,400italic,400,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
        <script type="text/javascript">
            jQuery(document).ready(function() {
                $("#slideshow > div:gt(0)").hide();

                setInterval(function() {
                    $('#slideshow > div:first')
                            .fadeOut(1000)
                            .next()
                            .fadeIn(1000)
                            .end()
                            .appendTo('#slideshow');
                }, 3000);
            });
        </script>
    </head>
    <div id="header">
        <div class="container">
            <div id="content-slider">
                <div id="slider">
                    <div id="mask">
                        <ul>
                            <li id="first" class="firstanimation">
                                <a href="#">
                                    <c:url var="imgUrl1" value='/images/img_1.jpg'></c:url>
                                    <img src="${imgUrl1}" alt="Cougar"/>
                                </a>
                                <div class="tooltip">
                                    <h1>Cougar</h1>
                                </div>
                            </li>

                            <li id="second" class="secondanimation">
                                <a href="#">
                                    <c:url var="imgUrl2" value='/images/img_2.jpg'></c:url>
                                    <img src="${imgUrl1}" alt="Lions"/>
                                </a>
                                <div class="tooltip">
                                    <h1>Lions</h1>
                                </div>
                            </li>

                            <li id="third" class="thirdanimation">
                                <a href="#">
                                    <c:url var="imgUrl3" value='/images/img_3.jpg'></c:url>
                                    <img src="${imgUrl3}" alt="Snowalker"/>
                                </a>
                                <div class="tooltip">
                                    <h1>Snowalker</h1>
                                </div>
                            </li>

                            <li id="fourth" class="fourthanimation">
                                <a href="#">
                                    <c:url var="imgUrl4" value='/images/img_4.jpg'></c:url>
                                    <img src="${imgUrl4}" alt="Howling"/>
                                </a>
                                <div class="tooltip">
                                    <h1>Howling</h1>
                                </div>
                            </li>

                            <li id="fifth" class="fifthanimation">
                                <a href="#">
                                    <c:url var="imgUrl5" value='/images/img_5.jpg'></c:url>
                                    <img src="${imgUrl5}" alt="Sunbathing"/>
                                </a>
                                <div class="tooltip">
                                    <h1>Sunbathing</h1>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <div class="progress-bar"></div>
                </div>
            </div>
        </div>
    </div>
    <div id="navigation">
        <c:url value="/j_spring_security_logout" var="logoutUrl" />
        <!-- csrt for log out-->
        <form action="${logoutUrl}" method="post" id="logoutForm">
            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}" />
        </form>

        <script>
            function formSubmit() {
                document.getElementById("logoutForm").submit();
            }
        </script>
        <ul>
            <sec:authorize ifAnyGranted="ROLE_ADMIN"></sec:authorize>
            <li><a href="<c:url value='/main' />">Home</a></li>
            <li><a href="<c:url value='/' />">About</a></li>
            <li><a href="<c:url value='/' />">Services</a></li>
            <li><a href="<c:url value='/view-products' />">View Products</a></li>
            <li><a href="<c:url value='/' />">Contact us</a></li>
                <c:if test="${pageContext.request.userPrincipal.name != null}">
                <li> <a href="javascript:formSubmit()"> Logout</a> </li>
                </c:if>

            <c:if test="${pageContext.request.userPrincipal.name == null}">
                <li> <a href="<c:url value='/login' />"> Login</a> </li>
                </c:if>
        </ul>
    </div>
</html>
