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
	  * Jeder Kinobetreiber bekommt eine eindeutige ID
	  */
	 
	 private int kinobetreiberID;
	 
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
	  * Auslesen der KinobetreiberID
	  */
	 
	public int getKinobetreiberID() {
		return kinobetreiberID;
	}

	/**
	 * Setzen der KinobetreiberID
	 */

	public void setKinobetreiberID(int kinobetreiberID) {
		this.kinobetreiberID = kinobetreiberID;
	}

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
	 
}

