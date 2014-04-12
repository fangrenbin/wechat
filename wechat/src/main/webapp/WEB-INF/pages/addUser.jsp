<%--
  Created by IntelliJ IDEA.
  User: renbinfang
  Date: 14-4-1
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
   add user
   <form action="<%=request.getContextPath() %>/user/addUser" method="post">
       username:<input name="username"><br/>
       password:<input name="password"><br/>
       email:   <input name="email"><br/>
       <input type="submit" value="Submit" />
   </form>
</body>
</html>
