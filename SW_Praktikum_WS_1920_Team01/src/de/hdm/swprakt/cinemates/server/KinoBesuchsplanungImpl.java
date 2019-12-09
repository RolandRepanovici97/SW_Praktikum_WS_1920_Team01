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


	public Umfrage showAllNewUmfrage(Nutzer n) {

		Vector <Umfrage> ergenisvector = new Vector <Umfrage>();
		Vector <Gruppe> gruppevector = gruppeMapper.getGruppenOf(n);
		for(Gruppe g: gruppevector) {
			Umfrage u = umfrageMapper.findByGruppename(g.getGruppenname());
			Vector <Integer> ueid= u.getUmfrageeinträgeIDs();
			Iterator iterate_value = ueid.iterator();
			Vector <Integer> intwerte = new Vector <Integer>();
			intwerte.addAll((Collection<? extends Integer>) iterate_value);		
			Vector <Umfrageeintrag> ueeintrag = new Vector <Umfrageeintrag>();
			for(Integer i: intwerte) {
				ueeintrag.add(i);


			}
			for(Umfrageeintrag umfrageeintrag: ueid) {
				Vector <Votum> votumvector = votumMapper.findVotumByUmfrageeintrag(umfrageeintrag);












			}
			/**Diese Methode wird aufgerufen, wenn eine neue Umfrage erstellt wird. Es wird hier lediglich der Umfragenname übergeben, da wir diesen benötigen um ein
			 * Umfrageeobjekt initial lebensfähig zu machen. Alle anderen Attribute können wir später vergeben.
			 * @author alina
			 */
		

		}

		public Umfrage createUmfrage(String umfragenname) { 

		Umfrage umfrage = new Umfrage();
		umfrage.setUmfragenname(umfragenname);
		
		return umfrage;




}

}
