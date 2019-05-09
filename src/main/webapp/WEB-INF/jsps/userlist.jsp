<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/13/013
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<%@include file="header.jsp"%>
<div class="tpl-content-wrapper" style="position: relative;">

    <div class="am-fl tpl-header-search" style="position: absolute;top: 10px;left: 22px;background-color: white;height: 25px;width: 162px;">
            <button class="tpl-header-search-btn am-icon-search" datatype="reload"></button>
            <input class="tpl-header-search-box" type="text" placeholder="搜索内容..." name="keyWord" id="keyWord">
    </div>

    <div class="row-content am-cf" style="position: absolute;top: 20px;">
        <table id="test" lay-filter="test"></table>
        <script type="text/html" id="barDemo">
            <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
        </script>
    </div>
</div>
<%--修改弹出框--%>
<form class="layui-form layui-form-pane1" id="form1" name="form1" style="" action="<%=request.getContextPath()%>/modify"  method="post" lay-filter="first1">
    <br>
    <div class="layui-form-item">
        <label class="layui-form-label">编号</label>
        <div class="layui-input-inline">
            <input  type="text" name="userNumber" id="number" lay-verify="required|number" autocomplete="off" class="layui-input" readonly>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">姓名</label>
        <div class="layui-input-inline">
            <input type="text" name="userName" id="name" lay-verify="required|name" required placeholder="请输入用户名" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
    <label class="layui-form-label">手机</label>
    <div class="layui-input-inline">
        <input type="tel" name="userPhone" id="phone" lay-verify="required|number" placeholder="请输入手机号码" lay-verType="tips" autocomplete="off" class="layui-input">
    </div>
</div>

    <div class="layui-form-item">
        <div class="layui-form-label">生日</div>
        <div class="layui-input-inline">
            <input type="text" id="birth" name="userBirthday" lay-verify="birth" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">权限</label>
        <div class="layui-input-inline">
            <select name="userType"  id="utype" lay-verify="required|" lay-filter="interest-search" lay-search>
                <option value=""></option>
                <option value="管理员">管理员</option>
                <option value="普通用户" >普通用户</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="*">修改</button>
        </div>
    </div>
</form>


</body>
<script>
    layui.use(['layer', 'table', 'element', 'form','laydate'], function(){
        var layer = layui.layer
            ,form = layui.form
            ,table = layui.table
            ,laydate = layui.laydate;

        laydate.render({
            elem:'#birth'
        });

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
                //{type: 'checkbox', fixed: 'left'}
                {field:'userNumber', title:'编号', align:'center',width:120, fixed: 'left', sort: true}
                ,{field:'userName', title:'姓名', align:'center',width:90, edit: 'text'}
                ,{field:'userType', title:'类型', align:'center',width:88, edit: 'text'}
                ,{field:'userBirthday', title:'生日', align:'center',width:115}
                ,{field:'userProvince', title:'省', align:'center',width:144, sort: true}
                ,{field:'userCity', title:'市', align:'center',width:102, sort: true}
                ,{field:'userArea', title:'县', align:'center',width:102, sort: true}
                ,{field:'userPhone', title:'电话', align:'center', width:124}
                ,{fixed: 'right', width:180,title:'操作', align:'center', toolbar: '#barDemo'}
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
                layer.confirm('真的删除行么', {offset:"100px"},function(index){
                    console.log("really?:"+obj);
                    console.log("data"+data.userNumber);
                    $.ajax({
                        url: "<%=request.getContextPath()%>/delete",
                        type: "POST",
                        data:{"userNumber":data.userNumber},
                        dataType: "json",
                        success: function(data){
                            if (data == null){
                                layer.msg("删除失败", {icon: 5});
                            }else{
                                //删除这一行
                                obj.del();
                                //关闭弹框
                                layer.close(index);
                                layer.msg("删除成功", {icon: 6});
                                layer.closeAll();
                                parent.location.reload();
                                Load(); //删除完需要加载数据
                            }
                        },
                        error:function (data) {
                            alert(data);
                        },
                    });
                });
            } else if(obj.event === 'edit') {
                layer.open({
                    title:'修改人员信息',
                    type:1,
                    offset:"100px",
                    area:['400px','555px'],
                    content:$("#form1")
                });
                $("#number").val(data.userNumber);
                $("#name").val(data.userName);
                $("#phone").val(data.userPhone);
                $("#utype").val(data.userType);
                $("#birth").val(data.userBirthday);
                form.render();
                form.on("submit(*)",function (message) {
                    $.ajax({
                        url:'<%=request.getContextPath()%>/modify',
                        method:'POST',
                        contentType:'application/json; charset=utf-8',
                        data:JSON.stringify({
                            number:message.field.userNumber,
                            name:message.field.userName,
                            phone:message.field.userPhone,
                            birth:message.field.userBirthday,
                            utype:message.field.userType
                        }),
                        success:function (returnCode) {

                            if(returnCode == '200'){
                                layer.closeAll("loading");
                                layer.load(2);
                                layer.msg("修改成功", {icon: 6});

                                setTimeout(function () {
                                        obj.update({
                                            name: data.userName,
                                            phone: data.userPhone,
                                            power:data.userPower
                                        });//修改成功修改表格数据不进行跳转
                                        location.reload();//刷新页面
                                        /* layer.closeAll();//关闭所有的弹出层*/
                                    },1000);
                                //form.render();
                            }else {
                                layer.msg("修改失败", {icon: 5});
                            }
                        }
                    })
                    return false;//阻止表单跳转  true：表单跳转
                });
            }
        });
        var $ = layui.jquery, active = {
            reload:function () {
                var keyWord=$("#keyWord").val();
                table.reload('contenttable',{
                    where:{keyWord:keyWord}
                });
            }
        };
    });
    layui.config({
        base: "js/" //address.js的路径
    }).use(['layer', 'jquery', "address"], function () {
        var layer = layui.layer, $ = layui.jquery, address = layui.address();
    });
</script>
</html>
