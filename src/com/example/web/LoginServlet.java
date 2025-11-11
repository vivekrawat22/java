package com.example.web;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String user = request.getParameter("username");
        String pass = request.getParameter("password");

        // Hardcoded credentials (change to DB-based if you want)
        if ("admin".equals(user) && "12345".equals(pass)) {
            out.println("<h2>Welcome, " + user + "!</h2>");
            out.println("<a href='employees.html'>Go to Employee Records</a><br>");
            out.println("<a href='attendance.jsp'>Go to Attendance Portal</a>");
        } else {
            out.println("<h3 style='color:red'>Invalid username or password!</h3>");
            out.println("<a href='index.html'>Try Again</a>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
