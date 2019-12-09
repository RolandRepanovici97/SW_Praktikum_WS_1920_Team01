package de.hdm.swprakt.cinemates.shared.bo;

public class Kinokette extends OwnedBusinessObject {
	
	/*Diese Klasse dient der Darstellung der Kinoketten,
	 *somit besitzt ein Kino beispielsweise mehrere Standorte. 
	 *
	 * @author Sinan Tayar
	 * 
	 * @version 1.0
	 * 
	 */

	 private static final long serialVersionUID = 1L;

	 
	/**
	 * Jede Kinokette besitzt eine eindeutige Beschreibung, 
	 * wie beispielsweise den Ort
	 */
		
	 private String beschreibung;
	 
	/**
	  * Name der Kinokette
	  */
	 
	 private String kinokettenname;
	
	/**
	 * Auslesen der Kinobeschreibung
	 */

	public String getBeschreibung() {
		return beschreibung;
	}

	/**
	 * Setzen der Kinobeschreibung
	 */

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	/**
	 * Auslesen des Namens der Kinokette
	 */

	public String getKinokettenname() {
		return kinokettenname;
	}

	/**
	 * Setzen des Namens der Kinokette
	 */

	public void setKinokettenname(String kinokettenname) {
		this.kinokettenname = kinokettenname;
	}
	
	
	public String toString() {
		
		return super.toString() +  "; Kinokettenname: " + this.kinokettenname + "; Beschreibung: " + this.beschreibung;
	}
	 
}

