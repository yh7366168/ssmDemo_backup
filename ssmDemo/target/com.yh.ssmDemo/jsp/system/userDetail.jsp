<%--
  Created by IntelliJ IDEA.
  User: T430487
  Date: 2019/7/5
  Time: 9:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户详情页面</title>
    <style type="text/css">
        .user_detail_div_clz {
            display: none;
            position: absolute;
            top: 18%;
            left: 19%;
            width: 60%;
            height: 45%;
            padding: 0;
            border: 8px solid #E8E9F7;
            background-color: #f1e8e8;
            z-index: 1003;
            overflow: auto;
            border-style: double;
        }

        /*按钮*/
        .user_detail_div_clz button {
            width: 60px;
            height: 30px;
            margin-left: 20px;
            margin-top: 5px;
        }

        /*表格*/
        .user_detail_table_clz {
            border: 0;
            text-align: right;
        }

        .user_detail_table_clz tr {
            border: 0;
            height: 30px;
        }

        .user_detail_table_clz td {
            border: 0;
            width: 200px;
        }
    </style>
</head>
<body>
<div id="user_detail_div" class="user_detail_div_clz">
    <div style="background-color: #c6c7c5; height: 40px">
        <button>修改</button>
        <button onclick="reback()">返回</button>
    </div>
    <div style="text-align: center; background-color:#cce0b7;height: 30px">
        <div style="padding-top: 4px;font-size: 18px;letter-spacing: 2px">用户详情信息</div>
    </div>
    <div id="user_detail_line_div"></div>
    <table class="user_detail_table_clz">
        <tr>
            <td>用户名</td>
            <td style="text-align: left"></td>
            <td>性别</td>
            <td id="user_detail_sex_val" style="text-align: left"></td>
        </tr>
        <tr>
            <td>年龄</td>
            <td id="user_detail_age_val" style="text-align: left"></td>
            <td>手机号</td>
            <td id="user_detail_phone_val" style="text-align: left"></td>
        </tr>
        <tr>
            <td>邮箱</td>
            <td id="user_detail_email_val" style="text-align: left"></td>
            <td>地址</td>
            <td id="user_detail_address_val" style="text-align: left"></td>
        </tr>
        <tr>
            <td>注册时间</td>
            <td id="user_detail_regiter_val" style="text-align: left"></td>
            <td>最后一次登录时间</td>
            <td id="user_detail_last_val" style="text-align: left"></td>
        </tr>
        <tr>
            <td>用户状态</td>
            <td id="user_detail_status_val" style="text-align: left"></td>
            <td></td>
            <td></td>
        </tr>
    </table>
</div>
</body>
</html>
<script src="${pageContext.request.contextPath}/lib/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
    function reback() {
        document.getElementById("bg").style.display = "none";
        document.getElementById("user_detail_div").style.display = "none";
    }

    /**
     * 点击用户名显示弹出详细信息
     * */
    function queryUserDetail(username) {
        console.log("username=" + username);
        <c:forEach var="user" items="${requestScope.userList}">
        if (username == "${user.username}") {
            $("#user_detail_sex_val").text("${user.sex}"==0?"男":"女");
            $("#user_detail_age_val").text("${user.age}");
            $("#user_detail_phone_val").text("${user.phone}");
            $("#user_detail_email_val").text("${user.email}");
            $("#user_detail_address_val").text("${user.address}");
            $("#user_detail_regiter_val").text("${user.createTime}");
            $("#user_detail_last_val").text("${user.lastLoginTime}");
            $("#user_detail_status_val").text("${user.userStatus}"==0?"生效":"未生效");
        }
        </c:forEach>
        document.getElementById("bg").style.display = "block";
        document.getElementById("user_detail_div").style.display = "block";
    }
</script>




















