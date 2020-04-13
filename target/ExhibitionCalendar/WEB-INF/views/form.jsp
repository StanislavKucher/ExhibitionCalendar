<%--
  Created by IntelliJ IDEA.
  User: Stanislav
  Date: 30.03.2020
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Title</title>--%>
<%--</head>--%>
<%--<body>--%>

<%--</body>--%>
<%--</html>--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ page contentType="text/html; charset=UTF-8" %>--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Регистрация</title>
</head>
<body>
<h1>Регистрация</h1>

<c:if test="${violations != null}">
    <c:forEach items="${violations}" var="violation">
        <p>${violation}.</p>
    </c:forEach>
</c:if>

<form action="${pageContext.request.contextPath}/processcustomer" method="post">
    <label for="firstname">first name : </label>
    <input type="text" name="firstname" id="firstname" value="${firstname}">
    <label for="lastname">Фамилия:
        <input type="text" name="lastname" id="lastname" value="${lastname}">
        <label for="email">Email: </label>
        <input type="text" name="email" id="email" value="${email}">
        <input type="submit" name="signup" value="Sign Up">
    </label>>
</form>
</body>
</html>
