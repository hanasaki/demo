<jsp:root 
    xmlns:jsp="http://java.sun.com/JSP/Page"
    xmlns="http://www.w3.org/1999/xhtml"
    xmlns:c="http://java.sun.com/jsp/jstl/core"
    xmlns:fn="http://java.sun.com/jsp/jstl/functions"
    xmlns:form="http://www.springframework.org/tags/form"
    version="2.0">
    <!--==-->
    <jsp:directive.page language="java" contentType="text/html" pageEncoding="ISO-8859-1" />
    <jsp:output omit-xml-declaration="true" />

    <!-- USE HTML5 -->
    <jsp:output doctype-root-element="HTML"
                doctype-system="about:legacy-compat" />
    <!--    
    <jsp:output doctype-root-element="HTML" COMMENTED OUT using above HTML5 
                doctype-public="-//W3C//DTD XHTML 1.0 Strict//EN"
                doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml-strict.dtd" />
    -->
    <!--ref:
    http://docs.oracle.com/javaee/1.4/tutorial/doc/JSPX3.html
    http://java.boot.by/wcd-guide/ch06s03.html
    http://www.toly.nl/mmbase/1896/HTML5_jspx_template
    -->

    <!--==-->
    <jsp:directive.page import="com.hanaden.demo.jpaspringmvc.domain.CarVo" />
    <jsp:directive.page import="javax.servlet.ServletContext" />

    <!--
        <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
        <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
    
        <!DOCTYPE html>-->
    <!--    Document : 
        Created on : Aug 20, 2012, 11:36:57 PM
        Author     : hanasaki-->

    <html lang="en">
        <head>
            <script type="text/javascript">
                var CONTEXT_ROOT = "${pageContext.request.contextPath}";
            </script>  
            <!--        <script type="text/javascript">
                        var CONTEXT_ROOT = '<%= request.getContextPath()%>';
                    </script>-->
                    <!--<base href= ${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/--> 
            <title>Parking Lot Management : root= ${pageContext.request.contextPath}</title>

            <!--<script type='text/javascript' src='${pageContext.request.contextPath}/resources/dojo/dojo.js'></script>-->


            <!--<script type="text/javascript" src='${pageContext.request.contextPath}/web-resources/js/jquery-1.8.1.js'></script>-->

            <script type="text/javascript" src='${pageContext.request.contextPath}/web-resources/js/jquery-1.8.1.js'></script>
            <script type="text/javascript" src='${pageContext.request.contextPath}/web-resources/js/jquery.form-3.15.js'></script>
            <script type="text/javascript" src='${pageContext.request.contextPath}/web-resources/js/functions.js'></script>
            <!--
                        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" type="text/css" />-->
            <!--form submit ref http://be.twixt.us/jquery/formSubmission.php-->
            <!--http://www.malsup.com/jquery/form/-->
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

</jsp:root>