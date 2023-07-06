<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>
<div class="col-md-12 mb-3">
    <div class="bg-dark p-2">
        <h1 class="text-white">Login</h1>
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
                <li class="nav-item active">
                    <a class="nav-link" href="/login">Login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/register">Register</a>
                </li>
            </ul>
        </div>
    </nav>
</div>
<div class="position-relative">

    <div class="col-md-4 position-absolute top-50 start-50 translate-middle">
        <c:if test="${registrationSuccess != null}">
            <p style="color: #0d580c"> User ${registrationSuccess} registered!</p>
        </c:if>
        <form method="POST" action="login">
            <div class="form-group col-md-4 ">
                <label for="username">Username:</label>
                <input type="text" class="form-control" name="username" id="username" required><br>
            </div>
            <div class="form-group col-md-4">
                <label for="password">Password:</label>
                <input type="password" class="form-control" name="password" id="password" required><br>
            </div>

            <button type="submit" class="btn btn-primary">Login</button>

        </form>
    </div>

</div>

</body>
</html>
