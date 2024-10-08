package com.ohgiraffers.section02.template;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCTemplate {

    public static Connection getConnection() {

        Connection con = null;
        Properties prop = new Properties();

        try {
            prop.load(new FileReader("src/main/java/com/ohgiraffers/config/connection-info.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String url = prop.getProperty("url");
        String user = prop.getProperty("user");
        String password = prop.getProperty("password");

        try {
            con = DriverManager.getConnection(url,user,password);
            System.out.println(con);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }
    public static void close(Connection con) {
        try {
            con.close();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
