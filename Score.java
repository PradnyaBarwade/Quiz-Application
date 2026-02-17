//package quizapplication;
//
//import java.awt.*;
//import javax.swing.*;
//import java.awt.event.*;
//
//public class Score extends JFrame implements ActionListener {
//
//    Score(String name, int score) {
//
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setBounds(400, 150, 750, 550);
//        getContentPane().setBackground(Color.WHITE);
//        setLayout(null);
//
//        ImageIcon i1 = new ImageIcon(getClass().getResource("/Icons/score.png"));
//        JLabel image = new JLabel(i1);
//        image.setBounds(0, 200, 300, 250);
//        add(image);
//
//        JLabel heading = new JLabel("Thank you " + name + " for playing Java World");
//        heading.setBounds(45, 30, 700, 30);
//        heading.setFont(new Font("Tahoma", Font.PLAIN, 26));
//        add(heading);
//
//        JLabel lblscore = new JLabel("Your score is " + score);
//        lblscore.setBounds(350, 200, 300, 30);
//        lblscore.setFont(new Font("Tahoma", Font.PLAIN, 26));
//        add(lblscore);
//
//        JButton submit = new JButton("Play Again");
//        submit.setBounds(380, 270, 120, 30);
//        submit.setBackground(new Color(30, 144, 255));
//        submit.setForeground(Color.WHITE);
//        submit.addActionListener(this);
//        add(submit);
//
//        setVisible(true);
//    }
//
//    public void actionPerformed(ActionEvent ae) {
//        setVisible(false);
//        new Login();
//    }
//}


package quizapplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Score extends JFrame implements ActionListener {

    JButton playAgain, dashboard;

    Score(String name, int score) {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(400, 150, 750, 550);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // Save score to database
        saveScoreToDB(name, score);

        ImageIcon i1 = new ImageIcon(getClass().getResource("/Icons/score.png"));
        JLabel image = new JLabel(i1);
        image.setBounds(0, 200, 300, 250);
        add(image);

        JLabel heading = new JLabel("Thank you " + name + " for playing Java World");
        heading.setBounds(45, 30, 700, 30);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 26));
        add(heading);

        JLabel lblscore = new JLabel("Your score is " + score);
        lblscore.setBounds(350, 200, 300, 30);
        lblscore.setFont(new Font("Tahoma", Font.PLAIN, 26));
        add(lblscore);

        // Play Again button
        playAgain = new JButton("Play Again");
        playAgain.setBounds(380, 270, 150, 40);
        playAgain.setBackground(new Color(30, 144, 255));
        playAgain.setForeground(Color.WHITE);
        playAgain.addActionListener(this);
        add(playAgain);

        // Dashboard button
        dashboard = new JButton("Dashboard");
        dashboard.setBounds(380, 330, 150, 40);
        dashboard.setBackground(new Color(30, 144, 255));
        dashboard.setForeground(Color.WHITE);
        dashboard.addActionListener(this);
        add(dashboard);

        setVisible(true);
    }

    void saveScoreToDB(String name, int score) {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO user_scores (name, score) VALUES (?, ?)");
            ps.setString(1, name);
            ps.setInt(2, score);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == playAgain) {
            setVisible(false);
            new Login();
        } else if (ae.getSource() == dashboard) {
            setVisible(false);
            new Dashboard();
        }
    }
}
