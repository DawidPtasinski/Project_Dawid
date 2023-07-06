<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>User Addresses</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>
<div class="col-md-12 mb-3">

    <div class="bg-dark p-2">
        <h1 class="text-white">User Addresses</h1>
    </div>

    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">

        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item ">
                    <a class="nav-link" href="/dashboard">Home </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/products">Products</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/cart">View Cart</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="/user/address">My Addresses<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/logout">Logout</a>
                </li>
            </ul>
        </div>
    </nav>
</div>
<div class="container-md">


    <div class="col-md-4">
        <form method="post" action="/user/address">
            <div class="form-group col-md-4">
                <label for="addressName">Address Name</label>
                <input type="text" class="form-control" name="addressName" id="addressName" required>
            </div>
            <div class="form-group col-md-4">
                <label for="country">Country</label>
                <input type="text" class="form-control" name="country" id="country" required>
            </div>
            <div class="form-group col-md-4">
                <label for="city">City</label>
                <input type="text" class="form-control" name="city" id="city" required>
            </div>
            <div class="form-group col-md-4">
                <label for="postalCode">Postal Code</label>
                <input type="text" class="form-control" name="postalCode" id="postalCode" required>
            </div>
            <div class="form-group col-md-4">
                <label for="address">Address</label>
                <input type="text" class="form-control" name="address" id="address" required>
            </div>
            <div class="form-group col-md-4">
                <button type="submit" class="btn btn-primary">Add Address</button>
            </div>
        </form>
    </div>
    <div class="col-md-4">
        <table class="table table-striped table-bordered table-hover table-sm">
            <thead class="thead-dark">
            <th>Address Name</th>
            <th>Country</th>
            <th>City</th>
            <th>Postal Code</th>
            <th>Address</th>
            </thead>
            <tbody>
            <c:forEach items="${addresses}" var="address">
                <tr>
                    <td>${address.addressName}</td>
                    <td>${address.country}</td>
                    <td>${address.city}</td>
                    <td>${address.postalCode}</td>
                    <td>${address.address}</td>
                </tr>
            </c:forEach>
            </tbody>

        </table>

    </div>
</div>

</body>
</html>
