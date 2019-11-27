package de.hdm.swprakt.cinemates.shared.bo;

/**
 * 
 * Diese Klasse repräsentiert Umfrageeinträge einer Umfrage. 
 * Die Klasse <code> Umfrageeintrag </code> erweitert <code> BusinessObject </code>.
 * 
 * @author alina
 * @version 1.o
 *
 */
public class Umfrageeintrag extends BusinessObject {

	/**
	 * zur Serialisierung benötigt.
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Ein Umfrageeintrag benötigt eine zugehörige Umfrage. Die Referenz wird
	 * mithilfe einer id, welche auf die zugehörige Umfrage zeigt, realisiert. Aus
	 * der id kann mithilfe einer entsprechenden Methode (siehe Mapperklasse) das
	 * zughörige Umfrageobjekt ermittelt werden.
	 */
	private int umfrageID;

	/**
	 * Ein Umfrageeintrag benötigt eine zugehörige Spielzeit und ein Kino. Die Referenz wird
	 * mithilfe einer id, welche auf die zugehörige Spielezeit bzw. das zugehörige Kino zeigt, realisiert.
	 * Aus der id kann mithilfe einer entsprechenden Methode (siehe Mapperklasse)
	 * das zughörige Spielzeitobjekt ermittelt werden. Die Spielzeit wird aus einem
	 * Spielplan entnommen und stellt die Vorführung eines Films zu einer bestimmten
	 * Zeit in einem bestimmten Kino dar.
	 */

	private int spielzeitID;

	/**
	 * Ein Umfrageeintrag benötigt ein zugehöriges Kino. Die Referenz wird
	 * mithilfe einer id, welche auf das zugehörige Kino zeigt, realisiert.
	 * Aus der id kann mithilfe einer entsprechenden Methode (siehe Mapperklasse)
	 * das zughörige Kinoobjekt ermittelt werden. Die Spielzeit wird aus einem
	 * Spielplan entnommen und stellt die Vorführung eines Films zu einer bestimmten
	 * Zeit in einem bestimmten Kino dar.
	 */

	private int kinoID;


	/*default Konstruktor 
	 */
	public Umfrageeintrag() {
		super();
	}

	/*
	 * Im Folgenden werden die Getter und Setter für die privaten Attribute gesetzt,
	 * damit wir von außen auf diese zugreifen können.
	 */

	/**
	 * Auslesen des Attributs umfrageID
	 */
	public int getUmfrageID() {
		return umfrageID;
	}

	/**
	 * Setzen des Attributs umfrageID
	 */
	public void setUmfrageID(int umfrageID) {
		this.umfrageID = umfrageID;
	}

	/**
	 * Auslesen des Attributs spielzeitID
	 */
	public int getSpielzeitID() {
		return spielzeitID;
	}

	/**
	 * Setzen des Attributs spielzeitID
	 */
	public void setSpielzeitID(int spielzeitID) {
		this.spielzeitID = spielzeitID;
	}


	/**
	 * Auslesen des Attributs kinoID
	 */

	public int getKinoID() {
		return kinoID;
	}
	/**
	 * Setzen des Attributs kinoID
	 */

	public void setKinoID(int kinoID) {
		this.kinoID = kinoID;
	}

	/**
	 * Erzeugen einer textuellen Darstellung der jeweiligen Instanz der Klasse
	 * Umfrageeintrag. Diese besteht aus dem Text, der durch die
	 * <code>toString()</code>-Methode der Superklasse <code> BusinessObject </code>
	 * erzeugt wird, ergänzt durch die Attribute umfrageID, spielzeitID und kinoID.
	 */
	@Override
	public String toString() {
		return super.toString() + "; Umfrage-ID: " + this.umfrageID + "; Spielzeit-ID: " + this.spielzeitID 
				+ "; Kino-ID: " +this.kinoID;
	}

	/**
	 * Diese Methode erzeugt eine ganze Zahl, die für die Instanz von
	 * <code>Umfrageeintrag</code> charakteristisch ist.
	 * 
	 */

	@Override
	public int hashCode() {
		return super.getID();
	}

}