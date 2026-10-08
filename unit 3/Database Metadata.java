import java.sql.*;

public class DatabaseMetadataExample {

    public static void main(String[] args) {

        String url =
                "jdbc:mysql://localhost:3306/studentdb";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con =
                    DriverManager.getConnection(
                            url, "root", "root");

            DatabaseMetaData meta =
                    con.getMetaData();

            System.out.println(
                    "Database Name: " +
                    meta.getDatabaseProductName());

            System.out.println(
                    "Database Version: " +
                    meta.getDatabaseProductVersion());

            System.out.println(
                    "Driver Name: " +
                    meta.getDriverName());

            System.out.println(
                    "Driver Version: " +
                    meta.getDriverVersion());

            System.out.println(
                    "URL: " + meta.getURL());

            System.out.println(
                    "User Name: " + meta.getUserName());

            System.out.println(
                    "Transactions Supported: " +
                    meta.supportsTransactions());

            System.out.println("Tables:");

            ResultSet rs =
                    meta.getTables(
                            null,
                            null,
                            "%",
                            new String[]{"TABLE"});

            while (rs.next()) {
                System.out.println(
                        rs.getString("TABLE_NAME"));
            }

            rs.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
