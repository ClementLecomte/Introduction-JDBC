<%--
  Created by IntelliJ IDEA.
  User: clement
  Date: 19/07/19
  Time: 10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Tests JDBC</title>
    <link type="text/css" rel="stylesheet"  />
</head>
<body>
<h1>Tests JDBC</h1>
<ul>
    <c:forEach items="${ jedis }" var="jedi" varStatus="boucle">
        <li>${ boucle.count }. ${ jedi }</li>
</c:forEach>
</ul>
</body>
</html>
