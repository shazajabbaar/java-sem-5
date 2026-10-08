import java.sql.*;

public class ResultSetMetadataExample {

    public static void main(String[] args) {

        String url =
                "jdbc:mysql://localhost:3306/studentdb";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con =
                    DriverManager.getConnection(
                            url, "root", "root");

            Statement stmt = con.createStatement();

            ResultSet rs =
                    stmt.executeQuery("SELECT * FROM student");

            ResultSetMetaData meta =
                    rs.getMetaData();

            int count = meta.getColumnCount();

            System.out.println(
                    "Number of columns: " + count);

            for (int i = 1; i <= count; i++) {

                System.out.println(
                        "Column " + i);

                System.out.println(
                        "Name: " +
                        meta.getColumnName(i));

                System.out.println(
                        "Type: " +
                        meta.getColumnTypeName(i));

                System.out.println(
                        "Size: " +
                        meta.getColumnDisplaySize(i));

                System.out.println(
                        "Nullable: " +
                        meta.isNullable(i));

                System.out.println();
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
