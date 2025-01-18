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
    </tr>
    <c:forEach var="entity" items="${products}">
        <tr>
            <td>${entity.id}</td>
            <td>${entity.name}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
