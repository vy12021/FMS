<%@ page import="org.apache.shiro.subject.Subject" %>
<%@ page import="org.apache.shiro.SecurityUtils" %>
<%@ page import="org.apache.shiro.authc.UsernamePasswordToken" %>
<%@ page import="org.apache.shiro.session.Session" %>
<%--
  Created by IntelliJ IDEA.
  User: LIUYONG
  Date: 14-2-17
  Time: 下午12:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;

    Subject subject = SecurityUtils.getSubject();
    Session mySession = subject.getSession();
    mySession.setAttribute("bathPath", basePath);
%>
<script src="<%=basePath%>/resources/style/dwz/js/jquery-1.7.2.min.js"></script>
<script src="<%=basePath%>/resources/style/dwz/js/jquery.validate.min.js"></script>
<script src="<%=basePath%>/resources/style/dwz/js/dwz.validate.method.js" type="text/javascript"></script>
<script src="<%=basePath%>/resources/style/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>
<script src="<%=basePath%>/resources/style/js/sysmgmt.js"></script>
<link rel="stylesheet" href="<%=basePath%>/resources/style/css/common.css" />
<link rel="Shortcut Icon" href="<%=basePath%>/resources/style/images/favicon.ico"/>

<div id="u">
    <shiro:authenticated>
        <span><a href="<%=basePath%>/admin/home"><shiro:principal/></a></span>
    </shiro:authenticated>
    <shiro:notAuthenticated>
        <span><a href="<%=basePath%>/admin/login">登陆</a></span>
    </shiro:notAuthenticated>
    <shiro:notAuthenticated>
    <span><a href="<%=basePath%>/register/create">注册</a></span>
    </shiro:notAuthenticated>
    <shiro:authenticated>
    <shiro:hasRole name="admin">
        <span><a href="<%=basePath%>/sysmgmt/index">管理入口</a></span>
    </shiro:hasRole>
    </shiro:authenticated>
    <shiro:authenticated>
        <span><a href="<%=basePath%>/admin/logout">注销</a></span>
    </shiro:authenticated>
</div>