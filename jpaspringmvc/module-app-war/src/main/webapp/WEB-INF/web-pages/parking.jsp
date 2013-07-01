<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         session="true" pageEncoding="ISO-8859-1"%>
<!--==-->
<!DOCTYPE html>
<!--==-->
<%@page import="com.hanaden.demo.jpaspringmvc.domain.CarVo" %>
<!--==-->
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>--%>

<!--
ISSUES WITH JSPX
http://stackoverflow.com/questions/2058649/jspx-script-element-on-glassfish-v3
http://stackoverflow.com/questions/4639643/how-to-produce-valid-html-with-jspx-not-xhtml
-->

<!--    Document : 
    Created on : Aug 20, 2012, 11:36:57 PM
    Author     : hanasaki-->

<html>
    <head>

        <title>Parking Lot Management-</title>
        <script type="text/javascript" src="<c:url value='/web-resources/js/jquery-1.8.1.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/web-resources/js/jquery.form-3.15.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/web-resources/js/parking.js'/>"></script>

        <style type="text/css">
            .error {
                color: #ff0000;
            }

            .errorblock {
                color: #000;
                background-color: #ffEEEE;
                border: 3px solid #ff0000;
                padding: 8px;
                margin: 16px;
            }
        </style>

    </head>

    <body>
        <c:set var="req" value="${pageContext.request}" />
        <c:set var="url">${req.requestURL}</c:set>
        <c:set var="uri">${req.requestURI}</c:set>
            <h1>Parking Lot</h1>

        <form:form method="POST" id="newCarForm" action="#" commandName="carVo"
                   onsubmit="return false">
            <form:errors path="*" cssClass="errorblock" element="div" />
            <table>
                <tr>
                    <td>Car Manufacturer :</td>
                    <td><form:input path="manufacturer" /></td>
                    <td><form:errors path="manufacturer" cssClass="error" /></td>
                </tr>
                <tr>
                    <td>Car Model :</td>
                    <td><form:input path="model" /></td>
                    <td><form:errors path="model" cssClass="error" /></td>
                </tr>
                <tr>
                    <td>Car Year :</td>
                    <td><form:input path="year" /></td>
                    <td><form:errors path="year" cssClass="error" /></td>
                </tr>
                <tr>
                    <td colspan="3">
                        <input type="submit" onclick="parkCar();" value="Park Car"/>
                    </td>
                </tr>
            </table>
        </form:form>

        <button id='buttonGetParkedCars'>getparkedcars</button>

        <div id="divCarList"></div>
    </body>

</html>