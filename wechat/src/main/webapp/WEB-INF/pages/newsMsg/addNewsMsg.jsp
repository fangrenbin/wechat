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
add new message
<form action="<%=request.getContextPath() %>/newsMsg/addNewsMsg" method="post">
    title:      <input name="title"><br/>
    description:<input name="description"><br/>
    picUrl:     <input name="picUrl"><br/>
    url:        <input name="url"><br/>
    <input type="submit" value="Submit" />
</form>
</form>
</body>
</html>
