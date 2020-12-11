package net.DBKlassen;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Betreiber {
	private Long betreiber_id;
	private String ladenname;
	private String nachname;
	private String vorname;
	private String strasse;
	private String hausnummer;
	private int plz;
	private String stadt;
	private Long tel_nr;
	
	public Betreiber() {}
	
	public Betreiber (String ladenname, String nachname, String vorname, String strasse, String hausnummer, int plz, String stadt, Long tel_nr) {
		setLadenname(ladenname);
		setNachname(nachname);
		setVorname(vorname);
		setStrasse(strasse);
		setHausnummer(hausnummer);
		setPlz(plz);
		setStadt(stadt);
		setTel_nr(tel_nr);
	}
	
	public void insert() throws SQLException {
		String sql = "INSERT INTO betreiber VALUES (betreiber_id_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		Boolean ok = false;
		try(PreparedStatement stmt = DBConnection.getConnection().prepareStatement(sql)) {
			stmt.setString(1, getLadenname());
			stmt.setString(2, getNachname());
			stmt.setString(3, getVorname());
			stmt.setString(4,  getStrasse());
			stmt.setString(5,  getHausnummer());
			stmt.setInt(6, getPlz());
			stmt.setString(7, getStadt());
			stmt.setLong(8, getTel_nr());
			
			stmt.executeUpdate();
			
			ok = true;
		} finally {
			if(ok) {
				DBConnection.getConnection().commit();
			} else {
				DBConnection.getConnection().rollback();
			}
		}
		
		sql = "SELECT betreiber_id FROM betreiber WHERE ladenname=? AND nachname=? AND vorname=? AND strasse =? AND hausnummer =? AND plz=?";
		try(PreparedStatement stmt = DBConnection.getConnection().prepareStatement(sql)) {
			stmt.setString(1, getLadenname());
			stmt.setString(2, getNachname());
			stmt.setString(3, getVorname());
			stmt.setString(4, getStrasse());
			stmt.setString(5, getHausnummer());
			stmt.setInt(6, getPlz());
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				setBetreiber_id(rs.getLong("betreiber_id"));
			}
			
		}
	}
	
	public void update() throws SQLException {
		String sql = "UPDATE betreiber SET ladenname=?, strasse=?, hausnummer=?, plz=?, stadt=?, tel_nr=? WHERE betreiber_id=?";
		
		Boolean ok = false;
		
		try(PreparedStatement stmt = DBConnection.getConnection().prepareStatement(sql)) {
			stmt.setString(1, getLadenname());
			stmt.setString(2, getStrasse());
			stmt.setString(3, getHausnummer());
			stmt.setInt(4, getPlz());
			stmt.setString(5, getStadt());
			stmt.setLong(6, getTel_nr());
			stmt.setLong(7, getBetreiber_id());
			
			stmt.executeUpdate();
			ok = true;
		} finally {
			if(ok) {
				DBConnection.getConnection().commit();
			} else {
				DBConnection.getConnection().rollback();
			}
		}
	}
	
	
	public Long getBetreiber_id() {
		return betreiber_id;
	}
	public void setBetreiber_id(Long restaurant_id) {
		this.betreiber_id = restaurant_id;
	}
	
	public String getLadenname() {
		return ladenname;
	}
	public void setLadenname(String ladenname) {
		this.ladenname = ladenname;
	}
	
	public String getStrasse() {
		return strasse;
	}
	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}
	
	public String getHausnummer() {
		return hausnummer;
	}
	public void setHausnummer(String hausnummer) {
		this.hausnummer = hausnummer;
	}
	
	public int getPlz() {
		return plz;
	}
	public void setPlz(int plz) {
		this.plz = plz;
	}
	
	public String getStadt() {
		return stadt;
	}
	public void setStadt(String stadt) {
		this.stadt = stadt;
	}
	
	public Long getTel_nr() {
		return tel_nr;
	}
	public void setTel_nr(Long tel_nr) {
		this.tel_nr = tel_nr;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	
}
