package net.DBKlassen;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BetreiberFactory {

	public Betreiber findbyId(long id) throws SQLException {
		Betreiber r = new Betreiber();
		r.setBetreiber_id(id);
		
		String sql = "SELECT * FROM betreiber WHERE restaurant_id = ?";
		try(PreparedStatement stmt = DBConnection.getConnection().prepareStatement(sql)) {
			stmt.setLong(1, id);
			
			ResultSet rs = stmt.executeQuery();
			r.setLadenname(rs.getString("name"));
			r.setNachname(rs.getString("nachname"));
			r.setVorname(rs.getString("nachname"));
			r.setStrasse(rs.getString("strasse"));
			r.setHausnummer(rs.getString("hausnummer"));
			r.setPlz(rs.getInt("plz"));
			r.setStadt(rs.getString("stadt"));
			r.setTel_nr(rs.getLong("tel_nr"));
		}
		
		return r;
	}
	
	public Betreiber findByOther(String ladenname, String strasse, String hausnummer, int plz) throws SQLException {
		Betreiber r = new Betreiber();
		r.setLadenname(ladenname);
		r.setStrasse(strasse);
		r.setHausnummer(hausnummer);
		r.setPlz(plz);
		
		String sql = "SELECT * FROM betreiber WHERE name=? AND strasse=? AND hausnummer=? AND plz=?";
		
		try(PreparedStatement stmt = DBConnection.getConnection().prepareStatement(sql)) {
			stmt.setString(1, ladenname);
			stmt.setString(2, strasse);
			stmt.setString(3, hausnummer);
			stmt.setInt(4, plz);
			
			ResultSet rs = stmt.executeQuery();
			r.setNachname(rs.getString("nachname"));
			r.setVorname(rs.getString("nachname"));
			r.setStadt(rs.getString("stadt"));
			r.setTel_nr(rs.getLong("tel_nr"));
		}
		
		return r;
	}
	
	
}
