package com.framework.demo;

import java.sql.*;

public class mysqlTest {

        public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        //8.0以上版本的数据库，驱动器是com.mysql.cj.jdbc.Driver，而不是之前的com.mysql.jdbc.Driver
        public static final String DB_URL = "jdbc:mysql://localhost:3306/mall?serverTimezone" +
                "=Asia/Shanghai&characterEncoding=utf8&useUnicode=true&useSSL=false";
        //8.0以上版本的数据库要在后面加上  ?useSSL=false&serverTimezone=UTC
        //在这个语句中，localhost是数据库链接名称，3306是端口号，jdbc是要访问的数据库
        public static final String USER = "root";//数据库用户名
        public static final String PASS = "111";//数据库密码

        public static void main(String[] args) {
            Connection conn = null;
            Statement stmt = null;
            try {
                //注册jdbc驱动
                Class.forName(JDBC_DRIVER);
                //打开链接
                System.out.println("连接数据库...");
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                System.out.println("连接成功");
                //执行查询
                System.out.println("实例化Statement...");
                stmt = conn.createStatement();
                String sql;
                System.out.println("开始查询数据库");
                sql = "select * from ums_role";//查询jdbc数据表，此处可能爆红，不要介意直接运行。如果介意，设置的搜索框内输入：SQL Dialects

                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    String create_time = rs.getString("create_time");
                    System.out.print("description:" + description);
                    System.out.print(" name:" + name);
                    System.out.print(" create_time:" + create_time);
                    System.out.println();
                }
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (stmt != null) stmt.close();
                } catch (SQLException se2) {

                }
                try {
                    if (conn != null) conn.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
        }
    }


