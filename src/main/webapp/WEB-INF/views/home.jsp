<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Entities</title>
</head>
<body>
<h1>Entities List</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>View Details</th>
        <th>Update</th>
        <th>Delete</th>
    </tr>
    <c:forEach var="entity" items="${products}">
        <tr>
            <td>${entity.id}</td>
            <td>${entity.name}</td>
            <td>
                <form action="view-details" method="get">
                    <input type="hidden" name="id" value="${entity.detailsId}" />
                    <button type="submit">View Details</button>
                </form>
            </td>
            <td>
                <form action="updateEntity" method="get">
                    <input type="hidden" name="id" value="${entity.id}" />
                    <button type="submit">Update</button>
                </form>
            </td>
            <td>
                <form action="deleteEntity" method="post">
                    <input type="hidden" name="id" value="${entity.id}" />
                    <button type="submit">Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
