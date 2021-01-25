<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

    <title>Info</title>
</head>
<body>
<jsp:include page="../_header.jsp"/>
<ul>
    <li>Title: ${post.title}</li>
    <li>Description: ${post.description}</li>
    <li>Category: ${categoryName}</li>
    <li>Tag: ${tagName}</li>
    <li>User: ${user}</li>
        <c:forEach items="${comments}" var="comment">
            <li>${comment}</li>
        </c:forEach>
    <li>Date: ${post.date}</li>
    <li>Count of views: ${post.count}</li>
</ul>

<div class="btn-group">
    <form action="/post/addLike/${post.id}" method="post">
    <button class="btn">Like ${likes}</button>
        <button class="btn dropdown-toggle" data-toggle="dropdown">
            <span class="caret"></span>
        </button>
        <ul class="dropdown-menu">
            <c:forEach items="${likesList}" var="like">
                <li>${like}</li>
            </c:forEach>
        </ul>
    </form>
</div>

<div class="btn-group">
    <form action="/post/addDislike/${post.id}" method="post">
        <button class="btn" >Dislike ${dislikes}</button>
        <button class="btn dropdown-toggle" data-toggle="dropdown">
            <span class="caret"></span>
        </button>
        <ul class="dropdown-menu">
            <c:forEach items="${dislikesList}" var="dislike">
                <li>${dislike}</li>
            </c:forEach>
        </ul>
    </form>
</div>

<c:forEach items="${reactions}" var="react">
    <form action="/post/reaction" method="post">
        <input type="hidden" value="${react.id}" name="reactionId">
        <input type="hidden" value="${post.id}" name="postId">
        <button>${react.reaction} ${countOfReactions}</button>
    </form>
</c:forEach>

<form action="/comment/save" method="post">
    <input type="hidden" value="${post.id}" name="postId">
    <p>
    <input type="text" name="comment" placeholder="Comment">
    </p>
    <p>
    <button>Add comment</button>
    </p>
</form>

<form action="/post/addBookmark/${post.id}" method="post">
    <button>Add to bookmarks</button>
</form>

<form action="/user/follow/${post.id}" method="post">
    <button>Follow this user</button>
</form>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
        crossorigin="anonymous"></script>
</body>
</html>
