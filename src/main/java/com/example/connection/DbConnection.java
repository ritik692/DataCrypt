package com.example.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DbConnection {
    static Connection con = null;
    PreparedStatement pst = null;

    public static Connection makeConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cryptography", "root", "system");
            if(con!=null){
                System.out.println("Party bc");
            }
            else{
                System.out.println("Shit bc");
            }

        } catch (Exception e) {
        }
        return con;
    }
}
