<%--
  Created by IntelliJ IDEA.
  User: renbinfang
  Date: 14-4-9
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
update news message<br/>
<form action="<%=request.getContextPath() %>/newsMsg/updateNewsMsg" method="post">
    title:      <input name="title" value="${newsMessage.title}"><br/>
    description:<input name="description" value="${newsMessage.description}"><br/>
    picUrl:     <input name="picUrl" value="${newsMessage.picUrl}"><br/>
    url:        <input name="url" value="${newsMessage.url}"><br/>
    <input type="submit" value="Update">
</form>
</body>
</html>
