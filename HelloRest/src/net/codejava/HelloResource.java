package net.codejava;


import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.websocket.server.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import com.sun.research.ws.wadl.Response;

@Path("/test")
public class HelloResource {
	
	
	
	
	
   // "jdbc:oracle:thin:@localhost:1521:db01", "PP_ORAZA", "pp_oracle");

    static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:db01";
    static final String USER = "PP_ORAZA";
    static final String PASS = "pp_oracle";    
    static final String DB_DRIVER = "oracle.jdbc.OracleDriver";
    @GET
	@Produces(MediaType.TEXT_PLAIN)
	public String direBonjour()
	{
    	try {
			Class.forName(DB_DRIVER);			//Sucht die Klassen vom Driver
			System.out.println("-------------------------------------------------Erfolgreich----------------------------------------");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage()+ "-------------------------------------------------------------------------------------------------");
		}
		
		
		try {
			Connection cdConnection = DriverManager.getConnection(DB_URL, USER,PASS); //Verb
			String sql = "SELECT * FROM BETREIBER";
			PreparedStatement stm = cdConnection.prepareStatement(sql);
			 ResultSet rs = stm.executeQuery();
	            while(rs.next()) {
	                String name = rs.getString("Ladenname");
	                System.out.println(name);
	            }
			
			System.out.println("-------------------------------------------------Erfolgreich2----------------------------------------");
			
			
		} catch(SQLException e) {
			System.out.println(e.getMessage()+ "-------------------------------------------------------------------------------------------------");
		}
		

		


		
		return "<html><title>Hello</title><body><h1>Halllo Welt!</h1><body></html>";
		

		
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHTMLHello() throws SQLException {
		
	/*	
		try {
			Class.forName(DB_DRIVER);
			System.out.println("-------------------------------------------------Erfolgreich----------------------------------------");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage()+ "-------------------------------------------------------------------------------------------------");
		}
		
		
		try {
			Connection cdConnection = DriverManager.getConnection(DB_URL, USER,PASS);
			String sql = "SELECT * FROM BETREIBER";
			PreparedStatement stm = cdConnection.prepareStatement(sql);
			 ResultSet rs = stm.executeQuery();
	            while(rs.next()) {
	                String name = rs.getString("Ladenname");
	                System.out.println(name);
	            }
			
			System.out.println("-------------------------------------------------Erfolgreich2----------------------------------------");
			
			
		} catch(SQLException e) {
			System.out.println(e.getMessage()+ "-------------------------------------------------------------------------------------------------");
		}
		
*/
		
		
		
		
        
        

		
		return "<html><title>Hello</title><body><h1>Halllo Welt!</h1><body></html>";
		

		
	}

	
    @GET
    @Path("/{param}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getMsg(@PathParam("param") String msg) {
         System.out.println("Hallo" + msg);
        String output = "Hallo " + msg;
        return output;
    }
	
}
