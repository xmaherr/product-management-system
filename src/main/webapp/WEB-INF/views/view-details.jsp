<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Product Details</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-2">
    <div class="row justify-content-center">
        <div class="col-md-8"> <!-- This will set the column width to 8 out of 12 (about 2/3 of the page) -->
            <div class="card shadow">
                <div class="card-body">
                    <h2 class="text-primary text-center">Product Details</h2>
                    <c:if test="${not empty product}">
                        <table class="table table-bordered">
                            <tr>
                                <th>ID</th>
                                <td>${product.id}</td>
                            </tr>
                            <tr>
                                <th>Name</th>
                                <td>${product.name}</td>
                            </tr>
                            <tr>
                                <th>Manufacturer</th>
                                <td>${product.details.manufacturerModel}</td>
                            </tr>
                            <tr>
                                <th>Price</th>
                                <td>${product.details.price}</td>
                            </tr>
                            <tr>
                                <th>Available</th>
                                <td>${product.details.available ? 'Yes' : 'No'}</td>
                            </tr>
                            <tr>
                                <th>Expiry Date</th>
                                <td>${product.details.expiryDate}</td>
                            </tr>
                        </table>
                    </c:if>
                    <c:if test="${empty product}">
                        <p class="text-danger text-center">No product details available.</p>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
