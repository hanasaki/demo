<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="com.hanaden.demo.jpaspringmvc.domain.CarVo" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
    <head>
        <title>Parking Lot Management</title>

        <script type="text/javascript" src="<c:url value='/web-resources/js/jquery-1.8.1.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/web-resources/js/jquery.form-3.15.js'/>"></script>

        <script type="text/javascript">
            $(document).ready(function() {
                // bind form using ajaxForm
//                alert('Page: ' + $('title').html() + ' dom loaded!');

                var options = {
                    target:     '#divCarList',
                    url:        'parking/parkCar',
                    dataType:   'json',
                    contentType: 'application/json; charset=utf-8',
                    type:        'post',
                    success:    function() {
                        alert(data.message);
                    },
                    error:    function() {
                        alert('error--');
                    }
                };

                $('#newCarForm').ajaxForm(options);
//                $('#newCarForm').ajaxForm(options);

            });

//            alert('Page: ' + $('title').html() + ' dom loaded! + processing json');
        </script>
    </head>

    <body>

        <h1>Parking Lot</h1>

        <form:form method="POST" id="newCarForm" commandName="carVo">
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


        <div id="divCarList"></div>
    </body>

</html>