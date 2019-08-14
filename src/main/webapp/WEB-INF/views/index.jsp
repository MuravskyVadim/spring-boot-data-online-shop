<%--<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--  <head>--%>
<%--    <title>Welcome to shop!!!</title>--%>
<%--  </head>--%>
<%--  <body>--%>
<%--  <div style="text-align: center;">--%>
<%--    <p>Welcome to online shop!</p>--%>
<%--    <form action="<%=request.getContextPath()%>/login" method="POST">--%>
<%--      <p>E-mail:<br><input type="email" name="email" value="${email}"></p>--%>
<%--      <p>Password:<br><input type="password" name="password"></p>--%>
<%--      ${message}--%>
<%--      <p><button type="submit" name="submit">Login</button></p>--%>
<%--    </form>--%>
<%--  </div>--%>
<%--  </body>--%>
<%--</html>--%>


<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--  <title>SHOP</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div align="center">--%>
<%--  ${message}--%>
<%--  <form action='<spring:url value="/signin"/>' method="post">--%>
<%--    <table>--%>
<%--      <tr>--%>
<%--        <td>E-mail</td>--%>
<%--        <td><input type="text" name="email"></td>--%>
<%--      </tr>--%>
<%--      <tr>--%>
<%--        <td>Password</td>--%>
<%--        <td><input type="password" name="password"></td>--%>
<%--      </tr>--%>
<%--      <tr>--%>
<%--        <td></td>--%>
<%--        <td><input type="submit" value="Login"></td>--%>
<%--      </tr>--%>
<%--    </table>--%>
<%--  </form>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Spring Security 5</title>
</head>
<body>
<h1>Spring Security - Custom UserDetailsService Example</h1>
<h2>${message}</h2>
<form action="/logout" method="GET">
    <input value="Logout" type="submit">
</form>
</body>
</html>
