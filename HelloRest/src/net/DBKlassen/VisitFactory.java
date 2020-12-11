package net.DBKlassen;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import net.codejava.DBConnection;

public class VisitFactory {

	public Visit findbyId(long id) throws SQLException {
		Visit v = new Visit();
		v.setVisit_id(id);
		
		String sql = "SELECT * FROM visit WHERE visit_id = ?";
		try(PreparedStatement stmt = DBConnection.getConnection().prepareStatement(sql)) {
			stmt.setLong(1, id);
			
			ResultSet rs = stmt.executeQuery();
			v.setNachname(rs.getString("nachname"));
			v.setVorname(rs.getString("vorname"));
			v.setStrasse(rs.getString("strasse"));
			v.setHausnummer(rs.getString("hausnummer"));
			v.setPlz(rs.getInt("plz"));
			v.setStadt(rs.getString("stadt"));
			v.setTel_nr(rs.getLong("tel_nr"));
			v.setAnkunftszeit(rs.getTimestamp("ankunftszeit"));
			v.setBetreiber_id(rs.getLong("restaurant_id"));
		}
		
		return v;
	}
	
	public Visit findByOther(String nachname, String vorname, Timestamp ankunftszeit, Long restaurant_id) throws SQLException {
		Visit v = new Visit();
		v.setNachname(nachname);
		v.setVorname(vorname);
		v.setAnkunftszeit(ankunftszeit);
		v.setBetreiber_id(restaurant_id);
		
		
		String sql = "SELECT * FROM visit WHERE nachname=? AND vorname=? AND datum=? AND restaurant_id=?";
		
		try(PreparedStatement stmt = DBConnection.getConnection().prepareStatement(sql)) {
			stmt.setString(1, nachname);
			stmt.setString(2, vorname);
			stmt.setTimestamp(3, ankunftszeit);
			stmt.setLong(5, restaurant_id);
			
			ResultSet rs = stmt.executeQuery();
			v.setStrasse(rs.getString("strasse"));
			v.setHausnummer(rs.getString("hausnummer"));
			v.setPlz(rs.getInt("plz"));
			v.setStadt(rs.getString("stadt"));
			v.setTel_nr(rs.getLong("tel_nr"));
		}
		
		return v;
	}
}
