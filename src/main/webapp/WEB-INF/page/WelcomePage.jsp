<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome Page</title>
</head>
<body>
    <h1>Online Gallery</h1>
    <form id="save_page_button" action="/save-image" method="get">
        <input type="submit" value="Save image">
    </form>
    <form id="show_page_button" action="/show-all-images" method="get">
        <input type="submit" value="show all images">
    </form>
</body>
</html>
