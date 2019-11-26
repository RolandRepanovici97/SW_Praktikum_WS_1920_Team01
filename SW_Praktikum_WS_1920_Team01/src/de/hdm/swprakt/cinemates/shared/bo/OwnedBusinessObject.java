/**
 * 
 */
package de.hdm.swprakt.cinemates.shared.bo;


/**
 * Diese Klasse stellt eine Erweiterung der abstrakten Klasse <code> BusinessObject </code> dar. Sie beinhaltet alle Attribute und
 * Methoden, welche in der Superklasse <code> BusinessObject </code> definiert sind,
 * sowie das Attribut ownership vom Typ Integer.
 * Diese Klasse wird verwendet, um die Abhängigkeit eines Nutzers zu einem BusinessObject darzustellen: Ein <code> OwnedBusinessObejct </code> "gehört" einem Nutzer.
 * Nicht alle BusinessObjects besitzen eine solche Abhängigkeit, daher ist diese Klasse separiert von <code> BusinessObject </code> .
 * @author alina
 * @version 1.0
 *
 */
public abstract class OwnedBusinessObject extends BusinessObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 *  Ein <code> OwnedBusinessObject </code> benötigt einen zugehörigen Owner (Objekt der Klasse <code> Nutzer </code>). Die Referenz wird
	 * mithilfe einer id, welche auf den zugehörigen Nutzer zeigt, realisiert.
	 *  Aus der id kann mithilfe einer entsprechenden Methode (siehe Mapperklasse)
	 * das zughörige Nutzerobjekt ermittelt werden.
	 */

	private int ownerID;

	/**
	 * Auslesen des Attributs ownerID
	 * @return the ownerID
	 */
	public int getOwnerID() {
		return ownerID;
	}

	/**
	 * Setzen des Attributs ownerID
	 * @param ownerID the ownerID to set
	 */
	public void setOwnerID(int ownerID) {
		this.ownerID = ownerID;
	}	
	
	
	public String toString() {
		
		return super.toString() + " Owner-ID: " + this.ownerID;
	}
	


}
