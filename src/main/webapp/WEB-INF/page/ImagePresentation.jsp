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
            <c:forEach var="photo" items="${stringImages}">
                <td>
                    <img src="data:photo/jpg;base64,${photo}" width="250" height="200">
                </td>
            </c:forEach>
        </tr>
    </table>
</body>
</html>
