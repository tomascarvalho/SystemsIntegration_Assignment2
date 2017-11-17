<%--
  Created by IntelliJ IDEA.
  User: tomas
  Date: 17/11/2017
  Time: 00:15
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file = "header.jsp" %>
<html>
<body>
<%@ include file = "navbar.jsp" %>

<!-- Page Content -->
<div class="container">

    <!-- Page Heading -->
    <h1 class="my-4">Search Result</h1>

    <div class="row">
        <c:forEach items="${cars}" var="car">
            <div class="col-lg-4 col-sm-6 portfolio-item">
                <div class="card h-100">
                    <a href="car?id=${car.id}"><img class="card-img-top" src="http://placehold.it/700x400" alt="Car description"></a>
                    <div class="card-body">
                        <h4 class="card-title">
                            <a href="car?id=${car.id}">${car.brand} ${car.model}</a>
                            <br>${car.price}€
                        </h4>
                        <p class="card-text">Brand: ${car.brand}</p>
                        <p class="card-text">Model: ${car.model}</p>
                        <p class="card-text">Mileage: ${car.mileage}km</p>
                        <p class="card-text">Year: ${car.year}</p>
                        <p class="card-text">Month: ${car.month}</p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <!-- /.row -->
</div>
<%@ include file = "footer.jsp" %>

</body>
</html>
