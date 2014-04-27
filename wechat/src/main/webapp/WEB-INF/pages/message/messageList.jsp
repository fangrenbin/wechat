<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>微信管理平台</title>
    <link href="../resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="../resources/css/style.css" rel="stylesheet">
</head>
<body>
<div class="navbar">
    <div class="navbar-inner">
        <a class="brand" href="#">Title</a>
        <ul class="nav">
            <li><a href="#">Home</a></li>
            <li><a href="#">About</a></li>
            <li><a href="#">Contact</a></li>
        </ul>
        <p class="navbar-text pull-right">
            <img class="user-icon HW" src="../resources/img/user.png">
            <a href="#">fangrenbin</a>
            <a href="Login.html" class="out">Logout</a>
        </p>
    </div>
</div>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span2">
            <ul class=" nav-list list-r">
                <li class="nav-header active">功能列表</li>
                <li><a href="<%=request.getContextPath() %>/user/listUser">用户管理</a></li>
                <li><a href="<%=request.getContextPath() %>/newsMsg/newsMsgList">新闻信息管理</a></li>
                <li><a href="<%=request.getContextPath() %>/message/messageList">接收用户消息</a></li>
                <li><a href="#">向用户回复消息</a></li>
                <li><a href="#">接受事件推送</a></li>
                <li><a href="#">会话界面自定义菜单</a></li>
            </ul>
        </div>
        <div class="span10">
            <div class="row-fluid">
                <form class="well form-search">
                    <div class="pull-right">
                        <input type="text" class="input-medium search-query">
                        <button type="submit" class="btn">Search</button>
                    </div>
                </form>
                <table class="table table-bordered table-hover">
                    <thead>
                    <tr>
                        <%--<th>toUserName</th>--%>
                        <th>fromUserName</th>
                        <th>createTime</th>
                        <th>content</th>
                        <th>operation</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${textMsgList}"  var="message">
                        <tr>
                            <%--<td>${message.toUserName}</td>--%>
                            <td>${message.fromUserName}</td>
                            <td>${message.createTime}</td>
                            <td>${message.content}</td>
                            <td>
                                <a href="<%=request.getContextPath() %>/user/updateUserPage?id=${user.id}">reply</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<footer>

</footer>
</body>
</html>