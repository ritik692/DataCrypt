package com.example.features;

import io.github.cdimascio.dotenv.Dotenv;
import java.io.IOException;
import javax.crypto.KeyAgreement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.ECGenParameterSpec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

@WebServlet(name = "KeyGeneration", urlPatterns = {"/keyGeneration"})
public class KeyGeneration extends HttpServlet {
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rst = null;
    String userid = null;
    String secretekey = null;
    String secretU = null;
    String secretV = null;
    String email = null;
    int otp = 0;
    String host = "smtp.gmail.com";
    String port = "587";
    String userName = null;
    String password = null;

    int i = 0;




    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        //connection from database
        System.out.println("Entered in doPost of KeyGeneration");
        try {
            con = com.example.connection.DbConnection.makeConnection();
        } catch (Exception e) {
        }

        userid = request.getParameter("userid");
        secretekey = request.getParameter("secretkey");
        System.out.println("userid in keygen: " + userid);
        System.out.println("secretkey: " + secretekey);

        //Generation ECC Key Agreement
        try {
            System.out.println("Finally entered in try block of keygeneration");

            KeyPairGenerator kpg;
            kpg = KeyPairGenerator.getInstance("EC", "SunEC");
            ECGenParameterSpec ecsp;
            //String parameter = "sect113r2";
            ecsp = new ECGenParameterSpec(secretekey);
            kpg.initialize(ecsp);
            KeyPair kpU = kpg.genKeyPair();
            PrivateKey privKeyU = kpU.getPrivate();
            PublicKey pubKeyU = kpU.getPublic();
            KeyPair kpV = kpg.genKeyPair();
            PrivateKey privKeyV = kpV.getPrivate();
            PublicKey pubKeyV = kpV.getPublic();
            KeyAgreement ecdhU = KeyAgreement.getInstance("ECDH");
            ecdhU.init(privKeyU);
            ecdhU.doPhase(pubKeyV, true);
            KeyAgreement ecdhV = KeyAgreement.getInstance("ECDH");

            ecdhV.init(privKeyV);
            ecdhV.doPhase(pubKeyU, true);
            secretU = new BigInteger(1, ecdhU.generateSecret()).toString(16).toUpperCase();
            secretV = new BigInteger(1, ecdhV.generateSecret()).toString(16).toUpperCase();

        } catch (Exception e) {
            System.out.println("Some error occured , thats why catch of keygeneration");
            e.printStackTrace();
        }

        //Generate  random no
        Random rand = new Random();
        otp = rand.nextInt((999999 - 100000) + 1) + 100000;

        System.out.println("otp generated is: " + otp);

        try {
            String query = "SELECT email FROM users WHERE userid = '" + userid + "' AND u_status = '1'";
            pst = con.prepareStatement(query);
            rst = pst.executeQuery();
            if (rst.next()) {
                System.out.println("entered in rst of keygen");
                email = rst.getString(1);
                System.out.println("Email updated: " + email);
                String subject = "OTP from DataCrypt using ECC";
                String message = "Your OTP is " + otp;

                try {
                    Dotenv dotenv = Dotenv.configure()
                            .directory("C:/Users/arora/Desktop/JavaWebApp/src/main/resources")
                            .load();
                    userName = dotenv.get("MAIL_USERNAME");
                    password = dotenv.get("MAIL_PASSWORD");
                    EmailUtility.sendEmail(host, port, userName, password, email, subject,
                            message);
                    // resultMessage = "The e-mail was sent successfully";
                } catch (Exception ex) {
                    ex.printStackTrace();
                    //resultMessage = "There were an error: " + ex.getMessage();
                }

            } else {
                session.setAttribute("MSG", "Secret key has not been generated.");
                response.sendRedirect("keyexchange.jsp?userid=" + userid);
            }
        } catch (Exception e) {
        }

        try {
            String sqlquery = "update users set user_key=?,secretu=?,user_otp=? where userid = '" + userid + "'";
            pst = con.prepareStatement(sqlquery);
            pst.setString(1, secretekey);
            pst.setString(2, secretU);
            pst.setInt(3, otp);
            i = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //success or failure message
        if (i > 0) {
            session.setAttribute("MSG", "ECDH Key agreement has been successfully generated.");
            System.out.println("ECDH Key agreement has been successfully generated");
            System.out.println("userId: " + userid);
            System.out.println("secretu: " + secretU);
            response.sendRedirect("secretkey.jsp?userid=" + userid + "&secretkey=" + secretU);
        } else {
            session.setAttribute("MSG", "Secret key has not been generated.");
            response.sendRedirect("keyexchange.jsp?userid=" + userid);
        }

    }
}
