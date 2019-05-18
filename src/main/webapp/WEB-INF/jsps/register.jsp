<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>注册界面 </title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="icon" type="image/png" href="images/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="images/app-icon72x72@2x.png">
    <meta name="apple-mobile-web-app-title" content="Amaze UI"/>
    <link rel="stylesheet" href="css/amazeui.min.css"/>
    <link rel="stylesheet" href="css/amazeui.datatables.min.css"/>
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
            <div class="tpl-login-title" style="padding-bottom: 10px;">注册用户</div>

            <div>
                <span style="color: #2bb8c4">已注册，请<a href="logins" style="color: #9956b5">登录</a></span>
            </div>

            <form class="layui-form" method="post" action="register">
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <input type="text" name="userNumber" lay-verify="number" autocomplete="off" placeholder="编号"
                               class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <input type="text" name="userName" lay-verify="name" autocomplete="off" placeholder="用户名"
                               class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <select name="companyName">
                            <option>请选择隶属公司</option>
                            <c:forEach items="${companylist}" var="name">
                                <option value="${name.id}">${name.companyName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <input type="text" name="checkNumber" lay-verify="checknumber" autocomplete="off" placeholder="邀请码"
                               class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <input type="text" name="userPassword" id="pass" lay-verify="pass" autocomplete="off"
                               placeholder="设置密码" class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <input type="text" name="userPassword1" lay-verify="regpass" autocomplete="off"
                               placeholder="再次确认密码" class="layui-input">
                    </div>
                </div>

                <div class="layui-input-block">
                    <button class="layui-btn" type="submit" lay-submit="" lay-filter="demo1">立即提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
<script src="js/amazeui.min.js"></script>
<script src="js/app.js"></script>
<script src="layui/layui.js"></script>
<script>
    layui.use(['form', 'jquery'], function () {
        var form = layui.form;

        //用ajax进行注册及跳转
        form.on("submit(demo1)",function (message) {

            $.ajax({
                url:"<%=request.getContextPath()%>/register",
                type:"post",
                contentType:'application/json; charset=utf-8',
                data:JSON.stringify({
                    number:message.field.userNumber,
                    name:message.field.userName,
                    companyid:message.field.companyName,
                    psw:message.field.userPassword,
                    checknum:message.field.checkNumber
                }),
                success:function (res) {
                    if (res == "1"){
                        setTimeout(function () {
                            window.location.href="<%=request.getContextPath()%>/logins";
                        },1000)
                        layer.msg("注册成功，正在跳转登录界面");
                        form.render();
                    } else {
                        layer.msg("邀请码错误，请核对公司与邀请码信息");
                    }
                }
            })
            return false;
        });



        form.verify({
            number: function (value) {
                if (value.length < 11) {
                    return "编号长度不能低于11";
                }
            }
            , name: function (value) {
                if (value.length < 1) {
                    return "用户名不能为空";
                }
            }
            , pass: function (value) {
                if (value < 6) {
                    return "密码强度太低";
                }
            }
            ,checknumber:function (value) {
                if(value.length!=4){
                    return "验证码格式不正确";
                }
            }
            , regpass: function (value) {
                var pass = $("#pass").val();
                if (!new RegExp(pass).test(value)) {
                    return "两次密码不一致";
                }
            }
        });
    });
</script>
</html>
