<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Checkout</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<div class="col-md-12 mb-3">

    <div class="bg-dark p-2">
        <h1 class="text-white">Checkout</h1>
    </div>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">

        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/dashboard">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/products">Products</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/cart">View Cart</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/user/address">My Addresses</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/logout">Logout</a>
                </li>
            </ul>
        </div>
    </nav>
</div>
<div class="col-md-4">

    <c:if test="${addresses.size() == 0}">
        <div class="alert alert-warning col-md-2" role="alert">
            No saved addresses
        </div>
    </c:if>

    <c:if test="${addresses.size() > 0}">
        <form action="/checkout/address" method="post">
            <c:forEach items="${addresses}" var="address">
                <label for="${address.addressName}">${address.addressName} </label>
                <input type="radio" id="${address.addressName}" name="selected_address" value="${address.id}">
            </c:forEach>
            <button type="submit" class="btn btn-primary">Select Address</button>
        </form>

    </c:if>


    <form method="POST" action="/checkout">
        <label for="addressName">Address Name</label>
        <input type="text" class="form-control" id="addressName" name="addressName"
               <c:if test="${selected_address != null}">value="${selected_address.addressName}" </c:if>>

        <label for="city">City</label>
        <input type="text" class="form-control" id="city" name="city"
               <c:if test="${selected_address != null}">value="${selected_address.city}" </c:if>/>

        <label for="country">Country</label>
        <input type="text" class="form-control" id="country" name="country"
               <c:if test="${selected_address != null}">value="${selected_address.country}" </c:if>/>
        <label for="postalCode">Postal Code</label>
        <input type="text" class="form-control" id="postalCode" name="postalCode"
               <c:if test="${selected_address != null}">value="${selected_address.formattedPostalCode()}" </c:if>/>
        <label for="address">Address Line</label>
        <input type="text" class="form-control" id="address" name="address"
               <c:if test="${selected_address != null}">value="${selected_address.address}" </c:if>/>

        <label for="update_address">Update Address</label>
        <input type="checkbox" class="form-control" id="update_address" name="updateAddress" value="true">

        <button type="submit" class="btn btn-primary">Order</button>
    </form>
</div>

</body>
</html>
