import java.sql.*;

public class StudentCRUD {

    static final String URL =
            "jdbc:mysql://localhost:3306/studentdb";
    static final String USER = "root";
    static final String PASSWORD = "root";

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con =
                    DriverManager.getConnection(URL, USER, PASSWORD);

            Statement stmt = con.createStatement();

            // INSERT
            String insert = "INSERT INTO student VALUES " +
                    "(1, 'Anu', 20, 'Computer Science')";
            stmt.executeUpdate(insert);

            // UPDATE
            String update =
                    "UPDATE student SET age=21 WHERE id=1";
            stmt.executeUpdate(update);

            // DELETE
            String delete =
                    "DELETE FROM student WHERE id=1";
            stmt.executeUpdate(delete);

            // SELECT
            ResultSet rs =
                    stmt.executeQuery("SELECT * FROM student");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " " +
                        rs.getString("name") + " " +
                        rs.getInt("age") + " " +
                        rs.getString("course")
                );
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
