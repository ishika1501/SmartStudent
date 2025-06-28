CREATE DATABASE IF NOT EXISTS smartstudent;
USE smartstudent;

CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    roll_no     VARCHAR(20)  NOT NULL UNIQUE,
    department  VARCHAR(50)  NOT NULL,
    email       VARCHAR(100) NOT NULL,
    phone       VARCHAR(15),
    marks       INT CHECK (marks BETWEEN 0 AND 100)
);
