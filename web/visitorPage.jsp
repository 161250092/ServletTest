<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 18/12/22
  Time: 下午2:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>游客数:</p>
<p>${visitor}</p>
<a style="text-decoration: none" href="${pageContext.request.contextPath}/user/login.jsp">登陆</a>
</body>
</html>
