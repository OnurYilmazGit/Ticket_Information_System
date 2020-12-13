import java.sql.*;
import java.util.Scanner;

public class Database {
    private static final String DB_URL = "jdbc:mysql://localhost/CS320_TIS?useSSL=false";
    private static final String USER = "root";
    private static final String PASS = "bnm678hjk.";
    private static Connection conn = null;
    private static Statement stmt = null;
    private static PreparedStatement pstmt = null;

    public void init() {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Problem!");
        }
    }
    
    protected static void events(int i) throws SQLException { }

        public static void main (String[]args) throws SQLException{
            Database ex = new Database();
            ex.init();
            System.out.println("1. Add User\n");
            System.out.print("Please choose any number from list: ");
            Scanner anything = new Scanner(System.in);
            int i = anything.nextInt();
            System.out.println("Number entered is : " + i);

            if (i >= 1 && i <= 4) {
                events(i);
            } else
                System.out.print("Please enter number between 1 and 12");
            int s = anything.nextInt();
            while ((s >= 1 && s <= 4) == false) {
                System.out.print("Please enter number between 1 and 12");
                int b = anything.nextInt();
                s = b;
                if (s >= 1 && s <= 4) {
                    events(s);
                }
            }
    }
}
