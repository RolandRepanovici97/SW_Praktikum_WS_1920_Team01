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
 * Für die konkrete Dokumentation der einzelnen Methoden, empfiehlt es sich in das synchrone
 * Interface @link KinoAdministration und in dessen Implementationsklasse @link KinoAdministrationImpl 
 * zu sehen.
 * @author alina
 * @version 1.o
 * @see KinoAdministration, KinoAdministrationImpl
 *
 */
public interface KinoAdministrationAsync {

	void init(AsyncCallback<Void> callback);

	void createFilm(String filmtitel, String beschreibung, String details, AsyncCallback<Film> callback);

	void createKino(String kinoname, String adresse, String beschreibung, Kinokette kinokette,
			AsyncCallback<Kino> callback);

	void deleteFilm(Film film, AsyncCallback<Void> callback);

	void deleteKino(Kino kino, AsyncCallback<Void> callback);

	void deleteSpielplan(Spielplan spielplan, AsyncCallback<Void> callback);

	void getAllFilme(AsyncCallback<Vector<Film>> callback);

	void createSpielzeit(Spielplan spielplan, int filmID, Date zeitpunkt, AsyncCallback<Spielzeit> callback);

	void deleteSpielzeit(Spielzeit sz, AsyncCallback<Void> callback);

	void getAllKinokette(AsyncCallback<Vector<Kinokette>> callback);

	void deleteKinokette(Kinokette kk, AsyncCallback<Void> callback);

	void addKinoToKinokette(Kino kino, Kinokette kinokette, AsyncCallback<Void> callback);

	void getAllKinos(AsyncCallback<Vector<Kino>> callback);

	void getAllSpielzeiten(AsyncCallback<Vector<Spielzeit>> callback);

	void getAllSpielplan(AsyncCallback<Vector<Spielplan>> callback);

	void save(Kino kino, AsyncCallback<Void> callback);

	void getKinoketteOf(Nutzer nutzer, AsyncCallback<Kinokette> callback);

	void findNutzerByEmail(String email, AsyncCallback<Nutzer> callback);

	void nameVerfügbarKino(String kinoname, AsyncCallback<Boolean> callback);

	void nameVerfügbarFilm(String filmtitel, AsyncCallback<Boolean> callback);

	void getFilmByID(int id, AsyncCallback<Film> callback);

	void getSpielzeitByID(int id, AsyncCallback<Spielzeit> callback);

	void getKinoByID(int id, AsyncCallback<Kino> callback);

	void getFilmByTitel(String filmtitel, AsyncCallback<Film> callback);

	void save(Film film, AsyncCallback<Void> callback);

	void findKinoketteByID(int id, AsyncCallback<Kinokette> callback);

	void findKinoketteByName(String kinokettenname, AsyncCallback<Kinokette> callback);

	void createKinokette(Nutzer nutzer, AsyncCallback<Kinokette> callback);

	void save(Kinokette kinokette, AsyncCallback<Void> callback);

	void getKinoByName(String kinoname, AsyncCallback<Kino> callback);

	void getKinosOfKinokette(Kinokette kinokette, AsyncCallback<Vector<Kino>> callback);

	void getSpielplanOfKino(Kino kino, AsyncCallback<Spielplan> callback);

	void getSpielplanByID(int id, AsyncCallback<Spielplan> callback);

	void save(Spielplan spielplan, AsyncCallback<Void> callback);

	void createSpielplan(Kino kino, AsyncCallback<Spielplan> callback);

	void getSpielzeitenByFilm(Film film, AsyncCallback<Vector<Spielzeit>> callback);

	void save(Spielzeit spielzeit, AsyncCallback<Void> callback);

	void nameVerfügbarNutzer(String nutzername, AsyncCallback<Boolean> callback);

	void createNutzer(String email, String nutzername, AsyncCallback<Nutzer> callback);

	void save(Nutzer nutzer, AsyncCallback<Void> callback);


}
