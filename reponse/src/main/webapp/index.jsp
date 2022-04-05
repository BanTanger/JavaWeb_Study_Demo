<html>
<body>
<h2>Hello World!</h2>
<%--${pageContext.request.contextPath}是jsp.jar用来指向提交路径的语法--%>
<form action="${pageContext.request.contextPath}/login" method="get">
    用户名:<input type="text" name="username">
    密码:<input type="password" name="password">
    <input type="submit">
</form>

</body>
</html>

