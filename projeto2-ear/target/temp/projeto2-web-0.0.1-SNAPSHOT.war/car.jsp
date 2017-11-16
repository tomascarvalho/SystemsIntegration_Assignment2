<%--
  Created by IntelliJ IDEA.
  User: tomas
  Date: 16/11/2017
  Time: 20:18
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file = "header.jsp" %>
<body>
<link href="assets/css/portfolio-item.css" rel="stylesheet">
<%@ include file = "navbar.jsp" %>

    <!-- Portfolio Item Row -->
    <div class="row">

        <div class="col-md-8">
            <img class="img-fluid" src="http://placehold.it/750x500" alt="">
        </div>

        <div class="col-md-4">
            <h3 class="my-3">
                ${car.brand} ${car.model}
                <br><small>${car.price}€</small>
            </h3>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam viverra euismod odio, gravida pellentesque urna varius vitae. Sed dui lorem, adipiscing in adipiscing et, interdum nec metus. Mauris ultricies, justo eu convallis placerat, felis enim.</p>
            <h3 class="my-3">Car Details</h3>
            <ul>
                <li>Brand: ${car.brand}</li>
                <li>Model: ${car.model}</li>
                <li>Mileage: ${car.mileage}km</li>
                <li>Year: ${car.year}</li>
                <li>Month: ${car.month}</li>
            </ul>
        </div>

    </div>
<!-- /.row -->

<!-- Related Projects Row -->
<h3 class="my-4">Related Cars</h3>

<div class="row">
    <c:forEach items="${car.getRelatedCars()}" var="related_car">
        <div class="col-md-3 col-sm-6 mb-4">
            <a href="car?id="${related_car.getId()}>
                <img class="img-fluid" src="${related_car.imageUrl}" alt="${related_car.brand} ${related_car.model}">
            </a>
        </div>
    </c:forEach>

</div>
<!-- /.row -->

</div>
<!-- /.container -->

<%@ include file = "footer.jsp" %>
</body>
</html>

