package com.ohgiraffers.understand;

import com.ohgiraffers.dto.EmployeeDTO;

import java.sql.*;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application01 {
    public static void main(String[] args) {

        // employee 테이블에서 급여를 가장 많이 받는 사원 정보 출력
        // 출력할 사원의 사번, 이름, 이메일, 전화번호 직급 출력 - 직급은 job 테이블

        Connection con = getConnection();
        Statement stmt = null;
        ResultSet rset = null;
        EmployeeDTO selectEmp = null;

        String query = "select * from employee order by salary desc limit 1";

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            if (rset.next()) {

                selectEmp = new EmployeeDTO();  // 공간 할당. 이것을 해야 출력됨 ,,

                selectEmp.setEmpId(rset.getString("EMP_ID"));
                selectEmp.setEmpName(rset.getString("EMP_NAME"));
                selectEmp.setEmpNo(rset.getString("EMP_NO"));
                selectEmp.setEmail(rset.getString("EMAIL"));
                selectEmp.setPhone(rset.getString("PHONE"));
                selectEmp.setDeptCode(rset.getString("DEPT_CODE"));
                selectEmp.setJobCode(rset.getString("JOB_CODE"));
                selectEmp.setSalLevel(rset.getString("SAL_LEVEL"));
                selectEmp.setSalary(rset.getInt("SALARY"));
                selectEmp.setBonus(rset.getDouble("BONUS"));
                selectEmp.setManagerId(rset.getString("MANAGER_ID"));
                selectEmp.setHireDate(rset.getDate("HIRE_DATE"));
                selectEmp.setEntDate(rset.getDate("ENT_DATE"));
                selectEmp.setEntYn(rset.getString("ENT_YN"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(stmt);
            close(rset);
        }
        System.out.println("selectEmp = " + selectEmp);
    }
}
