<%--
  Created by IntelliJ IDEA.
  User: LIUYONG
  Date: 14-2-7
  Time: 上午3:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/include-top.jsp"%>
<html>
<head>
    <title>注册激活</title>
</head>
    <body>
        <div id="content">
            <p style="text-align: center; font-size: 15pt; color: red;">${msg}</p>
            <table class="formTable">
                <tr align="center">
                    <th width="35%">user:</th>
                    <td width="65%">${user.username}</td>
                </tr>
                <tr align="center">
                    <th width="35%">email:</th>
                    <td width="65%">${userInfo.email}</td>
                </tr>
            </table>
        </div>
    </body>
</html>
<%@ include file="/WEB-INF/pages/tail.jsp"%>
