package com.example.features;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Logout", urlPatterns = {"/logout"})
public class Logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.setAttribute("ID", null);
        session.setAttribute("UTYPE", null);
        session.setAttribute("NAME", null);
        session.setAttribute("MSG", null);
        session.removeAttribute("ID");
        session.removeAttribute("UTYPE");
        session.removeAttribute("NAME");
        session.removeAttribute("MSG");
        session.invalidate();
        HttpSession sess = request.getSession(true);
        sess.setAttribute("LOGIN", "Logout Successfuly !!");
        RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
        rd.forward(request, response);
    }
}
