/**
 * 
 */
package de.hdm.swprakt.cinemates.shared.bo;

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

public class Umfrage extends BusinessObject {

	/**
	 * Attribut serialVersionUID
	 */
	public static final long serialVersionUID = 1L;

	/**
	 * Fremdschl�sselbeziehung zum Inhaber des Umfrages
	 */
	private int erstellerID = 0;

	/**
	 * Die Beschreibung des Umfrages
	 */
	private String beschreibung;

	/**
	 * Der Name des Umfrages
	 */
	private String umfragenname;

	/**
	 * Sammlung der IDs der Spielpl�ne in einer Vector die aus ein
	 * Fremdschl�sselbeziehung zu den Spielpl�ne des Umfrages besteht
	 */
	Vector<Integer> spielplanIDs = new Vector<>();

	/**
	 * Sammlung der IDs der Gruppen in einer Vector die ein Fremdschl�sselbeziehung
	 * zu den Spielpl�ne des Umfrages besteht
	 */
	Vector<Integer> gruppenIDs = new Vector<>();

	/**
	 * Auslesen des Fremdschl�ssels des Umfrage Erstellers
	 * 
	 * @return
	 */
	public int getErstellerID() {
		return erstellerID;
	}

	/**
	 * Setzen des Fremdschl�ssels des Umfrage Erstellers
	 * 
	 * @param erstellerID
	 */
	public void setErstellerID(int erstellerID) {
		this.erstellerID = erstellerID;
	}

	/**
	 * Auslesen des Umfrage Beschreibungs
	 * 
	 * @return
	 */
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
	 * Auslesen des IDs der Spielpl�ne die zu der Umfrage geh�ren
	 */
	public Vector<Integer> getSpielplanIDs() {
		return spielplanIDs;
	}

	/**
	 * Setzen des IDs der Spielpl�ne die zu der Umfrage geh�ren
	 * 
	 * @param spielplanIDs
	 */
	public void setSpielplanIDs(Vector<Integer> spielplanIDs) {
		this.spielplanIDs = spielplanIDs;
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
	 * Auslesen des IDs der Gruppen die an der Umfrage eingeladet sind.
	 * 
	 * @return
	 */
	public Vector<Integer> getGruppenIDs() {
		return gruppenIDs;
	}

	/**
	 * Setzen des IDs der Gruppen die an der Umfrage eingeladet sind.
	 * 
	 * @param gruppenIDs
	 */
	public void setGruppenIDs(Vector<Integer> gruppenIDs) {
		this.gruppenIDs = gruppenIDs;
	}

	/**
	 * Erzeugen einer textuellen Darstellung der ...
	 */
	@Override
	public String toString() {
		return super.toString() + "Name von der Umfrage oder sowas: " + this.umfragenname + "Umfrage ErstellerID "
				+ this.erstellerID;
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

	// Wir brauchen kein hashCode hier. Nur bei den BO
	@Override
	public int hashCode() {
		return this.erstellerID;
	}

}
