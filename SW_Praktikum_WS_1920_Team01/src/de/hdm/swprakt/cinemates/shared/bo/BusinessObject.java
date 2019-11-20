package de.hdm.swprakt.cinemates.shared.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * Diese Klasse stellt die Basisklasse dar. Sie beinhaltet Attribute und
 * Methoden, welche die anderen Business Objects auch ben�tigen.
 * <p>
 * Zentrales Merkmal ist, dass jedes <code>BusinessObject</code> eine Nummer
 * besitzt, die man in einer relationalen Datenbank auch als Prim�rschl�ssel
 * bezeichnen w�rde. Au�erdem ist jedes <code>BusinessObject</code> als
 * {@link Serializable} gekennzeichnet. Durch diese Eigenschaft kann jedes
 * <code>BusinessObject</code> automatisch in eine textuelle Form �berf�hrt und
 * z.B. zwischen Client und Server transportiert werden.
 * </p>
 * 
 * @author alina
 * @version 1.0
 *
 *
 */
public abstract class BusinessObject implements Serializable {

	/**
	 * Attribut serialVersionUID
	 */

	private static final long serialVersionUID = 1L;

	/**
	 * Attribut id, dieses identifiziert jedes <code> BusinessObject </code>
	 * eindeutig
	 * 
	 */

	private int id = 0;

	/*
	 * Attribut erstellungszeitpunkt
	 * 
	 */

	private Date erstellungszeitpunkt;

	/*
	 * Aulesen des Attributs id
	 * 
	 */
	public int getID() {
		return this.id;
	}

	/*
	 * Setzen des Attributs id
	 * 
	 */

	public void setID(int id) {
		this.id = id;
	}

	/*
	 * Auslesen des Attributs erstellungszeitpunkt
	 * 
	 */
	public Date getErstellungszeitpunkt() {
		return this.erstellungszeitpunkt;
	}

	/*
	 * Setzen des Attributs erstellungszeitpunkt
	 * 
	 */
	public void setErstellungszeitpunkt(Date erstellungszeitpunkt) {
		this.erstellungszeitpunkt = erstellungszeitpunkt;
	}

	/*
	 * Erzeugen einer textuellen Darstellung der jeweiligen Instanz.
	 */
	@Override
	public String toString() {
		/*
		 * Wir geben den Klassennamen gefolgt von der ID des Objekts zur�ck.
		 */
		return this.getClass().getName() + " #" + this.id;
	}

	/*
	 * Diese Methode �berpr�ft zwei Objekte auf Gleichheit
	 * 
	 */

	@Override
	public boolean equals(Object o) {

		/**
		 * Abfragen, ob ein Objekt ungleich NULL ist und ob ein Objekt gecastet werden
		 * kann.
		 * 
		 */

		if (o != null && o instanceof BusinessObject) {
			BusinessObject bo = (BusinessObject) o;
			try {
				if (bo.getID() == this.id)
					return true;
			} catch (IllegalArgumentException e) {

				/*
				 * falls eine Exception geworfen wird, geben wir false zur�ck.
				 * 
				 */

				return false;
			}
		}
		/*
		 * Wenn keine Gleichheit festgestellt wurde, geben wir false zur�ck.
		 */

		return false;
	}

	/*
	 * Diese Methode erzeugt eine ganze Zahl, die f�r das
	 * <code>BusinessObject</code> charakteristisch ist.
	 * 
	 */

	@Override
	public int hashCode() {
		return this.id;
	}

}
