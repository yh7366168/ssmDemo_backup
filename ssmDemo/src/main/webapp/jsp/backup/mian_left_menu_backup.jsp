<%--
  Created by IntelliJ IDEA.
  User: T430487
  Date: 2019/5/22
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>main-left</title>
    <link rel="shortcut icon" href="#"/>
    <style type="text/css">
        .menu_tree{
            background-color: rgba(45, 39, 61, 0.2);
            width: 100%;
            height: 100%;
        }

        ul li{
            /*消除ul和li前面的小圆点*/
            list-style: none;
        }

        /*清楚子菜单的缩进*/
        ul{
            margin: 0;
            padding: 0;
        }

        /*设置一级菜单的背景颜色*/
        .main > a{
            /*消除链接下面的下划线*/
            text-decoration: none;
            background-color: rgba(61, 36, 58, 0.27);
            font-size: 18px;
            color: black;
            display: block;
            width: 200px;
            padding-top: 3px;
        }

        /*二级菜单*/
        .menu a{
            text-decoration: none;
            background-color: white;
            margin-left: 20px;
            font-size: 16px;
            display: block;
            padding-top: 3px;
        }
    </style>
</head>
<body>
    <ul class="menu_tree">
        <li class="main">
            <a href="#">菜单1</a>
            <ul class="menu">
                <li><a href="#">子菜单1</a></li>
                <li><a href="#">子菜单2</a></li>
                <li><a href="#">子菜单3</a></li>
            </ul>
        </li>
        <li class="main">
            <a href="#">菜单2</a>
            <ul class="menu" target="_blank">
                <li><a href="#">子菜单1</a></li>
                <li><a href="#">子菜单2</a></li>
                <li><a href="#">子菜单3</a></li>
            </ul>
        </li>
        <li class="main">
            <a href="#">菜单3</a>
            <ul class="menu" >
                <li><a href="#">子菜单1</a></li>
                <li><a href="#">子菜单2</a></li>
                <li><a href="#">子菜单3</a></li>
            </ul>
        </li>
    </ul>
</body>
</html>

<script src="${pageContext.request.contextPath}/lib/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
    $(function () {

        $(".main > ul").slideUp(0);

        $(".main > a").click(function () {
            var thisNode = $(this).next("ul");
            //先关闭所有的
            $(".main > ul").slideUp(1);
            //再打开
            thisNode.slideDown();
        });
    });
</script>
