import java.sql.*;
import java.io.*;

public class BlobClobExample {

    public static void main(String[] args) {

        String url =
                "jdbc:mysql://localhost:3306/studentdb";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con =
                    DriverManager.getConnection(
                            url, "root", "root");

            // INSERT BLOB and CLOB
            String sql =
                    "INSERT INTO files " +
                    "(id, image_data, document_data) " +
                    "VALUES (?, ?, ?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, 1);

            FileInputStream image =
                    new FileInputStream("student.jpg");

            ps.setBinaryStream(2, image);

            FileReader document =
                    new FileReader("document.txt");

            ps.setCharacterStream(3, document);

            ps.executeUpdate();

            System.out.println("File stored successfully.");

            image.close();
            document.close();
            ps.close();

            // RETRIEVE BLOB
            PreparedStatement ps2 =
                    con.prepareStatement(
                            "SELECT image_data, document_data " +
                            "FROM files WHERE id=?");

            ps2.setInt(1, 1);

            ResultSet rs = ps2.executeQuery();

            if (rs.next()) {

                Blob blob = rs.getBlob("image_data");

                InputStream in = blob.getBinaryStream();

                FileOutputStream out =
                        new FileOutputStream("retrieved.jpg");

                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }

                in.close();
                out.close();

                Clob clob =
                        rs.getClob("document_data");

                Reader reader =
                        clob.getCharacterStream();

                FileWriter writer =
                        new FileWriter("retrieved.txt");

                char[] chars = new char[1024];
                int charsRead;

                while ((charsRead = reader.read(chars)) != -1) {
                    writer.write(chars, 0, charsRead);
                }

                reader.close();
                writer.close();

                System.out.println(
                        "Image and document retrieved.");
            }

            rs.close();
            ps2.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
