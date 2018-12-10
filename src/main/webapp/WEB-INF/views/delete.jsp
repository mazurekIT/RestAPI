<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BooksDelete</title>
</head>
<body>
Lista książek:<br>
<c:forEach items="${list}" var="book">
    <a href="/books/del/${book.id}">książka id ${book.id}</a><br>
</c:forEach>

</body>
</html>
