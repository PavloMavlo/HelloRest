package net.codejava;


import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static Connection conn;
	String OracleDriver = "oracle.jdbc.OracleDriver";
	
    static {
        conn = null;
        try {
   
            conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:db01", "PP_ORAZA", "pp_oracle");
            conn.setAutoCommit(false);
            System.out.println("Connect durchgefuehrt ....");
        } catch (Exception e) {
            System.err.println("Error while connecting to database");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static Connection getConnection() {
        return conn;
    }
}
