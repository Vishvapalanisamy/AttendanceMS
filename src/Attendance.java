import java.sql.*;
import java.util.*;

public class Attendance {
    private Connection conn;

    // Constructor - connect to DB
    public Attendance() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/attendance_db",
    "root",
    "Vishva@2005"
);

            System.out.println("✅ Connected to Database!");
        } catch (Exception e) {
            System.out.println(" Connection failed!");
            e.printStackTrace();
        }
    }

    // Add Student
    public void insertSampleStudent() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter student name: ");
            String name = sc.nextLine();
            System.out.print("Enter student email: ");
            String email = sc.nextLine();

            String query = "INSERT INTO students (name, email) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.executeUpdate();

            System.out.println("✅ Student added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Add Teacher
    public void insertSampleTeacher() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter teacher name: ");
            String name = sc.nextLine();
            System.out.print("Enter teacher email: ");
            String email = sc.nextLine();
            System.out.print("Enter teacher password: ");
            String password = sc.nextLine();

            String query = "INSERT INTO teachers (name, email, password) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.executeUpdate();

            System.out.println("✅ Teacher added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Mark Attendance
    public void markAttendance() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter student ID: ");
            int studentId = sc.nextInt();
            System.out.print("Enter teacher ID: ");
            int teacherId = sc.nextInt();
            sc.nextLine(); // consume newline
            System.out.print("Enter status (Present/Absent): ");
            String status = sc.nextLine();

            String query = "INSERT INTO attendance (student_id, teacher_id, date, status) VALUES (?, ?, CURDATE(), ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, studentId);
            stmt.setInt(2, teacherId);
            stmt.setString(3, status);
            stmt.executeUpdate();

            System.out.println("✅ Attendance marked!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // View Report
    public void viewReport() {
        try {
            String query = "SELECT s.name AS student, t.name AS teacher, a.date, a.status " +
                           "FROM attendance a " +
                           "JOIN students s ON a.student_id = s.id " +
                           "JOIN teachers t ON a.teacher_id = t.id";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("\n===== Attendance Report =====");
            while (rs.next()) {
                System.out.println(
                    "Student: " + rs.getString("student") +
                    ", Teacher: " + rs.getString("teacher") +
                    ", Date: " + rs.getDate("date") +
                    ", Status: " + rs.getString("status")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Main Menu
    public static void main(String[] args) {
        Attendance app = new Attendance();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Student Attendance System =====");
            System.out.println("1. Add Student");
            System.out.println("2. Add Teacher");
            System.out.println("3. Mark Attendance");
            System.out.println("4. View Attendance Report");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1: app.insertSampleStudent(); break;
                case 2: app.insertSampleTeacher(); break;
                case 3: app.markAttendance(); break;
                case 4: app.viewReport(); break;
                case 5: System.exit(0);
                default: System.out.println(" Invalid choice!");
            }
        }
    }
}
