package com.sjlx.mvcapp.dao;

import com.sjlx.mvcapp.domain.Customer;

import java.util.List;

/**
 * @author Facewaller
 * @create 2019-01-23
 */
public interface CustomerDAO {

    public List<Customer> getAll();

    public List<Customer> getAllWithCondition(ConditionCustomer conditionCustomer);

    public void save(Customer customer);

    public Customer get(Integer id);

    public void delete(Integer id);

    public long getCountWithName(String name);

    public void update(Customer customer);

}
