<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome Page</title>
</head>
<body>
    <h1>Online Gallery</h1>
    <form id="save_page_button" action="/save-photo" method="get">
        <input type="submit" value="Save photo">
    </form>
    <form id="show_page_button" action="/show-all-photos" method="get">
        <input type="submit" value="show all photos">
    </form>
</body>
</html>
