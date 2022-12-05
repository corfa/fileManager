<%--
  Created by IntelliJ IDEA.
  User: ivanw
  Date: 08.10.2022
  Time: 0:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>

</head>
<body>
<h1>authorization</h1>
<a href="reg.jsp">registration</a>

<form action="aut" method="POST">
  UserName: <input type="text" name="username" id="username">
  <br />
  Password: <input type="text" name="password" id ="password"/>

  <input type="submit" value="Submit" />
</form>
</body>
</html>
