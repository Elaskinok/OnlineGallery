<%--
  Created by IntelliJ IDEA.
  User: Yauheni
  Date: 23.03.2020
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Save image</title>
    <link href="../../style/ImageLoader.css" type="text/css" rel="stylesheet">
</head>
<body>
<form method="post" action="/drag-image" enctype="multipart/form-data"><%--
    <img id="upload-image" src="data:image/jpg;base64,${dragPicture}" alt="picture" width="200" height="200">--%>
    <input id="file-input" type="file" name="file" multiple>
    <input type="submit" value="Save">
</form>
</body>
</html>
