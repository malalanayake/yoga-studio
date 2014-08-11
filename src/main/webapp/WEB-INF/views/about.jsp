<%-- 
    Document   : about
    Created on : Aug 11, 2014, 10:15:22 AM
    Author     : jCalles
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
        <title>About</title>
    </head>
    <body>
        
        
        <div id="container">
            <%@include file="template/header.jsp"%>
            <div id="content-container">
                <%@include file="template/sidebar.jsp"%>
                <div id="content">
                    <h2>
                        About Us
                    </h2>

                    <br>
                    <p align="justify">Lotus Yoga Studio © is a unique yoga studio with the goal of recapturing what we consider to be the essence of yoga… simply put, yoga made available to everyone.In a time where yoga as a business is getting a lot of attention, the fact that it is being priced out of many people’s reach is in direct conflict with what we consider to be the spirit of yoga itself. The question our studio seeks to answer is: Can a yoga studio maintain itself as a business while keeping the focus of its intention on providing yoga as a service first and foremost?</p>
                    </br>

                    <br>
                    <p align="justify"> We’ve heard from people all over the country that they simply can’t afford to do yoga on a regular basis. Yoga is an amazing form of exercise that helps strengthen, stretch and de-stress the body, and focus and decompress the mind. We want to make it possible for everyone to do yoga regardless of economic limitations. Yoga is meant to help strengthen and stretch your arms and legs, not cost you one!
                        Who does this yoga? Everyone. Really! The beauty of this yoga is that it is challenging enough for all, yet not too extreme and therefore accessible to everyone. The ages of our students have ranged from 5 to 80. Yoga has many benefits: </p>
                    </br>

                    <br>
                    <p align="justify">
                        
                        ·	People do yoga because it helps them look and feel great. <br>
                    <br>
                    <p align="justify">  
                        ·	Yoga helps tone and sculpt muscles while gaining strength, flexibility and balance. <br>
                    <br>
                    <p align="justify">     
                        ·	In a world of stress, yoga helps people decompress and achieve a sense of inner peace, aiding in healing injury or disease.</p>
                    <br>

                </div>
                <!--<%@include file="template/right-side.jsp"%>-->
                <%@include file="template/footer.jsp"%>
            </div>
        </div>
    </body>
</html>
