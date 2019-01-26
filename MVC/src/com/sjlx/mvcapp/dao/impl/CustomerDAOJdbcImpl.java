package com.sjlx.mvcapp.dao.impl;

import com.sjlx.mvcapp.dao.ConditionCustomer;
import com.sjlx.mvcapp.dao.CustomerDAO;
import com.sjlx.mvcapp.dao.DAO;
import com.sjlx.mvcapp.domain.Customer;

import java.util.List;

/**
 * @author Facewaller
 * @create 2019-01-23
 */
public class CustomerDAOJdbcImpl extends DAO<Customer> implements CustomerDAO {

    @Override
    public List<Customer> getAll() {
        String sql = "SELECT id,name,address,phone FROM customers";
        return getForList(sql);
    }

    @Override
    public List<Customer> getAllWithCondition(ConditionCustomer conditionCustomer) {
        String sql = "SELECT id,name,address,phone FROM customers WHERE name LIKE ? AND address LIKE ? AND phone LIKE ?";
        return getForList(sql, conditionCustomer.getName(), conditionCustomer.getAddress(), conditionCustomer.getPhone());
    }


    @Override
    public void save(Customer customer) {
        String sql = "INSERT INTO customers(name,address,phone) VALUES(?,?,?)";
        update(sql, customer.getName(), customer.getAddress(), customer.getPhone());
    }

    @Override
    public Customer get(Integer id) {
        String sql = "SELECT id,name,address,phone FROM customers WHERE id = ?";
        return get(sql, id);
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM customers WHERE id = ?";
        update(sql, id);
    }

    @Override
    public long getCountWithName(String name) {
        String sql = "SELECT count(id) FROM customers WHERE name = ?";

        return getForValue(sql, name);
    }

    @Override
    public void update(Customer customer) {
        String sql = "UPDATE customers SET name = ?,address = ?,phone = ? WHERE id =?";
        update(sql,customer.getName(),customer.getAddress(),customer.getPhone(),customer.getId());
    }
}
