import java.sql.*;

public class ResultSetNavigation {

    public static void main(String[] args) {

        String url =
                "jdbc:mysql://localhost:3306/studentdb";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con =
                    DriverManager.getConnection(
                            url, "root", "root");

            Statement stmt = con.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );

            ResultSet rs =
                    stmt.executeQuery("SELECT * FROM student");

            // next()
            if (rs.next()) {
                System.out.println(
                        "Next: " + rs.getString("name"));
            }

            // first()
            if (rs.first()) {
                System.out.println(
                        "First: " + rs.getString("name"));
            }

            // last()
            if (rs.last()) {
                System.out.println(
                        "Last: " + rs.getString("name"));
            }

            // previous()
            if (rs.previous()) {
                System.out.println(
                        "Previous: " + rs.getString("name"));
            }

            // absolute()
            if (rs.absolute(2)) {
                System.out.println(
                        "Second record: " +
                        rs.getString("name"));
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
