<%--
  Created by IntelliJ IDEA.
  User: T430487
  Date: 2019/7/12
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>角色权限详细页面</title>
    <link rel="shortcut icon" href="#"/>

    <style type="text/css">
        .one_menu_tr td {
             font-size: 16px;
         }
        .two_menu_tr td {
            text-indent: 40px;
        }
        .role_detail_checkbox_tr td {
            text-indent: 40px;
            width: 250px;
        }
        tr {
            height: 30px;
        }
    </style>
</head>
<body>
<div style="margin-left: 45px;margin-top: 30px;">
    <div style="margin-bottom: 20px">
        <button id="roleDetail_update_button" style="width: 60px;height: 30px; margin-right: 20px;"
                onclick="updateRoleFun()">维护
        </button>
        <button id="roleDetail_save_button" style="width: 60px;height: 30px; margin-left: 20px; display: none"
                onclick="saveRoleFun('${roleDetailVO.roleId}')">保存
        </button>
        <button id="roleDetail_return_button" style="width: 60px;height: 30px;" onclick="">返回</button>
    </div>
    <div style="margin-top: 10px;margin-bottom: 10px;">
        <span>角色名</span> <input type="text" value="${roleDetailVO.roleName}" style="margin-right: 16px;" disabled="disabled">
    </div>
    <div style="text-align: center; width: 100%; height: 20px;">角色菜单配置</div>
    <div>
        <table>
            <!-- 模块一 系统管理-->
            <tr class="one_menu_tr">
                <td>系统管理</td>
            </tr>
            <tr class="two_menu_tr">
                <td>用户管理</td>
            </tr>
            <tr class="role_detail_checkbox_tr">
                <td><input type="checkbox" id="userIsSelect">查询</td>
                <td><input type="checkbox" id="userIsAdd">新增</td>
                <td><input type="checkbox" id="userIsUpdate">修改</td>
                <td><input type="checkbox" id="userIsDelete">删除</td>
            </tr>
            <tr class="two_menu_tr">
                <td>菜单管理</td>
            </tr>
            <tr class="role_detail_checkbox_tr">
                <td><input type="checkbox" id="menuIsSelect">查询</td>
                <td><input type="checkbox" id="menuIsAdd">新增</td>
                <td><input type="checkbox" id="menuIsUpdate">修改</td>
                <td><input type="checkbox" id="menuIsDelete">删除</td>
            </tr>
            <tr class="two_menu_tr">
                <td>角色管理</td>
            </tr>
            <tr class="role_detail_checkbox_tr">
                <td><input type="checkbox" id="roleIsSelect">查询</td>
                <td><input type="checkbox" id="roleIsAdd">新增</td>
                <td><input type="checkbox" id="roleIsUpdate">修改</td>
                <td><input type="checkbox" id="roleIsDelete">删除</td>
            </tr>
            <!-- 模块二 功能管理-->
            <tr class="one_menu_tr">
                <td>功能管理</td>
            </tr>
            <tr class="two_menu_tr">
                <td>黑名单管理</td>
            </tr>
            <tr class="role_detail_checkbox_tr">
                <td><input type="checkbox" id="whiteListIsSelect">查询</td>
                <td><input type="checkbox" id="whiteListIsAdd">新增</td>
                <td><input type="checkbox" id="whiteListIsUpdate">修改</td>
                <td><input type="checkbox" id="whiteListIsDelete">删除</td>
            </tr>
            <tr class="two_menu_tr">
                <td>白名单管理</td>
            </tr>
            <tr class="role_detail_checkbox_tr">
                <td><input type="checkbox" id="blackListIsSelect">查询</td>
                <td><input type="checkbox" id="blackListIsAdd">新增</td>
                <td><input type="checkbox" id="blackListIsUpdate">修改</td>
                <td><input type="checkbox" id="blackListIsDelete">删除</td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>

<script src="${pageContext.request.contextPath}/lib/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
    $(function () {
        isPropChecked(${roleDetailVO.userIsSelect}, $("#userIsSelect"));
        isPropChecked(${roleDetailVO.userIsAdd}, $("#userIsAdd"));
        isPropChecked(${roleDetailVO.userIsUpdate}, $("#userIsUpdate"));
        isPropChecked(${roleDetailVO.userIsDelete}, $("#userIsDelete"));
        isPropChecked(${roleDetailVO.menuIsSelect}, $("#menuIsSelect"));
        isPropChecked(${roleDetailVO.menuIsAdd}, $("#menuIsAdd"));
        isPropChecked(${roleDetailVO.menuIsUpdate}, $("#menuIsUpdate"));
        isPropChecked(${roleDetailVO.menuIsDelete}, $("#menuIsDelete"));
        isPropChecked(${roleDetailVO.roleIsSelect}, $("#roleIsSelect"));
        isPropChecked(${roleDetailVO.roleIsAdd}, $("#roleIsAdd"));
        isPropChecked(${roleDetailVO.roleIsUpdate}, $("#roleIsUpdate"));
        isPropChecked(${roleDetailVO.roleIsDelete}, $("#roleIsDelete"));
        isPropChecked(${roleDetailVO.whiteListIsSelect}, $("#whiteListIsSelect"));
        isPropChecked(${roleDetailVO.whiteListIsAdd}, $("#whiteListIsAdd"));
        isPropChecked(${roleDetailVO.whiteListIsUpdate}, $("#whiteListIsUpdate"));
        isPropChecked(${roleDetailVO.whiteListIsDelete}, $("#whiteListIsDelete"));
        isPropChecked(${roleDetailVO.blackListIsSelect}, $("#blackListIsSelect"));
        isPropChecked(${roleDetailVO.blackListIsAdd}, $("#blackListIsAdd"));
        isPropChecked(${roleDetailVO.blackListIsUpdate}, $("#blackListIsUpdate"));
        isPropChecked(${roleDetailVO.blackListIsDelete}, $("#blackListIsDelete"));
        //复选框默认不可用
        $("input[type=checkbox]").attr("disabled", true);

    });

    function isPropChecked(var1, var2) {
        if (var1 != null && var1 != undefined && typeof var1 == "boolean") {
            if (var1) {
                var2.prop("checked", true);
            } else {
                var2.prop("checked", false);
            }
        }
    }

    /*点击维护之后，保存按钮出现，复选框可选*/
    function updateRoleFun() {
        $("#roleDetail_update_button").css("display", "none");
        $("#roleDetail_save_button").css("display", "inline");
        $("input[type=checkbox]").attr("disabled", false);
    }

    /*点击保存，维护数据库，刷新页面*/
    function saveRoleFun(roleId) {
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/roleMenu/saveRoleMenu",
            async: false,
            data: {
                "roleId":roleId,
                "userIsSelect": $("#userIsSelect").is(":checked"),
                "userIsAdd": $("#userIsAdd").is(":checked"),
                "userIsUpdate": $("#userIsUpdate").is(":checked"),
                "userIsDelete": $("#userIsDelete").is(":checked"),
                "menuIsSelect": $("#menuIsSelect").is(":checked"),
                "menuIsAdd": $("#menuIsAdd").is(":checked"),
                "menuIsUpdate": $("#menuIsUpdate").is(":checked"),
                "menuIsDelete": $("#menuIsDelete").is(":checked"),
                "roleIsSelect": $("#roleIsSelect").is(":checked"),
                "roleIsAdd": $("#roleIsAdd").is(":checked"),
                "roleIsUpdate": $("#roleIsUpdate").is(":checked"),
                "roleIsDelete": $("#roleIsDelete").is(":checked"),
                "whiteListIsSelect": $("#whiteListIsSelect").is(":checked"),
                "whiteListIsAdd": $("#whiteListIsAdd").is(":checked"),
                "whiteListIsUpdate": $("#whiteListIsUpdate").is(":checked"),
                "whiteListIsDelete": $("#whiteListIsDelete").is(":checked"),
                "blackListIsSelect": $("#blackListIsSelect").is(":checked"),
                "blackListIsAdd": $("#blackListIsAdd").is(":checked"),
                "blackListIsUpdate": $("#blackListIsUpdate").is(":checked"),
                "blackListIsDelete": $("#blackListIsDelete").is(":checked")
            },
            success: function (data) {
                $("#right").html(data);
            },
            error: function (data) {
                alertUtil("error!")
            }
        });
    }


</script>
