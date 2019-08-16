<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>系统主页面</title>
    <link rel="shortcut icon" href="#"/>
    <style>
        /*--------整体布局----------*/
        html, body {
            height: 100%;
            margin: 0;
            padding: 0;
        }

        body {
            position: relative;
            background-image: none;
        }

        .top {
            width: 100%;
            height: 7.2%;
            background-color: #c6c7c5;
        }
        .top-line{
            width: 100%;
            height: 0.4%;
            background-color: #e2e2e2;
            margin: 0;
            padding: 0;
        }


        .leftDiv {
            position: absolute;
            right: 0;
            bottom: 0;
            left: 0;
            width: 240px;
            height: 92.5%;
            background-color: #f1f1f5;
        }

        .menuDiv {
            width: 98%;
            height: 100%;
        }

        #drap-line {
            position: absolute;
            top: 0;
            right: 0;
            width: 2%;
            height: 100%;
            background-color: #e2e2e2;
            margin-left: 2px;
            margin-right: 2px;
            border: 1px solid #cdceca;
            cursor: e-resize;
        }

        .rightDiv {
            position: absolute;
            right: 0;
            bottom: 0;
            left: 240px;
            height: 91%;
        }

        /*--------菜单树----------*/
        .menuDiv {
            background-color: #c6c7c5;
            width: 98%;
            height: 100%;
        }

        .menuDiv ul, li {
            /*消除ul和li前面的小圆点*/
            list-style: none;
            padding: 0px;
            margin: 0px;
        }

        /*清楚子菜单的缩进*/
        menuDiv ul {
            margin: 0;
            padding: 0;
        }

        /*设置一级菜单的背景颜色*/
        .main > a {
            /*消除链接下面的下划线*/
            text-decoration: none;
            background-color: #291d1d85;
            font-size: 18px;
            color: white;
            display: block;
            width: 100%;
            /*上下间隔*/
            padding-top: 7px;
            padding-bottom: 6px;
            /*文本靠左*/
            text-align: left;
            /*文本缩进*/
            text-indent: 8%;
            border: 1px solid #c1c1c1;
        }

        /*二级菜单*/
        .menu a {
            text-decoration: none;
            background-color: #c6c7c5;
            font-size: 16px;
            display: block;
            padding-top: 3px;
            width: 100%;
            text-align: left;
            text-indent: 16%;
            padding-top: 6px;
            padding-bottom: 6px;
            color:#4d4d4e;
        }
    </style>
</head>

<body>
<!-- 顶部导航 -->
<div>
    <div class="top">
        <div style="padding-top: 20px;text-align: right">
            <span style="margin-right: 20px;">用户：${userRoleVO.userName}</span>
            <span style="margin-right: 20px">用户角色:${userRoleVO.roleName}</span>
            <button style="width: 60px;height: 30px;margin-right: 50px" onclick="returnSystem()">退出</button>
        </div>
    </div>
    <div class="top-line"></div>
</div>

<!-- 左侧菜单栏 -->
<div id="left" class="leftDiv">
    <div id="menu" class="menuDiv">
        <ul class="menu_tree">
            <c:forEach items="${resultMapList}" var="entry" varStatus="vs">
                <li class="main">
                    <a href="#">${entry.key}</a>
                    <ul class="menu">
                        <c:forEach items="${entry.value}" var="menu" varStatus="status">
                            <li value="${menu.menuUrl}">
                                <a href="#" >${menu.menuName}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </li>
            </c:forEach>
        </ul>
    </div>
    <!-- 菜单左边界 -->
    <div id="drap-line" draggable="true"></div>
</div>

<div id="right" class="rightDiv">
    <jsp:include page="sys_index.jsp" flush="true"></jsp:include>
</div>

</body>
</html>
<script src="${pageContext.request.contextPath}/lib/jquery-3.4.1.min.js"></script>
<script>
    $(function () {
        /*------菜单树动画效果，点击一级菜单显示对应二级菜单----*/
        $(".main > ul").slideUp(0);
        $(".main > a").click(function () {
            var thisNode = $(this).next("ul");
            //先关闭所有的
            $(".main > ul").slideUp(1);
            //再打开
            thisNode.slideDown();
            return true;
        });

        /*点击菜单跳转对应的页面*/
        $(".menu li").click(function () {
            var menuUrl = $(this).attr("value");
            if(menuUrl==null || menuUrl=="" || menuUrl==undefined){
                alert("error！");
                return;
            }
            $.ajax({
                type:"GET",
                url:"${pageContext.request.contextPath}"+ menuUrl,
                data:{"curPage":"1"},
                dataType:"text",
                success:function (data) {
                    $("#right").html(data);
                }
            });
        });
    });

    function returnSystem() {
        window.location.href = "${pageContext.request.contextPath}/jsp/login/login.jsp";
    }

    var startPageX = 0;
    var endPageX = 0;
    var left = document.getElementById("left");
    var right = document.getElementById("right");
    var drapLine = document.getElementById("drap-line");
    window.onload = function () {
        /*
        * 鼠标开始拖动时触发
        * */
        drapLine.ondragstart = function (e) {
            startPageX = e.pageX;
        }
        /*
        * 鼠标拖动后触发
        * */
        drapLine.ondragend = function (e) {
            endPageX = e.pageX;
            if (endPageX != startPageX) {
                if (endPageX < 200) {
                    endPageX = 200;
                } else if (endPageX > 310) {
                    endPageX = 310;
                }
                left.style.width = endPageX + 'px';
                right.style.left = endPageX + 'px';
            }
        }
    }
</script>
