<%--
  Created by IntelliJ IDEA.
  User: tomas
  Date: 17/11/2017
  Time: 23:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form class="container" action="cars" method="get">
    <div class="row">
        <div class="col-md-6 mb-3">
            <label for="validationCustom01">Brand</label>
            <input type="text" class="form-control" id="validationCustom01" placeholder="Brand" name="brand">
        </div>
        <div class="col-md-6 mb-3">
            <label for="validationCustom02">Model</label>
            <input type="text" class="form-control" id="validationCustom02" placeholder="Model" name="model">
        </div>
    </div>
    <div class="row">
        <div class="col-md-3 mb-3">
            <label for="validationCustom03">Price from</label>
            <input type="number" class="form-control" id="validationCustom03" placeholder="From..." name="price_from">

        </div>
        <div class="col-md-3 mb-3">
            <label for="validationCustom04">Price to</label>
            <input type="number" class="form-control" id="validationCustom04" placeholder="To..." name="price_to">

        </div>
        <div class="col-md-3 mb-3">
            <label for="validationCustom05">Mileage from</label>
            <input type="number" class="form-control" id="validationCustom05" placeholder="From..." name=mileage_from">

        </div>
        <div class="col-md-3 mb-3">
            <label for="validationCustom06">Mileage to</label>
            <input type="number" class="form-control" id="validationCustom06" placeholder="To..." name="mileage_to">
        </div>
        <div class="col-md-3 mb-3">
            <label for="validationCustom07">More recent than</label>
            <input type="number" class="form-control" id="validationCustom07" placeholder="Since..." name="year_since">
        </div>
    </div>
    <div class="col-auto">
        <label class="mr-sm-2" for="inlineFormCustomSelect">Search by</label>
        <select class="custom-select mb-2 mr-sm-2 mb-sm-0" id="inlineFormCustomSelect" name="option">
            <option value="1">Brand</option>
            <option value="2">Brand and Model</option>
            <option value="3">Price from... to...</option>
            <option value="4">Mileage from... to...</option>
            <option value="5">Year since...</option>
        </select>
    </div>
    <button class="btn btn-primary" type="submit">Search</button>
</form>