/**
 * 
 */
package de.hdm.swprakt.cinemates.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.swprakt.cinemates.shared.bo.Gruppe;
import de.hdm.swprakt.cinemates.shared.bo.Nutzer;
import de.hdm.swprakt.cinemates.shared.bo.Umfrage;
import de.hdm.swprakt.cinemates.shared.bo.Umfrageeintrag;
import de.hdm.swprakt.cinemates.shared.bo.Votum;

/**
 * Synchrone Schnittstelle für eine RPC-fähige Klasse zur Planung eines Kinobesuchs.(Anlage und Verwaltung
 * von Gruppen, Umfragen, etc.)
 * @author alina
 * @version 1.0
 *
 */

@RemoteServiceRelativePath("kinobesuchsplanung")
public interface KinoBesuchsplanung extends RemoteService {

	/**
	 * Initialisierung des Objekts.
	 * @throws IllegalArgumentException
	 */

	public void init() throws IllegalArgumentException;

	public Nutzer findNutzerByEmail(String email);

	public Nutzer createNutzer(Nutzer nutzer);

	public Gruppe createGruppe(Gruppe gruppe);

	public Vector <Umfrage> showAllUmfrage();

	public Umfrage createUmfrage(String umfragenname);

	public Vector <Votum> showVotumOfUmfrageeintrag(Umfrageeintrag umfrageeintrag);

	public Umfrage editUmfrage(Umfrage umfrage, String umfragenname, String beschreibung);

	public void deleteUmfrage(Umfrage umfrage);

	public Votum abstimmen(Umfrageeintrag umfrageeintrag, Boolean istMöglicherTermin);

	public Vector <Umfrageeintrag> umfrageergebnisseAnzeigen(Umfrage umfrage);

	public Umfrageeintrag bestesErgebnisErmitteln(Umfrage umfrage);
	
	public void deleteGruppe(Gruppe gruppe);
	
	public void deleteUmfrageeintrag(Umfrageeintrag umfrageeintrag);
	
	public void deleteVotum(Votum votum);

}
