package com.sjlx.mvcapp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Facewaller
 * @create 2019-01-23
 */
public class JdbcUtils {


    private static String url = "jdbc:mysql://localhost:3306/books?useSSL=true";
    private static String user = "root";
    private static String pass = "0625";

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url,user,pass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
