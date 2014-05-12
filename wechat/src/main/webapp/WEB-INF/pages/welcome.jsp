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
        <ul class="nav">
            <li><a href="<%=request.getContextPath() %>/">Home</a></li>
            <li><a href="<%=request.getContextPath() %>/welcome">Welcome</a></li>
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
                <li><a href="<%=request.getContextPath() %>/wechatUser/userList">用户管理</a></li>
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
                    <div class="bs-callout bs-callout-danger">
                        <h4>Don't mix with other components</h4>
                        <p>Icon classes cannot be directly combined with other components. They should not be used along with other classes on the same element. Instead, add a nested <code>&lt;span&gt;</code> and apply the icon classes to the <code>&lt;span&gt;</code>.</p>
                    </div>
                </table>
            </div>
        </div>
    </div>
</div>
<footer>

</footer>
</body>
</html>