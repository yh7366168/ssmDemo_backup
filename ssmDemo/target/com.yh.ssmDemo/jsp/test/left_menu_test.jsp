<%--
  Created by IntelliJ IDEA.
  User: T430487
  Date: 2019/5/22
  Time: 9:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Div+CSS+JS树型菜单，可刷新</title>
    <style type="text/css">

        *{margin:0;padding:0;border:0;}
        body {
            font-family: arial, 宋体, serif;
            font-size:25px;
        }
        #nav {
            width:180px;
            line-height: 24px;
            list-style-type: none;
            text-align:left;
            /*定义整个ul菜单的行高和背景色*/
        }
        /*==================一级目录===================*/
        #nav a {
            width: 160px;
            display: block;
            padding-left:20px;
            /*Width(一定要)，否则下面的Li会变形*/
        }
        #nav li {
            background:#CCC; /*一级目录的背景色*/
            border-bottom:#FFF 1px solid; /*下面的一条白边*/
            float:left;
            /*float：left,本不应该设置，但由于在Firefox不能正常显示
            继承Nav的width,限制宽度，li自动向下延伸*/
        }
        #nav li a:hover{
            background:#CC0000; /*一级目录onMouseOver显示的背景色*/
        }
        #nav a:link  {
            color:#666; text-decoration:none;
        }
        #nav a:visited  {
            color:#666;text-decoration:none;
        }
        #nav a:hover  {
            color:#FFF;text-decoration:none;font-weight:bold;
        }
        /*==================二级目录===================*/
        #nav li ul {
            list-style:none;
            text-align:left;
        }
        #nav li ul li{
            background: #EBEBEB; /*二级目录的背景色*/
        }
        #nav li ul a{
            padding-left:20px;
            width:160px;
            /* padding-left二级目录中文字向右移动，但Width必须重新设置=(总宽度-padding-left)*/
        }
        /*下面是二级目录的链接样式*/
        #nav li ul a:link  {
            color:#666; text-decoration:none;
        }
        #nav li ul a:visited  {
            color:#666;text-decoration:none;
        }
        #nav li ul a:hover {
            color:#F3F3F3;
            text-decoration:none;
            font-weight:normal;
            background:#CC0000;
            /* 二级onmouseover的字体颜色、背景色*/
        }
        /*==============================*/
        #nav li:hover ul {
            left: auto;
        }
        #nav li.sfhover ul {
            left: auto;
        }
        #content {
            clear: left;
        }
        #nav ul.collapsed {
            display: none;
        }
        -->
        #PARENT{
            width:300px;
            padding-left:20px;
        }
    </style>
</head>
<body>
<div id="PARENT">
    <ul id="nav">
        <li><a href="#Menu=ChildMenu1"  onclick="DoMenu('ChildMenu1')">我的网站</a>
            <ul id="ChildMenu1" class="collapsed">
                <li><a href="http://www.netany.net" target="_blank">我的网站1</a></li>
                <li><a href="http://www.netany.net" target="_blank">我的网站2</a></li>
                <li><a href="http://www.netany.net" target="_blank">我的网站3</a></li>
            </ul>
        </li>
        <li><a href="#Menu=ChildMenu2" onclick="DoMenu('ChildMenu2')">我的帐务</a>
            <ul id="ChildMenu2" class="collapsed">
                <a href="http://www.netany.net" target="_blank">支付</a></li>
                <li><a href="#">网上支付</a></li>
                <li><a href="#">登记汇款</a></li>
                <li><a href="#">在线招领</a></li>
                <li><a href="#">历史帐务</a></li>
            </ul>
        </li>
        <li><a href="#Menu=ChildMenu3" onclick="DoMenu('ChildMenu3')">网站管理</a>
            <ul id="ChildMenu3" class="collapsed">
                <li><a href="#">登录</a></li>
                <a href="http://www.netany.net" target="_blank">管理</a></li>
                <li><a href="#">管理</a></li>
                <li><a href="#">管理</a></li>
            </ul>
        </li>
        <li><a href="#Menu=ChildMenu4" onclick="DoMenu('ChildMenu4')">网站管理</a>
            <ul id="ChildMenu4" class="collapsed">
                <li><a href="#">登录</a></li>
                <a href="http://www.netany.net" target="_blank">管理</a></li>
                <li><a href="#">管理</a></li>
                <li><a href="#">管理</a></li>
            </ul>
        </li>
    </ul>
</div>

</body>
</html>
<script type=text/javascript>
    var LastLeftID = "";
    function menuFix() {
        var obj = document.getElementById("nav").getElementsByTagName("li");

        for (var i=0; i<obj.length; i++) {
            obj[i].onmouseover=function() {
                this.className+=(this.className.length>0? " ": "") + "sfhover";
            }
            obj[i].onMouseDown=function() {
                this.className+=(this.className.length>0? " ": "") + "sfhover";
            }
            obj[i].onMouseUp=function() {
                this.className+=(this.className.length>0? " ": "") + "sfhover";
            }
            obj[i].onmouseout=function() {
                this.className=this.className.replace(new RegExp("( ?|^)sfhover\\b"), "");
            }
        }
    }
    function DoMenu(emid)
    {
        var obj = document.getElementById(emid);
        obj.className = (obj.className.toLowerCase() == "expanded"?"collapsed":"expanded");
        if((LastLeftID!="")&&(emid!=LastLeftID))    //关闭上一个Menu
        {
            document.getElementById(LastLeftID).className = "collapsed";
        }
        LastLeftID = emid;
    }
    function GetMenuID()
    {
        var MenuID="";
        var _paramStr = new String(window.location.href);
        var _sharpPos = _paramStr.indexOf("#");

        if (_sharpPos >= 0 && _sharpPos < _paramStr.length - 1)
        {
            _paramStr = _paramStr.substring(_sharpPos + 1, _paramStr.length);
        }
        else
        {
            _paramStr = "";
        }

        if (_paramStr.length > 0)
        {
            var _paramArr = _paramStr.split("&");
            if (_paramArr.length>0)
            {
                var _paramKeyVal = _paramArr[0].split("=");
                if (_paramKeyVal.length>0)
                {
                    MenuID = _paramKeyVal[1];
                }
            }
            /*
            if (_paramArr.length>0)
            {
                var _arr = new Array(_paramArr.length);
            }
            //取所有#后面的，菜单只需用到Menu
            //for (var i = 0; i < _paramArr.length; i++)
            {
                var _paramKeyVal = _paramArr[i].split('=');
                if (_paramKeyVal.length>0)
                {
                    _arr[_paramKeyVal[0]] = _paramKeyVal[1];
                }
            }
            */
        }
        if(MenuID!="")
        {
            DoMenu(MenuID)
        }
    }
    GetMenuID();    //*这两个function的顺序要注意一下，不然在Firefox里GetMenuID()不起效果
    menuFix();
</script>