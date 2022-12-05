<%--
  Created by IntelliJ IDEA.
  User: ivanw
  Date: 07.10.2022
  Time: 22:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>registration</title>
</head>
<body>
<h1>registration</h1>
<form action="reg" method="POST">
    UserName: <input type="text" name="username" id="username">
    <br />
    Password: <input type="text" name="password" id ="password"/>
    <br />
    Email: <input type="text" name="email" id ="email"/>
    <input type="submit" value="Submit" />
</form>
</body>
</html>
