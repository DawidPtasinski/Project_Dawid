<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Order success</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<h1>Order success</h1>

<div class="col-md-12 mb-3">

</div>
<div class="col-md-4">
    <c:out value="The order with id = ${order.id} was successfully placed." default="Order not found"/> <br>
    <c:out value="Total amount to pay: ${order.cart.sum()}"/>
    <a class="flex-sm-fill text-sm-center nav-link" href="/dashboard">Back to Home</a>

</div>
</body>
</html>
