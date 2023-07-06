<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>
<div class="col-md-12 mb-3">

    <div class="bg-dark p-2">
        <h1 class="text-white">Registration</h1>
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
                <li class="nav-item ">
                    <a class="nav-link" href="/login">Login</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="/register">Register</a>
                </li>
            </ul>
        </div>
    </nav>
</div>
<div class="col-md-2">
    <form method="POST" action="/register">
        <label for="username">Username:</label>
        <input type="text" name="username" id="username" required class="form-control"><br>
        <c:if test="${usernameExists}">
            <p style="color: red">Username exists</p>
        </c:if>

        <label for="password">Password:</label>
        <input type="password" name="password" id="password" required class="form-control"><br>

        <input type="submit" class="btn btn-primary" value="Register">
    </form>

</div>
</body>
</html>
