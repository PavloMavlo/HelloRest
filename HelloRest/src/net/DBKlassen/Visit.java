package net.DBKlassen;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import net.codejava.DBConnection;


public class Visit {
	private long visit_id;
	private String nachname;
	private String vorname;
	private String strasse;
	private String hausnummer;
	private int plz;
	private String stadt;
	private long tel_nr;
	private Timestamp ankunftszeit;
	private long betreiber_id;
	
	public Visit() {}
	
	public Visit(String nachname, String vorname, String strasse, String hausnummer, int plz, String stadt, Long tel_nr, Long betreiber_id) {
		setNachname(nachname);
		setVorname(vorname);
		setStrasse(strasse);
		setHausnummer(hausnummer);
		setPlz(plz);
		setStadt(stadt);
		setTel_nr(tel_nr);
		setBetreiber_id(betreiber_id);
	}
	
	public Visit(Long visit_id, String nachname, String vorname, String strasse, String hausnummer, int plz, String stadt, long tel_nr, Timestamp ankunftszeit, long betreiber_id) {
		setVisit_id(visit_id);
		setNachname(nachname);
		setVorname(vorname);
		setStrasse(strasse);
		setHausnummer(hausnummer);
		setPlz(plz);
		setStadt(stadt);
		setTel_nr(tel_nr);
		setAnkunftszeit(ankunftszeit);
		setBetreiber_id(betreiber_id);
	}

	public void insert() throws SQLException {
		String sql = "INSERT INTO visit VALUES(visit_id_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		Timestamp t = new Timestamp(System.currentTimeMillis());
		
		Boolean ok = false;
		
		try (PreparedStatement stmt = DBConnection.getConnection().prepareStatement(sql)) {
			stmt.setString(1, getNachname());
			stmt.setString(2, getVorname());
			stmt.setString(3, getStrasse());
			stmt.setString(4, getHausnummer());
			stmt.setInt(5, getPlz());
			stmt.setString(6, getStadt());
			stmt.setLong(7, getTel_nr());
			stmt.setTimestamp(8, t);
			stmt.setLong(9, getBetreiber_id());
			
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
	
	public Long getVisit_id() {
		return visit_id;
	}
	public void setVisit_id(Long visit_id) {
		this.visit_id = visit_id;
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
	
	public Long getBetreiber_id() {
		return betreiber_id;
	}
	public void setBetreiber_id(Long betreiber_id) {
		this.betreiber_id = betreiber_id;
	}

	public Timestamp getAnkunftszeit() {
		return ankunftszeit;
	}

	public void setAnkunftszeit(Timestamp ankunftszeit) {
		this.ankunftszeit = ankunftszeit;
	}
	
}
n