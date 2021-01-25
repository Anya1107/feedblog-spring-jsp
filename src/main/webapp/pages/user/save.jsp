<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

    <title>Save user</title>
</head>
<body>
<jsp:include page="../_header.jsp"/>
<div class="container">
    <form action="/user/save" method="post">
        <div class="form-group">
            <label for="exampleFormControlInput1">User name</label>
            <input name="username" type="text" class="form-control" id="exampleFormControlInput1">
        </div>
        <div class="form-group">
            <label for="exampleFormControlInput1">Password</label>
            <input name="password" type="password" class="form-control" id="exampleFormControlInput2">
        </div>
        <div class="form-group">
            <label for="exampleFormControlInput1">Full name</label>
            <input name="fullName" type="text" class="form-control" id="exampleFormControlInput4">
        </div>
        <div class="form-group">
            <label for="exampleFormControlInput1">Age</label>
            <input name="age" type="number" class="form-control" id="exampleFormControlInput3">
        </div>
        <button type="submit" class="btn btn-primary">Save</button>
    </form>
    ${message}
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
</body>
</html>
