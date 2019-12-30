/**
 * 
 */
package de.hdm.swprakt.cinemates.shared;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.swprakt.cinemates.shared.bo.Film;
import de.hdm.swprakt.cinemates.shared.bo.Gruppe;
import de.hdm.swprakt.cinemates.shared.bo.Nutzer;
import de.hdm.swprakt.cinemates.shared.bo.Umfrage;
import de.hdm.swprakt.cinemates.shared.bo.Umfrageeintrag;
import de.hdm.swprakt.cinemates.shared.bo.Votum;

/**
 * Das asynchrone Gegenstück des Interface {@link KinoBesuchsplanung}. Für die
 * konkrete Dokumentation der einzelnen Methoden, empfiehlt es sich in das synchrone
 * Interface @link KinoBesuchsplanung und in dessen Implementationsklasse @link KinoBesuchsplanungImpl zu sehen.
 * 
 * @author alina
 * @version 1.0
 * @see KinoBesuchsplanung, KinoBesuchsplanungImpl
 *
 */
public interface KinoBesuchsplanungAsync {

	void init(AsyncCallback<Void> callback);

	void findNutzerByEmail(String email, AsyncCallback<Nutzer> callback);

	void createNutzer(String email, String nutzername, AsyncCallback<Nutzer> callback);

	void createGruppe(Nutzer nutzer, String gruppenname, Vector<Nutzer> gruppenmitglieder,
			AsyncCallback<Gruppe> callback);

	void showAllUmfrage(AsyncCallback<Vector<Umfrage>> callback);

	void createUmfrage(String umfragenname, Film film, Date datum, AsyncCallback<Umfrage> callback);

	void showVotumOfUmfrageeintrag(Umfrageeintrag umfrageeintrag, AsyncCallback<Vector<Votum>> callback);

	void deleteUmfrage(Umfrage umfrage, AsyncCallback<Void> callback);

	void umfrageergebnisseAnzeigen(Umfrage umfrage, AsyncCallback<Vector<Umfrageeintrag>> callback);

	void bestesErgebnisErmitteln(Umfrage umfrage, AsyncCallback<Umfrageeintrag> callback);

	void deleteGruppe(Gruppe gruppe, AsyncCallback<Void> callback);

	void deleteUmfrageeintrag(Umfrageeintrag umfrageeintrag, AsyncCallback<Void> callback);

	void deleteVotum(Votum votum, AsyncCallback<Void> callback);

	void save(Nutzer nutzer, AsyncCallback<Void> callback);

	void save(Gruppe gruppe, AsyncCallback<Void> callback);

	void getAllGruppen(AsyncCallback<Vector<Gruppe>> callback);

	void getAllGruppenOfNutzer(Nutzer nutzer, AsyncCallback<Vector<Gruppe>> callback);

	void getAllGruppenOfUmfrage(Umfrage umfrage, AsyncCallback<Vector<Gruppe>> callback);

	void getOwnerOfGruppe(Gruppe gruppe, AsyncCallback<Nutzer> callback);

	void mitgliedEntfernen(Nutzer nutzer, Gruppe gruppe, AsyncCallback<Void> callback);

	void mitgliedHinzufügen(Nutzer nutzer, Gruppe gruppe, AsyncCallback<Gruppe> callback);

	void save(Umfrage umfrage, AsyncCallback<Umfrage> callback);

	void showAllUmfragenOfGruppe(Gruppe gruppe, AsyncCallback<Vector<Umfrage>> callback);

	void showAllUmfrageOfNutzer(Nutzer nutzer, AsyncCallback<Vector<Umfrage>> callback);

	void showAllUmfragenOFilm(Film film, AsyncCallback<Vector<Umfrage>> callback);

	void showAllUmfrageOfNutzerOhneErgebnis(Nutzer nutzer, AsyncCallback<Vector<Umfrage>> callback);

	void showUmfrageeinträgeofUmfrage(Umfrage umfrage, AsyncCallback<Vector<Umfrageeintrag>> callback);

	void findUmfrageByID(int id, AsyncCallback<Umfrage> callback);

	void nameVerfügbarGruppe(String gruppenname, AsyncCallback<Boolean> callback);

	void nameVerfügbarNutzer(String nutzername, AsyncCallback<Boolean> callback);

	void nameVerfügbarUmfrage(String umfragenname, AsyncCallback<Boolean> callback);

	void getAllNutzerOfGruppe(Gruppe gruppe, AsyncCallback<Vector<Nutzer>> callback);

	void ergebnisFinden(Umfrage umfrage, AsyncCallback<Boolean> callback);

	void finalesErgebnisBestimmen(Umfrage umfrage, Umfrageeintrag umfrageeintrag,
			AsyncCallback<Umfrageeintrag> callback);

	void findOwnerOfVotum(Votum votum, AsyncCallback<Nutzer> callback);

	void findVotumByID(int votumid, AsyncCallback<Votum> callback);

	void showAllVotum(AsyncCallback<Vector<Votum>> callback);

	void save(Votum votum, AsyncCallback<Void> callback);

	void createVotum(Umfrageeintrag umfrageeintrag, Boolean istMöglicherTermin, AsyncCallback<Votum> callback);

	void save(Umfrageeintrag umfrageeintrag, AsyncCallback<Void> callback);

	void createUmfrageeinträge(Umfrage umfrage, Film film, Date datum, AsyncCallback<Vector<Umfrageeintrag>> callback);

	void getOwnerOfUmfrage(Umfrage umfrage, AsyncCallback<Nutzer> callback);

}
