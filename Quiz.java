//package quizapplication;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.sql.*;
//
//public class Quiz extends JFrame implements ActionListener {
//
//    JLabel qno, question, totalTimerLabel;
//    JRadioButton opt1, opt2, opt3, opt4;
//    ButtonGroup groupoptions;
//    JButton next, back, submit;
//
//    String name;
//    int totalTimer = 300;   // 5 minutes total time
//    int count = 0;
//    int score = 0;
//
//    Timer globalTimer;
//
//    String correctAnswer = "";
//
//    ResultSet rs;
//    Connection con;
//    Statement st;
//
//    // To store selected answers
//    String[] selectedAnswers = new String[20];
//
//    Quiz(String name) {
//        this.name = name;
//
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setBounds(50, 0, 1440, 850);
//        getContentPane().setBackground(Color.WHITE);
//        setLayout(null);
//
//        //Image
//        ImageIcon i1 = new ImageIcon(getClass().getResource("/Icons/quiz.jpg"));
//        JLabel image = new JLabel(i1);
//        image.setBounds(0, 0, 1440, 392);
//        add(image);
//
//        qno = new JLabel();
//        qno.setBounds(100, 450, 50, 30);
//        qno.setFont(new Font("Tahoma", Font.PLAIN, 24));
//        add(qno);
//
//        question = new JLabel();
//        question.setBounds(150, 450, 900, 30);
//        question.setFont(new Font("Tahoma", Font.PLAIN, 24));
//        add(question);
//
//        // ONLY GLOBAL TIMER IS SHOWN
//        totalTimerLabel = new JLabel("Total Time Left - 300 sec");
//        totalTimerLabel.setBounds(1100, 450, 300, 30);
//        totalTimerLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
//        totalTimerLabel.setForeground(Color.RED);
//        add(totalTimerLabel);
//
//        opt1 = createOption(520);
//        opt2 = createOption(560);
//        opt3 = createOption(600);
//        opt4 = createOption(640);
//
//        groupoptions = new ButtonGroup();
//        groupoptions.add(opt1);
//        groupoptions.add(opt2);
//        groupoptions.add(opt3);
//        groupoptions.add(opt4);
//
//        // ----------------------
//        // UPDATED BUTTON ORDER
//        // ----------------------
//
//        next = new JButton("Next");
//        next.setBounds(1100, 550, 200, 40);  // TOP
//        styleButton(next);
//        next.addActionListener(this);
//        add(next);
//
//        back = new JButton("Back");
//        back.setBounds(1100, 600, 200, 40);  // MIDDLE
//        styleButton(back);
//        back.addActionListener(this);
//        back.setEnabled(false);
//        add(back);
//
//        submit = new JButton("Submit");
//        submit.setBounds(1100, 650, 200, 40); // BOTTOM
//        styleButton(submit);
//        submit.setEnabled(false);
//        submit.addActionListener(this);
//        add(submit);
//
//        loadQuestionsFromDB();
//        startGlobalTimer();
//        setVisible(true);
//    }
//
//    JRadioButton createOption(int y) {
//        JRadioButton rb = new JRadioButton();
//        rb.setBounds(170, y, 700, 30);
//        rb.setBackground(Color.WHITE);
//        rb.setFont(new Font("Dialog", Font.PLAIN, 20));
//        add(rb);
//        return rb;
//    }
//
//    void styleButton(JButton btn) {
//        btn.setFont(new Font("Tahoma", Font.PLAIN, 22));
//        btn.setBackground(new Color(30, 144, 255));
//        btn.setForeground(Color.WHITE);
//    }
//
//    void loadQuestionsFromDB() {
//        try {
//            con = DBConnection.getConnection();
//            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//            rs = st.executeQuery("SELECT * FROM questions order by rand() limit 20");
//            rs.next();
//            loadQuestion();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    void loadQuestion() {
//        try {
//            qno.setText((count + 1) + ". ");
//            question.setText(rs.getString("question_text"));
//
//            opt1.setText(rs.getString("option1"));
//            opt2.setText(rs.getString("option2"));
//            opt3.setText(rs.getString("option3"));
//            opt4.setText(rs.getString("option4"));
//
//            opt1.setActionCommand(opt1.getText());
//            opt2.setActionCommand(opt2.getText());
//            opt3.setActionCommand(opt3.getText());
//            opt4.setActionCommand(opt4.getText());
//
//            correctAnswer = rs.getString("answer");
//
//            restorePreviousSelection();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    void startGlobalTimer() {
//        globalTimer = new Timer(1000, e -> {
//            totalTimerLabel.setText("Total Time Left - " + totalTimer + " sec");
//            totalTimer--;
//
//            if (totalTimer <= 0) {
//                finishQuiz();   // Auto-submit
//            }
//        });
//        globalTimer.start();
//    }
//
//    void saveAnswer() {
//        ButtonModel selected = groupoptions.getSelection();
//
//        // Remove previous score if user changed answer
//        if (selectedAnswers[count] != null && selectedAnswers[count].equals(correctAnswer)) {
//            score -= 1;
//        }
//
//        if (selected != null) {
//            selectedAnswers[count] = selected.getActionCommand();
//            if (selected.getActionCommand().equals(correctAnswer)) {
//                score += 1;
//            }
//        } else {
//            selectedAnswers[count] = null;
//        }
//    }
//
//    void restorePreviousSelection() {
//        groupoptions.clearSelection();
//
//        if (selectedAnswers[count] == null) return;
//
//        if (opt1.getText().equals(selectedAnswers[count])) opt1.setSelected(true);
//        else if (opt2.getText().equals(selectedAnswers[count])) opt2.setSelected(true);
//        else if (opt3.getText().equals(selectedAnswers[count])) opt3.setSelected(true);
//        else if (opt4.getText().equals(selectedAnswers[count])) opt4.setSelected(true);
//    }
//
//    void nextQuestion() {
//        try {
//            if (!rs.next()) {
//                finishQuiz();
//                return;
//            }
//
//            count++;
//
//            back.setEnabled(true);
//
//            if (rs.isLast()) {
//                next.setEnabled(false);
//                submit.setEnabled(true);
//            }
//
//            loadQuestion();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    void previousQuestion() {
//        try {
//            if (count == 0) return;
//
//            rs.previous();
//            count--;
//
//            if (count == 0) back.setEnabled(false);
//
//            next.setEnabled(true);
//            submit.setEnabled(false);
//
//            loadQuestion();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    void finishQuiz() {
//        if (globalTimer != null) globalTimer.stop();
//        setVisible(false);
//        new Score(name, score);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent ae) {
//
//        if (ae.getSource() == next) {
//            saveAnswer();
//            nextQuestion();
//
//        } else if (ae.getSource() == back) {
//            saveAnswer();
//            previousQuestion();
//
//        } else if (ae.getSource() == submit) {
//            saveAnswer();
//            finishQuiz();
//        }
//    }
//}

package quizapplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Quiz extends JFrame implements ActionListener {

    JLabel qno, question, totalTimerLabel;
    JRadioButton opt1, opt2, opt3, opt4;
    ButtonGroup groupoptions;
    JButton next, back, submit;

    String name;
    int totalTimer = 300;   // 5 minutes
    int count = 0;
    int score = 0;

    Timer globalTimer;
    String correctAnswer = "";

    ResultSet rs;
    Connection con;
    Statement st;

    String[] selectedAnswers = new String[20];

    Quiz(String name) {
        this.name = name;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(50, 0, 1440, 850);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // ---------------- IMAGE ----------------
        ImageIcon i1 = new ImageIcon(getClass().getResource("/Icons/quiz.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(0, 0, 1440, 392);
        add(image);

        // ---------------- QUESTION ----------------
        qno = new JLabel();
        qno.setBounds(100, 450, 50, 30);
        qno.setFont(new Font("Tahoma", Font.PLAIN, 24));
        add(qno);

        question = new JLabel();
        question.setBounds(150, 450, 900, 30);
        question.setFont(new Font("Tahoma", Font.PLAIN, 24));
        add(question);

        // ---------------- TIMER ----------------
        totalTimerLabel = new JLabel("Total Time Left - 300 sec");
        totalTimerLabel.setBounds(1050, 450, 350, 30);
        totalTimerLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
        totalTimerLabel.setForeground(Color.RED);
        add(totalTimerLabel);

        // ---------------- OPTIONS ----------------
        opt1 = createOption(520);
        opt2 = createOption(560);
        opt3 = createOption(600);
        opt4 = createOption(640);

        groupoptions = new ButtonGroup();
        groupoptions.add(opt1);
        groupoptions.add(opt2);
        groupoptions.add(opt3);
        groupoptions.add(opt4);

        // ---------------- ICON BUTTONS ----------------
        next = createIconButton("/Icons/next.png", 1100, 550);
        next.setToolTipText("Next Question");
        next.addActionListener(this);
        add(next);

        back = createIconButton("/Icons/back.jpg", 1100, 610);
        back.setToolTipText("Previous Question");
        back.addActionListener(this);
        back.setEnabled(false);
        add(back);

        submit = createIconButton("/Icons/submit.png", 1100, 670);
        submit.setToolTipText("Submit Quiz");
        submit.addActionListener(this);
        submit.setEnabled(false);
        add(submit);

        loadQuestionsFromDB();
        startGlobalTimer();

        setVisible(true);
    }

    // ---------------- OPTION CREATOR ----------------
    JRadioButton createOption(int y) {
        JRadioButton rb = new JRadioButton();
        rb.setBounds(170, y, 700, 30);
        rb.setBackground(Color.WHITE);
        rb.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(rb);
        return rb;
    }

    // ---------------- ICON BUTTON CREATOR ----------------
    JButton createIconButton(String path, int x, int y) {
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        Image img = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);

        JButton btn = new JButton(new ImageIcon(img));
        btn.setBounds(x, y, 200, 50);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return btn;
    }

    // ---------------- DATABASE ----------------
    void loadQuestionsFromDB() {
        try {
            con = DBConnection.getConnection();
            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery("SELECT * FROM questions ORDER BY RAND() LIMIT 20");
            rs.next();
            loadQuestion();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void loadQuestion() {
        try {
            qno.setText((count + 1) + ". ");
            question.setText(rs.getString("question_text"));

            opt1.setText(rs.getString("option1"));
            opt2.setText(rs.getString("option2"));
            opt3.setText(rs.getString("option3"));
            opt4.setText(rs.getString("option4"));

            opt1.setActionCommand(opt1.getText());
            opt2.setActionCommand(opt2.getText());
            opt3.setActionCommand(opt3.getText());
            opt4.setActionCommand(opt4.getText());

            correctAnswer = rs.getString("answer");
            restorePreviousSelection();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------- TIMER ----------------
    void startGlobalTimer() {
        globalTimer = new Timer(1000, e -> {
            totalTimerLabel.setText("Total Time Left - " + totalTimer + " sec");
            totalTimer--;

            if (totalTimer <= 0) {
                finishQuiz();
            }
        });
        globalTimer.start();
    }

    // ---------------- ANSWER SAVE ----------------
    void saveAnswer() {
        ButtonModel selected = groupoptions.getSelection();

        if (selectedAnswers[count] != null && selectedAnswers[count].equals(correctAnswer)) {
            score--;
        }

        if (selected != null) {
            selectedAnswers[count] = selected.getActionCommand();
            if (selected.getActionCommand().equals(correctAnswer)) {
                score++;
            }
        } else {
            selectedAnswers[count] = null;
        }
    }

    void restorePreviousSelection() {
        groupoptions.clearSelection();
        if (selectedAnswers[count] == null) return;

        if (opt1.getText().equals(selectedAnswers[count])) opt1.setSelected(true);
        else if (opt2.getText().equals(selectedAnswers[count])) opt2.setSelected(true);
        else if (opt3.getText().equals(selectedAnswers[count])) opt3.setSelected(true);
        else if (opt4.getText().equals(selectedAnswers[count])) opt4.setSelected(true);
    }

    // ---------------- NAVIGATION ----------------
    void nextQuestion() {
        try {
            if (!rs.next()) {
                finishQuiz();
                return;
            }

            count++;
            back.setEnabled(true);

            if (rs.isLast()) {
                next.setEnabled(false);
                submit.setEnabled(true);
            }

            loadQuestion();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void previousQuestion() {
        try {
            if (count == 0) return;

            rs.previous();
            count--;

            if (count == 0) back.setEnabled(false);

            next.setEnabled(true);
            submit.setEnabled(false);

            loadQuestion();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------- FINISH ----------------
    void finishQuiz() {
        if (globalTimer != null) globalTimer.stop();
        setVisible(false);
        new Score(name, score);
    }

    // ---------------- ACTIONS ----------------
    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == next) {
            saveAnswer();
            nextQuestion();

        } else if (ae.getSource() == back) {
            saveAnswer();
            previousQuestion();

        } else if (ae.getSource() == submit) {
            saveAnswer();
            finishQuiz();
        }
    }
}

