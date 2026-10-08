import java.sql.*;
import java.util.Scanner;

public class JDBCExceptionHandling {

    public static void main(String[] args) {

        String url =
                "jdbc:mysql://localhost:3306/studentdb";

        Scanner sc = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con =
                    DriverManager.getConnection(
                            url, "root", "root");

            try {
                System.out.print("Enter student ID: ");
                int id = sc.nextInt();

                if (id <= 0) {
                    throw new IllegalArgumentException(
                            "ID must be positive.");
                }

                Statement stmt =
                        con.createStatement();

                try {
                    String sql =
                            "INSERT INTO student " +
                            "VALUES (" + id +
                            ", 'Test', 20, 'CS')";

                    stmt.executeUpdate(sql);

                    System.out.println(
                            "Record inserted.");

                } catch (SQLIntegrityConstraintViolationException e) {

                    System.out.println(
                            "Duplicate record. ID already exists.");

                } catch (SQLException e) {

                    System.out.println(
                            "Invalid SQL query.");
                    e.printStackTrace();

                } finally {
                    stmt.close();
                }

            } catch (InputMismatchException e) {

                System.out.println(
                        "Invalid input. Enter an integer.");

            } catch (IllegalArgumentException e) {

                System.out.println(e.getMessage());

            }

            con.close();

        } catch (ClassNotFoundException e) {

            System.out.println(
                    "JDBC Driver not found.");

        } catch (SQLException e) {

            System.out.println(
                    "Connection error.");
            e.printStackTrace();

        } finally {
            sc.close();
        }
    }
}
