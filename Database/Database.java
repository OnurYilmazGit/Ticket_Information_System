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
