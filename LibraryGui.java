
package librarymanagementsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class LibraryGui {
    public static void main(String[] args) {
      SwingUtilities.invokeLater(()->new LibraryGui().displayMainMenu());
    }
 
  
    public void displayMainMenu() {
        JFrame f = new JFrame("Library Management System");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(500, 600);

        // Colors
        Color pastelPink = Color.decode("#FFD1DC");
        Color darkPink = Color.decode("#FF69B4");
        Color white = Color.WHITE;

        // Panel with button grid
        JPanel p = new JPanel(new GridLayout(8, 1, 10, 10)); 
        p.setBackground(pastelPink);
        p.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40)); 

        // Buttons
        JButton addBookButton = createStyledButton("Add Book", darkPink, white);
        JButton editBookButton = createStyledButton("Edit Book", darkPink, white);
        JButton issueBookButton = createStyledButton("Issue Book", darkPink, white);
        JButton viewBooksButton = createStyledButton("View Books", darkPink, white);
        JButton issueRecordButton = createStyledButton("Issue Record", darkPink, white);
        JButton searchButton = createStyledButton("Search for Book", darkPink, white);
        JButton deleteBookButton = createStyledButton("Delete Book", darkPink, white);
        JButton exitButton = createStyledButton("Exit", darkPink, white);

        //Add buttons to the panel
   
        p.add(addBookButton);
        p.add(editBookButton);
        p.add(issueBookButton);
        p.add(viewBooksButton);
        p.add(issueRecordButton);
        p.add(searchButton);
        p.add(deleteBookButton);
        p.add(exitButton);
        //Add the panel to the frame
        f.add(p);
        //Set frame visibilty
        f.setVisible(true);

        
        // pink title label
        JLabel titleLabel = new JLabel("Library Management System", SwingConstants.CENTER);
        titleLabel.setForeground(new Color(255, 105, 180)); // Hot pink
        titleLabel.setFont(new Font("Segoe Script", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(pastelPink);
        wrapper.add(titleLabel, BorderLayout.NORTH);
        wrapper.add(p, BorderLayout.CENTER);

        f.setContentPane(wrapper);
        f.setVisible(true);

        // Add action listeners to each button
        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                // allow user to enter details and call addBook() method
                String department = JOptionPane.showInputDialog("Enter Department: ");
                String author = JOptionPane.showInputDialog("Enter Author: ");
                String title = JOptionPane.showInputDialog("Enter title: ");
                String ID = JOptionPane.showInputDialog("Enter Book ID ");
                if(department == null || author == null || title == null || ID == null || 
                department.isEmpty()|| title.isEmpty() || author.isEmpty() || ID.isEmpty()){
                JOptionPane.showMessageDialog(null, "Invalid. All fields are required.");
                return;    
                }
                try{
                    int bookID = Integer.parseInt(ID);
                    BookManager book = new BookManager(department, author, title, bookID);
                    book.addBook();
                    JOptionPane.showMessageDialog(null, "Book added successfully");
                    
                } catch(NumberFormatException E){
                    JOptionPane.showMessageDialog(null, "Invalid. Book ID must be a number");
                }
            }
        });
        
        editBookButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        //get book ID from the user
        String ID = JOptionPane.showInputDialog("Enter book ID: ");

        // If ID is null or empty, exit early
        if (ID == null || ID.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Book ID is required.");
            return; 
        }

        try {
            // Parse the book ID
            int bookID = Integer.parseInt(ID);

            // Get new details for the book from the user
            String newTitle = JOptionPane.showInputDialog("Enter new title: ");
            String newAuthor = JOptionPane.showInputDialog("Enter new author: ");
            
            // Validate input fields
            if (newTitle == null || newAuthor == null || newTitle.isEmpty() || newAuthor.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Invalid. All fields are required.");
                return;
            }

            // Find the book in the list by ID
            BookManager bookToEdit = null;
            for (BookManager book : BookManager.bookList) {
                if (book.getBookID() == bookID) {
                    bookToEdit = book;
                    break;
                }
            }

            // If book is found, call editBook() to update it
            if (bookToEdit != null) {
                bookToEdit.editBook(newTitle, newAuthor);
                JOptionPane.showMessageDialog(null, "Book edited successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Book not found.");
            }

        } catch (NumberFormatException ex) {
            // Handle invalid Book ID format
            JOptionPane.showMessageDialog(null, "Invalid. Book ID must be a number.");
        }
    }
});




issueBookButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String ID = JOptionPane.showInputDialog("Enter book ID to issue: ");
        String studentName = JOptionPane.showInputDialog("Enter student name: ");
        String studentid = JOptionPane.showInputDialog("Enter student ID: ");

        if (ID == null || studentName == null || studentid == null ||
            ID.isEmpty() || studentName.isEmpty() || studentid.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields are required.");
            return;
        }

        try {
            int bookID = Integer.parseInt(ID);
            int studentID = Integer.parseInt(studentid);
            BookManager manager = new BookManager();
            boolean success = manager.issueBook(bookID, studentName, studentID);

            if (success) {
                JOptionPane.showMessageDialog(null, "Book issued and record saved.");
            } else {
                JOptionPane.showMessageDialog(null, "Book not found or failed to issue.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid, IDs must be numbers.");
        }
    }
});


        
 issueRecordButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Check if there are any issued records
        if (StudentRecordManager.issueList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No issue records found.");
            return;
        }

        // Get the most recent issued record (from the list)
        StudentRecordManager lastIssuedRecord = StudentRecordManager.issueList.get(StudentRecordManager.issueList.size() - 1);

        // Call issueRecord() to save the record to a file (if not already saved)
        boolean recordSaved = lastIssuedRecord.issueRecord();
        
        // Sisplay the details from the issued record object
        String studentName = lastIssuedRecord.getStudentName();
        int studentID = lastIssuedRecord.getStudentID();
        int bookID = lastIssuedRecord.getBookID();
        String department = lastIssuedRecord.getDepartment();

        // Prepare the record details string
        String recordDetails = "Student Name: " + studentName + "\n" +
                               "Student ID: " + studentID + "\n" +
                               "Book ID: " + bookID + "\n" +
                               "Department: " + department + "\n";

        // Show a success or failure message to show whether the record was saved or not
        if (recordSaved) {
            JOptionPane.showMessageDialog(null, recordDetails, "Issued Book Record", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Failed to save the record.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
});


        
     

      viewBooksButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        JTextArea area = new JTextArea(30, 30);
        area.setEditable(false);

        String bookData = BookManager.viewBooks();
        area.setText(bookData);

        JOptionPane.showMessageDialog(null, new JScrollPane(area), "Book Records", JOptionPane.INFORMATION_MESSAGE);
    }
});


        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle Exit action
                System.exit(0); // Close the application
            }
        });
        
        
        searchButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Options for search
        String[] options = {"Search by Title", "Search by Author", "Search by Book ID"};
        int choice = JOptionPane.showOptionDialog(
            null,
            "Choose a search method:",
            "Search Book",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            options,
            options[0]
        );

        String result = "Search cancelled.";
        if (choice == 0) { // Search by Title
            String title = JOptionPane.showInputDialog("Enter book title:");
            if (title != null && !title.isEmpty()) {
                result = BookManager.searchBook(title, "", -1);
            }
        } else if (choice == 1) { // Search by Author
            String author = JOptionPane.showInputDialog("Enter author name:");
            if (author != null && !author.isEmpty()) {
                result = BookManager.searchBook("", author, -1);
            }
        } else if (choice == 2) { // Search by Book ID
            String idInput = JOptionPane.showInputDialog("Enter book ID:");
            try {
                int bookID = Integer.parseInt(idInput);
                result = BookManager.searchBook("", "", bookID);
            } catch (Exception ex) {
                result = "Invalid Book ID.";
            }
        }

        JOptionPane.showMessageDialog(null, result);
    }
});


        deleteBookButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String idInput = JOptionPane.showInputDialog("Enter the Book ID to delete:");

        if (idInput == null || idInput.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Operation cancelled.");
            return;
        }

        try {
            int bookID = Integer.parseInt(idInput);
            BookManager bookToDelete = null;

            // Search for the book in the list
            for (BookManager book : BookManager.bookList) {
                if (book.getBookID() == bookID) {
                    bookToDelete = book;
                    break;
                }
            }

            if (bookToDelete != null) {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to delete this book?\n\n" +
                        "Title: " + bookToDelete.getTitle() + "\n" +
                        "Author: " + bookToDelete.getAuthor() + "\n" +
                        "Department: " + bookToDelete.getDepartement(),
                        "Confirm Deletion",
                        JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    bookToDelete.deleteBook();
                    JOptionPane.showMessageDialog(null, "Book deleted successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Deletion cancelled.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Book not found.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid Book ID. It must be a number.");
        }
    }
});

    }
    private JButton createStyledButton(String text, Color bgColor, Color fgColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        return button;
    }

    public void getUserChoice(String action) {
        switch (action) {
            case "addBook":
                String department = JOptionPane.showInputDialog("Enter Department:");
                String title = JOptionPane.showInputDialog("Enter Book Title:");
                String author = JOptionPane.showInputDialog("Enter Author:");
                String id = JOptionPane.showInputDialog("Enter Book ID:");

                if (department == null || title == null || author == null || id == null || department.isEmpty()
                        || title.isEmpty() || author.isEmpty() || id.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Book not added.");
                    return;
                }

                try {
                    int bookID = Integer.parseInt(id);
                    BookManager b = new BookManager(department, author, title, bookID);
                    b.addBook();
                    JOptionPane.showMessageDialog(null, "Book added successfully.");
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid Book ID.");
                }
                break;

            case "issueBook":
                String studentName = JOptionPane.showInputDialog("Enter Student Name:");
                String studentid = JOptionPane.showInputDialog("Enter Student ID:");
                String issueBookId = JOptionPane.showInputDialog("Enter Book ID to Issue:");
                String dept = JOptionPane.showInputDialog("Enter Department of the Book:");

                if (studentName == null || studentid == null || issueBookId == null || dept == null ||
                    studentName.isEmpty() || studentid.isEmpty() || issueBookId.isEmpty() || dept.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Operation cancelled.");
                    return;
                }

                try {
                    int studentID = Integer.parseInt(studentid);
                    int bookID = Integer.parseInt(issueBookId);
                    BookManager bm = new BookManager();
                    bm.issueBook(bookID, studentName, studentID);
                    JOptionPane.showMessageDialog(null, "Book issued.");
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid ID entered.");
                }
                break;
        }
    }
    


    public void displayRecordList(String type) {
        //displays the records entered by the user
        StringBuilder output = new StringBuilder();

        if (type.equals("books")) {
    if (BookManager.bookList.isEmpty()) {
        output.append("No books found.");
    } else {
        for (BookManager b : BookManager.bookList) {
            output.append("Department: " + b.getDepartement()
                        + ", Title: " + b.getTitle()
                        + ", Author: " + b.getAuthor()
                        + ", ID: " + b.getBookID()
                        + "\n");
        }
    }
} else if (type.equals("issued")) {
    if (StudentRecordManager.issueList.isEmpty()) {
        output.append("No issued records found.");
    } else {
        for (StudentRecordManager s : StudentRecordManager.issueList) {
            output.append("Name: " + s.getStudentName()
                        + ", ID: " + s.getStudentID()
                        + ", Book ID: " + s.getBookID()
                        + ", Department: " + s.getDepartment()
                        + "\n");
        }
    }
}

            
        
        
        

        JOptionPane.showMessageDialog(null, output.toString());
        
   
    
         

    }

   
    
  
          

    
}

    
        
  
