package org.example.db;


import java.sql.*;

public class DataBase {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private static final String url = "jdbc:mysql://localhost:3306/java_web";
    private static final String user = "root";
    private static final String password_db = "9213";

    public static void createUser(String userName,String password,String email) throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, password_db);
        String sql = "INSERT INTO Users (userName, password, email) Values (?, ?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, userName);
        preparedStatement.setString(2, password);
        preparedStatement.setString(3, email);
        preparedStatement.executeUpdate();
    }
    public static boolean checkUser(String userName) throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, password_db);
        String sql = "SELECT * FROM Users WHERE userName=?;";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, userName);
        ResultSet resultSet = preparedStatement.executeQuery();
        String  id="";
        while(resultSet.next()){
            id = resultSet.getString("userName");
        }

        if (id==""){
            return false;
        }
        else{
            return true;
        }

    }
    }
