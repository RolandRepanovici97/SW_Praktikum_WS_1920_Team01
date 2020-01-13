package de.hdm.swprakt.cinemates.shared.bo;

public class Kino extends BusinessObject{

	/*
	 * Diese Klasse stellt die Kinos dar.
	 * 
	 * @author Sinan Tayar
	 * 
	 * @version 1.0
	 */

	/**
	 *  Attribut serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *  Beschreibung des Kinos
	 */
	private String beschreibung;

	/**
	 *  Name des Kinos
	 */
	private String kinoname;

	/**
	 *  Ort des Kinos
	 */
	private String adresse;

	/**
	 * Jedes Kino besitzt einen speziellen Spielplan. Ein Spielplan besteht aus den
	 * einzelnen Spielzeiten.
	 */

	private int spielplanID;

	private int kinoketteID;



	/*default Konstruktor 
	 */
	public Kino() {
		super();
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
	 * Auslesen des Kinonamens
	 */

	public String getKinoname() {
		return kinoname;
	}

	/**
	 * Setzen des Kinonamens
	 */

	public void setKinoname( String name) {
		this.kinoname = name;
	}

	/**
	 * Auslesen des Kinoortes
	 */

	public String getAdresse() {
		return adresse;
	}

	/**
	 * Setzen des Kinoortes
	 */

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}



	/**
	 * Auslesen der Kinospielpläne
	 */

	/*
	 * public Spielplan getSpielplanID() { return spielplanID; }
	 */

	public int getSpielplanID() {
		return spielplanID;
	}

	public void setSpielplanID(int spielplanID) {
		this.spielplanID = spielplanID;
	}

	public int getKinoketteID() {
		return kinoketteID;
	}

	public void setKinoketteID(int kinoketteID) {
		this.kinoketteID = kinoketteID;
	}

	/**
	 * Auslesen der Kinobspielpläne
	 */

	/*
	 * public void setSpielplanID(Spielplan spielplanID) { this.spielplanID =
	 * spielplanID; }
	 */

	public String toString() {

		return super.toString() +  "; Kinoname: " + this.kinoname + "; Beschreibung: " + this.beschreibung + "; Adresse: " + this.adresse + "; Spielplan-ID: " + this.spielplanID + "; Kinokette-ID: " + this.kinoketteID;
	}
}



