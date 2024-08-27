//package com.ohgiraffers.common
//
//import java.sql.Connection
//import java.sql.DriverManager
//import java.sql.SQLException
//import java.util.*
//
//public class JDBCTemplate {
//    public static Connection getConnection() {
//
//        Connection con = null;
//        Properties prop = new Properties();
//
//        try {
//            prop.load(new FileReader ("src/main/resources/java.properties"));
//            String url = prop.getProperty ("url");
//            String user = prop.getProperty ("user");
//            String password = prop.getProperty ("password");
//
//            con = DriverManager.getConnection(url, user, password);
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }finally {
//
//        }
//
//    }
//}