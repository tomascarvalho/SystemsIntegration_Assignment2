<%--
  Created by IntelliJ IDEA.
  User: jorgearaujo
  Date: 13/11/2017
  Time: 22:53
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file = "header.jsp" %>
<body>

<div class="container">
    <form class="form-signing" action="register" method="post">
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="email" class="sr-only">Email address</label>
        <input class="form-control" id="email" type="email" name="email" placeholder="Email" required autofocus>
        <label for="password" class="sr-only">Password</label>
        <input class="form-control" id="password" type="password" name="password" placeholder="Password" minlength="6" required>
        <label for="firstName" class="sr-only">First Name</label>
        <input class="form-control" id="firstName" type="text" name="firstName" placeholder="John" minlength="2" required>
        <label for="password" class="sr-only">Last Name</label>
        <input class="form-control" id="lastName" type="text" name="lastName" placeholder="Doe" minlength="2" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
        <p>Already registered? <a href="login.jsp">Please sign in</a></p>
    </form>
    <c:if test="${error.length()>0}">
        <div class="alert alert-warning alert-dismissible fade show" role="alert">
                ${error}
        </div>
    </c:if>

</div>

<%@ include file = "footer.jsp" %>
</body>
</html>
