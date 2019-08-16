<%--
  Created by IntelliJ IDEA.
  User: T430487
  Date: 2019/8/15
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="shortcut icon" href="#"/>
</head>
<body>

</body>
</html>
<script src="${pageContext.request.contextPath}/lib/jquery-3.4.1.min.js"></script>
<script type="text/javascript">

    function userRoleFun(curMenuId){
        //把全部按钮都先隐藏，然后显示具有权限的按钮
        $(".add_button_clz").css("display", "none");
        $(".update_button_clz").css("display", "none");
        $(".delete_button_clz").css("display", "none");
        $(".check_button_clz").css("display", "none");
        <c:forEach  items="${sessionScope.roleMenuListVO}" var="roleMenuVar">
        var buttonId = ${roleMenuVar.buttonId};
        var menuId = ${roleMenuVar.menuId};
        if(menuId == curMenuId){
            if(buttonId == 1){
                //能看到对应的页面就表面有对应的查询权限
            }else if(buttonId == 2){
                $(".add_button_clz").css("display","inline");
            }else if(buttonId == 3){
                $(".update_button_clz").css("display","inline");
            }else if(buttonId == 4){
                $(".delete_button_clz").css("display","inline");
            }else if(buttonId == 5){
                $(".check_button_clz").css("display","inline");
            }else{
                //其他按钮不扩展
            }
        }
        </c:forEach>
    }
</script>
