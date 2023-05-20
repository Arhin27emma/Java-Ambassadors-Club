package com.JavaDevelopers;

import javax.swing.*;
import java.sql.*;

public class conn {
    public static void main(String[] args) {
        getConnection();
        createNewTable();

    }

    public static Connection getConnection(){
        try{

            // Register the driver
            String driver = "com.mysql.cj.jdbc.Driver";

            // Database xampp connection
            String url = "jdbc:mysql://localhost:3306/club";
            String username = "root";
            String password = "";


            // Test connection if is successful
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, username, password);

            System.out.println("Connected successfully");
            return conn;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void createNewTable(){
        try {

            DatabaseMetaData databaseMetaData = conn.getConnection().getMetaData();
            ResultSet resultSet = databaseMetaData.getTables(null,null,"club",null);

            if(resultSet.next()){

                JOptionPane.showMessageDialog(
                        null,
                        "Table not created",
                        "Database error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
            else {

                // Creating a table in the club database
                String signup = "CREATE TABLE signup("
                        + "id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,"
                        + "Full_Name VARCHAR(250),"
                        + "Username VARCHAR(250),"
                        + "password VARCHAR(250),"
                        + "Email VARCHAR(250) )";

                PreparedStatement statement = conn.getConnection().prepareStatement(signup);
                statement.executeUpdate();
                JOptionPane.showMessageDialog(null,"Database table(signup) created successfully");

            }

        } catch (Exception e) {
            System.out.println("Database table created");
        }
    }

}
