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
	private String name;

	/**
	 *  Ort des Kinos
	 */
	private String ort;

	/**
	 * Jedes Kino besitzt einen speziellen Spielplan. Ein Spielplan besteht aus den
	 * einzelnen Spielzeiten.
	 */

//	private Spielplan spielplanID;

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

	public String getName() {
		return name;
	}

	/**
	 * Setzen des Kinonamens
	 */

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Auslesen des Kinoortes
	 */

	public String getOrt() {
		return ort;
	}

	/**
	 * Setzen des Kinoortes
	 */

	public void setOrt(String ort) {
		this.ort = ort;
	}

	/**
	 * Auslesen der Kinospielpläne
	 */

	/*
	 * public Spielplan getSpielplanID() { return spielplanID; }
	 */

	/**
	 * Auslesen der Kinobspielpläne
	 */

	/*
	 * public void setSpielplanID(Spielplan spielplanID) { this.spielplanID =
	 * spielplanID; }
	 */

}



