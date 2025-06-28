# 🎓 SmartStudent - Java-Based Student Management System

SmartStudent is a modern, desktop-based Student Management System built using **Java**, **Swing (FlatLaf)**, **JDBC**, and **MySQL**. It enables administrators to efficiently manage student data with features like login, CRUD operations, search, export, and subject-wise grade calculation.

---
### 📸 Here is the inserted screenshot:

[Working Screenshot](https://docs.google.com/document/d/1QbXFkBiu3In4-P3LPV2-xjGP-y_vATN6NKyF_HJp1fg/edit?usp=sharing)

---

## 🚀 Features

- 🔐 Admin Login (default: `admin / admin123`)
- 📋 Add, View, Update, and Delete student records
- 🔍 Search by Roll Number, Name, Department, or Marks Range
- 📊 View Statistics (total students, highest/lowest marks, department count)
- 📤 Export data to CSV file
- 📚 Add subject-wise marks and calculate grades
- 🌈 FlatLaf-powered modern GUI (dark/light theme support)

---
## 🛠️ Setup Instructions

### 📌 Step 1: MySQL Setup
1. Open **MySQL Workbench** or **XAMPP phpMyAdmin**.  
2. Run the `student.sql` file to create the database and table.  
3. Make sure the database is named **`smartstudent`**.

---
### 📌 Step 2: Compile the Project

javac -cp "lib/*" -d out $(find src -name "*.java")


---

### 📌 Step 3: Run the Project

java -cp "out:lib/*" com.ishika.smartstudent.Main  


---
## 🔐 Default Admin Login
- Username: admin
- Password: admin123

---



## 🧰 Tech Stack

- Java 8 or higher
- Swing (FlatLaf for UI theming)
- JDBC (Java Database Connectivity)
- MySQL 5.7+ or 8.0+
- Git & GitHub

---


## 📁 Project Structure

```bash
SmartStudent/
├── lib/                     # JARs for MySQL and FlatLaf
│   ├── mysql-connector-j-9.3.0.jar
│   └── flatlaf-3.2.5.jar
├── src/                     # Source code
│   └── com/ishika/smartstudent/
│       ├── db/
│       ├── dao/
│       ├── model/
│       ├── service/
│       └── gui/
├── student.sql              # SQL file to set up database
├── README.md                # Project documentation

