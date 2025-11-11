package com.example.web;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AttendanceServlet extends HttpServlet {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/webappdb";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "root";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String studentId = request.getParameter("studentId");
        String date = request.getParameter("date");
        String status = request.getParameter("status");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS)) {
                String query = "INSERT INTO Attendance(StudentID, Date, Status) VALUES (?, ?, ?)";
                try (PreparedStatement ps = con.prepareStatement(query)) {
                    ps.setString(1, studentId);
                    ps.setDate(2, java.sql.Date.valueOf(date));
                    ps.setString(3, status);
                    int rows = ps.executeUpdate();
                    if (rows > 0) {
                        response.sendRedirect("success.jsp");
                        return;
                    } else {
                        out.println("<h3 style='color:red'>Failed to record attendance!</h3>");
                    }
                }
            }
        } catch (Exception e) {
            out.println("<pre>");
            e.printStackTrace(out);
            out.println("</pre>");
        }

        out.println("<a href='attendance.jsp'>Back</a>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("attendance.jsp");
    }
}
