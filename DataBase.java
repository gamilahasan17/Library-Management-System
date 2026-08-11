
package librarymanagementsystem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.* ;
public class DataBase {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/library";
        String user = "root";
        String password = ""; 
          try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);

            System.out.println("Book Table:");
            String booksQuery = "SELECT * FROM books";
            Statement stmt1 = conn.createStatement();
            ResultSet booksRs = stmt1.executeQuery(booksQuery);

            while (booksRs.next()) {
                int bookId = booksRs.getInt("BookID");
                String title = booksRs.getString("Title");
                String author = booksRs.getString("Author");
                String department = booksRs.getString("Department");

                System.out.println(bookId + " | " + title + " | " + author + " | " + department);
            }

            System.out.println("\nStudent Records Table:");
            String studentQuery = "SELECT * FROM studentrecords";
            Statement stmt2 = conn.createStatement();
            ResultSet studentRs = stmt2.executeQuery(studentQuery);

            while (studentRs.next()) {
                int studentId = studentRs.getInt("StudentID");
                String studentName = studentRs.getString("StudentName");
                int bookId = studentRs.getInt("BookID");
                String department = studentRs.getString("Department");

                System.out.println(studentId + " | " + studentName + " | BookID: " + bookId + " | " + department);
            }

        
            booksRs.close();
            studentRs.close();
            stmt1.close();
            stmt2.close();
            conn.close();

            System.out.println("\nDone.");

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("SQL error.");
            e.printStackTrace();
        }
    }
}

        
    


