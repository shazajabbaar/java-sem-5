import java.sql.*;

public class BankTransfer {

    public static void main(String[] args) {

        String url =
                "jdbc:mysql://localhost:3306/studentdb";

        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(
                    url, "root", "root");

            con.setAutoCommit(false);

            int fromAccount = 101;
            int toAccount = 102;
            double amount = 1000;

            PreparedStatement withdraw =
                    con.prepareStatement(
                        "UPDATE account " +
                        "SET balance = balance - ? " +
                        "WHERE acc_no = ?");

            withdraw.setDouble(1, amount);
            withdraw.setInt(2, fromAccount);

            int rows1 =
                    withdraw.executeUpdate();

            PreparedStatement deposit =
                    con.prepareStatement(
                        "UPDATE account " +
                        "SET balance = balance + ? " +
                        "WHERE acc_no = ?");

            deposit.setDouble(1, amount);
            deposit.setInt(2, toAccount);

            int rows2 =
                    deposit.executeUpdate();

            if (rows1 == 1 && rows2 == 1) {

                con.commit();

                System.out.println(
                        "Transaction successful.");
            } else {

                con.rollback();

                System.out.println(
                        "Transaction failed.");
            }

            withdraw.close();
            deposit.close();
            con.close();

        } catch (Exception e) {

            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            e.printStackTrace();
        }
    }
}
