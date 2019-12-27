/**
 * 
 */
package de.hdm.swprakt.cinemates.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.swprakt.cinemates.shared.bo.Film;
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

	public Nutzer findNutzerByEmail(String email) throws IllegalArgumentException;

	public Nutzer createNutzer(String email, String nutzername) throws IllegalArgumentException;

	public void save(Nutzer nutzer) throws IllegalArgumentException;

	public void save(Gruppe gruppe) throws IllegalArgumentException;

	public Vector <Umfrage> showAllUmfrage();

	public Umfrage createUmfrage(String umfragenname) throws IllegalArgumentException;

	public Vector <Votum> showVotumOfUmfrageeintrag(Umfrageeintrag umfrageeintrag) throws IllegalArgumentException;

	public void deleteUmfrage(Umfrage umfrage) throws IllegalArgumentException;


	public Vector <Umfrageeintrag> umfrageergebnisseAnzeigen(Umfrage umfrage) throws IllegalArgumentException;

	public Umfrageeintrag bestesErgebnisErmitteln(Umfrage umfrage) throws IllegalArgumentException;

	public void deleteUmfrageeintrag(Umfrageeintrag umfrageeintrag) throws IllegalArgumentException;

	public void deleteVotum(Votum votum) throws IllegalArgumentException;

	public Vector <Gruppe>getAllGruppen() throws IllegalArgumentException;

	public Vector <Gruppe> getAllGruppenOfNutzer(Nutzer nutzer) throws IllegalArgumentException;

	public Vector <Gruppe> getAllGruppenOfUmfrage(Umfrage umfrage) throws IllegalArgumentException;

	public Nutzer getOwnerOfGruppe(Gruppe gruppe) throws IllegalArgumentException;

	public void mitgliedEntfernen(Nutzer nutzer, Gruppe gruppe) throws IllegalArgumentException;

	public Gruppe mitgliedHinzufügen(Nutzer nutzer, Gruppe gruppe) throws IllegalArgumentException;

	public Gruppe createGruppe(Nutzer nutzer, String gruppenname, Vector<Nutzer> gruppenmitglieder) throws IllegalArgumentException;

	public void deleteGruppe(Gruppe gruppe) throws IllegalArgumentException;

	public Umfrage save(Umfrage umfrage) throws IllegalArgumentException;

	public Vector<Umfrage> showAllUmfragenOfGruppe(Gruppe gruppe) throws IllegalArgumentException;

	public Vector<Umfrage> showAllUmfrageOfNutzer(Nutzer nutzer) throws IllegalArgumentException;

	public Vector<Umfrage> showAllUmfragenOFilm(Film film) throws IllegalArgumentException;

	public Vector<Umfrage> showAllUmfrageOfNutzerOhneErgebnis(Nutzer nutzer) throws IllegalArgumentException;

	public Vector <Umfrageeintrag> showUmfrageeinträgeofUmfrage(Umfrage umfrage) throws IllegalArgumentException;

	public Umfrage findUmfrageByID(int id) throws IllegalArgumentException;

	public Boolean nameVerfügbarGruppe(String gruppenname) throws IllegalArgumentException;

	public Boolean nameVerfügbarNutzer(String nutzername) throws IllegalArgumentException;

	public Boolean nameVerfügbarUmfrage(String umfragenname) throws IllegalArgumentException;



}
