package com.sjlx.mvcapp.dao;

/**
 * @author Facewaller
 * @create 2019-01-23
 */

import com.sjlx.mvcapp.db.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 封装了基本的CRUD的方法
 * 当前 DAO 直接在方法中获取数据库连接
 *
 * @param <T>当前DAO处理的实体类的类型是什么
 */
public class DAO<T> {

    private QueryRunner queryRunner = new QueryRunner();

    private Class<T> clazz;

    public DAO() {
        Type superClass = getClass().getGenericSuperclass();
        if (superClass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) superClass;

            Type[] typeArgs = parameterizedType.getActualTypeArguments();
            if (typeArgs[0] instanceof Class) {
                clazz = (Class<T>) typeArgs[0];
            }
        }
    }

    public <E> E getForValue(String sql, Object... args) {
        try {
            try (Connection connection = JdbcUtils.getConnection()) {
                return (E)queryRunner.query(connection,sql,new ScalarHandler(),args);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<T> getForList(String sql, Object... args) {
        try {
            try (Connection connection = JdbcUtils.getConnection()) {
                return queryRunner.query(connection,sql,new BeanListHandler<>(clazz),args);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public T get(String sql, Object... args) {

        try {
            try (Connection connection = JdbcUtils.getConnection()) {
                return queryRunner.query(connection, sql, new BeanHandler<>(clazz), args);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(String sql, Object... args) {
        try {
            try (Connection connection = JdbcUtils.getConnection()) {
                queryRunner.update(connection, sql, args);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
