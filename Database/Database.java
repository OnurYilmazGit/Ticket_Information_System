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

    protected static void events(int i) throws SQLException {
        Scanner input = new Scanner(System.in);
        Statement stmt = conn.createStatement();

        String[] args;
        if (i == 1) {
            System.out.print("Please enter Valid UserName: ");
            String name_of_user = input.nextLine().toLowerCase().trim();
            System.out.print("Please enter a Password ");
            String user_password = input.nextLine();
            String sql = "INSERT INTO user (`user_name`, `user_pass`) VALUES ('" + name_of_user + "','" + user_password + "')";
            try {
                int number_row = stmt.executeUpdate(sql);
                if (number_row > 0) {
                    System.out.println(number_row + " row(s) affected!");
                    System.out.println("User successfully added to Database");
                }
            } catch (SQLException throwables) {
                System.out.println("There is a such user in database. Plese try again");
            }
            System.out.println("-> To see main list again please enter 1 or enter random number to exit.");
            int number = input.nextInt();
            if (number == 1) {
                args = new String[0];
                main(args);
            }
        }
        else if( i == 2) {
            System.out.print("Please enter your UserName: ");
            String name_of_user = input.nextLine();
            System.out.print("Please enter a Password ");
            String user_password = input.nextLine();
            String sql = "SELECT * FROM user WHERE (user_name = '" +name_of_user+ "') AND (user_pass ='" +user_password+ "')";
            try {
                stmt.executeQuery(sql);
                System.out.println("Successfully login.");
            } catch (SQLException throwables) {
                System.out.println("There is no such a user in database. Plese try again");
            }
            System.out.println("-> To see main list again please enter 1 or enter random number to exit.");
            int number = input.nextInt();
            if (number == 1) {
                args = new String[0];
                main(args);
            }
        }
        else if( i == 3){
            System.out.println("List of Whole Events: \n");
            String sql = "SELECT * FROM main_page";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String event_name = rs.getString("event_name");
                String event_type = rs.getString("event_type");
                String location = rs.getString("location");
                String start_time = rs.getString("start_time");
                String date = rs.getString("date");
                double price = rs.getDouble("price");
                int available_ticket = rs.getInt("available ticket");

                System.out.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\n", "Event Name: " + event_name, "Event Type: " + event_type, "Location: " + location, "Start time : " + start_time,
                        "Date: " + date, "Price: " + price, "available_ticket:" + available_ticket);
            }
            System.out.println("-> To see main list again please enter 1");
            int number = input.nextInt();
            if (number == 1) {
                args = new String[0];
                main(args);
            } else
                events(12);
        }
    }


        public static void main (String[]args) throws SQLException{
            Database ex = new Database();
            ex.init();
            System.out.println("1. Add User\n2. Login User\n3. Show Main Page");
            System.out.print("Please choose any number from list: ");
            Scanner anything = new Scanner(System.in);
            int i = anything.nextInt();
            System.out.println("Number entered is : " + i);

            if (i >= 1 && i <= 12) {
                events(i);
            } else
                System.out.print("Please enter number between 1 and 12");
            int s = anything.nextInt();
            while ((s >= 1 && s <= 12) == false) {
                System.out.print("Please enter number between 1 and 12");
                int b = anything.nextInt();
                s = b;
                if (s >= 1 && s <= 12) {
                    events(s);
                }
            }
    }
}
