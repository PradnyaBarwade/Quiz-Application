package quizapplication;

import java.sql.*;

public class DBConnection {

    public static Connection getConnection() {
        Connection con = null;

        String url = "jdbc:mysql://localhost:3306/quiz_app";
        String user = "root";
        String pass = "Pradnya@05"; // MySQL password 

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
