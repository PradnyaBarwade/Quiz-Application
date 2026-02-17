CREATE DATABASE quiz_app;
USE quiz_app;


CREATE TABLE questions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    question_text VARCHAR(500) NOT NULL,
    option1 VARCHAR(255) NOT NULL,
    option2 VARCHAR(255) NOT NULL,
    option3 VARCHAR(255) NOT NULL,
    option4 VARCHAR(255) NOT NULL,
    answer VARCHAR(255) NOT NULL
);

INSERT INTO questions (question_text, option1, option2, option3, option4, answer) VALUES
('Who invented Java programming language?', 'James Gosling', 'Bjarne Stroustrup', 'Guido van Rossum', 'Dennis Ritchie', 'James Gosling'),

('Which company originally developed Java?', 'Sun Microsystems', 'Microsoft', 'Google', 'IBM', 'Sun Microsystems'),

('Which of the following is not a Java feature?', 'Object-Oriented', 'Platform Independent', 'Pointer Support', 'Secure', 'Pointer Support'),

('Which keyword is used to inherit a class in Java?', 'this', 'super', 'extends', 'implements', 'extends'),

('Which method is the entry point of a Java program?', 'start()', 'run()', 'main()', 'init()', 'main()'),

('Which of the following is not a Java primitive data type?', 'int', 'float', 'boolean', 'String', 'String'),

('Which keyword is used to create an object in Java?', 'class', 'new', 'create', 'object', 'new'),

('Which concept allows multiple methods with the same name?', 'Abstraction', 'Encapsulation', 'Overloading', 'Overriding', 'Overloading'),

('Which concept is achieved using the "extends" keyword?', 'Polymorphism', 'Inheritance', 'Abstraction', 'Encapsulation', 'Inheritance'),

('Which access modifier makes variables accessible only within the same class?', 'public', 'private', 'protected', 'default', 'private'),

('Which of these is used to handle exceptions?', 'try-catch', 'for loop', 'switch', 'if-else', 'try-catch'),

('Which exception occurs when dividing a number by zero?', 'NullPointerException', 'NumberFormatException', 'ArithmeticException', 'IllegalArgumentException', 'ArithmeticException'),

('Which keyword is used to stop a loop?', 'exit', 'stop', 'break', 'end', 'break'),

('Which collection class stores elements in key-value pairs?', 'ArrayList', 'HashMap', 'HashSet', 'Vector', 'HashMap'),

('Which method is used to start a thread?', 'execute()', 'run()', 'start()', 'launch()', 'start()'),

('What is JVM in Java?', 'Java Version Manager', 'Java Virtual Machine', 'Java Variable Model', 'Java Vendor Machine', 'Java Virtual Machine'),

('Which of the following is true about interfaces in Java?', 'They cannot contain abstract methods', 'They allow multiple inheritance', 'They cannot be implemented', 'They can contain constructors', 'They allow multiple inheritance'),

('Which file extension is used for Java bytecode?', '.java', '.exe', '.class', '.jar', '.class'),

('Which package contains ArrayList class?', 'java.util', 'java.io', 'java.lang', 'java.awt', 'java.util'),

('Which operator is used for comparison in Java?', '=', '==', ':=', '===', '==');

select * from questions;



CREATE TABLE results (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    score INT NOT NULL,
    taken_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
drop table results;

CREATE TABLE user_scores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    score INT NOT NULL,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
drop table user_scores;
select * from user_scores;

