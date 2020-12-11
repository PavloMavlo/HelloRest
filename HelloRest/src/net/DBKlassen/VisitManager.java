package net.DBKlassen;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import net.codejava.DBConnection;

public class VisitManager {

	//Suche 1: Name, Tag -> Timestamp
	public List<Timestamp> findTimestamp(String nachname, String vorname, Calendar day) throws SQLException {
		
		//Liste an potenziellen Timestamps heraussuchen
		
		String sql = "SELECT ankunftszeit FROM visit WHERE vorname=? AND nachname=?";
		List<Timestamp> list = new ArrayList<Timestamp>();
		
		try(PreparedStatement stmt = DBConnection.getConnection().prepareStatement(sql)) {
			stmt.setString(1, vorname);
			stmt.setString(2, nachname);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Timestamp t = rs.getTimestamp("ankunftszeit");
				
				list.add(t);
			}
		}
		
		//Timestamps aussortieren, die nicht am richtigen Tag passiert sind
		
		for(Timestamp t: list) {
			if(t.getTime()%86400000 != day.getTimeInMillis()%86400000) {
				list.remove(t);
			}
		}
		return list;
	}
	
	
	
	
	//Suche 2: Timestamp -> Kontaktdaten von relevanten Personen
	public List<Visit> findByDate(Timestamp t) throws SQLException {
		List<Visit> list = new ArrayList<Visit>();

		long twohoursprior = t.getTime()-3600000;
		Timestamp anfang = new Timestamp(twohoursprior);
		
		long twohoursafter = t.getTime()+3600000;
		Timestamp ende = new Timestamp(twohoursafter);
		
		String sql = "SELECT * FROM visit WHERE ankunftszeit>=? AND ankunftszeit<=?";
		
		try(PreparedStatement stmt = DBConnection.getConnection().prepareStatement(sql)) {
			stmt.setTimestamp(1, anfang);
			stmt.setTimestamp(2, ende);
			
			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				Visit v= new Visit(rs.getLong("visit_id"), rs.getString("nachname"), rs.getString("vorname"), rs.getString("strasse"), rs.getString("hausnummer"), rs.getInt("plz"), rs.getString("stadt"), rs.getLong("tel_nr"), rs.getTimestamp("ankunftszeit"), rs.getLong("restaurant_id"));
				
				list.add(v);
			}
		}
		
		return list;
	}
}
