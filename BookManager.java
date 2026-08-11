package librarymanagementsystem;
   import java.io.File;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class BookManager {
    
    //Attributes
    private String departement , author , title;
    private int bookID;
    
    //Getters and setters
    public String getDepartement() {
    return departement;
}

    public String getAuthor() {
    return author;
}

    public String getTitle() {
    return title;
}

    public int getBookID() {
    return bookID;
}
    public void setBookID(int bookID){
    this.bookID=bookID;
}
  
    static ArrayList<BookManager> bookList = new ArrayList<>();
    
    public BookManager(){ //Default Constructor
        departement = author = title = "";
        bookID = 0;
    }
    public BookManager(String departement, String author, String title, int bookID){ //Constructor to create object with all details
        this.departement = departement;
        this.author = author;
        this.title = title;
        this.bookID = bookID;
    }
    //Method adds book to a departement and save is to a file
    public void addBook(){
        bookList.add(this); //Adds current book object to the ArrayList
        
        //Saves the book details to a file
        try{
            // Create department folder on D: drive if it doesn't exist
            new File("C:/" + departement).mkdir();
            FileWriter f = new FileWriter("C:/" +departement + "/" + bookID + ".txt");
            f.write("Departement: "+departement+"\nTitle: "+title+"\nAuthor: "+author+"\nBook ID: "+bookID); //Writes book data in the file
            f.close(); //Closes the file after writing
            System.out.println("Book Added to: " +departement+" Departement"); //Confirms to the user
            
        } catch(Exception e){
            System.out.println("Failed to add book..."); //Displaying a message if saving fails
        }
    }
    //Method deletes book from the library
    public void deleteBook(){
        for(int i=0 ; i<bookList.size() ; i++){
            if(bookList.get(i).bookID == this.bookID){ //Check if the book is found in the ArrayList
                bookList.remove(i); //Removes from ArrayList if found
                File bookFile = new File("C:/" + departement + "/" + bookID + ".txt");
                if (bookFile.exists()) {
                    bookFile.delete(); // Delete the file
                }//Deletes the file of the book
                System.out.println("Book Deleted: "+this.title); //Confirms to the user
                return; //Exit the method after deleting the book
            }
        }    
        System.out.println("Book not found in library.."); //If the book doesn't exist in the ArrayList
        
    }
    
    //Method edits the data of an existing book
    public void editBook(String newTitle, String newAuthor) {
    boolean found = false;

    for (BookManager book : bookList) {
        if (book.bookID == this.bookID) { // Check if the book is found in the ArrayList
            found = true;

            // Delete the old file (removes the old book file from the department folder)
            File oldFile = new File("C:/" + book.departement + "/" + book.bookID + ".txt");
            if (oldFile.exists()) {
                oldFile.delete(); // Delete the old file
            }

            // Update book details (only title and author)
            book.title = newTitle;
            book.author = newAuthor;

            // Ensure the department folder exists on C: drive
            File deptFolder = new File("C:/" + book.departement);  // Assuming department folder already exists in C:/
            if (!deptFolder.exists()) {
                System.out.println("Department folder does not exist.");
                return;  // Exit if the department folder doesn't exist
            }

            // Write updated data to a new file in the department folder on C:/
            try {
                // Write updated book details to a new file in the department folder
                FileWriter f = new FileWriter("C:/" + book.departement + "/" + book.bookID + ".txt");
                f.write("Department: " + book.departement + "\nTitle: " + newTitle + "\nAuthor: " + newAuthor + "\nBook ID: " + book.bookID);
                f.close(); // Close the file after writing
                System.out.println("Book Edited and saved successfully."); // Confirms to the user
            } catch (IOException e) {
                System.out.println("Failed to edit book..."); // Displaying a message if saving fails
            }
            break;
        }
    }

    if (!found) {
        System.out.println("Book not found in library.");
    }
}


    public static String searchBook(String title, String author, int bookID) {
    for (BookManager book : bookList) {
        if (book.getTitle().equalsIgnoreCase(title) ||
            book.getAuthor().equalsIgnoreCase(author) ||
            book.getBookID() == bookID) {
            return "Book Found:\nTitle: " + book.getTitle() +
                   "\nAuthor: " + book.getAuthor() +
                   "\nDepartment: " + book.getDepartement() +
                   "\nBook ID: " + book.getBookID();
        }
    }
    return "Book not found in library.";
}

   

    public boolean issueBook(int bookId, String studentName, int studentID) {
    for (BookManager book : bookList) {
        if (book.bookID == bookId) {
            // Book found â€” create and issue record
            StudentRecordManager issued = new StudentRecordManager(studentName, studentID, bookId, book.departement);
            return issued.issueRecord(); // true if successful
        }
    }
    return false; // book not found
}

    
   public static String viewBooks() {
    if (bookList.isEmpty()) {
        return "No books available.";
    }

    String result = "";
    for (BookManager book : bookList) {
        result += "\nDepartement: " + book.departement +
                  "\nTitle: " + book.title +
                  "\nAuthor: " + book.author +
                  "\nBook ID: " + book.bookID + "\n";
    }
    return result;
}

        
  }

