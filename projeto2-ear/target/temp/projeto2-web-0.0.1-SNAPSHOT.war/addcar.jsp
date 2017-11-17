<%--
  Created by IntelliJ IDEA.
  User: jorgearaujo
  Date: 15/11/2017
  Time: 22:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file = "header.jsp" %>
<body>
<%@ include file = "navbar.jsp" %>

<form action="addCar" method="post" enctype="multipart/form-data">
    <input class="input" type="text" name="brand" placeholder="Brand">
    <input class="input" type="text" name="model" placeholder="Model">
    <input class="input" type="text" name="mileage" placeholder="Mileage">
    <input class="input" type="text" name="year" placeholder="Year">
    <input class="input" type="text" name="month" placeholder="Month">
    <input class="input" type="text" name="price" placeholder="Price">
    <input class="input" type="file" name="photo" placeholder="Photo">
    <button type="submit">
        <span>Create new ad</span>
    </button>
    ${notification}
</form>
<%@ include file = "footer.jsp" %>
</body>
</html>
