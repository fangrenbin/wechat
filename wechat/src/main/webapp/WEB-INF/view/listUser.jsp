<%--
  Created by IntelliJ IDEA.
  User: renbinfang
  Date: 14-4-1
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>list user</title>
</head>
<body>
<table border="1">
    <tr>
        <th width="20%">id</th>
        <th width="20%">username</th>
        <th width="20%">password</th>
        <th width="20%">email</th>
        <th width="20%">operation</th>
    </tr>
    <c:forEach items="${userList}"  var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.password}</td>
            <td>${user.email}</td>
            <td>
                <a href="<%=request.getContextPath() %>/user/updateUserPage?id=${user.id}">update</a>
                <a href="<%=request.getContextPath() %>/user/viewUser?id=${user.id}">view</a>
                <a href="<%=request.getContextPath() %>/user/deleteUser?id=${user.id}">delete</a>
            </td>
        </tr>
    </c:forEach>
    <a href="<%=request.getContextPath() %>/user/addUserPage" target="_blank">add user</a>
</table>
</body>
</html>
