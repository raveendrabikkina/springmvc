<%--
  Created by IntelliJ IDEA.
  User: ravi
  Date: 31/8/19
  Time: 6:12 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Welcome to Spring MVC</title>
</head>
<body>
<form action="/adduser" method="post">

    Hi,<br/>
    ${user.firstName}<br/>
    ${user.lastName}<br/>
    ${user.title}<br/>

    <input type="text" name="firstName"/><br/>
    <input type="text" name="lastName"/><br/>
    <input type="text" name="title"/><br/>
    <input type="submit"><br/>
</form>
</body>
</html>
