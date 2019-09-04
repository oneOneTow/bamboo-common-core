package com.luzhiqing.common.util;

import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JooqUtil {
    private static String driver="com.mysql.jdbc.Driver";
    private static String url="jdbc:mysql://119.29.75.134:3306/jooq";
    private static String username="vens";
    private static String password="lzq520WW";
    public static DSLContext getContext()  {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return DSL.using(connection);
    }
}

