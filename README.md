# 📚 Library Management System

A Java-based Library Management System developed as a Programming 2 project. The application provides a graphical interface for managing books and student borrowing records.

## ✨ Features

* 📖 Add new books
* ✏️ Edit existing book information
* 🗑️ Delete books
* 🔍 Search for books by title, author, or Book ID
* 📋 View available books
* 📚 Issue books to students
* 🧾 View student issue records
* 💾 File-based data storage
* 🗄️ MySQL database connectivity
* 🖥️ Java Swing graphical interface

## 🛠️ Technologies Used

* Java
* Java Swing
* ArrayList
* File I/O
* MySQL
* JDBC
* NetBeans IDE

## 🏗️ Main Components

### BookManager

Manages book-related operations including adding, editing, deleting, searching, viewing, and issuing books.

Book information includes:

* Book ID
* Title
* Author
* Department

### StudentRecordManager

Manages student borrowing records, including:

* Student ID
* Student Name
* Book ID
* Department

### LibraryGui

Provides the graphical user interface using Java Swing.

### DataBase1

Handles MySQL database connectivity using JDBC.

## 🗄️ Database Design

The system uses two main tables:

### Books

| Column     | Description            |
| ---------- | ---------------------- |
| BookID     | Unique book identifier |
| Title      | Book title             |
| Author     | Book author            |
| Department | Associated department  |

### StudentRecords

| Column      | Description              |
| ----------- | ------------------------ |
| StudentID   | Student identifier       |
| StudentName | Student's name           |
| BookID      | Borrowed book identifier |
| Department  | Student's department     |

## 🚀 How It Works

1. Launch the application.
2. Add books through the graphical interface.
3. Search, edit, or delete existing books.
4. Issue books to students.
5. View available books and borrowing records.
6. Store and retrieve information through the application's data management features.

## 🎓 Academic Project

**Course:** Programming 2
**Department:** Computer Systems Engineering
**University:** MSA University

## 👥 Team

* Gamila Hasan — GUI
* Laila Zulfacar — GUI
* Mariam Mohamed Reyad — Student Record Manager
* Marina Ayman — Book Manager
* Tasbeeh Khater — Database Handler
