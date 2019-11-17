<%--
  Created by IntelliJ IDEA.
  User: ravi
  Date: 31/8/19
  Time: 6:12 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Welcome to Spring MVC</title>
</head>
<body>
<form:form action="/adduser" method="post" modelAttribute="user">
    <c:out value="${user.title}"/>
    <c:out value="${user.firstName}"/>
    <c:out value="${user.lastName}"/>
    <table>
        <tr>
            <td><c:out value="${'Hi,'}"/>
            </td>
        </tr>
        <tr>
            <td><form:label path="title">Title</form:label></td>
            <td><form:input path="title"/></td>
        </tr>
        <tr>
            <td><form:label path="firstName">First Name</form:label></td>
            <td><form:input path="firstName"/></td>
        </tr>
        <tr>
            <td><form:label path="lastName">Last Name</form:label></td>
            <td><form:input path="lastName"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>
