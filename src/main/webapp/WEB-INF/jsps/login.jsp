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
    <link rel="stylesheet" href="layui/css/layui.css"/>
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
            <div class="tpl-login-title" style="padding-bottom: 10px;">用户登录</div>

            <div>
                <span style="color: #2bb8c4">没有账号，请<a href="toregister" style="color: #9956b5">注册</a></span>
            </div>
            <form class="layui-form" action="login" method="post">

                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <input type="text" name="userNumber1" lay-verify="number" autocomplete="off" placeholder="编号" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <input type="text" name="userPassword" id="pass" lay-verify="pass" autocomplete="off"
                               placeholder="输入密码" class="layui-input">
                    </div>
                </div>
                <div class="layui-input-block">
                    <button class="layui-btn" type="submit" lay-submit="" lay-filter="demo1">立即登录</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="js/amazeui.min.js"></script>
<script src="js/app.js"></script>
<script src="layui/layui.js"></script>
<script>
    layui.use(['form', 'jquery'], function () {
        var form = layui.form;

        form.on("submit(demo1)",function (message) {
           $.ajax({
               url:"<%=request.getContextPath()%>/login",
               type:"post",
               contentType:'application/json; charset=utf-8',
               data:JSON.stringify({
                    number:message.field.userNumber1,
                   pass:message.field.userPassword
               }),
               success:function (res) {
                   if(res == "1"){
                       setTimeout(function () {
                           window.location.href="<%=request.getContextPath()%>/index";
                       },1000)
                       layer.msg("登录成功，请等待...");
                   }else{
                       layer.msg("用户名或密码错误");
                       window.location.reload();
                   }
               }
           });
            return false;
        });

        form.verify({
            number:function (value) {
                if(value.length<0){
                    return "用户编号不为空";
                }
            }
            ,pass:function (value) {
                if (value.length<0){
                    return "密码不为空";
                }
            }
        });

    });
</script>
</body>
</html>
