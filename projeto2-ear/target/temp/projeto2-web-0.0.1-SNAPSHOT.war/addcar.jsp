<%--
  Created by IntelliJ IDEA.
  User: jorgearaujo
  Date: 15/11/2017
  Time: 22:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Car</title>
</head>
<body>
<form action="addCar" method="post">
    <input class="input" type="text" name="brand" placeholder="Brand">
    <input class="input" type="text" name="model" placeholder="Model">
    <input class="input" type="text" name="mileage" placeholder="Mileage">
    <input class="input" type="text" name="year" placeholder="Year">
    <input class="input" type="text" name="month" placeholder="Month">
    <input class="input" type="text" name="price" placeholder="Price">
    <button type="submit">
        <span>Create new ad</span>
    </button>
    ${notification}
</form>
</body>
</html>
