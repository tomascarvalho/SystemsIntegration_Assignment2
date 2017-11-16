<%--
  Created by IntelliJ IDEA.
  User: tomas
  Date: 16/11/2017
  Time: 00:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file = "header.jsp" %>

<body>

<%@ include file = "navbar.jsp" %>


<form action="edit_profile" method="post">
        <input class="input" type="email" name="email" placeholder="Email">
        <input class="input" type="password" name="password" placeholder="Password">
        <input class="input" type="password" name="newPassword" placeholder="New Password" minlength="6">
        <input class="input" type="password" name="confirmPassword" placeholder="Confirm Password" minlength="6">
        <input class="input" type="text" name="firstName" placeholder="First Name">
        <input class="input" type="text" name="lastName" placeholder="Last Name">
        <button type="submit">
            <span>Edit</span>
        </button>
    </form>

</body>
</html>
