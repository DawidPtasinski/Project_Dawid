<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Order details</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>

<div class="col-md-12 mb-3">
    <div class="bg-dark p-2">
        <h1 class="text-white">Order details</h1>
    </div>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">

        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/admin">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/admin/products">Products</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/admin/addProduct">Add product</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/admin/orders">Orders</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/logout">Logout</a>
                </li>
            </ul>
        </div>
    </nav>
</div>


<div class="col-md-10">
    <div class="row">
        <div class="col-md-4">
            <div class="mb-3 row">
                <label for="orderId" class="col-sm-2 col-form-label">Order Id</label>
                <div class="col-sm-10">
                    <input type="text" readonly disabled class="form-control-plaintext" id="orderId"
                           value="${order.id}">
                </div>
            </div>
            <div class="mb-3 row">
                <label for="userId" class="col-sm-2 col-form-label">User name</label>
                <div class="col-sm-10">
                    <input type="text" readonly disabled class="form-control-plaintext" id="userId"
                           value="${order.cart.user.username}">
                </div>
            </div>
            <div class="mb-3 row">
                <label for="orderDate" class="col-sm-2 col-form-label">Order date</label>
                <div class="col-sm-10">
                    <input type="text" readonly disabled class="form-control-plaintext" id="orderDate"
                           value="${order.orderedDate()}">
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="mb-3 row">
                <label for="addressName" class="col-sm-2 col-form-label border-1">Address name</label>
                <div class="col-sm-10">
                    <input type="text" readonly disabled class="form-control-plaintext border-1" id="addressName"
                           value="${order.address.addressName}">
                </div>
            </div>
            <div class="mb-3 row">
                <label for="country" class="col-sm-2 col-form-label">Country</label>
                <div class="col-sm-10">
                    <input type="text" readonly disabled class="form-control-plaintext" id="country"
                           value="${order.address.country}">
                </div>
            </div>
            <div class="mb-3 row">
                <label for="city" class="col-sm-2 col-form-label">City</label>
                <div class="col-sm-10">
                    <input type="text" readonly disabled class="form-control-plaintext" id="city"
                           value="${order.address.city}">
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="mb-3 row">
                <div class="col-sm-10">
                </div>
            </div>

            <div class="mb-3 row">
                <label for="postalCode" class="col-sm-2 col-form-label border-1">Postal Code</label>
                <div class="col-sm-10">
                    <input type="text" readonly disabled class="form-control-plaintext border-1" id="postalCode"
                           value="${order.address.postalCode}">
                </div>
            </div>
            <div class="mb-3 row">
                <label for="address" class="col-sm-2 col-form-label">Address</label>
                <div class="col-sm-10">
                    <input type="text" readonly disabled class="form-control-plaintext" id="address"
                           value="${order.address.address}">
                </div>
            </div>
        </div>
    </div>

    <div class="row">

        <table class="table table-striped table-bordered table-hover table-sm">
            <thead class="thead-dark">
            <th>Product Name</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Sum</th>
            </thead>
            <tbody>
            <c:forEach items="${order.cart.cartItems}" var="cartItem">
                <tr>
                    <td>${cartItem.product.name}</td>
                    <td>${cartItem.product.price}</td>
                    <td>${cartItem.quantity}</td>
                    <td>${cartItem.sum()}</td>
                </tr>

            </c:forEach>
            <tr class="table-success">
                <td><b>Total Sum</b></td>
                <td><b><c:out value="${order.cart.sum()}" default="0.00"/></b></td>
                <td></td>
                <td></td>
            </tr>
            </tbody>

        </table>
    </div>
</div>


</body>
</html>
