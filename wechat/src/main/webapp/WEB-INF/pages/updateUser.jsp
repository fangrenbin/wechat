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
         update user<br/>
         <form action="<%=request.getContextPath() %>/user/updateUser" method="post">
             <input type="hidden" name="${_csrf.parameterName}"
                    value="${_csrf.token}" />
             id<input type="text" value="${user.id}" name="id"><br/>
             username<input type="text" value="${user.username}" name="username"> <br />
             password<input type="text" value="${user.password}" name="password">  <br />
             email <input type="text" value="${user.email}" name="email">  <br/>
             <input type="submit" value="Update">
         </form>
</body>
</html>
