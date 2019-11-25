package de.hdm.swprakt.cinemates.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.swprakt.cinemates.shared.bo.Film;

/**
 * Diese Mapperklasse bildet <code>Film</code> Objekte auf eine relationale
 * Datenbank ab. Sie beinhalte Methoden, um Filme anzeigen, anlegen, editieren
 * und löschen zu können. Es können hier sowohl Objekte der Klasse
 * <code> Film </code> in Datenbankstrukturen, als auch Datenbankstrukturen in
 * Objekte der Klasse <code> Film </code> umgewandelt werden.
 * 
 * @author Ömer Degirmenci
 * @version 1.0
 *
 */

public class FilmMapper {

	/**
	 * Die Klasse <code>FilmMapper</code> wird wie jede andere Mapperklasse nur ein
	 * einziges mal instantiiert. Die Variable filmMapper speichert diese Instanz.
	 * Wir stellen die einmalige Initalisierung durch den Bezeichner
	 * <code>static</code> sicher.
	 * 
	 */
	private static FilmMapper filmMapper = null;

	/**
	 * Ein geschützter Konstruktor verhindert die Möglichkeit, mit <code>new</code>
	 * neue Instanzen der Klasse <code>FilmMapper</code> zu erzeugen.
	 * 
	 */
	protected FilmMapper() {

	}

	/**
	 * Mithilfe dieser statischen Methode kann die einzige Instanz der Klasse
	 * <code>FilmMapper</code> erzeugt werden. Die Methode beinhaltet die Prüfung,
	 * ob bereits ein FilmMapper-Objekt erzeugt ist, dadurch wird sichergestellt,
	 * dass immer nur ein Objekt dieser Klasse erzeugt ist. Ist bereits ein
	 * <code>FilmMapper</code> - Objekt erzeugt worden, so geben wir die Referenz
	 * auf dieses zurück. (Singleton Objekt)
	 * 
	 * @return das einzige <code>FilmMapper</code> - Objekt
	 */

	public static FilmMapper filmMapper() {
		if (filmMapper == null) {
			filmMapper = new FilmMapper();
		}

		return filmMapper;
	}

	public Vector<Film> findAll() {

		Connection con = DBConnection.connection();
		Vector<Film> film = new Vector<Film>();

		try {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `film`");

			while (rs.next()) {
				Film f = new Film();
				f.setFilmtitel(rs.getString("film_id"));
				f.setBeschreibung(rs.getString("Beschreibung"));
				f.setDetails(rs.getString("Details"));
				film.add(f);
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return film;

	}

	public Film findByID(int id) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `film` WHERE film_id = " + id + "ORDER BY `film_id`");

			if (rs.next()) {
				Film f = new Film();
				f.setFilmtitel(rs.getString("film_id"));
				f.setBeschreibung(rs.getString("Beschreibung"));
				f.setDetails(rs.getString("Details"));

				return f;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}