package net.codejava;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;


import javax.websocket.server.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.dbklassen.Betreiber;
import net.dbklassen.Visit;

import java.util.List;

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
				System.out.println("-------------------------------------------------Erfolgreich2----------------------------------------");

	            while(rs.next()) {
	                String name = rs.getString("Ladenname");
	                System.out.println(name);
	                
	            }
			
			
			
		} catch(SQLException e) {
			System.out.println(e.getMessage()+ "-------------------------------------------------------------------------------------------------");
		}
		

		


		
		return "Moin Meister";
		

		
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHTMLHello() throws SQLException {
		
		
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
				System.out.println("-------------------------------------------------Erfolgreich2----------------------------------------");

	            while(rs.next()) {
	                String name = rs.getString("Ladenname");
	                System.out.println(name);
	            }
			
			
			
		} catch(SQLException e) {
			System.out.println(e.getMessage()+ "-------------------------------------------------------------------------------------------------");
		}
		

		
		
		
		
        
        

		
		return "Hallo";
		

		
	}      
	
	
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{x}")
    public String getMsg(@QueryParam("x") String x) {

      
        return x + "Test";
    }
    

    @GET
    @Path("/query")
    public Response getUsers2(
    	@QueryParam("firstName") String firstName,
        @QueryParam("lastName") String lastName,
        @QueryParam("strasse") String strasse,
        @QueryParam("nummer") String nummer,
        @QueryParam("plz") int plz,
        @QueryParam("ort") String ort,
        @QueryParam("tel") long tel) throws SQLException {

    	System.out.println("Es geht!"+ "Daten: " + firstName+ " " + lastName +" "+ strasse + " "+ nummer + " " + plz+ " " + ort+ " "+ tel);
        
    	
    	long test = 1040;
    	long visitid= 1;
    	Timestamp time = new Timestamp(System.currentTimeMillis());
    	
    
    	
    	Betreiber betrieb = new Betreiber("Laden","Bob","Mueller","Strassenweg","2",30232,"Hannover",test);
   
    	//betrieb.insert();
    	
    	Visit besucher = new Visit(lastName,firstName,strasse,nummer,plz,ort,tel,test);
     	besucher.insert();

    	
    	
    	return Response
           .status(200)
           .entity("Daten: " + firstName+ " " + lastName +" "+ strasse + " "+ nummer + " " + plz+ " " + ort+ " "+ tel).build();

    }

}
	

