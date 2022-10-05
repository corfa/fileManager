<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8" />
  <title>User Info</title>
</head>
<body>

<hr>

<hr>

<h1>${currentPath}</h1>
<h1>${date}</h1>
<hr>

<c:forEach var="directory" items="${directories}">
  <a href="?path=${directory.getPath().replace("\\", "/")}">${directory.getName()}/</a><br>
</c:forEach>
<c:forEach var="file" items="${files}">
  <a href="?path=${file.getPath().replace("\\", "/")}">${file.getName()}</a><br>
</c:forEach>
</body>
</html>