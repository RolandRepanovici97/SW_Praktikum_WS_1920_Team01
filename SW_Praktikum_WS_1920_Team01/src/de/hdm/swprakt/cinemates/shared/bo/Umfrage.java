/**
 * 
 */
package de.hdm.swprakt.cinemates.shared.bo;

import java.util.Date;
import java.util.Vector;

/**
 * Realisierung eines Umfrages......???
 * 
 * 
 * 
 * 
 * 
 * 
 * @author Roland
 *
 */

public class Umfrage extends OwnedBusinessObject {

	/**
	 * Attribut serialVersionUID
	 */
	public static final long serialVersionUID = 1L;

	/**
	 * Die Beschreibung des Umfrages
	 */
	private String beschreibung;

	/**
	 * Der Name der Umfrage
	 */
	private String umfragenname;

	private Date datum;

	private int filmID;
	
	private int gruppenID;
	
	

	/**
	 * Sammlung der IDs der Umrageeintr�ge in einer Vector die aus ein
	 * Fremdschlüsselbeziehung zu den Umfrageeintr�gen der Umfrage besteht
	 */
	Vector<Integer> umfrageeinträgeIDs = new Vector<>();



	/**
	 * Auslesen des Umfrage Beschreibungs
	 * 
	 * @return
	 */


	/*default Konstruktor 
	 */
	public Umfrage() {
		super();
	}
	public String getBeschreibung() {
		return beschreibung;
	}

	/**
	 * Setzen des Beschreibungs des Umfrages
	 * 
	 * @param beschreibung
	 */
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	/*
	 * Auslesen des IDs der Umfrageeintr�ge die zu der Umfrage geh�ren
	 */
	public Vector<Integer> getUmfrageeinträgeIDs() {
		return umfrageeinträgeIDs;
	}

	/**
	 * Setzen des IDs der Umfrageeintr�ge die zu der Umfrage gehören
	 * 
	 * @param umfrageeintr�geIDs
	 */
	public void setSpielplanIDs(Vector<Integer> umfrageeinträgeIDs) {
		this.umfrageeinträgeIDs = umfrageeinträgeIDs;
	}

	/**
	 * Auslesen des Namens des Umfrages
	 * 
	 * @return
	 */
	public String getUmfragenname() {
		return umfragenname;
	}

	/**
	 * Setzen des Namens des Umfrages
	 * 
	 * @param umfragenname
	 */
	public void setUmfragenname(String umfragenname) {
		this.umfragenname = umfragenname;
	}

	/**
	 * Auslesen der IDs der Gruppe
	 * @return gruppenID
	 */
	public int getGruppenID() {
		return gruppenID;
	}

	/**
	 * Setzen der ID der Gruppe
	 * 
	 * @param gruppenID
	 */
	public void setGruppenID(int gruppenID) {
		this.gruppenID = gruppenID;
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

	/**
	 * Erzeugen einer textuellen Darstellung der ...
	 */
	@Override
	public String toString() {
		return super.toString() + "; Umfragename: " + this.umfragenname + "; Beschreibung: " + this.beschreibung + "; Datum: " + this.datum + "; Film-ID: " + this.filmID;
	}

	/**
	 * Diese Methode �berp�ft zwei Objekte auf Geleichheit. (Vielleicht brauchen wir
	 * eine "equals" Methode hier nicht.
	 */
	@Override
	public boolean equals(Object o) {
		/**
		 * Abfragen, ob ein Objekt ungleich NULL ist und ob ein Objekt gecastet werden
		 * kann
		 */
		if (o != null && o instanceof Umfrage) {
			Umfrage c = (Umfrage) o;
			try {
				return super.equals(c);
			} catch (IllegalArgumentException e) {
				return false;
			}
		}
		return false;

	}

}

