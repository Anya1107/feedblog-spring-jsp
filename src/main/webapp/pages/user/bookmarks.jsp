<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

    <title>User bookmarks</title>
</head>
<body>
<jsp:include page="../_header.jsp"/>
<div class="card text-center">
    <div class="card-header">
        <ul class="nav nav-tabs card-header-tabs">
            <li class="nav-item">
                <a class="nav-link active" aria-current="true" href="/user/account">Info</a>
            </li>
            <li class="nav-item ">
                <a class="nav-link active" href="/user/userPosts">Posts</a>
            </li>
            <li class="nav-item ">
                <a class="nav-link active" href="/user/bookmarks">Bookmarks</a>
            </li>
            <li class="nav-item ">
                <a class="nav-link active" href="/user/history">History</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" href="/user/subscriptions">Subscriptions</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" href="/user/followers">Followers</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" href="/user/requests">Requests</a>
            </li>
        </ul>
    </div>
    <div class="container">
        <c:forEach items="${bookmarks}" var="bookmark">
            <div class="row justify-content-center m-3">
                <div class="card col-8">
                    <div class="card-body">
                        <h5 class="card-title">${bookmark.post.title}</h5>
                        <p class="card-text">${bookmark.post.description}</p>
                        <a href="/post/${bookmark.id}" class="btn btn-primary">Open</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
</body>
</html>