
package librarymanagementsystem;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class StudentRecordManager {
    
    // Attributes
private String studentName;
private int studentID;
private int bookID;
private String departement;

//Setters and getters
public String getStudentName() {
    return studentName;
}

public int getStudentID() {
    return studentID;
}

public int getBookID() {
    return bookID;
}

public String getDepartment() {
    return departement;
}


// List to store issued records
static ArrayList<StudentRecordManager> issueList = new ArrayList<>();

// Default Constructor
public StudentRecordManager() {
studentName = departement = "";
studentID = bookID = 0;
}

// Parameterized Constructor
public StudentRecordManager(String studentName, int studentID, int bookID, String department) {
this.studentName = studentName;
this.studentID = studentID;
this.bookID = bookID;
this.departement = department;
}

// Method to issue a book and save record to file
public boolean issueRecord() {
    issueList.add(this); // Add to in-memory list

    try {
        // Create "IssuedBooks" folder on C: drive if it doesn't exist
        File issuedBooksFolder = new File("C:/IssuedBooks");
        if (!issuedBooksFolder.exists()) {
            issuedBooksFolder.mkdirs();
        }

        // Save record to a file
        FileWriter save = new FileWriter("C:/IssuedBooks/" + bookID + ".txt");
        save.write("Student Name: " + studentName + "\n" +
                   "Student ID: " + studentID + "\n" +
                   "Book ID: " + bookID + "\n" +
                   "Department: " + departement + "\n");
        save.close();

        return true; // success
    } catch (IOException e) {
        return false; // failed
    }
}


}



    

