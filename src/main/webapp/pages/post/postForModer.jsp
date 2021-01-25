<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

    <title>Info for moder</title>
</head>
<body>
<jsp:include page="../_header.jsp"/>
<ul>
    <li>Title: ${post.title}</li>
    <li>Description: ${post.description}</li>
    <li>Category: ${post.category.title}</li>
    <li>Tag: ${post.tag.name}</li>
</ul>
<form action="/post/postForModer/${post.id}" method="post">
    <button>Add</button>
</form>
<form action="/post/deletePostByModer/${post.id}" method="post">
    <button>Delete</button>
</form>
<form action="/comment/saveModerComment/${post.id}" method="post">
    <label for="exampleFormControlInput1">Comment</label>
    <input name="comment" type="text" class="form-control" id="exampleFormControlInput1">
    <button>Add</button>
</form>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
        crossorigin="anonymous"></script>
</body>
</html>