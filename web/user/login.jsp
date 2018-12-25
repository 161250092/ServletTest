
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>sign in</title>
</head>
<body>
<br>
<br>
<br>
<form action="${pageContext.request.contextPath}/Login" method="post">
    <table align="center">
        <tr>
            <td>用户名：</td>
            <td>
                <input type="text" name="user" value=""/>
            </td>
        </tr>
        <tr>
            <td>密码：</td>
            <td>
                <input type="password" name="pw" value=""/>
            </td>
        </tr>
        <tr>
            <td colspan="2">

                <input type="submit" value="登录"/>
            </td>
        </tr>
    </table>

    <a style="text-decoration: none" href="${pageContext.request.contextPath}/VisitorServlet">游客浏览</a>
</form>
</body>
</html>

