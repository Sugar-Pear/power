<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/13/013
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
   <%@include file="header.jsp"%>
</head>
<body>
<div class="tpl-content-wrapper">
    <div class="row-content am-cf">

            <table id="test" lay-filter="test"></table>
        <script type="text/html" id="barDemo">
            <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
            <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
        </script>
    </div>
</div>
</body>
<script>
    layui.use(['layer', 'table', 'element', 'form'], function(){
        var layer = layui.layer
            ,form = layui.form
            ,table = layui.table;

        //渲染
        table.render({
            elem: '#test'  //绑定table表格
            ,height: 600
            ,url: '<%=request.getContextPath()%>/list' //后台springmvc接收路径
            ,page:true    //true表示分页
            /* page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
             layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                 //,curr: 5 //设定初始在第 5 页
                 ,groups: 3 //只显示 1 个连续页码
                 ,first: true //不显示首页
                 ,last: true //不显示尾页
              }*/
//            ,where:{rows:limit} //传参数
            ,limit: 10
            ,id:'contenttable'
            ,toolbar: '#toolbarDemo'
            ,cols: [[
                {type: 'checkbox', fixed: 'left'}
                ,{field:'userNumber', title:'编号', align:'center',width:120, fixed: 'left', sort: true}
                ,{field:'userName', title:'姓名', align:'center',width:90, edit: 'text'}
                ,{field:'userType', title:'类型', align:'center',width:80, edit: 'text', sort: true}
                ,{field:'userBirthday', title:'生日', align:'center',width:150}
                ,{field:'userAddress', title:'地址', align:'center',width:200, sort: true}
                ,{field:'userPhone', title:'电话', align:'center', width:120}
                ,{fixed: 'right', width:220,title:'操作', align:'center', toolbar: '#barDemo'}
            ]]
        });


        //监听表格行点击
        table.on('tr', function(obj){
            console.log(obj)
        });

        //监听表格复选框选择
        table.on('checkbox(test)', function(obj){
            console.log(obj)
        });

        //监听表格单选框选择
        table.on('radio(test2)', function(obj){
            console.log(obj)
        });

        //监听单元格编辑
        table.on('edit(test2)', function(obj){
            var value = obj.value //得到修改后的值
                ,data = obj.data //得到所在行所有键值
                ,field = obj.field; //得到字段

        });

        //监听工具条
        table.on('tool(test)', function(obj){
            var data = obj.data;
            if(obj.event === 'del'){
                layer.confirm('真的删除行么', function(index){
                    obj.del();
                    layer.close(index);
                });
            } else if(obj.event === 'edit'){
                layer.prompt({
                    formType: 2
                    ,value: data.username
                }, function(value, index){
                    obj.update({
                        username: value
                    });
                    layer.close(index);
                });
            }
        });

        var $ = layui.jquery, active = {
            getCheckData: function(){//获取选中数据
                var checkStatus = table.checkStatus('test')
                    ,data = checkStatus.data;
                layer.alert(JSON.stringify(data));
            }
            ,getCheckLength: function(){//获取选中数目
                var checkStatus = table.checkStatus('test')
                    ,data = checkStatus.data;
                layer.msg('选中了：'+ data.length + ' 个');
            }
            ,isAll: function(){验证是否全选
                var checkStatus = table.checkStatus('test');
                layer.msg(checkStatus.isAll ? '全选': '未全选')
            }
            ,parseTable: function(){
                table.init('parse-table-demo', {
                    limit: 3
                });
            }
            ,add: function(){
                table.addRow('test')
            }
            ,delete: function(){
                layer.confirm('确认删除吗？', function(index){
                    table.deleteRow('test')
                    layer.close(index);
                });
            }
            ,reload:function () {
                var keyWord=$("#keyWord").val();
                var keyType=$("#key_type option:selected").val();
                table.reload('contenttable',{
                    where:{keyWord:keyWord,keyType:keyType}
                });
            }
        };
        $('i').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        $('.layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });
</script>
</html>
