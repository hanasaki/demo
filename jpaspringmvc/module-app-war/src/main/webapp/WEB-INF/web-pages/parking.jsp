<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%--<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>--%>
<%@page import="com.hanaden.demo.jpaspringmvc.domain.CarVo" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!--<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">-->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<!--<!DOCTYPE html>-->
<!--    Document : 
    Created on : Aug 20, 2012, 11:36:57 PM
    Author     : hanasaki-->

<html>
    <head>
        <script type="text/javascript">
            var CONTEXT_ROOT = '<%= request.getContextPath()%>';
        </script>
        <!--<base href= ${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/--> 
        <title>Parking Lot Management</title>
        <!--<script type="text/javascript" src="<c:url value="/resources/dojo/dojo.js" />"> </script>-->
        <script type="text/javascript" src="<c:url value='/web-resources/js/jquery-1.8.1.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/web-resources/js/jquery.form-3.15.js'/>"></script>
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

        <script type="text/javascript">
            $(document).ready(function() {
                // bind form using ajaxForm
//                alert('Page: ' + $('title').html() + ' dom loaded!');

                var options = {
                    target:     '#divCarList',
                    url:        'parking/parkCar',
                    dataType:   'json',
                    method:     'post',
                    success:    function() {
                        alert(data.message);
                    },
                    error:    function() {
                        alert('error--');
                    }
                };
                alert('cc');
                $('#newCarForm').ajaxForm(function() {
                    alert("Thank you for your comment!");
                });
//                $('#newCarForm').ajaxForm(options);
                alert('dd');
            });

            alert('Page: ' + $('title').html() + ' dom loaded! + processing json');
        </script>
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
                        <!--<input type="submit" />-->
                        <input type="submit" value="0"/>
                        <!--<input type="submit" onclick="parkCar()" value="1"/>-->
                    </td>
                </tr>
            </table>
        </form:form>

        <script type = "text/javascript" >
            function parkCar() {
                var frm = $('#newCarForm');
                var frmJSON = frm.serializeArray();
//                var data = JSON.serialize(frm);
                var data = frm.serialize(frm); // for http GET not POST

                $.ajax({
                    url: "parking/parkCar",
                    dataType: "json",
                    contentType: "application/json;charset=utf-8",
                    type: frm.attr('method'),
                    //                    data: JSON.stringify(data),
                    data: frm,
                    success: function(msg) {
                        if (msg !== null) {
                            alert("success" + data);
                            //                            return msg.URL;
                        }
                    },
                    error: function(data) {
                        alert("error" + data);
                    }
                });
                return false;
            }
        </script>

        <script type = "text/javascript" >
            function getCarById() {
                var frm = $('#newCarForm');
//                var data = JSON.serialize(frm);
                var data = frm.serialize(frm);

                alert("asdfasfd");

                alert($('#newCarForm').serialize);
                alert("dd" + JSON.stringify(data));
                alert("dd" + JSON.stringify(data));
                $.ajax({
                    url: "getCarById",
                    dataType: "json",
                    contentType: "application/json;charset=utf-8",
                    type: frm.attr('method'),
                    //                    data: JSON.stringify(data),
                    data: frm,
                    success: function(msg) {
                        if (msg !== null) {
                            alert("success" + data);
                            //                            return msg.URL;
                        }
                    },
                    error: function(data) {
                        alert("error" + data);
                    }
                });
                return false;
            }
        </script>

        <script type="text/javascript">
            function displayCars(data) {
                $.each(data.items, function(i, item) {
                    $("<img/>").attr("src", item.media.m).appendTo("#images");
                    if (i === 3)
                        return false;
                });
                //                
                $.each(data, function() {
                    var tbl_row = "";
                    $.each(this, function(k, v) {
                        tbl_row += "<td>" + v + "</td>";
                    })
                    tbl_body += "<tr>" + tbl_row + "</tr>";
                })
                $("#target_table_id tbody").html(tbl_body);
            }
        </script>

        <div id="divCarList"></div>
    </body>

</html>