<%--
  Created by IntelliJ IDEA.
  User: Yauheni
  Date: 23.03.2020
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Presentation</title>
</head>
<body>
    <h1>Image presentation</h1>

    <table id="image_table">
        <tr>
            <c:forEach var="image" items="${images}">
                <td>
                    <img src="data:image/jpg;base64,${image}" width="250" height="200">
                </td>
            </c:forEach>
        </tr>
    </table>
</body>
</html>
