package com.pluralsight;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author Kamil
 */
public class TestConnection {

    public static void main(String[] args) {
        String Url = "jdbc:mysql://localhost:3306/ride_tracker";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Trying to connect");
            try (Connection connection = DriverManager.getConnection(Url, "root", "15lipiec")) {

                System.out.println("Connection Established Successfull and the DATABASE NAME IS:"
                        + connection.getMetaData().getDatabaseProductName());
            }
        } catch (Exception e) {
            System.out.println("Unable to make connection with DB");
            e.printStackTrace();
        }
    }
}
