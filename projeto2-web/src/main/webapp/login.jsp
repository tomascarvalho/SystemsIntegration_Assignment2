<%--
  Created by IntelliJ IDEA.
  User: jorgearaujo
  Date: 14/11/2017
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>success</title>
</head>
<body>
<form action="login" method="post">
    <input class="input" type="email" name="email" placeholder="Email">
    <input class="input" type="password" name="password" placeholder="Password">
    <button type="submit">
        <span>Login</span>
    </button>
    ${error}
</form>
</body>
</html>
