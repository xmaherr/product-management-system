<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Product Details</title>
</head>
<body>
<h2>Product Details</h2>

<!-- Check if productDetails object exists -->
<c:if test="${not empty productDetails}">
    <table border="1">
        <tr>
            <th>Product ID</th>
            <td>${productDetails.productEntity.id}</td>
        </tr>
        <tr>
            <th>Product Name</th>
            <td>${productDetails.productEntity.name}</td>
        </tr>
        <tr>
            <th>Manufacturer</th>
            <td>${productDetails.manufacturer}</td>
        </tr>
        <tr>
            <th>Price</th>
            <td>${productDetails.price}</td>
        </tr>
        <tr>
            <th>Available</th>
            <td>${productDetails.available}</td>
        </tr>
        <tr>
            <th>Expiry Date</th>
            <td>${productDetails.expiryDate}</td>
        </tr>
    </table>
</c:if>

<!-- If the productDetails object is empty -->
<c:if test="${empty productDetails}">
    <p>No product details available.</p>
</c:if>
</body>
</html>
