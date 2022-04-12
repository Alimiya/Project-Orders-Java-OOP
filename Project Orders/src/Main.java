import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection con = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "0516");
            stmt = con.createStatement();
            PreparedStatement ps = con.prepareStatement("insert into public.orders(firstname, secondname, title, description, Price)values" + "(?,?,?,?,?)");
            Orders ord = new Orders("Project",
                    "Project in Java for unniversity",
                    0, "Ali", "Orymbayev", "OOP");
            Scanner s = new Scanner(System.in);
            System.out.print("First name:");
            ord.setFn(s.nextLine());
            System.out.print("Second name:");
            ord.setSn(s.nextLine());
            System.out.print("Title:");
            ord.setOt(s.nextLine());
            System.out.print("Description:");
            ord.setDescription(s.nextLine());
            System.out.print("Price:");
            ord.setPrice(s.nextDouble());
            ps.setString(1, ord.getFn());
            ps.setString(2, ord.getSn());
            ps.setString(3, ord.getOt());
            ps.setString(4, ord.getDescription());
            ps.setDouble(5, ord.getPrice());
            ps.executeUpdate();

            ResultSet rs = stmt.executeQuery("select*from public.orders");
            while (rs.next())
                System.out.println("First name: " + rs.getString(2) + " | " +
                        "Second name: " + rs.getString(3) + " | " +
                        "Title of your order: " + rs.getString(4) + " | " +
                        "Description of order: " + rs.getString(5) + " | " +
                        "Price: " + rs.getDouble(6) + " Tenge");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                assert stmt != null;
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        Interfac i = new Interfac() {
            @Override
            public void ending() {
                System.out.println("Thanks for your order");
            }
        };
        i.ending();
    }


}