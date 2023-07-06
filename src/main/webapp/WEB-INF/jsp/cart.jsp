<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Cart</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>

<div class="col-md-12 mb-3">
    <div class="bg-dark p-2">
        <h1 class="text-white">Cart</h1>
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
                <li class="nav-item active">
                    <a class="nav-link" href="/cart">View Cart<span class="sr-only">(current)</span></a>
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
<table class="table table-striped table-bordered table-hover table-sm">
    <thead class="thead-dark">
        <th>Product Name</th>
        <th>Price</th>
        <th>Quantity</th>
    </thead>
<tbody>
<c:forEach items="${cartItems}" var="cartItem">
    <tr>
        <td>${cartItem.product.name}</td>
        <td>${cartItem.product.price}</td>
        <td>
            <form method="post" action="/cart/updateQuantity">
                <input type="hidden" value="${cartItem.product.id}" id="productId" name="productId">
                <div class="row">
                    <div class="col col-lg-3">
                        <input type="number" class="form-control" value="${cartItem.quantity}" step="1" min="0"
                               id="quantity" name="quantity">
                    </div>
                    <div class="col col-lg-2">
                        <button type="submit" class="btn btn-outline-primary">Update quantity</button>
                    </div>

                </div>
            </form>

        </td>
    </tr>

</c:forEach>
<tr class="table-success">
<td><b>Total Sum</b></td>
<td><b><c:out value="${sum}" default="0.00"/></b></td>
    <td></td>
    </tr>
    </tbody>

    </table>

    <div class="col-md-4">
    <div class="row justify-content-md-center">
    <a href="/checkout" class="btn btn-primary">Order</a>
    </div>
    </div>

    </div>

    </body>
    </html>
