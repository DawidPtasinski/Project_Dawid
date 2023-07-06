<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add product</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>

<div class="col-md-12 mb-3">
    <div class="bg-dark p-2">
        <h1 class="text-white">Add product</h1>
    </div>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">

        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item active">
                    <a class="nav-link" href="/admin">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/admin/products">Products</a>
                </li>
                <li class="nav-item active">
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


<form method="post" action="/admin/addProduct">

    <div class="form-group col-md-4">
        <label for="productName">Product name</label>
        <input type="text" class="form-control" name="productName" id="productName" required>
    </div>
    <br/>
    <div class="form-group col-md-4">
        <label for="price">Price</label>
        <input type="number" class="form-control" name="price" id="price" step="0.01" min="0" required>
    </div>

    <input type="submit" class="btn btn-primary" value="Add product">

</form>
</body>
</html>
