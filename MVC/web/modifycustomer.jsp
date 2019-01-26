<%@ page import="com.sjlx.mvcapp.domain.Customer" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/25 0025
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改用户信息</title>
</head>
<body>
<%
    Object message = request.getAttribute("message");
    if (message != null) {
%>
<h4 style="color: red"><%= message%>
</h4>
<%
    }

    String id = null;
    String oldName = null;
    String name = null;
    String address = null;
    String phone = null;

    Customer customer = (Customer) request.getAttribute("customer");

    if (customer != null) {
        id = customer.getId() + "";
        oldName = customer.getName();
        name = customer.getName();
        address = customer.getAddress();
        phone = customer.getPhone();
    } else {
        id = request.getParameter("id");
        oldName = request.getParameter("oldName");
        name = request.getParameter("oldName");

        address = request.getParameter("address");
        phone = request.getParameter("phone");
    }
%>
<form action="customerServlet?method=update" method="post">

    <input type="hidden" name="id" value="<%= id %>"/>
    <input type="hidden" name="oldName" value="<%= oldName %>"/>

    <table>
        <tr>
            <td>姓名：</td>
            <td><input type="text" name="name" value="<%= name %>"/></td>
        </tr>
        <tr>
            <td>地址：</td>
            <td><input type="text" name="address" value="<%= address %>"/></td>
        </tr>
        <tr>
            <td>电话：</td>
            <td><input type="text" name="phone" value="<%= phone %>"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="修改"/></td>
        </tr>
    </table>
</form>
</body>
</html>
