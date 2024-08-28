package com.ohgiraffers.section02.controller.dao;

import com.ohgiraffers.section02.controller.dto.MenuDTO;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.List;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class MenuDAO {
    // 데이터 엑세스 오브젝트 - 데이터베이스와 상호작용을 할 클래스

    private Properties prop = new Properties(); // 필드로 prop 객체 가짐.

    public MenuDAO(String url) { // 생성자(MenuDAO)를 생성할 때 꼭! url 을 입력받게 함.
        try {
            prop.loadFromXML(new FileInputStream(url));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int selectLastMenuCode(Connection con) { // 제일 최신 메뉴 코드 반환.
        Statement stmt = null;
        ResultSet rset = null;
        int maxCode = 0;

        String query = prop.getProperty("selectLastMenuCode");
        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            if (rset.next()) {
                maxCode = rset.getInt("MAX(MENU_CODE)");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(stmt);
            close(rset);
        }
        return maxCode;
    }

    public List<Map<Integer,String>> selectAllCategoryList(Connection con) {
        Statement stmt = null;
        ResultSet rset = null;

        List<Map<Integer, String>> categoryList = null;

        String query2 = prop.getProperty("selectAllCategoryList");
        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query2);
            categoryList = new ArrayList<>();

            while (rset.next()) {
                Map<Integer, String> categoryMap = new HashMap<Integer, String>();
                categoryMap.put(rset.getInt("CATEGORY_CODE"), rset.getString("CATEGORY_NAME"));
                categoryList.add(categoryMap);
            }
            return categoryList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(stmt);
            close(rset);
        }
    }

    public int insertMenu(Connection con, MenuDTO menuDTO) {
        PreparedStatement pstmt = null;
        int result = 0;

        String query = prop.getProperty("insertMenu");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,menuDTO.getName());
            pstmt.setInt(2,menuDTO.getPrice());
            pstmt.setInt(3,menuDTO.getCategoryCode());
            pstmt.setString(4,menuDTO.getStatus());

            result = pstmt.executeUpdate();


        } catch (SQLException e) {
            System.out.println("잘못된 값이 입력됨 ... ");
        } finally {
            close(con);
            close(pstmt);
        }
        return result;
    }
}