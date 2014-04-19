<%--
  Created by IntelliJ IDEA.
  User: renbinfang
  Date: 14-3-31
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page session="false"%>
<html>
<body>
<h1>Title : ${title}</h1>
<h1>Message : ${message}</h1>
<h2>system user manager</h2>
<a href="<%=request.getContextPath() %>/user/listUser">user manager</a>
<br/>
<h2>news message manager</h2>
<a href="<%=request.getContextPath() %>/newsMsg/newsMsgList">news message manager</a>
<br/>
<h2>wechat message</h2>
<a href="<%=request.getContextPath() %>/message/messageList">wechat message manager</a>
<br/>
</body>
</html>
