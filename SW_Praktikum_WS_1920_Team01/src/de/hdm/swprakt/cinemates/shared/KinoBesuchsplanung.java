/**
 * 
 */
package de.hdm.swprakt.cinemates.shared;

import java.util.Date;
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
 * Synchrone Schnittstelle für eine RPC-fähige Klasse zur Planung eines
 * Kinobesuchs.(Anlage und Verwaltung von Gruppen, Umfragen, etc.)
 * 
 * @author alina
 * @version 1.0
 * @see KinoBesuchsplanungAsync, KinoBesuchsplanungImpl
 *
 */

@RemoteServiceRelativePath("kinobesuchsplanung")
public interface KinoBesuchsplanung extends RemoteService {

	/*
	 * Initalisierung der Variablen, welche die Referenzen auf die Mapeprklassen
	 * darstellen. Wir initialisieren diese durch den Aufruf des
	 * protected-Konstruktors. Dieser ermöglicht uns, dass jeweils nur eine Instanz
	 * dieser Klasse erzeugt werden kann.
	 */
	public void init() throws IllegalArgumentException;

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

	/**
	 * Diese Methode wird aufgerufen, wenn ein Gruppenobjekt in der Datenbank
	 * gespeichert werden soll.
	 * 
	 * @param Gruppenobjekt, welches gespeichert werden soll
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public void save(Gruppe gruppe) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn alle Gruppen angezeigt werden sollen.
	 * 
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Vector<Umfrage> showAllUmfrage();

	/**
	 * Diese Methode wird aufgerufen, wenn alle Gruppen eines Nutzers angezeigt
	 * werden sollen.
	 * 
	 * @param Nutzerobjekt dessen Gruppen gefunden werden sollen
	 * @throws IllegalArgumentException
	 * @author alina
	 */
	public Vector<Gruppe> getAllGruppenOfNutzer(Nutzer nutzer) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn alle Gruppenmitglieder einer Gruppe
	 * angezeigt werden sollen.
	 * 
	 * @param Gruppenobjekt, dessen Nutzer gefunden werden sollen
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Vector<Nutzer> getAllNutzerOfGruppe(Gruppe gruppe) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn der Owner einer Gruppe ermittelt werden
	 * soll.
	 * 
	 * @param Gruppenobjekt zu welchem der zugehörige Besitzer ermittelt werrden
	 *                      soll
	 * @throws IllegalArgumentException
	 * @author alina
	 */
	public Nutzer getOwnerOfGruppe(Gruppe gruppe) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn alle Gruppen angezeigt werden sollen, die
	 * zu einer Umfrage gehören.
	 * 
	 * @param Umfrageobjekt dessen zugehörige Gruppen gefunden werden sollen
	 * @throws IllegalArgumentException
	 * @author alina
	 */
	public Vector<Gruppe> getAllGruppenOfUmfrage(Umfrage umfrage) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn ein Mitglied aus einer Gruppe entfernt
	 * werden soll.
	 * 
	 * @param Nutzer und Gruppe, aus welcher der Nutzer gelöscht werden soll
	 * @throws IllegalArgumentException
	 * @author alina
	 */
	public void mitgliedEntfernen(Nutzer nutzer, Gruppe gruppe) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn ein neues Mitglied einer Gruppe
	 * hinzugefügt werden soll.
	 * 
	 * @param Nutzer und Gruppe, zu welcher der Nutzer hinzugeügt werden soll
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Gruppe mitgliedHinzufügen(Nutzer nutzer, Gruppe gruppe) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn der Name einer Gruppe gesetzt werden
	 * soll. Damit dieser nicht doppelt vergeben werden kann, erfolgt zunächst die
	 * Prüfung, ob dieser bereits vergeben ist.
	 * 
	 * @param Der Gruppenname, welcher gesetzt werden soll
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Boolean nameVerfügbarGruppe(String gruppenname) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn eine Gruppe erstellt wird. Diese
	 * Realisierung ist nicht besonders elegant, aber das Attribut gruppenmitglieder
	 * erwartet Integer-Werte, welche die IDs der einzelnen Nutzerobjekte
	 * darstellen.
	 * 
	 * @param Nuter, welcher als Owner der Gruppe hinterlegt wird; gewünschter
	 *               Gruppenname und ein Vector aus Nutzerobjekten, welche Mitglied
	 *               der Gruppe werden sollen
	 * @throws IllegalArgumentException
	 * @author alina
	 */
	public Gruppe createGruppe(Nutzer nutzer, String gruppenname, Vector<Nutzer> gruppenmitglieder)
			throws IllegalArgumentException;

	/*
	 * Diese Methode realisiert das Löschen einer Gruppe. Hier wird auch die
	 * Löschweitergabe betrachtet. Unserer Logik nach gehören Umfragen zu Grupepen.
	 * Wird nun eine Gruppe gelöscht, so müssen auch deren Umfragen gelöscht werden.
	 * 
	 * @param Gruppenonbjekt, welches gelöscht werden soll
	 * 
	 * @throws IllegalArgumentException
	 * 
	 * @author alina
	 */
	public void deleteGruppe(Gruppe gruppe) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn eine Umfrage in der Datenbank gespeichert
	 * wird.
	 * 
	 * @param Umfrageobjekt, welches gespeichert werden soll
	 * @throws IllegalArgumentException
	 * @author alina
	 */
	public void save(Umfrage umfrage) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn nach einer Umfrage mittels ihrer ID
	 * gesucht werden soll.
	 * 
	 * @param Umfrageobjekt, welches gespeichert werden soll
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Umfrage findUmfrageByID(int id) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn der Name einer Umfrage gesetzt werden
	 * soll. Damit dieser nicht doppelt vergeben werden kann, erfolgt zunächst die
	 * Prüfung, ob dieser bereits vergeben ist.
	 * 
	 * @param Der Umfragenname, welcher gesetzt werden soll
	 * @throws IllegalArgumentException
	 * @author alina
	 */
	public Boolean nameVerfügbarUmfrage(String umfragenname) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn eine neue Umfrage erstellt wird. 
	 * 
	 * @param Name der Umfrage, Film, Gruppe und Datum an dem der Film gesehen werden soll
	 * @throws IllegalArgumentException
	 * @author alina
	 */
	public Umfrage createUmfrage(String umfragenname, Film film, Gruppe gruppe, Date datum) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn wir alle in der Datenbank gespeicherten
	 * Umfragen ausgeben möchten.
	 * 
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Vector<Gruppe> getAllGruppen() throws IllegalArgumentException;



	/**
	 * Diese Methode wird aufgerufen, wenn eine Gruppe durch ihren Namens
	 * gefunden werden soll.
	 * 
	 * @param Name der Gruppe 
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Gruppe findGruppeByName(String gruppenname) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn der Owner einer Umfrage ermittelt werden
	 * soll.
	 * 
	 * @param Umfrageobjekt zu welchem der zugehörige Besitzer ermittelt werrden
	 *                      soll
	 * @throws IllegalArgumentException
	 * @author alina
	 */
	public Nutzer getOwnerOfUmfrage(Umfrage umfrage) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn wir alle Umfragen eines Nutzers ausgeben
	 * möchten. Das heißt wir suchen nach den Gruppen des Nutzers und hier wiederum
	 * nach den Umfragen, welche zu den Gruppen gehören und geben diese aus.
	 * 
	 * @param Nutzer dessen Umfragen ausgegeben werden sollen
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Vector<Umfrage> showAllUmfrageOfNutzer(Nutzer nutzer) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn wir alle Umfragen eines Gruppe ausgeben
	 * möchten.
	 * 
	 * @param Gruppe deren Umfragen ausgegeben werden sollen
	 * @throws IllegalArgumentException
	 * @author alina
	 */
	public Vector<Umfrage> showAllUmfragenOfGruppe(Gruppe gruppe) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn wir alle Umfragen zu einem Film ausgeben
	 * möchten.
	 * 
	 * @param Film zu welchem die Umfragen ausgegeben werden sollen
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Vector<Umfrage> showAllUmfragenOFilm(Film film) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn wir alle Umfragen eines Nutzers, welche
	 * noch kein Ergebnis haben, ausgeben möchten. Das heißt wir suchen nach den
	 * Gruppen des Nutzers und hier wiederum nach den Umfragen, welche zu den
	 * Gruppen gehören. Wir iterieren durch die Umfrageeinträge durch und schauen ob
	 * einer der Einträge als finales Ergebnis markiert ist. Ist kein Eintrag als
	 * final markiert, so geben wir all diese Umfragen zurück.
	 * 
	 * @param Nutzer dessen Umfragen angezeigt werden sollen
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Vector<Umfrage> showAllUmfrageOfNutzerOhneErgebnis(Nutzer nutzer) throws IllegalArgumentException;

	/*
	 * Diese Methode realisiert das Löschen einer Umfrage. Hier wird auch die
	 * Löschweitergabe betrachtet. Unserer Logik nach besteht eine Umfrage aus
	 * Umfrageeinträgen. Votum-Objekte können wiederum Umgfrageeinträgen zugehörig
	 * sein. Wird eine Umfrage gelöscht, so müssen auch die Umfrageeinträge und
	 * deren Votum-Objekte gelöscht werden.
	 * 
	 * @param Umfrageobjekt, welches gelöscht werden soll
	 * 
	 * @throws IllegalArgumentException
	 * 
	 * @author alina
	 */
	public void deleteUmfrage(Umfrage umfrage) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen wird dann aufgerufen, wenn eine neue Umfrage
	 * erstellt wird. Ziel jeder Umfrage ist es, einen passenden Termin zu finden um
	 * mit einer bestimmten Gruppe zu einem bestimmten Tag in einen bestimmten Film
	 * zu gehen. Lediglich das Kino und die konkrete Uhrzeit sind abzustimmen. Um
	 * die Umfrage mit Einträgen zu befüllen, wird diese Methode aufgerufen. Es
	 * werden die Argumente umfrage, film und datum übergeben. Film und Datum werden
	 * vom Umfrageersteller bereitgestellt. Die Verbindung zur Umfrage geschieht
	 * dann "automatisch".
	 * 
	 * 
	 * @param Umfrage zu welcher die Einträge gehören, Film welcher angeschaut
	 *                werden soll und Datum zu welchem der Film gesehen werden soll
	 * @throws IllegalArgumentException
	 * @author alina
	 */
	public Vector<Umfrageeintrag> createUmfrageeinträge(Umfrage umfrage, Film film, Date datum)
			throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn ein Umfrageeintrag-Objekt in der
	 * Datenbank gespeichert werden soll.
	 * 
	 * @param Umfrageeintrag, welcher gespeichert werden soll soll
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public void save(Umfrageeintrag umfrageeintrag) throws IllegalArgumentException;

	/*
	 * Diese Methode wird benötgt, um alle Umfrageeinträge einer Umfrage zu finden.
	 * 
	 * @param Umfrage, zu welcher die Umfrageeinträge gefunden werden sollen
	 * 
	 * @throws IllegalArgumentException
	 * 
	 * @author alina
	 */
	public Vector<Umfrageeintrag> showUmfrageeinträgeofUmfrage(Umfrage umfrage) throws IllegalArgumentException;

	/*
	 * Diese Methode realisiert das Löschen eines Umfrageeeintrags. Hier wird auch
	 * die Löschweitergabe betrachtet. Unserer Logik nach besteht eine Umfrage aus
	 * Umfrageeinträgen. Votum-Objekte können wiederum Umgfrageeinträgen zugehörig
	 * sein. Wird ein Umfrageeintrag gelöscht, so müssen auch deren Votum-Objekte
	 * gelöscht werden.
	 * 
	 * @param Umfrageeintrag welcher gelöscht werden soll
	 * 
	 * @throws IllegalArgumentException
	 * 
	 * @author alina
	 */
	public void deleteUmfrageeintrag(Umfrageeintrag umfrageeintrag) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn ein neues Votum-Objekt erzeugt werden
	 * soll
	 * 
	 * @param Umfrageeintrag zu welchem das Votum gesetzt werden soll, Boolean-Wert
	 *                       welcher entweder ein Ja-Votum oder ein Nein-Votum
	 *                       repräsentiert
	 * @throws IllegalArgumentException
	 * @author alina
	 */
	public Votum createVotum(Umfrageeintrag umfrageeintrag, Boolean istMöglicherTermin) throws IllegalArgumentException;

	/**
	 * Diese Methode realisiert das Löschen eines Votumobjekts.
	 * 
	 * @param Votumobjekt, welches gelöscht werden soll
	 * 
	 * @throws IllegalArgumentException
	 * 
	 * @author alina
	 */
	public void deleteVotum(Votum votum) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn ein Votum-Objekt in der Datenbank
	 * gespeichert werden soll.
	 * 
	 * @param Votumobjekt, welches gespeichert werden soll
	 * @throws IllegalArgumentException
	 * @author alina
	 */
	public void save(Votum votum) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn alle Vota zu einem Umfrageeintrag
	 * angezeigt werden sollen. Hierzu wird die Mapper Methode @link
	 * findVotumByUmfrageeintrag aufgerufen, welche uns einen Vector von Objekten
	 * der Klasse <Votum> zurückgibt.
	 * 
	 * @param Umfrageeintrag, zu welchem die Votum-Objekte ausgegeben werden sollen
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Vector<Votum> showVotumOfUmfrageeintrag(Umfrageeintrag umfrageeintrag) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn alle Votum-Objekte gefunden werden
	 * sollen.
	 * 
	 * @throws IllegalArgumentException
	 * @author alina
	 */
	public Vector<Votum> showAllVotum() throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn ein bestimmtes von dem die ID bekannt ist
	 * Votum-Objekt gefunden werden soll.
	 * 
	 * @param VotumID
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Votum findVotumByID(int votumid) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn der Owner eines Votums ermittelt werden
	 * soll.
	 * 
	 * @param Votumobjekt zu welchem der Owner ermittelt werden soll
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Nutzer findOwnerOfVotum(Votum votum) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn wir alle Umfrageeinträge und deren Vota
	 * anzeigen möchten. Wir übergeben eine Umfrage, deren Ergebnisse wir darstellen
	 * möchten. Wir erhalten die Umfrageeinträge der Umfrage zurück.
	 * 
	 * @param Umfrage deren Ergebnisse angezeigt werden sollen
	 * @author alina
	 */

	public Vector<Umfrageeintrag> umfrageergebnisseAnzeigen(Umfrage umfrage) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn wir überprüfen wollen, ob zu einer
	 * Umfrage ein passendes Ergebnis gefunden wurde. Sollte eines gefunden worden
	 * sein, so geben wir true zurück.
	 * 
	 * @param Umfrage deren Ergebnis ermittelt werden soll
	 * @author alina
	 */

	public Boolean ergebnisFinden(Umfrage umfrage) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn wir den Umfrageeintrag einer Umfrage mit
	 * den meisten Stimmen ermitteln wollen.
	 * 
	 * @param Umfrage deren Ergebnis ermittelt werden soll
	 * @author alina
	 */

	public Umfrageeintrag bestesErgebnisErmitteln(Umfrage umfrage) throws IllegalArgumentException;

	/**
	 * Diese Methode wird aufgerufen, wenn es kein eindeutiges Ergebnis einer
	 * Umfrage gegeben hat und nun der Owner der Umfrage über deren Ausgang
	 * entscheidet. Das finale Ergebnis wird zurückgegeben.
	 * 
	 * @param Umfrage deren Ergebnis bestimmt werden soll und Umfrageeintrag der als
	 *                Ergebnis markiert wird
	 * @author alina
	 */
	public Umfrageeintrag finalesErgebnisBestimmen(Umfrage umfrage, Umfrageeintrag umfrageeintrag)
			throws IllegalArgumentException;

}
