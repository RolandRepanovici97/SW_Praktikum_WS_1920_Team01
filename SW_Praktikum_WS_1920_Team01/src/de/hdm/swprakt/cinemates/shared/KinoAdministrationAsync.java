/**
 * 
 */
package de.hdm.swprakt.cinemates.shared;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.swprakt.cinemates.shared.bo.Film;

import de.hdm.swprakt.cinemates.shared.bo.Kino;
import de.hdm.swprakt.cinemates.shared.bo.Kinokette;
import de.hdm.swprakt.cinemates.shared.bo.Nutzer;
import de.hdm.swprakt.cinemates.shared.bo.Spielplan;
import de.hdm.swprakt.cinemates.shared.bo.Spielzeit;

/**
 * Das asynchrone Gegenstück des Interface {@link KinoAdministration}
 * 
 * @author alina
 * @version 1.o
 *
 */
public interface KinoAdministrationAsync {

	void createFilm(String filmtitel, String beschreibung, String details, AsyncCallback<Film> callback);

	void createKino(String kinoname, String adresse, String beschreibung, AsyncCallback<Kino> callback);
	
	

	// void editSpielplan(Spielplan spielplan, AsyncCallback<Void> callback);

	// void editSpielzeit(Spielzeit spielzeit, AsyncCallback<Void> callback);

	void deleteFilm(Film film, AsyncCallback<Void> callback);

	void deleteKino(Kino kino, AsyncCallback<Void> callback);

	void deleteSpielplan(Spielplan spielplan, AsyncCallback<Void> callback);

	void getAllFilme(AsyncCallback<Vector<Film>> callback);

	// void findAllSpielplan(AsyncCallback<Vector<Spielplan>> callback);

	void createSpielzeit(int filmID, Date zeitpunkt, AsyncCallback<Spielzeit> callback);

	void saveFilm(Film film, AsyncCallback<Void> callback);

	void saveNutzer(Nutzer nutzer, AsyncCallback<Void> callback);

	void deleteSpielzeit(Spielzeit sz, AsyncCallback<Void> callback);

	void getKinosOf(Kinokette kk, AsyncCallback<Vector<Kino>> callback);

	void getAllKinokette(AsyncCallback<Vector<Kinokette>> callback);

	void deleteKinokette(Kinokette kk, AsyncCallback<Void> callback);

	void addKinoToKinokette(Kinokette kinokette, AsyncCallback<Kino> callback);

	void getAllKinos(AsyncCallback<Vector<Kino>> callback);

	void getSpielplanOf(Kino k, AsyncCallback<Spielplan> callback);

	void getSpielzeitOf(Spielplan sp, AsyncCallback<Vector<Spielzeit>> callback);

	void getAllKinoOfKinokette(Kinokette kinokette, AsyncCallback<Vector<Kino>> callback);

	void saveKino(Kino k, AsyncCallback<Void> callback);

	void getAllSpielzeiten(AsyncCallback<Vector<Spielzeit>> callback);

	void getAllSpielplan(AsyncCallback<Vector<Spielplan>> callback);

	void saveSpielplan(Spielplan spielplan, AsyncCallback<Void> callback);

<<<<<<< HEAD
	void save(Kino kino, AsyncCallback<Void> callback);

	void getKinoketteOf(Nutzer nutzer, AsyncCallback<Kinokette> callback);
	
	void findNutzerByEmail(String email, AsyncCallback<Nutzer> callback);
=======
	void init(AsyncCallback<Void> callback);
>>>>>>> branch 'master' of https://github.com/alinahafner/SW_Praktikum_WS_1920_Team01.git

}
