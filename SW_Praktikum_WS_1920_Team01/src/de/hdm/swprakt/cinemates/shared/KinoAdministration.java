/**
 * 
 */
package de.hdm.swprakt.cinemates.shared;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.swprakt.cinemates.shared.bo.Film;
import de.hdm.swprakt.cinemates.shared.bo.Kino;
import de.hdm.swprakt.cinemates.shared.bo.Kinokette;
import de.hdm.swprakt.cinemates.shared.bo.Nutzer;
import de.hdm.swprakt.cinemates.shared.bo.Spielplan;
import de.hdm.swprakt.cinemates.shared.bo.Spielzeit;

/**
 * Synchrone Schnittstelle für eine RPC-fähige Klasse zur Verwaltung von Kinos,
 * Kinoketten Filmen, Spielpläne und Spielzeiten.
 * 
 * @author alina
 * @version 1.0
 * @see KinoAdministrationImpl, KinoAdministrationAsync
 *
 */

@RemoteServiceRelativePath("kinoadministration")
public interface KinoAdministration extends RemoteService {

	/*
	 * Initalisierung der Variablen, welche die Referenzen auf die Mapeprklassen
	 * darstellen. Wir initialisieren diese durch den Aufruf des
	 * protected-Konstruktors. Dieser ermöglicht uns, dass jeweils nur eine Instanz
	 * dieser Klasse erzeugt werden kann.
	 */
	public void init() throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn ein Film anhand seiner ID gefunden werden
	 * soll
	 * 
	 * @param Integer Wert, welcher die ID des Films repräsentiert
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Film getFilmByID(int id) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn ein Film anhand seines Titels gefunden
	 * werden soll
	 * 
	 * @param Filmtitel
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Film getFilmByTitel(String filmtitel) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn wir alle in der Datenbank gespeicherten
	 * Filme erhalten möchten.
	 * 
	 * @throws IllegalArgumentException
	 * @author Ömer
	 */

	public Vector<Film> getAllFilme() throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn ein Filmobjekt in der Datenbank
	 * gespeichert werden soll.
	 * 
	 * @param Filmobjekt, welches gespeichert werden soll
	 * @throws IllegalArgumentException
	 * @author Ömer
	 */

	public void save(Film film) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn ein Film erstellt wird.
	 * 
	 * @param Filmtitel, Filmbeschreibung und Details (Die Details repräsentieren im
	 *                   Grunde lediglich die Länge des Films)
	 * @throws IllegalArgumentException
	 * @author Ömer
	 * @author alina
	 */

	public Film createFilm(String filmtitel, String beschreibung, String details) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn der Titel eines Films gesetzt werden
	 * soll. Damit dieser nicht doppelt vergeben werden kann, erfolgt zunächst die
	 * Prüfung, ob dieser bereits vergeben ist.
	 * 
	 * @param Der Filmtitel, welcher gesetzt werden soll
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Boolean nameVerfügbarFilm(String filmtitel) throws IllegalArgumentException;

	/**
	 * Diese Methode realisiert das Löschen eines Films. Hier wird auch die
	 * Löschweitergabe betrachtet. Unserer Logik nach gehören Spielzeiten zu Filmen.
	 * Wird ein Film gelöscht, so müssen auch alle Spielzeiten, zu denen dieser Film
	 * angeboten wird gelöscht werden. Außerdem gehört eine Umfrage zu einem Film.
	 * Wird der Film gelöscht, so müssen auch alle Umfragen gelöscht werden, welche
	 * zu diesem Film gehören. Um dies zu realisieren bedienen wir uns der Klasse,
	 * welche die Applikationslogik für Umfragen bereitstellt und rufen die dort
	 * definierte Löschmethode für Umfragen auf.
	 * 
	 * @param Filmobjekt, welches gelöscht werden soll
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public void deleteFilm(Film film) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn eine Kinokette anhand ihrer ID gefunden
	 * werden soll
	 * 
	 * @param Integer Wert, welcher die ID der Kinokette repräsentiert
	 * @throws IllegalArgumentException
	 * @author Ömer
	 */

	public Kinokette findKinoketteByID(int id) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn eine Kinokette anhand ihres Namens
	 * gefunden werden soll
	 * 
	 * @param Kinokettenname
	 * @throws IllegalArgumentException
	 * @author Ömer
	 */

	public Kinokette findKinoketteByName(String kinokettenname) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn alle Kinoketten, welche dem System
	 * bekannt sind, ausgegeben werden sollen
	 * 
	 * @throws IllegalArgumentException
	 * @author Ömer
	 */

	public Vector<Kinokette> getAllKinokette() throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn sich ein Kinobetreiber das erste mal auf
	 * dem CineMates Admin-Client einloggt. Unserer Logik nach gehört einem
	 * Kinobetreiber eine Kinokette, welche mehrere Kinos enthalten kann. Loggt er
	 * sich zum ersten Mal auf dem Admin-Client ein, so wird neben dem Nutzerobjekt
	 * auch als default eine Kinokette angelegt, welche zunächst leer ist, aber der
	 * er später Kinos hinzufügen kann.
	 * 
	 * @param Aktueller Nutzer: Kinobetreiber
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Kinokette createKinokette(Nutzer nutzer) throws IllegalArgumentException;

	/**
	 * Diese Methode realisiert das Löschen einer Kinokette. Hier wird auch die
	 * Löschweitergabe betrachtet. Unserer Logik nach gehören Kinos zu Kinoketten.
	 * Wird eine Kinokette gelöscht, so müssen auch alle Kinos, welche zu dieser
	 * Kinokette gehören, gelöscht werden.
	 * 
	 * @param Kinoketteobjekt, welches gelöscht werden soll
	 * @throws IllegalArgumentException
	 * @author Ömer
	 */

	public void deleteKinokette(Kinokette kinokette) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn die Kinokette eines Nutzers zurückgegeben
	 * werden soll.
	 * 
	 * @param Nutzer dessen Kinokette benötigt wird
	 * @throws IllegalArgumentException
	 * @author Ömer
	 */

	public Kinokette getKinoketteOf(Nutzer nutzer) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn ein Kinoketteobjekt aktualisiert werden
	 * soll.
	 * 
	 * @param Kinoketteobjekt, welches aktualisiert werden soll
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public void save(Kinokette kinokette) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn ein Kino anhand seiner ID gefunden werden
	 * soll
	 * 
	 * @param Integerwert, welcher die ID des Kinos repräsentiert
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Kino getKinoByID(int id) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn ein Kino anhand seines Namens gefunden
	 * werden soll
	 * 
	 * @param Name des Kinos
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Kino getKinoByName(String kinoname) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn alle Kinos einer Kinokette ausgegeben
	 * werden sollen
	 * 
	 * @param Kinokette, deren Kinos gefunden werden sollen
	 * @throws IllegalArgumentException
	 * @author Ömer
	 */

	public Vector<Kino> getKinosOfKinokette(Kinokette kinokette) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn alle Kinos, welche CineMates bekannt
	 * sind, ausgegeben werden sollen.
	 * 
	 * @throws IllegalArgumentException
	 * @author Ömer
	 */

	public Vector<Kino> getAllKinos() throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn ein neues Kinoobjekt erstellt wird.
	 * 
	 * @param Kinoname, Adresse, Beschreibung und Kinokette des aktuellen Nutzers
	 * @throws IllegalArgumentException
	 * @author Ömer
	 * @author alina
	 */

	public Kino createKino(String kinoname, String adresse, String beschreibung, Kinokette kinokette)
			throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn der Name eines Kinos gesetzt werden soll.
	 * Damit dieser nicht doppelt vergeben werden kann, erfolgt zunächst die
	 * Prüfung, ob dieser bereits vergeben ist.
	 * 
	 * @param Der Kinoname, welcher gesetzt werden soll
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Boolean nameVerfügbarKino(String kinoname) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn ein neues Kino angelegt wird.
	 * 
	 * @param Kino, Kinokette
	 * @throws IllegalArgumentException
	 * @author Ömer
	 */

	public void addKinoToKinokette(Kino kino, Kinokette kinokette) throws IllegalArgumentException;

	/**
	 * Diese Methode realisiert das Löschen eines Kinos. Hier wird die
	 * Löschweitergabe realisiert. Unserer Logik nach besitzt ein Kino einen
	 * Spielplan, welcher wiederum aus Spielzeit besteht. Wird ein Kino gelöscht, so
	 * muss auch der dazugehörige Spielplan gelöscht werden. Durch den Aufruf der
	 * Löschmethode des Spieplans werden anschließend alle Spielzeiten dieses
	 * Spielplans gelöscht.
	 * 
	 * @param Kinoobjekt, welches gelöscht werden soll
	 * @throws IllegalArgumentException
	 * @author Ömer
	 * @author alina
	 * 
	 */

	public void deleteKino(Kino kino) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn ein Kinoobjekt in der Datenbank gespeichert
	 * wird.
	 * 
	 * @param Kinoobjekt, welches gespeichert werden soll
	 * @throws IllegalArgumentException
	 * @author alina
	 */
	public void save(Kino kino) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn der Spielplan eines übergebenen Kinos
	 * gefunden werden soll.
	 * 
	 * @param Kino dessen Spielplan gefunden werden soll
	 * @throws IllegalArgumentException
	 * @author Ömer
	 */

	public Spielplan getSpielplanOfKino(Kino kino) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn alle Spielpläne, welche CineMates bekannt
	 * sind, ausgegeben werden sollen
	 * 
	 * @throws IllegalArgumentException
	 * @author Ömer
	 */

	public Vector<Spielplan> getAllSpielplan() throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn der Spielplan eines übergebenen Kinos
	 * gefunden werden soll.
	 * 
	 * @param Integerwert, welcher die ID des Spielplans repräsentiert
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Spielplan getSpielplanByID(int id) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn ein Spielplanobjekt aktualisiert werden
	 * soll.
	 * 
	 * @param Spielplanobjekt, welches aktualisiert werden soll
	 * @throws IllegalArgumentException
	 * @author Ömer
	 */

	public void save(Spielplan spielplan) throws IllegalArgumentException;

	/**
	 * Diese Methode wird immer dann aufgerufen, wenn ein neues Kino erstellt wird.
	 * Unserer Logik nach besitzt jedes Kino einen Spielplan. Wird ein neues Kino
	 * erstellt, so muss für dieses auch ein Spielplan erstellt werden. Dieser ist
	 * standardmäßig leer, kann aber durch hinzufügen von Spielzeiten befüllt
	 * werden. Hier geht es nur darum, ein lebensfähiges Spielplanobjekt zu
	 * erstellen.
	 * 
	 * @param Kino
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Spielplan createSpielplan(Kino kino) throws IllegalArgumentException;

	/**
	 * Diese Methode realisiert das Löschen eines Kinos. Hier wird die
	 * Löschweitergabe realisiert. Unserer Logik nach besteht ein Spielplan aus
	 * Spielzeiten. Wird ein Spielplan gelöscht, so werden alle dort enthaltenen
	 * Spielzeiten gelöscht.
	 * 
	 * @param Spielplanobjekt, welches gelöscht werden soll
	 * @throws IllegalArgumentException
	 * @author Ömer
	 * 
	 */

	public void deleteSpielplan(Spielplan spielplan) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn alle CineMates bekannten Spielpläne
	 * ausgegeben werden sollen
	 * 
	 * @param Integerwert, welcher die ID der Spielzeit repräsentiert
	 * @throws IllegalArgumentException
	 * @author Ömer
	 */

	public Vector<Spielzeit> getAllSpielzeiten() throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn ein Spielzeitobjekt anhand seiner ID
	 * gefunden werden soll
	 * 
	 * @param Integerwert, welcher die ID der Spielzeit repräsentiert
	 * @throws IllegalArgumentException
	 * @author Ömer
	 */

	public Spielzeit getSpielzeitByID(int id) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn alle Spielzeiten eines Films gefunden
	 * werden sollen
	 * 
	 * @param Film dessen Spielzeiten gefunden werden sollen
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Vector<Spielzeit> getSpielzeitenByFilm(Film film) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn eine neue Spielzeit erstellt werden soll.
	 * 
	 * @param Spielplan, filmID (Referenz auf den Film) und Zeitpunkt, zu welchem
	 *                   der Film vorgeführt werden soll
	 * @throws IllegalArgumentException
	 * @author Ömer
	 * @author alina
	 */

	public Spielzeit createSpielzeit(int spielplanID, int filmID, Date zeitpunkt) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn eine Spielzeit gelöscht werden soll
	 * 
	 * @param Spielzeit welche gelöscht werden soll
	 * @throws IllegalArgumentException
	 * @author Ömer
	 */

	public void deleteSpielzeit(Spielzeit spielzeit) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn ein Spielzeitobjekt in der Datenbank
	 * gespeichert werden soll.
	 * 
	 * @param Spielzeitobjekt, welches gespeichert werden soll
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public void save(Spielzeit spielzeit) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn wir ein Nutzerobjekt anhand seiner E-Mail
	 * finden möchten.
	 * 
	 * @param Email des Nutzers der gefunden werden soll
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Nutzer findNutzerByEmail(String email) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn der Nutzer seinen Nutzernamen setzen
	 * möchten. Damit dieser nicht doppelt vergeben werden kann, erfolgt zunächst
	 * die Prüfung, ob dieser bereits vergeben ist.
	 * 
	 * @param Der Nutzername, welcher gesetzt werden soll
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Boolean nameVerfügbarNutzer(String nutzername) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen wenn ein neuer Nutzer erstellt wird.
	 * 
	 * @param Email und Nutzername des neu zu erstellenden Nutzers
	 * @throws IllegalArgumentException
	 * @author roland
	 * @author alina
	 * 
	 */

	public Nutzer createNutzer(String email, String nutzername) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn ein Nutzerobjekt in der Datenbank
	 * gespeichert werden soll.
	 * 
	 * @param Nutzerobjekt, welches gespeichert werden soll
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public void save(Nutzer nutzer) throws IllegalArgumentException;
}
