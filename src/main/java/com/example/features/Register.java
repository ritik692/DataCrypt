package com.example.features;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLOutput;

@WebServlet(name = "Register", urlPatterns = {"/register"})
public class Register extends HttpServlet {
    static Connection con = null;
    PreparedStatement pst = null;
    ResultSet rst = null;
    String name = null;
    String email = null;
    String mobile = null;
    String dob = null;
    String gender = null;
    String userid = null;
    int i = 0;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        userid = request.getParameter("reg_id");
        HttpSession session = request.getSession(true);

        try {
            con = com.example.connection.DbConnection.makeConnection();
            String query = "delete from users WHERE userid= '" + userid + "' ";
            pst = con.prepareStatement(query);
            i = pst.executeUpdate();

        } catch (Exception e) {
        }

        if (i > 0) {
            session.setAttribute("MSG", "User has been successfuly deleted !!");
            response.sendRedirect("userlist.jsp");
        } else {
            session.setAttribute("MSG", "User has not been deleted !!");
            response.sendRedirect("userlist.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        ServletConfig config = getServletConfig();
        String context = config.getServletContext().getRealPath("/");


        System.out.println("Session: " + session);
        try {
            con = com.example.connection.DbConnection.makeConnection();
        } catch (Exception e) {

        }

        userid = com.example.connection.AutoID.globalGenId("registration");
        System.out.println("userid: " + userid);
        name = request.getParameter("name");
        email = request.getParameter("email");
        mobile = request.getParameter("mobile");
        dob = request.getParameter("dob");
        gender = request.getParameter("gender");

        try {
            String sqlquery = "INSERT INTO users(userid,name,email,mobile,dob,gender,created) VALUES(?,?,?,?,?,?, NOW())";
//
            pst = con.prepareStatement(sqlquery);
            pst.setString(1, userid);
            pst.setString(2, name);
            pst.setString(3, email);
            pst.setString(4, mobile);
            pst.setString(5, dob);
            pst.setString(6, gender);
            i = pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Value of i: " + i);

        if (i > 0) {
            com.example.connection.AutoID.updateAutoID("registration", userid);
            session.setAttribute("ID",userid);
            System.out.println("Session ID: " + session.getAttribute("ID"));
            response.sendRedirect("keyexchange.jsp?userid=" + userid);
        } else {
            session.setAttribute("MSG", "Email Id is already registered");
            response.sendRedirect("register.jsp");
        }
    }
}
