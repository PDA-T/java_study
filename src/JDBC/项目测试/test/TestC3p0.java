package JDBC.��Ŀ����.test;

import JDBC.��Ŀ����.db.C3P0Util;

import java.sql.Connection;
import java.sql.SQLException;

public class TestC3p0 {
    public static void main(String[] args) {
        Connection conn= C3P0Util.getConnection();
        try {
            System.out.println(conn.getCatalog());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
