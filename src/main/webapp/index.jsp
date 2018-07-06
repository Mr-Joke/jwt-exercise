<%--
  Created by IntelliJ IDEA.
  User: 11014219
  Date: 2018/7/2
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <title>用户登录</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/user/register" method="post">
    <table align="center">
        <caption>用户注册</caption>
        <tr>
            <td>用户名：</td>
            <td><input type="text" name="username"/></td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><input type="password" name="password"/></td>
        </tr>
        <tr>
            <td>邮箱：</td>
            <td><input type="email" name="email"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="提交"/></td>
            <td><input type="reset" value="重置"/></td>
        </tr>
    </table>
</form>
</body>
</html>
