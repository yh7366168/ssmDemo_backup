<%--
  Created by IntelliJ IDEA.
  User: T430487
  Date: 2019/5/20
  Time: 9:23
  To change this template use File | Settings | File Templates.
--%>
<%-- 测试easyUI--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>test jsp</title>
    <link rel="shortcut icon" href="#"/>
    <link href="${pageContext.request.contextPath}/lib/jquery-easyui-1.8.1/themes/default/easyui.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/lib/jquery-easyui-1.8.1/themes/icon.css" rel="stylesheet" type="text/css" />
    <script src="${pageContext.request.contextPath}/lib/jquery-3.4.1.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/lib/jquery-easyui-1.8.1/jquery.easyui.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/lib/jquery-easyui-1.8.1/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8" />
</head>
<body>
    <div onclick="confimFun()">删除</div>
</body>
</html>
<script type="text/javascript">
    function confimFun() {
        $.messager.confirm("操作提示：","确认删除吗" , function (data) {
            if(data){
                $.messager.alert("操作提示！","操作确认！");
            }else{
                $.messager.alert("操作提示！","选择取消！")
            }
        } );
    }
</script>

