package com.example.web;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class EmployeeServlet extends HttpServlet {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/webappdb";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "root";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String empId = request.getParameter("empId");

        out.println("<html><head><title>Employees</title></head><body>");
        out.println("<h2>Employee Records</h2>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS)) {
                String query;
                if (empId != null && !empId.isEmpty()) {
                    query = "SELECT * FROM Employee WHERE EmpID = ?";
                } else {
                    query = "SELECT * FROM Employee";
                }

                try (PreparedStatement ps = con.prepareStatement(query)) {
                    if (empId != null && !empId.isEmpty()) {
                        ps.setInt(1, Integer.parseInt(empId));
                    }
                    try (ResultSet rs = ps.executeQuery()) {
                        out.println("<table border='1'><tr><th>ID</th><th>Name</th><th>Salary</th></tr>");
                        boolean has = false;
                        while (rs.next()) {
                            has = true;
                            out.println("<tr><td>" + rs.getInt("EmpID") + "</td><td>" +
                                        rs.getString("Name") + "</td><td>" +
                                        rs.getDouble("Salary") + "</td></tr>");
                        }
                        out.println("</table>");
                        if (!has) {
                            out.println("<p>No employee records found.</p>");
                        }
                    }
                }
            }
        } catch (Exception e) {
            out.println("<pre>");
            e.printStackTrace(out);
            out.println("</pre>");
        }

        out.println("<br><a href='employees.html'>Back</a>");
        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
