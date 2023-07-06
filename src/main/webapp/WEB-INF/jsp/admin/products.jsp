<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Products</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>


<div class="col-md-12 mb-3">
    <div class="bg-dark p-2">
        <h1 class="text-white">Products</h1>
    </div>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">

        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item active">
                    <a class="nav-link" href="/admin">Home</a>
                </li>
                <li class="nav-item active">
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

<div class="col-md-6">
    <table class="table table-striped table-bordered table-hover table-sm">
        <thead class="thead-dark">
        <th>Id</th>
        <th>Product Name</th>
        <th>Price</th>
        </thead>
        <tbody>
        <c:forEach items="${products.content}" var="product">
            <tr>
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>${product.price}</td>
            </tr>

        </c:forEach>
        </tbody>

    </table>
    <ul class="pagination">
        <c:if test="${products.pageable.pageNumber > 0}">
            <li class="page-item"><a
                    href="/admin/products?page=${products.pageable.pageNumber-1}&size=${products.pageable.pageSize}"
                    class="page-link ">Previous Page</a></li>

        </c:if>
        <c:if test="${products.pageable.pageNumber < 0}">
            <li class="page-item"><a
                    href="#"
                    class="page-link disabled">Previous Page</a></li>
        </c:if>
        <c:if test="${(products.pageable.pageNumber+1) * products.pageable.pageSize < products.totalElements}">
            <li class="page-item"></li>
            <a href="/admin/products?page=${products.pageable.pageNumber+1}&size=${products.pageable.pageSize}"
               class="page-link">Next Page</a>
        </c:if>
        <c:if test="${(products.pageable.pageNumber+1) * products.pageable.pageSize > products.totalElements}">
            <li class="page-item"></li>
            <a href="$"
               class="page-link disabled">Next Page</a>
        </c:if>
    </ul>


</div>


</body>
</html>
