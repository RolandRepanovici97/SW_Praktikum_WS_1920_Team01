/**
 * 
 */
package de.hdm.swprakt.cinemates.shared;

import java.util.ArrayList;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.swprakt.cinemates.shared.bo.Film;
import de.hdm.swprakt.cinemates.shared.bo.Gruppe;
import de.hdm.swprakt.cinemates.shared.bo.Kino;
import de.hdm.swprakt.cinemates.shared.bo.Kinokette;
import de.hdm.swprakt.cinemates.shared.bo.Nutzer;
import de.hdm.swprakt.cinemates.shared.bo.Spielplan;
import de.hdm.swprakt.cinemates.shared.bo.Spielzeit;
import de.hdm.swprakt.cinemates.shared.bo.Umfrage;
import de.hdm.swprakt.cinemates.shared.bo.Umfrageeintrag;
import de.hdm.swprakt.cinemates.shared.bo.Votum;

/**
 * Das asynchrone Gegenstück des Interface {@link KinoAdministration}
 * @author alina
 * @version 1.o
 *
 */
public interface KinoAdministrationAsync {

	/**
	void init(AsyncCallback<Void> callback);

	void delete(Film film, AsyncCallback<Void> callback);

	void delete(Gruppe gruppe, AsyncCallback<Void> callback);

	void delete(Kinokette kinokette, AsyncCallback<Void> callback);

	void delete(Kino kino, AsyncCallback<Void> callback);

	void delete(Nutzer nutzer, AsyncCallback<Void> callback);

	void delete(Spielplan spielplan, AsyncCallback<Void> callback);

	void delete(Spielzeit spielzeit, AsyncCallback<Void> callback);

	void delete(Umfrageeintrag umfrageeintrag, AsyncCallback<Void> callback);

	void delete(Umfrage umfrage, AsyncCallback<Void> callback);

	void delete(Votum votum, AsyncCallback<Void> callback);
	


	void findAllFilm(Film film, AsyncCallback<ArrayList<Film>> callback);

	void getGruppenOf(Nutzer nutzer, AsyncCallback<ArrayList<Gruppe>> callback);

	void getGruppenOf(Umfrage umfrage, AsyncCallback<Vector<Gruppe>> callback);

	void findAllKinokette(AsyncCallback<Vector<Kinokette>> callback);

	void findAllKino(AsyncCallback<Vector<Kino>> callback);

	void findAllNutzer(AsyncCallback<Vector<Nutzer>> callback);

	void findAllSpielplan(AsyncCallback<Vector<Spielplan>> callback);

	void findAllSpielzeit(AsyncCallback<Vector<Spielzeit>> callback);

	void findAllUmfrageeintrag(AsyncCallback<Vector<Umfrageeintrag>> callback);

	void findUmfrageeintragOf(Spielzeit spielzeit, AsyncCallback<Vector<Umfrageeintrag>> callback);

	void findUmfrageeintragOf(Kino kino, AsyncCallback<Vector<Umfrageeintrag>> callback);

	void findAllUmfrage(AsyncCallback<Vector<Umfrage>> callback);

	void findAllVotum(AsyncCallback<Vector<Votum>> callback);
	
	

	void findFilmByID(int id, AsyncCallback<Film> callback);

	void findGruppeByID(int id, AsyncCallback<Gruppe> callback);

	void findKinoketteByID(int id, AsyncCallback<Kinokette> callback);

	void findKinoByID(int id, AsyncCallback<Kino> callback);

	void findNutzerByID(int id, AsyncCallback<Nutzer> callback);

	void findSpielplanByID(int id, AsyncCallback<Spielplan> callback);

	void findSpielzeitByID(int id, AsyncCallback<Spielzeit> callback);

	void findUmfrageeintragByID(int id, AsyncCallback<Umfrageeintrag> callback);

	void findUmfrageByID(int id, AsyncCallback<Umfrage> callback);

	void findVotumByID(int id, AsyncCallback<Votum> callback);

	void create(Gruppe gruppe, AsyncCallback<Void> callback);

	void create(Film film, AsyncCallback<Void> callback);

	void create(Kinokette kinokette, AsyncCallback<Void> callback);

	void create(Kino kino, AsyncCallback<Void> callback);

	void create(Nutzer nutzer, AsyncCallback<Void> callback);

	void create(Spielplan spielplan, AsyncCallback<Void> callback);

	void create(Spielzeit spielzeit, AsyncCallback<Void> callback);

	void save(Film film, AsyncCallback<Void> callback);

	void save(Gruppe gruppe, AsyncCallback<Void> callback);

	void save(Kinokette kinokette, AsyncCallback<Void> callback);

	void save(Kino kino, AsyncCallback<Void> callback);

	void save(Nutzer Nutzer, AsyncCallback<Void> callback);

	void save(Spielplan spielplan, AsyncCallback<Void> callback);

	void save(Spielzeit spielzeit, AsyncCallback<Void> callback);
	
	
	

	*/

}
