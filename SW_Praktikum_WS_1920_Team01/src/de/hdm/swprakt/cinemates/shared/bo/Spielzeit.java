package de.hdm.swprakt.cinemates.shared.bo;

import java.util.Date;



public class Spielzeit extends BusinessObject{
	
	/*
	 * Diese Klasse stellt die Spielzeit der Filme dar....
	 * 
	 * @author Ömer Degirmenci
	 * 
	 * @version 1.0
	 */

	// Attribut serialVersionUID

	private static final long serialVersionUID = 1L;

	// Attribut uhrzeit
	
	private Date uhrzeit;
	
	// Attribut datum
	
	private Date datum;
	
	// Attribut filmID 
	
	private int filmID;
	
	// Attribut kinoID
	
	private int kinoID;
	
	// Attribut erstellerID
	
	private int erstellerID;

	public Date getUhrzeit() {
		return uhrzeit;
	}

	public void setUhrzeit(Date uhrzeit) {
		this.uhrzeit = uhrzeit;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public int getFilmID() {
		return filmID;
	}

	public void setFilmID(int filmID) {
		this.filmID = filmID;
	}

	public int getKinoID() {
		return kinoID;
	}

	public void setKinoID(int kinoID) {
		this.kinoID = kinoID;
	}

	public int getErstellerID() {
		return erstellerID;
	}

	public void setErstellerID(int erstellerID) {
		this.erstellerID = erstellerID;
	}
	
	
	
	/*
	 * Diese Methode überprüft zwei Objekte auf Gleichheit
	 * 
	 */

	//@Override
	//public boolean equals(Object o) {

	/**
	 * Abfragen, ob ein Objekt ungleich NULL ist und ob ein Objekt gecastet werden
	 * kann.
	 * 
	 */
		
	/*
	 * Wenn keine Gleichheit festgestellt wurde, geben wir false zurück.
	 */
	
	
	
	/**
	 * Diese Methode erzeugt eine ganze Zahl, die für die Instanz von
	 * <code>Spielzeit</code> charakteristisch ist.
	 * 
	 */

	@Override
	public int hashCode() {
		return super.getID();
	}
	
}
