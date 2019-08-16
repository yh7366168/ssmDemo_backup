<%--
  Created by IntelliJ IDEA.
  User: T430487
  Date: 2019/5/13
  Time: 9:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>注册页面</title>
    <link rel="shortcut icon" href="#"/>
    <style type="text/css">
        body{
            /*背景*/
            background-image: url("${pageContext.request.contextPath}/img/background_003.jpg");
            /*透明*/
            background-size: cover;
        }
        /*表单设置*/
        form{
            width: 290px;
            height: 440px;
            /*内边距*/
            padding-left:50px;
            padding-top: 50px;
            padding-right: 48px;
            padding-bottom: 60px;
            /*边框*/
            border: 0px;
            /*外边距*/
            margin-left: 37%;/*左外边距1200*/
            margin-top: 8%;/*上外边距300*/
        }
        /*form表单里面所有div元素*/
        form div{
            height: 30px;
            margin-bottom: 18px;
        }
        .labelClz{
            width: 80px;
            display: inline-block;
        }
    </style>
</head>

<body>
<form>
    <div>
        <label class="labelClz">用户名</label>
        <input type="text" id="username" name="username" required="required">
        <img id="userImg" src="${pageContext.request.contextPath}/img/isChecked.png"  style="width: 15px;height: 15px;vertical-align: middle;" hidden="hidden">
    </div>
    <div>
        <label class="labelClz">密码</label>
        <input type="text" id="password" name="password" required="required">
    </div>
    <div>
        <label class="labelClz">性别</label>
        <span>男</span>
        <input type="radio" name="sex" value="0" checked="checked">
        <span style="margin-left: 40px;">女</span>
        <input type="radio" name="sex" value="1">
    </div>
    <div>
        <label class="labelClz">年龄</label>
        <input type="text" id="age" name="age" required="required">
    </div>
    <div>
        <label class="labelClz">手机号</label>
        <input type="text" id="phone" name="phone" required="required">
    </div>
    <div>
        <label class="labelClz">邮箱</label>
        <input type="text" id="email" name="email" required="required">
    </div>
    <div>
        <label class="labelClz">地址</label>
        <input type="text" id="address" name="address" required="required">
    </div>
    <div>
        <input type="checkbox" name="isRead" value="1" required="required">
        <label style="font-size: 13px;margin-left: 7px">我已阅读并确认遵守用户协议！</label>
    </div>
    <div>
        <input type="submit" value="注册" style="height: 28px;width: 257px;background-color: #607d8b;color: white">
    </div>
</form>
</body>
</html>

<script src="${pageContext.request.contextPath}/lib/jquery-3.4.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/yh_common.js"></script>
<script type="text/javascript">
    $(function () {
        //校验用户名：失去焦点时触发
        $("#username").blur(function () {
            var username = $.trim($("#username").val());
            if(!isNull(username)){
                $.ajax({
                    type:"GET",
                    url:"${pageContext.request.contextPath}/user/checkUsernameIsExist",
                    async:false,
                    data:{"username":username},
                    dataType:"text",
                    success:function (message) {
                        if(message == "exist"){
                            alert("该用户名已经存在，请选择其他用户名！");
                        }else{
                            $("#userImg").show();
                        }
                    }
                });
            }
        });
        //校验密码，规则：输入之后校验，密码最少6位最多16位的数字或者字母
        $("#password").blur(function () {
            var password = $("#password").val();
            if(!isNull(password)){
                var regx = /^[a-zA-Z0-9]{6,12}$/;//最少6位最多16位
                if(!regx.test(password)){
                    alert("密码格式不对！请输入6到12位有效密码！");
                }
            }
        });

        $("form").submit(function () {
            var data = JSON.stringify({
                "username":$.trim( $("#username").val() ),
                "password":$.trim( $("#password").val() ),
                "sex": $("input[name='sex']:checked").val(),
                "age": $.trim($("#age").val()),
                "phone": $.trim($("#phone").val()),
                "email": $.trim($("#email").val()),
                "address": $.trim($("#address").val())
            });
            $.ajax({
                url:"${pageContext.request.contextPath}/user/registerUser",
                type:"post",
                async:false,
                contentType: 'application/json;charset=UTF-8',
                dataType:'text',
                data:data,
                success:function (result) {
                    if(result == "success"){
                        alert("注册成功！返回登录页面！");
                        window.location.href = "${pageContext.request.contextPath}/jsp/login/login.jsp";
                    }else{
                        alert("注册失败！");
                    }
                }
            });
            return false;
        });
    });

</script>