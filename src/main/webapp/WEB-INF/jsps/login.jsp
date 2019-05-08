<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/1/001
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>登录界面</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="icon" type="image/png" href="images/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="images/app-icon72x72@2x.png">
    <meta name="apple-mobile-web-app-title" content="Amaze UI" />
    <link rel="stylesheet" href="css/amazeui.min.css" />
    <link rel="stylesheet" href="css/amazeui.datatables.min.css" />
    <link rel="stylesheet" href="css/app.css">
    <script src="js/jquery.min.js"></script>

</head>

<body data-type="login">
<script src="js/theme.js"></script>
<div class="am-g tpl-g">
    <!-- 风格切换 -->
    <div class="tpl-skiner">
        <div class="tpl-skiner-toggle am-icon-cog">
        </div>
        <div class="tpl-skiner-content">
            <div class="tpl-skiner-content-title">
                选择主题
            </div>
            <div class="tpl-skiner-content-bar">
                <span class="skiner-color skiner-white" data-color="theme-white"></span>
                <span class="skiner-color skiner-black" data-color="theme-black"></span>
            </div>
        </div>
    </div>
    <div class="tpl-login">
        <div class="tpl-login-content">
            <div class="tpl-login-logo"></div>

            <form class="am-form tpl-form-line-form" action="login" method="post">

                <div class="am-form-group">
                    <input type="text" class="tpl-form-input" id="user-number" name="userNumber" placeholder="请输入编号">
                </div>

                <div class="am-form-group">
                    <input type="password" class="tpl-form-input" id="user-password" name="userPassword" placeholder="请输入密码">
                </div>
                <div class="am-form-group">
                    <button type="submit" class="am-btn am-btn-primary  am-btn-block tpl-btn-bg-color-success  tpl-login-btn">登录</button><br>
                    <a href="toregister"><button type="button" class="am-btn am-btn-primary  am-btn-block tpl-btn-bg-color-success  tpl-login-btn">注册</button></a>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="js/amazeui.min.js"></script>
<script src="js/app.js"></script>
</body>
</html>
