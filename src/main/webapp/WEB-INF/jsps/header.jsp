<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/4/004
  Time: 10:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>电力设备管理系统</title>
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="icon" type="image/png" href="images/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="images/app-icon72x72@2x.png">
    <meta name="apple-mobile-web-app-title" content="电力设备管理系统" />
    <script src="js/echarts.min.js"></script>
    <link rel="stylesheet" href="css/amazeui.min.css" />
    <link rel="stylesheet" href="css/amazeui.datatables.min.css" />
    <link rel="stylesheet" href="css/app.css">
    <link rel="stylesheet" href="layui/css/layui.css"/>
    <script src="layui/layui.js"></script>
    <script src="js/jquery.min.js"></script>

</head>

<body  style="height: 710px;">
<script src="js/theme.js"></script>
<div class="am-g tpl-g">
    <!-- 头部 -->
    <header>
        <!-- logo -->
        <div class="am-fl tpl-header-logo">
            <a href="javascript:;"><img src="images/logo.png" alt=""></a>
        </div>
        <!-- 右侧内容 -->
        <div class="tpl-header-fluid">
            <!-- 其它功能-->
            <div class="am-fr tpl-header-navbar">
                <li>
                    <!-- 欢迎语 -->
                    <li class="am-text-sm tpl-header-navbar-welcome">
                        <a>欢迎你, <span>${user.userName}</span></a>
                    </li>
                        <!-- 退出 -->
                    <li class="am-text-sm">
                        <a href="exit">
                            <span class="am-icon-sign-out"></span> 退出
                        </a>
                    </li>
                </ul>
            </div>
        </div>

    </header>
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
    <!-- 侧边导航栏 -->
    <div class="left-sidebar">
        <!-- 用户信息 -->
        <div class="tpl-sidebar-user-panel">
            <div class="tpl-user-panel-slide-toggleable">
                <div class="tpl-user-panel-profile-picture">
                    <img src="images/users/${user.userImg}">
                </div>
                <span class="user-panel-logged-in-text">
              <i class="am-icon-circle-o am-text-success tpl-user-panel-status-icon"></i>${user.userName}
          </span>
                <a href="touserupdate" class="tpl-user-panel-action-link"> <span class="am-icon-pencil"></span> 账号设置</a>
            </div>
        </div>

        <!-- 菜单 -->
        <ul class="sidebar-nav">
            <li class="sidebar-nav-link">
                <a href="index" class="active">
                    <i class="am-icon-home sidebar-nav-link-logo"></i> 首页
                </a>
            </li>
            <li class="sidebar-nav-link">
                <a href="userlist">
                    <i class="am-icon-table sidebar-nav-link-logo"></i> 用户列表
                </a>
            </li>

            <%--只是测试用的--%>
            <li class="sidebar-nav-link">
                <a href="javascript:;" class="sidebar-nav-sub-title">
                    <i class="am-icon-table sidebar-nav-link-logo"></i>设备管理
                    <span class="am-icon-chevron-down am-fr am-margin-right-sm sidebar-nav-sub-ico"></span>
                </a>
                <ul class="sidebar-nav sidebar-nav-sub">
                    <li class="sidebar-nav-link">
                        <a href="javascript:;" class="sidebar-nav-sub-title">
                            <span class="am-icon-angle-right sidebar-nav-link-logo"></span> 文字列表
                        </a>
                        <ul class="">
                            <a href="javascript:;" class="sidebar-nav-sub-title">
                                <span class="am-icon-angle-right sidebar-nav-link-logo"></span> 文字
                            </a>
                        </ul>
                    </li>

                    <li class="sidebar-nav-link">
                        <a href="table-list-img.html">
                            <span class="am-icon-angle-right sidebar-nav-link-logo"></span> 图文列表
                        </a>
                    </li>
                </ul>
            </li>






            <li class="sidebar-nav-link">
                <a href="equipmentlist">
                    <i class="am-icon-table sidebar-nav-link-logo"></i> 设备列表
                </a>
            </li>
            <li class="sidebar-nav-link">
                <a href="tasklists">
                    <i class="am-icon-wpforms sidebar-nav-link-logo"></i> 任务列表
                </a>
            </li>
            <li class="sidebar-nav-link" <c:if test="${user.userType eq '普通用户'}">hidden</c:if>>
                <a href="saytask">
                    <i class="am-icon-wpforms sidebar-nav-link-logo"></i> 发布任务
                </a>
            </li>
        </ul>
    </div>
</div>
</div>

<script src="js/amazeui.min.js"></script>
<script src="js/amazeui.datatables.min.js"></script>
<script src="js/dataTables.responsive.min.js"></script>
<script src="js/app.js"></script>

</body>

</html>
