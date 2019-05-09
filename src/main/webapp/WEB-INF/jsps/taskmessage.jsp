<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/9/009
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<%@include file="header.jsp"%>
<div class="tpl-content-wrapper">
    <div class="row-content am-cf">
        <div>
            <form class="layui-form" method="post">
                <div class="layui-form-item">
                    <label class="layui-form-label" style="width: 110px;">设备编号</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${task.equipmentNumber}" id="number" name="equipmentNumber" autocomplete="off" class="layui-input" lay-verify="required" readonly>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label" style="width: 110px;">任务时间</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${task.taskBirthDate}" id="taskBirthDate" name="taskBirthDate" autocomplete="off" class="layui-input" lay-verify="required" readonly>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label" style="width: 110px;">截止时间</label>
                    <div class="layui-input-inline">
                        <input type="text" value="${task.taskEndDate}" id="taskEndDate" name="taskEndDate" autocomplete="off" class="layui-input" lay-verify="required" readonly>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-submit lay-filter="*">接收任务</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
