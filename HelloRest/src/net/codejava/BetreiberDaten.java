package net.codejava;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;







@Path("/Betreiber")
public class BetreiberDaten {

	
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHTMLHello() throws SQLException {
		
     
		
		return "<html><title>Hello</title><body><h1>Halllo Welt!</h1><body></html>";
		

		
	}      
	
	
	
	
}
