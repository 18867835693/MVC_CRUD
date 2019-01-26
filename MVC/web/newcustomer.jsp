<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/24 0024
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加新用户</title>
</head>
<body>

<%
    Object message = request.getAttribute("message");
    if (message != null) {
%>
<h4 style="color: red"><%= message%></h4>
<%
    }
%>
<form action="customerServlet?method=add" method="post">
    <table>
        <tr>
            <td>姓名：</td>
            <td><input type="text" name="name"
                       value="<%= request.getParameter("name") == null ? "" :request.getParameter("name")%>"/></td>
        </tr>
        <tr>
            <td>地址：</td>
            <td><input type="text" name="address"
                       value="<%= request.getParameter("address") == null ? "" :request.getParameter("address")%>"/>
            </td>
        </tr>
        <tr>
            <td>电话：</td>
            <td><input type="text" name="phone"
                       value="<%= request.getParameter("phone") == null ? "" :request.getParameter("phone")%>"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="确认添加"/></td>
        </tr>
    </table>
</form>
</body>
</html>
