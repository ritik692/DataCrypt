package com.example.connection;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import io.github.cdimascio.dotenv.Dotenv;

public class DbConnection {
    static Connection con = null;

    public static Connection makeConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Dotenv dotenv = Dotenv.configure()
                    .directory("C:/Users/arora/Desktop/JavaWebApp/src/main/resources")
                    .load();
            String db_url = dotenv.get("DB_URL");
            String db_username = dotenv.get("DB_USERNAME");
            String db_password = dotenv.get("DB_PASSWORD");
            con = DriverManager.getConnection(db_url, db_username, db_password);
            System.out.print("Status: ");
            if(con!=null){
                System.out.println("Successful Db-Connection");
            }
            else{
                System.out.println("Db-connection could not be established");
            }

        } catch (Exception e) {
        }
        return con;
    }
}
