<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/8/008
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <link rel="stylesheet" href="layui/css/layui.css"/>
    <script src="layui/layui.js"></script>
    <script src="js/jquery.min.js"></script>

</head>
<body>
<%@include file="header.jsp"%>
<div class="tpl-content-wrapper">
    <div class="row-content am-cf">
        <div>
            <form class="layui-form" method="post">
                <div class="layui-form-item">
                    <label class="layui-form-label" style="width: 110px;">设备编号</label>
                    <div class="layui-input-inline">
                        <input type="text" id="number" name="equipmentNumber" autocomplete="off" class="layui-input" lay-verify="required">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label" style="width: 110px;">截止日期</label>
                    <div class="layui-input-inline">
                        <input type="text" id="endDate" name="endDate" lay-verify="birth" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label" style="width: 110px;">任务描述</label>
                    <div class="layui-input-block">
                        <textarea id="task" name="taskDes" placeholder="描述任务具体信息内容" class="layui-textarea"></textarea>
                    </div>
                </div>
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="send" >立即发布</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<script>
    layui.use(['form','laydate'],function () {
        var layer = layui.layer
            ,form = layui.form
            ,laydate = layui.laydate


        laydate.render({
            elem: '#endDate'
        });

        form.on('submit(send)',function (message) {
            $.ajax({
                url:"<%=request.getContextPath()%>/sendtask",
                type:'post',
                contentType:'application/json; charset=utf-8',
                data:JSON.stringify({
                    number:message.field.equipmentNumber,
                    task:message.field.taskDes,
                    endDate:message.field.endDate
                }),
                success:function (res) {
                    if(res == "1"){
                        setTimeout(function () {
                            layer.msg("发布成功，请到任务列表查看");
                            form.render();
                        },1000)
                        }else if(res=="0"){
                            layer.msg("设备不存在，请重新发布");
                            form.render();
                        }else if (res=="2") {
                        layer.msg("设备维修中。。。");
                        form.render();
                    }else if (res="3"){
                        layer.msg("设备等待维修中。。。");
                        form.render();
                    }
                    else {
                        layer.msg("发布失败，请重新发布");
                        form.render();
                    }
                }
            });
            return false;
        });
    });
</script>
</html>
