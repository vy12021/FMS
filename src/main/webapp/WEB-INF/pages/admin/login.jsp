<%--
  Created by IntelliJ IDEA.
  User: LIUYONG
  Date: 14-1-31
  Time: 下午8:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/include-top.jsp"%>
<html>
    <head>
        <script>
            $(document).ready(function() {
                $('#submitForm').validate();
                refreshcode('','','/admin/validatecode');
            });
            function refreshcode(name, value, url) {
                ajaxCallBack(name, value, url, function() {
                    if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                        document.getElementById('validatecode').src = '<%=basePath%>'+xmlhttp.responseText;
                    }
                });
            }

        </script>
        <title>用户登录</title>
    </head>
<body>
    <div id="content">
        <div id="logo">
        </div>
        <div id="form">
            <form id="submitForm" action="<%=basePath%>/admin/login" method="POST">
                <table class="formTable">
                    <tr style="height: 40px;">
                        <th class="formTh">用户名:</th>
                        <td class="formTd">
                            <input class="required loginInput" style="height: 38px; width: 250px;" type="text" name="username" placeholder="输入用户名" />
                        </td>
                    </tr>
                    <tr style="height: 40px;">
                        <th class="formTh">密&nbsp;&nbsp;码:</th>
                        <td class="formTd">
                            <input class="required loginInput" style="height: 38px; width: 250px;" type="password" name="password" size="38" placeholder="输入密码" />
                        </td>
                    </tr>
                    <tr style="height: 40px;">
                        <th class="formTh">验证码:</th>
                        <td class="formTd">
                            <input type="text" class="loginInput" name="validatecode" style="float: left; height: 38; width: 120px;"
                                    placeholder="输入验证码"/>
                            &nbsp;
                            <img id="validatecode" src=""
                                 style="float: inherit; width: 119px;height: 35px;"
                                 onclick="refreshcode(this.name,this.value,'/admin/validatecode')"/>
                            <span class="info">点图刷新</span>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" id="formSubmitTd" >
                            <input type="checkbox" style="height: inherit" name="rememberMe" value="true"/>
                            <span>记住我</span>
                            <input type="submit" value="登录">
                            <input type="reset" name="reset" value="重置" />
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <p style="text-align: center; color: red;">${loginMsg}</p>
    </div>
</body>
</html>
<%@ include file="/WEB-INF/pages/tail.jsp"%>
