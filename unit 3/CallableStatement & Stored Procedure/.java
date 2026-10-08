import java.sql.*;

public class CallableExample {

    public static void main(String[] args) {

        String url =
                "jdbc:mysql://localhost:3306/studentdb";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con =
                    DriverManager.getConnection(
                            url, "root", "root");

            CallableStatement cs =
                    con.prepareCall("{call getStudent(?)}");

            cs.setInt(1, 1);

            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " " +
                        rs.getString("name") + " " +
                        rs.getInt("age") + " " +
                        rs.getString("course")
                );
            }

            rs.close();
            cs.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
