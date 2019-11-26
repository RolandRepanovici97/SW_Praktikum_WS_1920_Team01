/**
 * 
 */
package de.hdm.swprakt.cinemates.shared.bo;

/**
 * Diese Klasse repräsentiert das Votum zu einem zugehörigen Umfrageeintrag.
 * Die Klasse <code> Votum </code> erweitert <code> OwnedBusinessObject </code>.
 * @author alina
 * @version 1.0
 */
public class Votum extends OwnedBusinessObject{

	/**
	 * zur Serialisierung benötigt.
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * Ein Votum benötigt einen zugehörigen Umfrageeintrag. Die Referenz wird
	 * mithilfe einer id, welche auf den zugehörigen <code> Uumfrageeintrag </code>, zeigt, realisiert. Aus
	 * der id kann mithilfe einer entsprechenden Methode (siehe Mapperklasse) das
	 * zughörige Umfrageeintragobjekt ermittelt werden.
	 */
	private int umfrageeintragID;
	

	/**
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

	/**
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
	 * Auslesen des Attributs istMöglicherTermin
	 * @return the istMöglicherTermin
	 */
	public boolean getisIstMöglicherTermin() {
		return istMöglicherTermin;
	}

	/**
	 * Setzen des Attributs istMöglicherTermin
	 * @param istMöglicherTermin the istMöglicherTermin to set
	 */
	public void setIstMöglicherTermin(boolean istMöglicherTermin) {
		this.istMöglicherTermin = istMöglicherTermin;
	}
	

	/**
	 * Erzeugen einer textuellen Darstellung der jeweiligen Instanz der Klasse
	 * <code> Votum </code>. Diese besteht aus dem Text, der durch die
	 * <code>toString()</code>-Methode der Superklasse <code> OwnedBusinessObject </code>
	 * erzeugt wird, ergänzt durch die Attribute umfrageeintragID und istMöglicherTermin.
	 */
	@Override
	public String toString() {
		return super.toString() + "; Umfrageeintrag-ID: " + this.umfrageeintragID 
				 + "; möglicherTermin: " + this.istMöglicherTermin;
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
