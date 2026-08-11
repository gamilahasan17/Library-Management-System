# 📚 Library Management System

A Java-based Library Management System developed as a Programming 2 course project. The application provides a graphical interface for managing books and student borrowing records, with data stored using file handling and MySQL database connectivity.

## ✨ Features

* 📖 Add new books
* ✏️ Edit existing book information
* 🗑️ Delete books
* 🔍 Search for books by title, author, or Book ID
* 📋 View all available books
* 📚 Issue books to students
* 🧾 Store and view student issue records
* 💾 Save book and issue information to files
* 🗄️ Connect to a MySQL database
* 🖥️ User-friendly Java Swing GUI

## 🛠️ Technologies Used

* **Java**
* **Java Swing** — Graphical User Interface
* **ArrayList** — In-memory book and issue record management
* **File I/O** — Saving book and borrowing information
* **MySQL** — Database management
* **JDBC** — Java-MySQL connectivity
* **NetBeans IDE**

## 🏗️ Project Structure

The project is organized around several main classes:

### `BookManager`

Responsible for managing book information and operations, including:

* Adding books
* Editing books
* Deleting books
* Searching for books
* Viewing available books
* Issuing books

Book information includes:

* Book ID
* Title
* Author
* Department

### `StudentRecordManager`

Handles student borrowing records and stores information including:

* Student ID
* Student Name
* Book ID
* Department

### `LibraryGui`

Provides the graphical user interface using Java Swing.

The main menu provides access to:

* Add Book
* Edit Book
* Issue Book
* View Books
* Issue Record
* Search for Book
* Delete Book
* Exit

### `DataBase1`

Handles MySQL database connectivity using JDBC and retrieves information from the `books` and `studentrecords` tables.

## 🗄️ Database Design

The project uses two main tables:

### Books

| Column       | Description                    |
| ------------ | ------------------------------ |
| `BookID`     | Unique identifier for the book |
| `Title`      | Book title                     |
| `Author`     | Book author                    |
| `Department` | Associated department          |

### StudentRecords

| Column        | Description               |
| ------------- | ------------------------- |
| `StudentID`   | Unique student identifier |
| `StudentName` | Student's name            |
| `BookID`      | ID of the borrowed book   |
| `Department`  | Student's department      |

`BookID` connects the student borrowing records with the books table.

## 💻 Example Workflow

1. Launch the application.
2. Select **Add Book** to create a new book record.
3. Enter the department, title, author, and Book ID.
4. Use **Search for Book** to find an existing book.
5. Edit or delete a book when required.
6. Select **Issue Book** to record a student's borrowed book.
7. View available books or issue records through the main menu.

## 🚀 Getting Started

### Prerequisites

Make sure you have:

* Java JDK installed
* NetBeans IDE or another Java IDE
* MySQL Server
* MySQL Connector/J (JDBC driver)

### Database Setup

Create a MySQL database named:

```sql
CREATE DATABASE library;
```

The application expects the database connection to use:

```text
Database: library
Host: localhost
Port: 3306
Username: root
Password: 
```

Create the required `books` and `studentrecords` tables according to the project database structure.

### Running the Project

1. Clone or download this repository.
2. Open the project in NetBeans.
3. Configure the MySQL JDBC driver.
4. Make sure the MySQL server is running.
5. Verify the database connection settings.
6. Run the `LibraryGui` class.

## 📸 Application

The application provides a simple Java Swing interface with dedicated buttons for each library management operation.

## 🎓 Academic Project

**Course:** Programming 2
**Department:** Computer Systems Engineering
**University:** MSA University
**Semester:** Spring 2025

### Team

* Gamila Hassan — GUI
* Laila Zulfacar — GUI
* Mariam Mohamed Reyad — Student Record Manager
* Marina Ayman — Book Manager
* Tasbeeh Khater — Database Handler

## 📚 References

* FileWriter Class in Java — GeeksforGeeks
* Java Swing GUI — Nanyang Technological University
* Database Connectivity with MySQL — GeeksforGeeks
