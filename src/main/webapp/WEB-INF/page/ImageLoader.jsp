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
    <link rel="stylesheet" href="../../style/ImageLoader.css">
    <script type="text/javascript" src="../../js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="../../js/script.js"></script>
</head>
<body>
<form id="upload-container" method="POST" action="/drag-image">
    <img id="upload-image" src="data:image/jpg;base64,${dragPicture}" alt="picture" width="200" height="200">
    <div>
        <input id="file-input" type="file" name="file" multiple>
        <label for="file-input">Choose file</label>
<%--        <span>you can drag it here</span>--%>
    </div>
</form>
</body>
</html>
