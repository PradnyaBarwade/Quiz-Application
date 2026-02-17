//package quizapplication;
//
//import javax.swing.*;
//import java.awt.*;
//import java.sql.*;
//
//public class Dashboard extends JFrame {
//
//    Dashboard() {
//
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setBounds(300, 100, 700, 500);
//        getContentPane().setBackground(Color.WHITE);
//        setLayout(new BorderLayout());
//
//        JLabel heading = new JLabel("Quiz Score Dashboard", JLabel.CENTER);
//        heading.setFont(new Font("Tahoma", Font.BOLD, 26));
//        heading.setForeground(new Color(30, 144, 255));
//        add(heading, BorderLayout.NORTH);
//
//        // Table to display user scores
//        String[] columns = {"ID", "Name", "Score", "Date"};
//        String[][] data = fetchScores();
//
//        JTable table = new JTable(data, columns);
//        table.setEnabled(false);
//        JScrollPane sp = new JScrollPane(table);
//        add(sp, BorderLayout.CENTER);
//
//        setVisible(true);
//    }
//
//    String[][] fetchScores() {
//        try {
//            Connection con = DBConnection.getConnection();
//            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//            ResultSet rs = st.executeQuery("SELECT * FROM user_scores ORDER BY score desc  ");
//
//            // Count rows
//            rs.last();
//            int rows = rs.getRow();
//            rs.beforeFirst();
//
//            String[][] data = new String[rows][4];
//            int i = 0;
//            while (rs.next()) {
//                data[i][0] = String.valueOf(rs.getInt("id"));
//                data[i][1] = rs.getString("name");
//                data[i][2] = String.valueOf(rs.getInt("score"));
//                data[i][3] = rs.getString("date");
//                i++;
//            }
//            return data;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new String[0][0];
//        }
//    }
//
//    public static void main(String[] args) {
//        new Dashboard();
//    }
//}


package quizapplication;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Dashboard extends JFrame {

    Dashboard() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 100, 700, 500);
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        JLabel heading = new JLabel("Quiz Score Dashboard", JLabel.CENTER);
        heading.setFont(new Font("Tahoma", Font.BOLD, 26));
        heading.setForeground(new Color(30, 144, 255));
        add(heading, BorderLayout.NORTH);

        // Table to display user scores (ID removed)
        String[] columns = {"Name", "Score", "Date"};
        String[][] data = fetchScores();

        JTable table = new JTable(data, columns);
        table.setEnabled(false);
        JScrollPane sp = new JScrollPane(table);
        add(sp, BorderLayout.CENTER);

        setVisible(true);
    }

    String[][] fetchScores() {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT name, score, date FROM user_scores ORDER BY score DESC");

            // Count rows
            rs.last();
            int rows = rs.getRow();
            rs.beforeFirst();

            String[][] data = new String[rows][3];  // only 3 columns now
            int i = 0;
            while (rs.next()) {
                data[i][0] = rs.getString("name");
                data[i][1] = String.valueOf(rs.getInt("score"));
                data[i][2] = rs.getString("date");
                i++;
            }
            return data;

        } catch (Exception e) {
            e.printStackTrace();
            return new String[0][0];
        }
    }

    public static void main(String[] args) {
        new Dashboard();
    }
}
