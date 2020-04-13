<%--
  Created by IntelliJ IDEA.
  User: Stanislav
  Date: 30.03.2020
  Time: 15:09
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
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Данные клиента</title>
</head>
<body>
<h1>Спасибо за регистрацию!</h1>
<h2>Ваши введённые данные:</h2>
<p><strong>Имя:</strong> ${firstname}</p>
<p><strong>Фамилия:</strong> ${lastname}</p>
<p><strong>Email: </strong>${email}</p>
</body>
</html>