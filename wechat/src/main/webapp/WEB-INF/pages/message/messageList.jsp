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
    <title></title>
</head>
<body>
<table border="1">
    <tr>
        <th width="20%">id</th>
        <th width="20%">toUserName</th>
        <th width="20%">fromUserName</th>
        <th width="20%">createTime</th>
        <th width="20%">msgType</th>
        <th width="20%">content</th>
        <th width="20%">msgId</th>
        <th width="20%">operation</th>
    </tr>
    <c:forEach items="${textMsgList}"  var="message">
        <tr>
            <td>${message.id}</td>
            <td>${message.toUserName}</td>
            <td>${message.fromUserName}</td>
            <td>${message.createTime}</td>
            <td>${message.msgType}</td>
            <td>${message.content}</td>
            <td>${message.msgId}</td>
            <td>
                <a href="<%=request.getContextPath() %>/user/updateUserPage?id=${user.id}">reply</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
