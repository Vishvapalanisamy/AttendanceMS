CREATE DATABASE IF NOT EXISTS attendance_db;
USE attendance_db;

CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE teachers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL
);

CREATE TABLE attendance (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    teacher_id INT,
    date DATE,
    status ENUM('Present','Absent'),
    FOREIGN KEY(student_id) REFERENCES students(id),
    FOREIGN KEY(teacher_id) REFERENCES teachers(id)
);
