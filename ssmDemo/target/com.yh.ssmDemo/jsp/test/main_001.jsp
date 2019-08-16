
<%--
    开发发现，使用鼠标事件出现卡顿，onmousedown、onmousemove、onmouseup
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>系统主页面</title>
    <link rel="shortcut icon" href="#"/>
    <style>
        /* css样式不建议使用id选择器，此处为方便暂时使用, 请见谅 */
        * {
            margin: 0;
            padding: 0;
        }

        html, body {
            height: 100%;
            text-align: center;
        }

        body {
            position: relative;
        }

        .top {
            width: 100%;
            height: 88px;
            /*background-color: #ccc;*/
            background-image: url(${pageContext.request.contextPath}/img/menu_top.png);
            background-size: cover;
            opacity: 0.7;
        }

        #left {
            position: absolute;
            top: 88px;
            right: 0;
            bottom: 0;
            left: 0;
            width: 220px;
        }

        #menu {
            width: 100%;
            height: 100%;
        }

        #drap-line {
            position: absolute;
            top: 88px;
            left:220px;
            right: 0;
            width: 4px;
            bottom: 0;
            background-color: #999;
            cursor: e-resize;
        }

        #right {
            position: absolute;
            top: 88px;
            right: 0;
            bottom: 0;
            left: 224px;
        }
    </style>
</head>

<body>
<!-- 顶部导航 -->
<div class="top">

</div>
<!-- 菜单 -->
<div id="left">
    <div id="menu">
        <div id="div1">页面1</div>
        <div>页面2</div>
    </div>
</div>
<!-- 菜单左边界 -->
<div id="drap-line"></div>
<!-- 主页面 -->
<div id="right">
    <iframe id="main_iframe" width="100%" height="100%" src="${pageContext.request.contextPath}/jsp/main/c.jsp"></iframe>
</div>

</body>
</html>
<script src="${pageContext.request.contextPath}/lib/jquery-3.4.1.min.js"></script>
<script>
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
                drapLine.style.left = endPageX + 'px';
                right.style.left = endPageX + drapLine.style.width + 'px';
            }
        }
    }

</script>
