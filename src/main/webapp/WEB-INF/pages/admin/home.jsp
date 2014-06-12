<%--
  Created by IntelliJ IDEA.
  User: LIUYONG
  Date: 14-2-18
  Time: 上午2:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/include-top.jsp"%>
<html>
<head>
    <title>用户主页</title>
</head>
    <body>
        <div id="content">
            <p style="text-align: center; color: red;">${loginMsg}</p>
            <p style="text-align: center; color: red;">欢迎你</p>
            <c:if test="${u.activateStatus eq '0'}">
                <p style="text-align: center; color: red;"><a href="<%=basePath%>/register/reSendEmail/${u.id}" >账户还未激活请点击发送激活邮件</a></p>
            </c:if>
        </div>
    </body>
</html>
<%@ include file="/WEB-INF/pages/tail.jsp"%>
