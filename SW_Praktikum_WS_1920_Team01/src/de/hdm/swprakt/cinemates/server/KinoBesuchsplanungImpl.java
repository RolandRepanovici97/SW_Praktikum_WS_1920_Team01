/**
 * 
 */
package de.hdm.swprakt.cinemates.server;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.swprakt.cinemates.server.db.GruppeMapper;
import de.hdm.swprakt.cinemates.server.db.NutzerMapper;
import de.hdm.swprakt.cinemates.server.db.OwnedBusinessObjectMapper;
import de.hdm.swprakt.cinemates.server.db.UmfrageMapper;
import de.hdm.swprakt.cinemates.server.db.UmfrageeintragMapper;
import de.hdm.swprakt.cinemates.server.db.VotumMapper;
import de.hdm.swprakt.cinemates.shared.KinoAdministration;
import de.hdm.swprakt.cinemates.shared.KinoBesuchsplanung;
import de.hdm.swprakt.cinemates.shared.bo.Gruppe;
import de.hdm.swprakt.cinemates.shared.bo.Nutzer;
import de.hdm.swprakt.cinemates.shared.bo.Umfrage;
import de.hdm.swprakt.cinemates.shared.bo.Umfrageeintrag;
import de.hdm.swprakt.cinemates.shared.bo.Votum;
/**
 * Diese Klasse stellt die Implementierungsklasse des Interface {@link KinoBesuchsplanung} dar. 
 * Sie beinhaltet die komplette Applikationslogik, welche zur Planung eines Kinobesuchs benötigt wird. 
 * (Anlage und Verwaltung von Gruppen, Umfragen, etc.) Sie benötigt Zugriff auf die  <code> KinoAdministration </code>,
 * da diese Methoden bereitstellt, welche für die Kinobesuchsplanung relevant sind. 
 * 
 * @author alina
 * @version 1.0
 *
 */
public class KinoBesuchsplanungImpl extends RemoteServiceServlet implements KinoBesuchsplanung {
	/** Zugriff auf KinoAdministration
	 * 
	 */

	private KinoAdministration administration = null;

	/** Der Kinobesucher benötigt Zuriff auf die Daten rund um eine Umfrage, Gruppe etc.
	 *  Dieser Zugriff wird über die jeweiligen Mapper realisiert. 
	 *  */
	private NutzerMapper nutzerMapper = null;
	private GruppeMapper gruppeMapper = null;
	private UmfrageMapper umfrageMapper = null;
	private UmfrageeintragMapper umfrageeintragMapper = null;
	private VotumMapper votumMapper = null;
	private OwnedBusinessObjectMapper ownedBusinessObjectMapper = null;


	public KinoBesuchsplanungImpl() throws IllegalArgumentException {

	}


	/* Initalisierung der Variablen, welche die Referenzen auf die Mapeprklassen darstellen. 
	 * Wir initialisieren diese durch den Aufruf des protected-Konstruktors. Dieser 
	 * ermöglicht uns, dass jeweils nur eine Instanz dieser Klasse erzeugt werden kann.
	 * */
	public void init() throws IllegalArgumentException {

		this.nutzerMapper = NutzerMapper.nutzerMapper();
		this.gruppeMapper = GruppeMapper.gruppeMapper();
		this.umfrageMapper = UmfrageMapper.umfrageMapper();
		this.umfrageeintragMapper = UmfrageeintragMapper.umfrageeintragMapper();
		this.votumMapper = VotumMapper.votumMapper();
		this.ownedBusinessObjectMapper = OwnedBusinessObjectMapper.ownedBusinessObjectMapper();

		KinoAdministrationImpl kinoAdministrationImpl = new KinoAdministrationImpl();
		kinoAdministrationImpl.init();
		this.administration = kinoAdministrationImpl;
	}


	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für Nutzer-Objekte
	 * ***************************************************************************
	 */

	/**
	 * Diese Methode...
	 */
	public Nutzer findNutzerByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Diese Methode...
	 */
	public Nutzer createNutzer(Nutzer nutzer) {

		/**
	 Einfügen eines Nutzers in die Nutzertabelle mit zugehöriger OwnedBusinessObject Referenz
	 Zurückgeben des Nutzerobjektes
		 */

		return null;
	}


	/**
	 * Diese Methode...
	 */
	public Gruppe createGruppe(Gruppe gruppe) {

		gruppe = gruppeMapper.insert(gruppe);

		return gruppe;
	}

	public Vector <Umfrage> showAllUmfrage() {


	}

	/*
	 * @author alina
	 */


	//	
	//	public Umfrage showAllNewUmfrage(Nutzer n) {
	//
	//		Vector <Umfrage> ergenisvector = new Vector <Umfrage>();
	//		Vector <Gruppe> gruppevector = gruppeMapper.getGruppenOf(n);
	//		for(Gruppe g: gruppevector) {
	//			Umfrage u = umfrageMapper.findByGruppename(g.getGruppenname());
	//			Vector <Integer> ueid= u.getUmfrageeinträgeIDs();
	//			Iterator iterate_value = ueid.iterator();
	//			Vector <Integer> intwerte = new Vector <Integer>();
	//			intwerte.addAll((Collection<? extends Integer>) iterate_value);		
	//			Vector <Umfrageeintrag> ueeintrag = new Vector <Umfrageeintrag>();
	//			for(Integer i: intwerte) {
	//				ueeintrag.add(i);
	//
	//
	//			}
	//			for(Umfrageeintrag umfrageeintrag: ueid) {
	//				Vector <Votum> votumvector = votumMapper.findVotumByUmfrageeintrag(umfrageeintrag);
	//
	//







	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für Umfrage-Objekte
	 * ***************************************************************************
	 */



	/**Diese Methode wird aufgerufen, wenn eine neue Umfrage erstellt wird. Es wird hier lediglich der Umfragenname übergeben, da wir diesen benötigen um ein
	 * Umfrageeobjekt initial lebensfähig zu machen. Alle anderen Attribute können wir später vergeben.
	 * @author alina
	 */

	public Umfrage createUmfrage(String umfragenname) { 

		Umfrage umfrage = new Umfrage();
		umfrage.setUmfragenname(umfragenname);
		umfrageMapper.insert(umfrage);

		return umfrage;

	}

	/**Diese Methode wird aufgerufen, wenn alle Vota zu einem Umfrageeintrag angezeigt werden sollen. Hierzu wird die Mapper Methode @link findVotumByUmfrageeintrag
	 * aufgerufen, welche uns einen Vector von Objekten der Klasse <Votum> zurückgibt.
	 * @author alina
	 */

	public Vector <Votum> showVotumOfUmfrageeintrag(Umfrageeintrag umfrageeintrag) {
		return this.votumMapper.findVotumByUmfrageeintrag(umfrageeintrag);

	}


	/**Diese Methode wird aufgerufen, wenn eine Umfrage bearbeitet wird. Hier kann der Name und die Beschreibung geändert werden.
	 * Es wird die zu bearbeitende Umfrage übergeben. Diese wird in der DB gesucht und entsprechend geändert.
	 * 
	 * @author alina
	 */

	public Umfrage editUmfrage(Umfrage umfrage, String umfragenname, String beschreibung) { 
		int umfrageID= umfrage.getID();
		Umfrage dbumfrage = umfrageMapper.findByID(umfrageID);
		umfrage.setUmfragenname(umfragenname);
		umfrage.setBeschreibung(beschreibung);
		umfrageMapper.update(dbumfrage);

		return umfrage;

	}

	/**Diese Methode wird aufgerufen, wenn eine Umfrage gelöscht wird.
	 * Es wird die zu löschende Umfrage übergeben. Diese wird in der DB gesucht und dort entsprechend gelöscht.
	 * 
	 * @author alina
	 */

	public void deleteUmfrage(Umfrage umfrage) { 
		int umfrageID= umfrage.getID();
		Umfrage dbumfrage = umfrageMapper.findByID(umfrageID);
		umfrageMapper.delete(dbumfrage);


	}

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für Votum und Umfrageeintrag -Objekte
	 * ***************************************************************************
	 */


	/**Diese Methode wird aufgerufen, wenn ein Nutzer für einen Umfrageeintrag abstimmt, das heißt ein Votum abgibt. 
	 * 
	 * @author alina
	 */
	public Votum abstimmen(Umfrageeintrag umfrageeintrag, boolean istMöglicherTermin) {
		Votum votum = new Votum();
		votum.setUmfrageeintragID(umfrageeintrag.getID());
		votum.setIstMöglicherTermin(istMöglicherTermin);
		votumMapper.update(votum);
		umfrageeintragMapper.update(umfrageeintrag);

		return votum;

	}

	/**Diese Methode wird aufgerufen, wenn wir alle Umfrageeinträge und deren Vota anzeigen möchten. Wir übergeben eine Umfrage, deren Ergebnisse wir darstellen möchten.
	 * Wir erhalten die Umfrageeinträge der Umfrage zurück. 
	 * 
	 * @author alina
	 */
	public Vector <Umfrageeintrag> umfrageergebnisseAnzeigen(Umfrage umfrage) {
		// Zunächst erstellen wir einen leeren Vector
		Vector <Umfrageeintrag> umfrageeinträge = new Vector <Umfrageeintrag>();
		// In den folgenden drei Variablen positiv, negativ und egal wird die Anzahl der verschiedenen Vota auf einen Umfrageeintrag festgehalten.
		int positiv = 0;
		int negativ = 0;
		int egal =  0;
		//Nun suchen wir alle Umfrageeinträge der übergegebenen Umfrage
		umfrageeinträge = umfrageeintragMapper.findByUmfrage(umfrage);
		//Wir iterieren durch die Umfrageeinträge durch und suchen die Votum-Objekte
		for (Umfrageeintrag eintrag: umfrageeinträge) {
			//Diese speichern wir wieder in einem Vector
			Vector <Votum> zwischenvector = votumMapper.findVotumByUmfrageeintrag(eintrag);
			/** Wir iterieren durch den Vector mit Votum-Objekten und ermitteln jeweils, ob das Votum positiv, negativ oder neutral war.
			 * Diese Ergebnisse speichern wir uns. Wir möchten die Ergebnisse neben dem jeweiligen Umfrageeintrag anzeigen. 
			 * 
			 * 
			 */
			for(Votum votum: zwischenvector) {
				if(votum.getIstMöglicherTermin()== true) {
					positiv +=1;

				}
				else if(votum.getIstMöglicherTermin() == false) {
					negativ+=1;
				}

				else if(votum.getIstMöglicherTermin()==null) {
					egal+=1;
				}
			}


		}
		return umfrageeinträge;
	}

	/**Diese Methode wird aufgerufen, wenn wir den bestmöglichen Termin einer Umfrage anzeigen möchten. Wir übergeben eine Umfrage, deren Ergebnisse wir darstellen möchten.
	 * Wir erhalten den bestmöglichen Termin zurück. 
	 * 
	 * @author alina
	 */
	public Umfrageeintrag bestesErgebnisErmitteln(Umfrage umfrage) {

		// Zunächst erstellen wir einen leeren Vector
		Vector <Umfrageeintrag> umfrageeinträge = new Vector <Umfrageeintrag>();

		// In den folgenden drei Variablen positiv, negativ und egal wird die Anzahl der verschiedenen Vota auf einen Umfrageeintrag festgehalten.
		int positiv = 0;
		int negativ = 0;
		int egal =  0;

		//Nun suchen wir alle Umfrageeinträge der übergegebenen Umfrage

		umfrageeinträge = umfrageeintragMapper.findByUmfrage(umfrage);

		//Wir iterieren durch die Umfrageeinträge durch und suchen die Votum-Objekte
		for (Umfrageeintrag eintrag: umfrageeinträge) {

			//Diese speichern wir wieder in einem Vector
			Vector <Votum> zwischenvector = votumMapper.findVotumByUmfrageeintrag(eintrag);

			/** Wir iterieren durch den Vector mit Votum-Objekten und ermitteln jeweils, ob das Votum positiv, negativ oder neutral war.
			 * Diese Ergebnisse speichern wir uns. Wir möchten die Ergebnisse neben dem jeweiligen Umfrageeintrag anzeigen. 
			 * 
			 * 
			 */
			for(Votum votum: zwischenvector) {
				if(votum.getIstMöglicherTermin()== true) {
					positiv +=1;
					eintrag.setPositiveAbstimmungen(positiv);

				}
				else if(votum.getIstMöglicherTermin() == false) {
					negativ+=1;
				}

				else if(votum.getIstMöglicherTermin()==null) {
					egal+=1;
				}
			}
			for (Umfrageeintrag eintrag2: umfrageeinträge) {
				Integer abst= eintrag2.getPositiveAbstimmungen();
				Vector <Integer> einträge = new Vector<Integer>();
				einträge.add(abst);
				int max = 0;
				for(int i = 0; i < einträge.length(); i++) {
					if(einträge[0] > max)
						max = einträge[i];
				}
				return max;


			}


		}

	}

}
