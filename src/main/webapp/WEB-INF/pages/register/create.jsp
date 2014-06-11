<%@ page import="java.io.BufferedOutputStream" %>
<%--
  Created by IntelliJ IDEA.
  User: LIUYONG
  Date: 14-1-31
  Time: 下午8:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/include-top.jsp"%>
<html>
    <head>
        <script>
            $(document).ready(function() {
                $('#submitForm').validate();
            });
            function validatePW(value) {
                var pw =  document.getElementById('pw').value;
                if(value!=pw) {
                    document.getElementById('pwconfirm').setAttribute('style','border-color:red;');
                } else {
                    document.getElementById('pwconfirm').setAttribute('style', '');
                    document.getElementById('pwinfo').innerHTML='';
                }
            }

            function isUsernameAvailable(name, value, url) {
                if(value.length == 0) {
                    document.getElementById('usernameinfo').innerHTML="";
                    return;
                }
                ajaxCallBack(name, value, url, function() {
                    if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                        document.getElementById('usernameinfo').innerHTML = xmlhttp.responseText;
                    }
                });
            }

            function isEmailAvailable(name, value, url) {
                if(value.length == 0) {
                    document.getElementById('emailinfo').innerHTML="";
                    return;
                }
                ajaxCallBack(name, value, url, function() {
                    if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                        document.getElementById('emailinfo').innerHTML = xmlhttp.responseText;
                    }
                });
            }
        </script>
        <title>新用户注册</title>
    </head>
<body>
    <div>
        <div id="form">
            <form id="submitForm" action="<%=basePath%>/register/create"
                  method="POST">
                <table style="width: 80%">
                    <tr>
                        <th class="formTh">username:</th>
                        <td class="formTd">
                            <input class="required" type="text" name="username" size="27" placeholder="请输入用户名"
                               onkeyup="isUsernameAvailable(this.name, this.value, '/register/usernameValidate')"
                               onblur="isUsernameAvailable(this.name, this.value, '/register/usernameValidate')"/>
                            <span id="usernameinfo" class="info"></span>
                        </td>
                    </tr>
                    <tr>
                        <th class="formTh">password:</th>
                        <td class="formTd">
                            <input id="pw" class="required alphanumeric" minlength="6" maxlength="20" type="password"
                                   name="password" size="27" placeholder="请输入密码"/>
                            <input id="view" type="button" style="width: 40px;"
                                   onmousedown="displayPW(document.getElementById('pw'))"
                                   onmouseup="hiddenPW(document.getElementById('pw'))" />
                        </td>
                    </tr>
                    <tr>
                        <th class="formTh">confirm:</th>
                        <td class="formTd">
                            <input class="required" equalto="#pw" type="password" id="pwconfirm"
                                   size="27" placeholder="请确认密码" onkeyup="validatePW(this.value)"/>
                            <span id="pwinfo" class="info" style="color: red;"></span>
                        </td>
                    </tr>
                    <tr>
                        <th class="formTh">sex:</th>
                        <td class="formTd">
                            <select name="UserInfo.sex">
                                <option value="男">男</option>
                                <option value="女">女</option>
                                <option value="其他">其他</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th class="formTh">age:</th>
                        <td class="formTd">
                            <input class="required digits" min="3" max="100" type="text" name="UserInfo.age" size="27" placeholder="请输入年龄"/>
                        </td>
                    </tr>
                    <tr>
                        <th class="formTh">email:</th>
                        <td class="formTd">
                            <input class="required email" type="text" name="UserInfo.email" size="27" placeholder="请输入常用联系邮箱"
                                   onkeyup="isEmailAvailable(this.name, this.value, '/register/emailValidate')"
                                    onblur="isEmailAvailable(this.name, this.value, '/register/emailValidate')"/>
                            <span id="emailinfo" class="info"></span>
                        </td>
                    </tr>
                        <td colspan="2" id="formSubmitTd">
                            <input type="submit" name="submit" value="注册">
                            <input type="reset" name="reset" value="重置" />
                        </td>
                </table>
            </form>
            <c:if test="${msg != null && msg != ''}">
            <p style="text-align: center; color: red;">${msg},如果长时间未收到请
                <a href="<%=basePath%>/register/reSendEmail/${userId}" >重新发送</a></p>
            </c:if>
        </div>
    </div>
</body>
</html>
<%@ include file="/WEB-INF/pages/tail.jsp"%>
