package cn.bookstore.dbpool;

import com.db.durid.DBUtils;

import java.sql.Connection;

public class test {
    public static void main(String[] args) {
        Connection connection = new DBUtils().getConnection();
        System.out.println("θΏζ₯ζε"+connection);
    }
}
