<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Product</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-8"> <!-- This will make the form take up about 2/3 of the page width -->
            <div class="card shadow">
                <div class="card-body">
                    <h2 class="text-center text-primary">Add New Product</h2>
                    <form:form method="post" modelAttribute="product" action="add-product" enctype="multipart/form-data">
                        <div class="mb-3">
                            <label for="name" class="form-label">Product Name</label>
                            <form:input path="name" id="name" type="text" cssClass="form-control" placeholder="Enter product name" />
                            <form:errors path="name" cssClass="text-danger" />
                        </div>
                        <div class="mb-3">
                            <label for="price" class="form-label">Product Price</label>
                            <form:input path="details.price" id="price" type="number" cssClass="form-control" placeholder="Enter product price" step="0.01" />
                            <form:errors path="details.price" cssClass="text-danger" />
                        </div>
                        <div class="mb-3">
                            <label for="available" class="form-label">Available</label>
                            <form:checkbox id="available" path="details.available" cssClass="form-check-input" />
                            <form:errors path="details.available" cssClass="text-danger" />
                        </div>
                        <div class="mb-3">
                            <label for="manufacturer" class="form-label">Manufacturer</label>
                            <form:input path="details.manufacturerModel" id="manufacturer" type="text" cssClass="form-control" placeholder="Enter manufacturer name" />
                            <form:errors path="details.manufacturerModel" cssClass="text-danger" />
                        </div>
                        <div class="mb-3">
                            <label for="expiryDate" class="form-label">Expiry Date</label>
                            <form:input path="details.expiryDate" id="expiryDate" type="date" cssClass="form-control" />
                            <form:errors path="details.expiryDate" cssClass="text-danger" />
                        </div>
                        <div class="mb-3">
                            <label for="formFile" class="form-label">Product Image</label>
                            <form:input name="image" class="form-control" type="file" id="formFile" path="details.imageFile"/>
                        </div>
                        <button type="submit" class="btn btn-primary w-100">Add Product</button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
