WebAppProject - Servlets + JSP + JDBC demo
==========================================
This project is set up to be opened in Visual Studio Code and deployed to Tomcat.

Important steps to run:
1. Install JDK 11+, VS Code, Tomcat 9+, MySQL.
2. In VS Code install: Extension Pack for Java, Tomcat for Java, Debugger for Java.
3. Place MySQL JDBC driver (mysql-connector-j-X.Y.Z.jar) into the project's lib/ folder.
   (Download from https://dev.mysql.com/downloads/connector/j/)
4. Open this folder in VS Code.
5. Configure Tomcat in VS Code (Command Palette -> Tomcat: Add Tomcat Server).
6. In VS Code Tomcat Servers panel -> Add Web App -> select this project's folder.
7. Start Tomcat and run the app.
8. Database setup:
   - Create database and tables with SQL:
     CREATE DATABASE webappdb;
     USE webappdb;
     CREATE TABLE Employee (EmpID INT PRIMARY KEY, Name VARCHAR(50), Salary DECIMAL(10,2));
     INSERT INTO Employee VALUES (1,'Alice',50000),(2,'Bob',60000);
     CREATE TABLE Attendance (ID INT AUTO_INCREMENT PRIMARY KEY, StudentID VARCHAR(20), Date DATE, Status VARCHAR(10));
9. Default DB credentials in servlets: username=root, password=root. Change if needed.
10. If you see ClassNotFoundException for the JDBC driver, ensure the jar is in lib/ and referencedLibraries is set in .vscode/settings.json.

Enjoy!
