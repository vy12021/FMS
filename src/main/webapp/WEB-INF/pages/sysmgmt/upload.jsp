<%--
  Created by IntelliJ IDEA.
  User: LIUYONG
  Date: 14-2-10
  Time: 上午12:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/include-top.jsp"%>
<html>
<head>
    <title>资源上传</title>
</head>
<body>
    <p align="center"> 请您选择需要上传的文件</p>
    <form id="form1" class="" name="form1" method="post"
          action="<%=basePath%>/sysmgmt/upload" enctype="multipart/form-data"
          style=" padding-top: 10%; padding-left: 40%;">
        <table class="formTable">
            <tr>
                <th class="formTh">上传文件：</th>
                <td class="formTd">
                    <input name="file" type="file" size="40" >
                </td>
            </tr>
            <tr align="center">
                <td colspan="2">
                    <input type="submit" name="submit" value="提交" >
                    <input type="reset" name="reset" value="重置" >
                </td>
            </tr>
        </table>
    </form>
    <p style="text-align: center; font-size: 15pt;">${msg}</p>

</body>
</html>
<%@ include file="/WEB-INF/pages/tail.jsp"%>
