<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Update Product</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-8"> <!-- This will make the form take up about 2/3 of the page width -->
            <div class="card shadow">
                <div class="card-body">
                    <h2 class="text-primary text-center">Update Product</h2>
                    <form:form modelAttribute="product" action="delete-image" method="post" class="mt-2">
                        <input type="hidden" name="productId" value="${product.id}" />
                        <div class="mb-3">
                            <c:if test="${not empty product.details.image}">
                                <p class="mt-2">Current Image:</p>
                                <img src="data:image/jpeg;base64,${image}" alt="Product Image" style="width: 150px; height: auto; border-radius: 10px; box-shadow: 0px 4px 6px rgba(0,0,0,0.1);">
                                <!-- Delete button to call delete-image method -->
                                <input type="hidden" name="id" value="${product.details.productDetailsModelId}" />
                                <button type="submit" class="btn btn-danger btn-sm" style="margin-left: 20px">Delete Image</button>

                            </c:if>
                        </div>
                    </form:form>
                    <form:form action="update-product" modelAttribute="product" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="id" value="${product.id}" />
                        <div class="mb-3">
                            <label for="name" class="form-label">Product Name</label>
                            <form:input id="name" path="name" cssClass="form-control" />
                            <form:errors path="name" cssClass="text-danger" />
                        </div>
                        <div class="mb-3">
                            <label for="manufacturer" class="form-label">Manufacturer</label>
                            <form:input id="manufacturer" path="details.manufacturerModel" cssClass="form-control" />
                            <form:errors path="details.manufacturerModel" cssClass="text-danger" />
                        </div>
                        <div class="mb-3">
                            <label for="price" class="form-label">Price</label>
                            <form:input id="price" path="details.price" cssClass="form-control" type="number" step="0.01" />
                            <form:errors path="details.price" cssClass="text-danger" />
                        </div>
                        <div class="mb-3">
                            <label for="available" class="form-label">Available</label>
                            <form:checkbox id="available" path="details.available" cssClass="form-check-input" />
                            <form:errors path="details.available" cssClass="text-danger" />
                        </div>
                        <div class="mb-3">
                            <label for="expiryDate" class="form-label">Expiry Date</label>
                            <form:input id="expiryDate" path="details.expiryDate" cssClass="form-control" type="date" />
                            <form:errors path="details.expiryDate" cssClass="text-danger" />
                        </div>
                        <div class="mb-3">
                            <label for="formFile" class="form-label">Product Image</label>
                            <form:input name="image" class="form-control" type="file" id="formFile" path="details.imageFile"/>
                        </div>
                        <button type="submit" class="btn btn-primary w-100">Update Product</button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
