<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%--
  Created by IntelliJ IDEA.
  User: LIUYONG
  Date: 14-1-31
  Time: 下午8:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="../include.jsp" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>安全中心</title>
    <link rel="Shortcut Icon" href="<%=basePath%>/resources/style/images/favicon.ico"/>
    <link href="<%=basePath%>/resources/style/dwz/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
    <link href="<%=basePath%>/resources/style/dwz/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
    <link href="<%=basePath%>/resources/style/dwz/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
    <link href="<%=basePath%>/resources/style/dwz/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
    <!--[if IE]>
    <link href="<%=basePath%>/resources/style/dwz/themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
    <![endif]-->

    <!--[if lte IE 9]>
    <script src="<%=basePath%>/resources/style/dwz/js/speedup.js" type="text/javascript"></script>
    <![endif]-->

    <script src="<%=basePath%>/resources/style/dwz/js/jquery-1.7.2.js" type="text/javascript"></script>
    <script src="<%=basePath%>/resources/style/dwz/js/jquery.cookie.js" type="text/javascript"></script>
    <script src="<%=basePath%>/resources/style/dwz/js/jquery.validate.js" type="text/javascript"></script>
    <script src="<%=basePath%>/resources/style/dwz/js/jquery.bgiframe.js" type="text/javascript"></script>
    <script src="<%=basePath%>/resources/style/dwz/xheditor/xheditor-1.2.1.min.js" type="text/javascript"></script>
    <script src="<%=basePath%>/resources/style/dwz/xheditor/xheditor_lang/zh-cn.js" type="text/javascript"></script>
    <script src="<%=basePath%>/resources/style/dwz/uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script>

    <!-- svg图表  supports Firefox 3.0+, Safari 3.0+, Chrome 5.0+, Opera 9.5+ and Internet Explorer 6.0+ -->
    <script type="text/javascript" src="<%=basePath%>/resources/style/dwz/chart/raphael.js"></script>
    <script type="text/javascript" src="<%=basePath%>/resources/style/dwz/chart/g.raphael.js"></script>
    <script type="text/javascript" src="<%=basePath%>/resources/style/dwz/chart/g.bar.js"></script>
    <script type="text/javascript" src="<%=basePath%>/resources/style/dwz/chart/g.line.js"></script>
    <script type="text/javascript" src="<%=basePath%>/resources/style/dwz/chart/g.pie.js"></script>
    <script type="text/javascript" src="<%=basePath%>/resources/style/dwz/chart/g.dot.js"></script>

    <script src="<%=basePath%>/resources/style/dwz/js/dwz.core.js" type="text/javascript"></script>
    <script src="<%=basePath%>/resources/style/dwz/js/dwz.util.date.js" type="text/javascript"></script>
    <script src="<%=basePath%>/resources/style/dwz/js/dwz.validate.method.js" type="text/javascript"></script>
    <script src="<%=basePath%>/resources/style/dwz/js/dwz.barDrag.js" type="text/javascript"></script>
    <script src="<%=basePath%>/resources/style/dwz/js/dwz.drag.js" type="text/javascript"></script>
    <script src="<%=basePath%>/resources/style/dwz/js/dwz.tree.js" type="text/javascript"></script>
    <script src="<%=basePath%>/resources/style/dwz/js/dwz.accordion.js" type="text/javascript"></script>
    <script src="<%=basePath%>/resources/style/dwz/js/dwz.ui.js" type="text/javascript"></script>
    <script src="<%=basePath%>/resources/style/dwz/js/dwz.theme.js" type="text/javascript"></script>
    <script src="<%=basePath%>/resources/style/dwz/js/dwz.switchEnv.js" type="text/javascript"></script>
    <script src="<%=basePath%>/resources/style/dwz/js/dwz.alertMsg.js" type="text/javascript"></script>
    <script src="<%=basePath%>/resources/style/dwz/js/dwz.contextmenu.js" type="text/javascript"></script>
    <script src="<%=basePath%>/resources/style/dwz/js/dwz.navTab.js" type="text/javascript"></script>
    <script src="<%=basePath%>/resources/style/dwz/js/dwz.tab.js" type="text/javascript"></script>
    <script src="<%=basePath%>/resources/style/dwz/js/dwz.resize.js" type="text/javascript"></script>
    <script src="<%=basePath%>/resources/style/dwz/js/dwz.dialog.js" type="text/javascript"></script>
    <script src="<%=basePath%>/resources/style/dwz/js/dwz.dialogDrag.js" type="text/javascript"></script>
    <script src="<%=basePath%>/resources/style/dwz/js/dwz.sortDrag.js" type="text/javascript"></script>
    <script src="<%=basePath%>/resources/style/dwz/js/dwz.cssTable.js" type="text/javascript"></script>
    <script src="<%=basePath%>/resources/style/dwz/js/dwz.stable.js" type="text/javascript"></script>
    <script src="<%=basePath%>/resources/style/dwz/js/dwz.taskBar.js" type="text/javascript"></script>
    <script src="<%=basePath%>/resources/style/dwz/js/dwz.ajax.js" type="text/javascript"></script>
    <script src="<%=basePath%>/resources/style/dwz/js/dwz.pagination.js" type="text/javascript"></script>
    <script src="<%=basePath%>/resources/style/dwz/js/dwz.database.js" type="text/javascript"></script>
    <script src="<%=basePath%>/resources/style/dwz/js/dwz.datepicker.js" type="text/javascript"></script>
    <script src="<%=basePath%>/resources/style/dwz/js/dwz.effects.js" type="text/javascript"></script>
    <script src="<%=basePath%>/resources/style/dwz/js/dwz.panel.js" type="text/javascript"></script>
    <script src="<%=basePath%>/resources/style/dwz/js/dwz.checkbox.js" type="text/javascript"></script>
    <script src="<%=basePath%>/resources/style/dwz/js/dwz.history.js" type="text/javascript"></script>
    <script src="<%=basePath%>/resources/style/dwz/js/dwz.combox.js" type="text/javascript"></script>
    <script src="<%=basePath%>/resources/style/dwz/js/dwz.print.js" type="text/javascript"></script>

    <!-- 可以用dwz.min.js替换前面全部dwz.*.js (注意：替换是下面dwz.regional.zh.js还需要引入)
    <script src="bin/dwz.min.js" type="text/javascript"></script>
    -->
    <script src="<%=basePath%>/resources/style/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>
    <script src="<%=basePath%>/resources/style/js/sysmgmt.js"></script>
    <link rel="stylesheet" href="<%=basePath%>/resources/style/css/dwz.css" />

    <script type="text/javascript">
        $(function(){
            DWZ.init("<%=basePath%>/resources/style/dwz/dwz.frag.xml", {
                loginUrl:"login_dialog.html", loginTitle:"登录",	// 弹出登录对话框
//		loginUrl:"login.html",	// 跳到登录页面
                statusCode:{ok:200, error:300, timeout:301}, //【可选】
                pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
                debug:false,	// 调试模式 【true|false】
                callback:function(){
                    initEnv();
                    $("#themeList").theme({themeBase:"themes"}); // themeBase 相对于index页面的主题base路径
                }
            });
            $('#weatherBox').hide();
        });
        $(document).ready(function(){

        });
    </script>
</head>

<body scroll="no">
<div id="layout">
<div id="header">
    <div class="headerNav">
        <a class="logo" style="background:url(../resources/style/images/Logo_text.png) no-repeat;" href="<%=basePath%>">标志</a>
        <ul class="nav">
            <shiro:authenticated>
                <li><a href="<%=basePath%>/admin/home"><shiro:principal/></a></li>
            </shiro:authenticated>
            <li id="showWeather">
                <a href="javascript:">查看天气</a>
            </li>
            <li><a href="<%=basePath%>/sysmgmt/changepwd" target="dialog" width="600">设置</a></li>
            <shiro:authenticated>
                <li><a href="<%=basePath%>/admin/logout">注销</a></li>
            </shiro:authenticated>
        </ul>
        <ul id="module" class="module">
            <li class="moduleli">
                <a class="modulea" href="<%=basePath%>/sysmgmt/tree" target="ajax" rel="sidebar">
                    <img class="moduleimage" src="<%=basePath%>/resources/style/images/sysmgmt.png" alt=""/>
                    <span class="moduletext">系统设置</span>
                </a>
            </li>
            <li class="moduleli">
                <a class="modulea" href="" target="sidebar">
                    <img class="moduleimage" src="<%=basePath%>/resources/style/images/resourcemgmt.png" alt=""/>
                    <span class="moduletext">资源管理</span>
                </a>
            </li>
        </ul>
        <iframe id="weatherBox" name="ifm" allowtransparency="true" frameborder="0" width="387" height="98" scrolling="no"
                src="http://tianqi.2345.com/plugin/widget/index.htm?s=2&z=1&t=0&v=0&d=3&k=3&f=1&q=1&e=1&a=1&c=54511&w=387&h=98">
        </iframe>
    </div>

    <!-- navMenu -->

</div>

<div id="leftside">
    <div id="sidebar_s">
        <div class="collapse">
            <div class="toggleCollapse"><div></div></div>
        </div>
    </div>
    <div id="sidebar">
        <div class="toggleCollapse"><h2>主菜单</h2></div>

        <div class="accordion" fillSpace="sidebar">
            <div class="accordionHeader">
                <h2><span>Folder</span>界面组件</h2>
            </div>
            <div class="accordionContent">
                <ul class="tree treeFolder">
                    <li><a href="tabsPage.html" target="navTab">主框架面板</a>
                        <ul>
                            <li><a href="main.html" target="navTab" rel="main">我的主页</a></li>
                            <li><a href="http://www.baidu.com" target="navTab" rel="page1">页面一(外部页面)</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
            <div class="accordionHeader">
                <h2><span>Folder</span>流程演示</h2>
            </div>
            <div class="accordionContent">
                <ul class="tree">
                    <li><a href="newPage1.html" target="dialog" rel="dlg_page">列表</a></li>
                    <li><a href="newPage1.html" target="dialog" rel="dlg_page2">列表</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
    <div id="container">
        <div id="navTab" class="tabsPage">
            <div class="tabsPageHeader">
                <div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
                    <ul class="navTab-tab">
                        <li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
                    </ul>
                </div>
                <div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
                <div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
                <div class="tabsMore">more</div>
            </div>
            <ul class="tabsMoreList">
                <li><a href="javascript:;">我的主页</a></li>
            </ul>
            <div class="navTab-panel tabsPageContent layoutBox">
                <div class="page unitBox">
                    <div class="accountInfo">
                        <div class="alertInfo">
                        </div>
                        <div class="right">
                        </div>
                    </div>
                    <div class="pageFormContent" layoutH="80" style="margin-right:230px">
                        <div class="divider"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="footer">Copyright &copy; 2014 <a href="">KiteSong团队</a>00000000号</div>
</body>
</html>