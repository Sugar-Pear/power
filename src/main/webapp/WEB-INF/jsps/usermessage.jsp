<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/6/006
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<%@include file="header.jsp" %>
<div class="tpl-content-wrapper">
    <div class="row-content am-cf">
        <div style="position:absolute;left: 400px">
            <div class="layui-upload">
                <div class="layui-upload-list">
                    <img class="layui-upload-img" id="demo1" style="height: 150px;width: 150px;" src="images/users/${user.userImg}">
                    <p id="demoText"></p>
                </div>
                <div >
                    <button type="button" class="layui-btn" id="test2">修改头像</button>
                </div>
            </div>
        </div>
        <form class="layui-form" action="userupdate" method="post">
            <div class="layui-form-item">
                <div class="layui-form-label">编号</div>
                <div class="layui-input-inline">
                    <input type="text" name="userNumber" lay-verify="number" value="${user.userNumber}"
                           autocomplete="off" class="layui-input" readonly>
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-form-label">姓名</div>
                <div class="layui-input-inline">
                    <input type="text" placeholder="姓名" name="userName" lay-verify="name" value="${user.userName}"
                           autocomplete="off" class="layui-input" readonly>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-form-label">生日</div>
                <div class="layui-input-inline">
                    <input type="text" id="test1" name="userBirthday" lay-verify="birth" value="${user.userBirthday}"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-form-label">年龄</div>
                <div class="layui-input-inline">
                    <input type="text" name="userAge" lay-verify="age" value="${user.userAge}" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-form-label">类型</div>
                <div class="layui-input-block">
                    <input type="radio" name="userType" value="管理员" title="管理员" <c:if test="${user.userType eq '管理员'}">
                           checked="checked"</c:if>>
                    <input type="radio" name="userType" value="普通用户" title="普通用户" <c:if test="${user.userType eq '普通用户'}">
                           checked="checked"</c:if>>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-form-label">电话</div>
                <div class="layui-input-inline">
                    <input type="text" name="userPhone" lay-verify="phone" value="${user.userPhone}" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-form-label">密码</div>
                <div class="layui-input-inline">
                    <input type="text" name="userPassword" lay-verify="pass" value="${user.userPassword}"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-form-label">地址</div>
                <div class="layui-input-inline">
                    <select name="province" lay-filter="province" id="myprovince">
                        <option value=""><c:choose><c:when
                                test="${user.userProvince eq null}">请选择省</c:when><c:otherwise>${user.userProvince}</c:otherwise></c:choose></option>
                    </select>
                </div>
                <div class="layui-input-inline">
                    <select name="city" lay-filter="city" id="mycity">
                        <option value=''><c:choose><c:when
                                test="${user.userCity eq null}">请选择市/区</c:when><c:otherwise>${user.userCity}</c:otherwise></c:choose></option>
                    </select>
                </div>
                <div class="layui-input-inline">
                    <select name="area" lay-filter="area" id="myarea">
                        <option value=''><c:choose><c:when
                                test="${user.userArea eq null}">请选择县</c:when><c:otherwise>${user.userArea}</c:otherwise></c:choose></option>
                    </select>
                </div>
            </div>
            <br><br>
            <div class="layui-input-block">
                <button class="layui-btn" type="submit" lay-submit="">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </form>
    </div>
</div>
</body>
<script type="text/javascript" src="layui/layui.js"></script>
<script type="text/javascript">
    layui.use(['laydate', 'upload'], function () {
        var laydate = layui.laydate;
        var $ = layui.$;
        var upload = layui.upload;

        var uploadInst = upload.render({
            elem: '#test2'
            , url: "<%=request.getContextPath()%>/uploadHeadImage"
            , before: function (obj) {
                //预读本地文件示例，不支持ie8
                obj.preview(function (index, file, result) {
                    $('#demo1').attr('src', result); //图片链接（base64）
                });
            }
            , done: function (res) {
                //如果上传失败
                if (res <= 0) {
                    return layer.msg('上传失败');
                }
                //上传成功
                layer.msg('上传成功');
                location.reload()
            }
            , error: function () {
                //演示失败状态，并实现重传
                var demoText = $('#demoText');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function () {
                    uploadInst.upload();
                });
            }
        });


        laydate.render({
            elem: '#test1'
        });
    });

    layui.config({
        base: "js/" //address.js的路径
    }).use(['layer', 'jquery', "address"], function () {
        var layer = layui.layer, $ = layui.jquery, address = layui.address();
    });
</script>
</html>
