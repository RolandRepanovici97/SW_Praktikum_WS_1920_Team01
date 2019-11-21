package de.hdm.swprakt.cinemates.shared.bo;


public class Film extends BusinessObject {

	/*
	 * Diese Klasse stellt die Filme dar....
	 * 
	 * @author Ömer Degirmenci
	 * 
	 * @version 1.0
	 */

	// Attribut serialVersionUID

	private static final long serialVersionUID = 1L;

	// Attribut filmtitel

	private String filmtitel;

	// Attribut beschreibung

	private String beschreibung;

	// Attribut spiellänge

	private float spiellänge;

	// Aulesen des Attributs filmtitel

	public String getFilmtitel() {
		return filmtitel;
	}

	// Setzen des Attributs filmtitel

	public void setFilmtitel(String filmtitel) {
		this.filmtitel = filmtitel;
	}

	// Aulesen des Attributs beschreibung

	public String getBeschreibung() {
		return beschreibung;
	}

	// Setzen des Attributs beschreibung

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	// Aulesen des Attributs spiellänge

	public float getSpiellänge() {
		return spiellänge;
	}

	// Setzen des Attributs spiellänge

	public void setSpiellänge(float spiellänge) {
		this.spiellänge = spiellänge;
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
}
