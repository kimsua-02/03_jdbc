package com.ohgiraffers.section03;

import java.sql.Connection;
import java.sql.SQLException;

import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application {
    public static void main(String[] args) {

        // transaction 처리

        Connection con = getConnection();

        try {
            con.setAutoCommit(false);

            System.out.println("autoCommit : " + con.getAutoCommit());

            con.commit();
            // con.rollback();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
