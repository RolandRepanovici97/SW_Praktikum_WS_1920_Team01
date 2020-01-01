package de.hdm.swprakt.cinemates.shared.bo;

import java.util.Date;



public class Spielzeit extends OwnedBusinessObject{

	/*
	 * Diese Klasse stellt die Spielzeit der Filme dar....
	 * 
	 * @author Ömer Degirmenci
	 * 
	 * @version 1.0
	 */

	// Attribut serialVersionUID

	private static final long serialVersionUID = 1L;

	// Attribut datum

	private Date zeitpunkt;

	// Attribut filmID 

	private int filmID;

	private int spielplanID;

	/*default Konstruktor 
	 */
	public Spielzeit() {
		super();
	}

	public Date getZeitpunkt() {
		return zeitpunkt;
	}

	public void setZeitpunkt(Date zeitpunkt) {
		this.zeitpunkt = zeitpunkt;
	}

	public int getFilmID() {
		return filmID;
	}

	public void setFilmID(int filmID) {
		this.filmID = filmID;
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
	 * Erzeugen einer textuellen Darstellung der jeweiligen Instanz der Klasse
	 * <code> Spielzeit </code>. Diese besteht aus dem Text, der durch die
	 * <code>toString()</code>-Methode der Superklasse <code> OwnedBusinessObject </code>
	 * erzeugt wird. 
	 */
	@Override
	public String toString() {
		return super.toString() + "; Zeitpunkt: " + this.zeitpunkt 
				+ "; Film-ID: " + this.filmID;
	}


	/**
	 * Diese Methode erzeugt eine ganze Zahl, die für die Instanz von
	 * <code>Spielzeit</code> charakteristisch ist.
	 * 
	 */

	@Override
	public int hashCode() {
		return super.getID();
	}

	public int getSpielplanID() {
		// TODO Auto-generated method stub
		return spielplanID;
	}

	public void setSpielplanID(int spielplanID) {
		this.spielplanID = spielplanID;
	}

}
