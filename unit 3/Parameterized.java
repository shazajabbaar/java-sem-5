import java.sql.*;
import java.util.Scanner;

public class StudentPreparedStatement {

    public static void main(String[] args) {

        String url =
                "jdbc:mysql://localhost:3306/studentdb";
        String user = "root";
        String password = "root";

        Scanner sc = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con =
                    DriverManager.getConnection(url, user, password);

            // INSERT
            System.out.print("Enter ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter name: ");
            String name = sc.nextLine();

            System.out.print("Enter age: ");
            int age = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter course: ");
            String course = sc.nextLine();

            String insert =
                    "INSERT INTO student VALUES (?, ?, ?, ?)";

            PreparedStatement ps =
                    con.prepareStatement(insert);

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setInt(3, age);
            ps.setString(4, course);

            ps.executeUpdate();

            System.out.println("Student inserted successfully.");

            // SEARCH
            System.out.print("Enter ID to search: ");
            int searchId = sc.nextInt();

            String search =
                    "SELECT * FROM student WHERE id=?";

            PreparedStatement ps2 =
                    con.prepareStatement(search);

            ps2.setInt(1, searchId);

            ResultSet rs = ps2.executeQuery();

            if (rs.next()) {
                System.out.println("ID: " +
                        rs.getInt("id"));
                System.out.println("Name: " +
                        rs.getString("name"));
                System.out.println("Age: " +
                        rs.getInt("age"));
                System.out.println("Course: " +
                        rs.getString("course"));
            } else {
                System.out.println("Student not found.");
            }

            rs.close();
            ps.close();
            ps2.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        sc.close();
    }
}
