<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/1/001
  Time: 14:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--消息框开始&ndash;-->
<!-- 弹窗&ndash;&gt-->
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <link rel="stylesheet" type="text/css" href="layui/css/layui.css">
    <script src="js/jquery.min.js"></script>
    <%--<script src="${basePath}/static/js/home/index.js"></script>--%>
    <script src="layui/layui.js"></script>
    <title>后台管理系统</title>
</head>
<body>
<input type="hidden" value="${user.userNumber}" id="userNumber">
<input type="hidden" value="${user.userName}" id="userName">
<div class="tanchuang">
    <div class="tanchuangClose"><span class="layui-btn">关闭</span></div>
    <h6>消息提示：</h6>
    <div id="messages"></div>
</div>
<!--消息框结束&ndash;-->
<%--<script src="${basePath}/static/js/home/index.js"></script>--%>
<script src="js/webSocket.js"></script>
</body>
</html>