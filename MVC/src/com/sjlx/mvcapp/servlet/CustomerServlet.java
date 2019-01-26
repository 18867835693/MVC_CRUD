package com.sjlx.mvcapp.servlet;

import com.sjlx.mvcapp.dao.ConditionCustomer;
import com.sjlx.mvcapp.dao.CustomerDAO;
import com.sjlx.mvcapp.dao.impl.CustomerDAOJdbcImpl;
import com.sjlx.mvcapp.domain.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Facewaller
 * @create 2019-01-24
 */
@WebServlet(name = "CustomerServlet", urlPatterns = "/customerServlet")
public class CustomerServlet extends HttpServlet {

    private CustomerDAO customerDAO = new CustomerDAOJdbcImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        switch (method) {
            case "add":
                add(request, response);
                break;
            case "delete":
                delete(request, response);
                break;
            case "modify":
                modify(request, response);
                break;
            case "query":
                query(request, response);
                break;
            case "update":
                update(request, response);
                break;
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String oldName = request.getParameter("oldName");

        System.out.println(id + name + address + phone + oldName);

        if(!oldName.equalsIgnoreCase(name)){

            long count = customerDAO.getCountWithName(name);
            System.out.println(count);
            if (count > 0) {
                request.setAttribute("message", "用户名" + name + "已经被占用, 请重新选择!");
                request.getRequestDispatcher("/modifycustomer.jsp").forward(request, response);
                return;
            }
        }

        Customer customer = new Customer(name,address,phone);
        customer.setId(Integer.parseInt(id));
        customerDAO.update(customer);
        response.sendRedirect("customerServlet?method=query");
        System.out.println("update");
    }

    private void modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String forwardPath = "/error.jsp";

        //1. 获取请求参数 id
        String idStr = request.getParameter("id");

        try {
            Customer customer = customerDAO.get(Integer.parseInt(idStr));
            if(customer != null){
                forwardPath = "/modifycustomer.jsp";
                //3. 将 customer 放入 request 中
                request.setAttribute("customer", customer);
            }
        } catch (NumberFormatException e) {}


        //4. 响应 modifycustomer.jsp 页面: 转发.
        request.getRequestDispatcher(forwardPath).forward(request, response);
        System.out.println("modify");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        int id = 0;
        try {
            id = Integer.parseInt(idStr);
            System.out.println(id);
            customerDAO.delete(id);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        response.sendRedirect("customerServlet?method=query");
    }

    private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");

        ConditionCustomer conditionCustomer = new ConditionCustomer(name, address, phone);

        List<Customer> customers = customerDAO.getAllWithCondition(conditionCustomer);

        System.out.println(customers);

        request.setAttribute("customers", customers);
        request.getRequestDispatcher("/index.jsp").forward(request, response);

    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");

        long count = customerDAO.getCountWithName(name);

        if (count > 0) {
            request.setAttribute("message", "用户名" + name + "已经被占用, 请重新选择!");
            request.getRequestDispatcher("/newcustomer.jsp").forward(request, response);
            return;
        }

        //3. 若验证通过, 则把表单参数封装为一个 Customer 对象 customer
        Customer customer = new Customer(name, address, phone);

        //4. 调用 CustomerDAO 的  save(Customer customer) 执行保存操作
        customerDAO.save(customer);

        response.sendRedirect("customerServlet?method=query");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
