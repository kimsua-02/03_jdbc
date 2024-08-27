package com.ohgiraffers.understand;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application02 {
    public static void main(String[] args) {

        // 선동일 사원의 사번, 이름, 이메일, 전화번호 직급 출력 - 직급은 job 테이블

        Connection con = getConnection();
        Statement stmt = null;
        ResultSet rset = null;

            try {
                stmt = con.createStatement();
                rset = stmt.executeQuery("select emp_id, emp_name, email, phone");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }
}
