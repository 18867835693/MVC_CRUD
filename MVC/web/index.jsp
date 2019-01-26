<%@ page import="java.util.List" %>
<%@ page import="com.sjlx.mvcapp.domain.Customer"%>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/23 0023
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>员工表CRUD</title>
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript">
        $(function(){
            $(".delete").click(function () {
                var flag =confirm("确定要删除此条信息么？");
                return flag;
            });
        });
    </script>
</head>
<body>

<form action="customerServlet?method=query" method="post">
    <table>
        <tr>
            <td>姓名：</td>
            <td><input type="text" name="name"/></td>
        </tr>
        <tr>
            <td>地址：</td>
            <td><input type="text" name="address"/></td>
        </tr>
        <tr>
            <td>电话：</td>
            <td><input type="text" name="phone"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="查询"/></td>
            <td><a href="newcustomer.jsp">添加用户</a></td>
        </tr>
    </table>
</form>

<%
    List<Customer> customers = (List<Customer>) request.getAttribute("customers");
    if (customers != null && customers.size() > 0) {

%>

<hr>
<br><br>

<table border="1" cellpadding="10" cellspacing="0">
    <tr>
        <th>编号</th>
        <th>姓名</th>
        <th>地址</th>
        <th>电话</th>
        <th>更新/删除</th>
    </tr>

    <%
        for (Customer customer : customers) {
    %>

    <tr>
        <td><%= customer.getId() %>
        </td>
        <td><%= customer.getName() %>
        </td>
        <td><%= customer.getAddress() %>
        </td>
        <td><%= customer.getPhone() %>
        </td>
        <td>
            <a href="customerServlet?method=modify&id=<%= customer.getId()%>">更新</a>
            <a class="delete" href="customerServlet?method=delete&id=<%= customer.getId()%>">删除</a>
        </td>
    </tr>

    <%
        }
    %>

</table>

<%
    }
%>
</body>
</html>
