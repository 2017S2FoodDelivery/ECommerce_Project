package com.ajax;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

public class ComposerData {
    private HashMap composers = new HashMap();
    public HashMap getComposers() {
        return composers;
    }
    
    public ComposerData() {
        
        String driverName = "com.mysql.jdbc.Driver";
        String connectionUrl = "jdbc:mysql://localhost:3306/";
        String dbName = "ecmrproj";
        String userId = "lenguyen";
        String password = "thongminh";

        try {
        Class.forName(driverName);
        } catch (ClassNotFoundException e) {
        e.printStackTrace();
        }

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try{ 
        connection = DriverManager.getConnection(connectionUrl+dbName, userId, password);
        statement=connection.createStatement();
        String sql ="SELECT * FROM product";        
        resultSet = statement.executeQuery(sql);
        while(resultSet.next()){
            System.out.print(resultSet.getString("productname"));
            String temp=resultSet.getString("productname");
            composers.put(temp, new Composer(temp, temp, temp, temp));
        }
        } catch (Exception e) {
        e.printStackTrace();
        }
        
    }
}
