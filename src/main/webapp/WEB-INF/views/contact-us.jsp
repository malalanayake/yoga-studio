<%-- 
    Document   : contact-us
    Created on : Aug 11, 2014, 11:27:17 AM
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

        <title>Contact Us</title>
    </head>
    <body>
        <div id="container">
            <%@include file="template/header.jsp"%>
            <div id="content-container">
                <%@include file="template/sidebar.jsp"%>
                <div id="content">
                    <h2>Contact Us</h2>

                    <br>
                    <p align="justify"> <strong> Do you have any question? Please Call Us! </strong>  </p>
                    </br>

                    <br>
                    <p align="justify"> Telephones: (641) - 7776321 , (312)-2867970 </p>
                    </br> 

                    <br>
                    <p align="justify"> <strong>  You can find us in our social networks </strong>   </p>
                    </br>

                    <br>
                    <c:url var="imgTwitter" value='/images/twitter.png'></c:url>
                    <p align="justify"> <img src="${imgTwitter}" alt="twitter" width="42" height="42">  @lotusyogastudio</p>
                    </br> 
                    
                    <br>
                    <c:url var="imgFacebook" value='/images/facebook.png'></c:url>
                    <p align="justify"> <img src="${imgFacebook}" alt="facebook" width="42" height="42">    lotusyogastudio</p>
                    </br> 
                    
                    

                    <br>
                    <p align="justify"> <strong> You can find our location on Google Maps, 1000 N, 4th Street, Fairfield, IA. 52557</strong>  </p>
                    </br>

                    <style>
                        #map_canvas {
                            width: 500px;
                            height: 400px;
                        }

                    </style>
                    <script src="https://maps.googleapis.com/maps/api/js"></script>
                    <script>
                        function initialize() {
                            var map_canvas = document.getElementById('map_canvas');
                            var map_options = {
                                center: new google.maps.LatLng(41.018227, -91.967328),
                                zoom: 8,
                                mapTypeId: google.maps.MapTypeId.ROADMAP
                            }
                            var map = new google.maps.Map(map_canvas, map_options);
                        }
                        google.maps.event.addDomListener(window, 'load', initialize);
                    </script>
                    <div id="map_canvas"></div>
                </div>
                <!--<%@include file="template/right-side.jsp"%>-->
                <%@include file="template/footer.jsp"%>
            </div>
        </div>
        
    </body>
</html>
