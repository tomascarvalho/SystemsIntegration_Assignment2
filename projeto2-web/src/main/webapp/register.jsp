<%--
  Created by IntelliJ IDEA.
  User: jorgearaujo
  Date: 13/11/2017
  Time: 22:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file = "header.jsp" %>
<body>
<form action="register" method="post">
        <input class="input" type="email" name="email" placeholder="Email">
        <input class="input" type="password" name="password" minlength="6" placeholder="Password">
        <button type="submit">
            <span>Register</span>
        </button>
</form>
<%@ include file = "footer.jsp" %>
</body>
</html>
