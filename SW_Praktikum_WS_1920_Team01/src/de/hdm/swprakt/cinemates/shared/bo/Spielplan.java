package de.hdm.swprakt.cinemates.shared.bo;

import java.util.Vector;



public class Spielplan extends OwnedBusinessObject {
	
	
	/*
	 * Diese Klasse stellt den Spielplan
	 * @author Ömer Degirmenci
	 * 
	 * @version 1.0
	 */

	// Attribut serialVersionUID

	private static final long serialVersionUID = 1L;

	
	
	// Attribut spielPlanName
	
	private String spielplanname; 
	
	/**
	 * Sammlung der  Spielzeiten IDs in einem Vector. 
	 * 
	 */
	Vector<Integer> spielzeitIDs = new Vector<>();

	

	/*default Konstruktor 
	 */
	public Spielplan() {
		super();
	}


	// Aulesen des Attributs spielplanname
	
	public String getSpielplanname() {
		return spielplanname;
	}
	
	// Setzen des Attributs spielplanname

	public void setSpielplanname(String spielplanname) {
		this.spielplanname = spielplanname;
	}

	// Aulesen des Attributs spielzeitIDs
	
	public Vector<Integer> getSpielzeitIDs() {
		return spielzeitIDs;
	}

	// Setzen des Attributs spielzeitID
	
	public void setSpielzeitIDs(Vector<Integer> spielzeitIDs) {
		this.spielzeitIDs = spielzeitIDs;
	}
	
	
	/**
	 * Erzeugen einer textuellen Darstellung der jeweiligen Instanz der Klasse
	 * <code> Spielplan </code>. Diese besteht aus dem Text, der durch die
	 * <code>toString()</code>-Methode der Superklasse <code> OwnedBusinessObject </code>
	 * erzeugt wird. 
	 */
	@Override
	public String toString() {
		return super.toString() + "; Spielplanname: " + this.spielplanname;
	}
	
	
	/**
	 * Diese Methode erzeugt eine ganze Zahl, die für die Instanz von
	 * <code>Spielplan</code> charakteristisch ist.
	 * 
	 */

	@Override
	public int hashCode() {
		return super.getID();
	}
}


