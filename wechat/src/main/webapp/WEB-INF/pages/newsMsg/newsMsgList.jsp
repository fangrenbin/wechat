<%--
  Created by IntelliJ IDEA.
  User: renbinfang
  Date: 14-4-3
  Time: 11:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>News message list</title>
</head>
<body>
<table border="1">
    <tr>
        <th width="20%">id</th>
        <th width="20%">title</th>
        <th width="20%">description</th>
        <th width="20%">addTime</th>
        <th width="20%">updateTime</th>
        <th width="20%">action</th>
    </tr>
    <c:forEach items="${newsMessageList}"  var="newsMsg">
        <tr>
            <td>${newsMsg.id}</td>
            <td>${newsMsg.title}</td>
            <td>${newsMsg.description}</td>
            <td>${newsMsg.addTime}</td>
            <td>${newsMsg.updateTime}</td>
            <td>
                <a href="<%=request.getContextPath() %>/newsMsg/viewNewsMsg?id=${newsMsg.id}">view</a>
                <a href="<%=request.getContextPath() %>/newsMsg/updateNewsMsgPage?id=${newsMsg.id}">update</a>
                <a href="<%=request.getContextPath() %>/newsMsg/deleteNewsMsg?id=${newsMsg.id}">delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
