package com.example.features;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static com.example.connection.ECC.hexToBytes;

public class DecryptedKey extends HttpServlet {
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rst = null;
    String userid = null;
    String secretekey = null;
    String secretU = null;
    String secretV = null;
    String email = null;
    int otp = 0;
    String fileid = null;
    String privatekey = null;

    int i = 0;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        //connection from database

        try {
            con = com.example.connection.DbConnection.makeConnection();
        } catch (Exception e) {
        }

        fileid = request.getParameter("fileid");
        String pubkey = request.getParameter("publickey");

        try {
            String query = "SELECT privatekey,file_path,encrypted_aes_key FROM files WHERE privatekey = '" + pubkey + "' AND id = '" + fileid + "'";
            System.out.println("query: " + query);
            pst = con.prepareStatement(query);
            rst = pst.executeQuery();
            if (rst.next()) {
                privatekey = rst.getString(1);
                String file_path = rst.getString(2);
                String encrypted_aes_key = rst.getString(3);

                // decode the base64 encoded string
                String decryptedPlainText = null;
                byte[] desc = hexToBytes(encrypted_aes_key.toString());
                decryptedPlainText = new String(desc, "UTF-8");

                response.sendRedirect("download_file.jsp?filename=" + file_path + "&aes_key=" + decryptedPlainText);

            } else {
                session.setAttribute("MSG", "Please enter valid public key.");
                response.sendRedirect("keydescription.jsp?fileid=" + fileid);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
