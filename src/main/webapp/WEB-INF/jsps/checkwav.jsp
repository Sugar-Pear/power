<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/13/013
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<%@include file="header.jsp"%>
<div class="tpl-content-wrapper">
    <div class="row-content am-cf">
        <table id="test" lay-filter="test"></table>
        <script type="text/html" id="barDemo">
            <%--lay-event="detail"--%>
            <a href="test1" class="layui-btn layui-btn-primary layui-btn-xs">检测</a>
            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">通过审核</a>
        </script>
    </div>
</div>
</body>
<script>
    layui.use(['layer', 'table', 'element', 'form'], function () {
        var layer = layui.layer
            , form = layui.form
            , table = layui.table;

        //渲染
        table.render({
            elem: '#test'  //绑定table表格
            , height: 600
            , url: '<%=request.getContextPath()%>/checkwavlist' //后台springmvc接收路径
            , page: true    //true表示分页
            /* page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
             layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                 //,curr: 5 //设定初始在第 5 页
                 ,groups: 3 //只显示 1 个连续页码
                 ,first: true //不显示首页
                 ,last: true //不显示尾页
              }*/
//            ,where:{rows:limit} //传参数
            , limit: 10
            , id: 'contenttable'
            , toolbar: '#toolbarDemo'
            , cols: [[
                 {field: 'userNumber', title: '检测人编号', align: 'center', width: 135, fixed: 'left', sort: true}
                ,{field: 'equipmentNumber', title: '检测设备编号', align: 'center', width: 163, fixed: 'left', sort: true}
                , {field: 'checkProblem', title: '检测问题', align: 'center', width: 379, fixed: 'left'}
                , {field: 'taskEndDate', title: '完成时间', align: 'center', width: 208}
                , {fixed: 'right', width: 176, title: '操作', align: 'center', toolbar: '#barDemo'}
            ]]
        });


        //监听表格行点击
        table.on('tr', function (obj) {
            console.log(obj)
        });

        //监听表格复选框选择
        table.on('checkbox(test)', function (obj) {
            console.log(obj)
        });

        //监听表格单选框选择
        table.on('radio(test2)', function (obj) {
            console.log(obj)
        });

        //监听单元格编辑
        table.on('edit(test2)', function (obj) {
            var value = obj.value //得到修改后的值
                , data = obj.data //得到所在行所有键值
                , field = obj.field; //得到字段

        });

        //监听工具条
        table.on('tool(test)', function (obj) {
            var data = obj.data;
            if (obj.event === 'del') {
                $.ajax({
                    url:'<%=request.getContextPath()%>/checkWav',
                    type:'post',
                    data:{"equipmentNumber":data.equipmentNumber},
                    dataType:'json',
                    success:function (res) {
                        if (res == "1"){
                            setTimeout(function () {
                                window.location.reload();
                            },1000);
                            layer.msg("已经通过审核");
                        }else {
                            layer.msg("未通过审核");
                        }
                    }
                })
            }else if (obj.event === 'detail') {

            }
        });
    });
</script>
</html>
