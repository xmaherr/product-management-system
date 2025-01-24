<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Products</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h1 class="text-primary text-center">Product List</h1>
    <div class="table-responsive">
        <!-- Adjust table width and fix alignment -->
        <table class="table table-striped table-hover table-sm w-auto mx-auto">
            <thead class="table-primary">
            <tr>
                <th class="text-center" style="width: 15%;">ID</th>
                <th class="text-left" style="width: 30%;">Name</th>
                <th class="text-center" style="width: 50%;">Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="product" items="${products}">
                <tr>
                    <td class="text-center">${product.id}</td>
                    <td class="text-left">${product.name}</td>
                    <td>
                        <div class="d-flex justify-content-center gap-5">
                            <a href="view-details?id=${product.id}" class="btn btn-info btn-sm">View Details</a>
                            <a href="update-product?id=${product.id}" class="btn btn-warning btn-sm">Update</a>
                            <form action="delete-product" method="post" class="d-inline">
                                <input type="hidden" name="id" value="${product.id}" />
                                <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                            </form>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="text-center mt-3">
        <a href="add-product" class="btn btn-success">Add Product</a>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
