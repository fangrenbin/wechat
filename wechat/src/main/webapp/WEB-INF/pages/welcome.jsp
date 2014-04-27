<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page session="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>微信管理平台</title>
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="resources/css/style.css" rel="stylesheet">
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
            <img class="user-icon HW" src="resources/img/user.png">
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
                        <th>Student-ID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Grade</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>003</td>
                        <td>Rabindranath</td>
                        <td>Sen</td>
                        <td>A+</td>
                    </tr>
                    <tr>
                        <td>003</td>
                        <td>Rabindranath</td>
                        <td>Sen</td>
                        <td>A+</td>
                    </tr>
                    <tr>
                        <td>003</td>
                        <td>Rabindranath</td>
                        <td>Sen</td>
                        <td>A+</td>
                    </tr>
                    <tr>
                        <td>003</td>
                        <td>Rabindranath</td>
                        <td>Sen</td>
                        <td>A+</td>
                    </tr>
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