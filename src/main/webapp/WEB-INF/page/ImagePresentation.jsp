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

    <c:forEach var="image" items="${images}">
        <img src="data:image/jpg;base64,${image.imageBytes}" alt="${image.name}">
    </c:forEach>

    <img src="data:image/jpg;base64,${Photo}" alt="photo">
</body>
</html>
