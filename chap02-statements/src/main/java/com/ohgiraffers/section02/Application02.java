package com.ohgiraffers.section02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application02 {
    public static void main(String[] args) {

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        /*
        *
        * preparedStatement
        * - 완선된 쿼리문과 미완성된 쿼리문을 모두 사용할 수 있다.
        * - 미완성 쿼리 = 위치홀더를 사용한 쿼리문 (?)
        * */

        try {
            pstmt = con.prepareStatement("SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_ID = ?");
            pstmt.setString(1,"200"); // 인덱스지만 1번부터 시작함. (1번 모니터에 200을 넣겠다는 의미).
            rset = pstmt.executeQuery(); // 이 행의 executeQuery 가 실행되는 시점에 1번 모니터에 200이 들어가게 되어 '200 선동일'이 출력됨

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
