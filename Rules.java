package quizapplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Rules extends JFrame implements ActionListener {

    String name;
    JButton start, back;

    Rules(String name) {
        this.name = name;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        //Label
        JLabel heading = new JLabel("Welcome " + name + " to Java World");
        heading.setBounds(50, 20, 700, 50);
        heading.setFont(new Font("Viner Hand ITC", Font.BOLD, 40));
        heading.setForeground(new Color(30, 144, 254));
        add(heading);

        // Rules 
        String rulesText =
                "<html>" +
                        "1. The quiz consists of 20 multiple-choice questions.<br><br>" +
                        "2. Each question carries 1 marks.<br><br>" +
                        "3. You will get <b>300 seconds</b> for all question.<br><br>" +
                        "4. Your final score will be shown after submitting the quiz.<br><br>" +
                        "5. Stay calm and attempt all questions carefully.<br><br>" +
                        "</html>";

        //Add rules
        JLabel rules = new JLabel(rulesText);
        rules.setBounds(20, 90, 750, 300);
        rules.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(rules);

        //Back Button
        back = new JButton("Back");
        back.setBounds(250, 450, 100, 30);
        back.setBackground(new Color(30, 144, 254));
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        //Start Button
        start = new JButton("Start");
        start.setBounds(400, 450, 100, 30);
        start.setBackground(new Color(30, 144, 254));
        start.setForeground(Color.WHITE);
        start.addActionListener(this);
        add(start);

        setSize(800, 600);
        setLocation(350, 100);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        
        //If user selects start button
        if (ae.getSource() == start) {
            setVisible(false);
            new Quiz(name);  
        } else {
            setVisible(false);
            new Login();
        }
    }
}

