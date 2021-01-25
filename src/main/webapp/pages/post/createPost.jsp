<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

    <title>Create new post</title>
</head>
<body>
    <jsp:include page="../_header.jsp"/>
<div class="container">
    <form action="/post/create" method="post">
        <div class="form-group">
            <label for="exampleFormControlInput1">Title</label>
            <input name="title" type="text" class="form-control" id="exampleFormControlInput1">
        </div>
        <div class="form-group">
            <label for="exampleFormControlTextarea1">Description</label>
            <textarea name="description" class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
        </div>
        <p>
            <label>Tag</label>
            <select name="tagId">
                <c:forEach items="${tags}" var="tag">
                    <option value="${tag.id}">${tag.name}</option>
                </c:forEach>
            </select>
        </p>
        <p>
            <label>Category</label>
            <select name="categoryId">
                <c:forEach items="${categories}" var="category">
                    <option value="${category.id}">${category.title}</option>
                </c:forEach>
            </select>
        </p>
        <p>
        <button type="submit" class="btn btn-primary">Create</button>
        </p>
    </form>
    ${message}
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
</body>
</html>
