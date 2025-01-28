<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Product Details</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .product-image {
            width: 150px; /* Adjust the width as needed */
            height: auto; /* Maintain aspect ratio */
            border-radius: 10px; /* Optional: Rounded corners */
            box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1); /* Optional: Add shadow */
        }
    </style>
</head>
<body class="bg-light">
<div class="container mt-2">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card shadow position-relative">
                <div class="card-body">
                    <!-- Product Image -->

                    <h2 class="text-primary text-center">Product Details</h2>
                    <c:if test="${not empty product}">
                        <table class="table table-bordered">
                            <tr>
                                <c:if test="${not empty product.details.image}">
                                    <td colspan="2" style='text-align:center; vertical-align:middle'>
                                        <img src="data:image/jpeg;base64,${image}" alt="Product Image" class="product-image">
                                    </td>
                                </c:if>
                                <c:if test="${empty product.details.image}">
                                    <td colspan="2">
                                       NO IMAGE FOR THIS PRODUCT!
                                    </td>
                                </c:if>

                            </tr>
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
