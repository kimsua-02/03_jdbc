package com.ohgiraffers.section02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application03 {
    public static void main(String[] args) {

        // 성씨를 입력 받아 해당 성을 가진 사원 조회
        // select emp_id, emp_name from employee where emp_name like concat(?,'%');


        Scanner scr = new Scanner(System.in);
        System.out.println("입력하신 성 씨로 사원을 조회합니다. 성 을 입력해주세요.");
        String name = scr.nextLine();
        String query = "SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_NAME LIKE concat(?,'%')";

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;


        try{
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, name);
            rset = pstmt.executeQuery();

            while (rset.next()) {
                System.out.println(rset.getString(1) + " " + rset.getString(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(pstmt);
            close(rset);
        }

    }
}
