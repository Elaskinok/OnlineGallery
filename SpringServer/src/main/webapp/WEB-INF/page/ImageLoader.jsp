<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Save photo</title>
    <link href="../../style/ImageLoader.css" type="text/css" rel="stylesheet">
</head>
<body>
<div id="upload-container">
    <form method="post" action="/save-photo" enctype="multipart/form-data">
        <input id="file-input" type="file" name="picture" multiple>
        <input type="submit" value="Save">
    </form>
</div>
</body>
</html>
