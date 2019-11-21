/**
 * 
 */
package de.hdm.swprakt.cinemates.shared.bo;

/**
 * Diese Klasse repräsentiert das Votum zu einem zugehörigen Umfrageeintrag.
 * Die Klasse <code> Votum </code> erweitert <code> Business Object </code>.
 * @author alina
 * @version 1.0
 */
public class Votum extends BusinessObject{

	/*
	 * Ein Votum benötigt einen zugehörigen Umfrageeintrag. Die Referenz wird
	 * mithilfe einer id, welche auf den zugehörigen <code> Uumfrageeintrag </code>, zeigt, realisiert. Aus
	 * der id kann mithilfe einer entsprechenden Methode (siehe Mapperklasse) das
	 * zughörige Umfrageeintragobjekt ermittelt werden.
	 */
	private int umfrageeintragID;

	/*
	 * Ein Votum benötigt einen "Votumgeber", der darstellt, von welchem Nutzer die Stimme abgegeben wird.
	   Die Referenz wird mithilfe einer id, welche auf das zugehörige Nutzerobjekt zeigt, realisiert. Aus
	 * der id kann mithilfe einer entsprechenden Methode (siehe Mapperklasse) das
	 * zughörige Nutzerobjekt ermittelt werden.
	 */
	private int votumgeberID;

	/*
	 *Dieses Attribut repräsentiert die Abstimmung eines Nutzers. 
	 Stimmmt ein Nutzer für den jeweiligen Umfrageeintrag positiv ab (d.h. diese Kombination aus Spielzeit und Ort ist für ihn möglich), 
	 so wird das Attribut istMöglichertmin auf true gesetzt. Stimmt er negativ ab (d.h. diese Kombination aus Spielzeit und Ort ist ihm nicht möglich),
	 so wird das Attribut istMöglicherTermin auf false gesetzt. Enthält er sich, so bleibt es null. 
	 Das Attribut wird später zur Ermittlung des optimalen Termins innerhalb einer <code> Umfrage </code> benötigt. 
	 */

	private boolean istMöglicherTermin;


	/*default Konstruktor 
	 */
	public Votum() {
		super();
	}

	/*
	 * Im Folgenden werden die Getter und Setter für die privaten Attribute gesetzt,
	 * damit wir von außen auf diese zugreifen können.
	 */


	/**
	 * Auslesen des Attributs umfrageeintragID
	 * @return the umfrageeintragID
	 */

	public int getUmfrageeintragID() {
		return umfrageeintragID;
	}

	/**
	 * Setzen des Attributs umfrageeintragID
	 * @param umfrageeintragID the umfrageeintragID to set
	 */
	public void setUmfrageeintragID(int umfrageeintragID) {
		this.umfrageeintragID = umfrageeintragID;
	}

	/**
	 * Auslesen des Attributs votumgeberID
	 * @return the votumgeberID
	 */
	public int getVotumgeberID() {
		return votumgeberID;
	}

	/**
	 * Setzend es Attributs votumgeberID
	 * @param votumgeberID the votumgeberID to set
	 */
	public void setVotumgeberID(int votumgeberID) {
		this.votumgeberID = votumgeberID;
	}

	/**
	 * Auslesen des Attributs istMöglicherTermin
	 * @return the istMöglicherTermin
	 */
	public boolean isIstMöglicherTermin() {
		return istMöglicherTermin;
	}

	/**
	 * Setzen des Attributs istMöglicherTermin
	 * @param istMöglicherTermin the istMöglicherTermin to set
	 */
	public void setIstMöglicherTermin(boolean istMöglicherTermin) {
		this.istMöglicherTermin = istMöglicherTermin;
	}

	/*
	 * /**
	 * Erzeugen einer textuellen Darstellung der jeweiligen Instanz der Klasse
	 * <code> Votum </code>. Diese besteht aus dem Text, der durch die
	 * <code>toString()</code>-Methode der Superklasse <code> BusinessObject </code>
	 * erzeugt wird, ergänzt durch die Attribute umfrageeintragID, votumgeberID und istMöglicherTermin.
	 */
	@Override
	public String toString() {
		return super.toString() + "Votum zu Umfrageeintrag # " + this.umfrageeintragID 
				+ "Abgegeben von # " + this.votumgeberID + "Ist der Termin möglich?" + this.istMöglicherTermin;
	}


	/**
	 * Diese Methode erzeugt eine ganze Zahl, die für die Instanz von
	 * <code>Votum</code> charakteristisch ist.
	 * 
	 */

	@Override
	public int hashCode() {
		return super.getID();
	}

}
