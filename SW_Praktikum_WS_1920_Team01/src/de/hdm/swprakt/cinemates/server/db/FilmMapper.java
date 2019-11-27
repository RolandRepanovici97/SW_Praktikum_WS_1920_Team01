package de.hdm.swprakt.cinemates.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.swprakt.cinemates.shared.bo.Film;
import de.hdm.swprakt.cinemates.shared.bo.Kinokette;



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
				f.setID(rs.getInt("film_id"));
				f.setFilmtitel(rs.getString("filmtitel"));
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

	public Film insert(Film film) {

		Connection con = DBConnection.connection();

		try {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MAX(film_id) AS `maxid` FROM `film`");

			if (rs.next()) {
				film.setID(rs.getInt("maxid") + 1);
			}

			PreparedStatement pstmt = con.prepareStatement(
					"INSERT INTO `film` (`film_id`, `bo_id`, `Filmtitel`, `Beschreibung` , `Details`) VALUES (?, ?, ?, ?) ");
			pstmt.setInt(1, film.getID());
			pstmt.setString(2, film.getFilmtitel());
			pstmt.setString(3, film.getBeschreibung());
			pstmt.setString(4, film.getDetails());
			return film;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	public Film update(Film film) {

		Connection con = DBConnection.connection();

		try {

			PreparedStatement pstmt = con.prepareStatement(
					"UPDATE `film` SET `Filmtitel` = ?, `Beschreibung` = ?, `Details` = ? WHERE `film_id` = ?");
			pstmt.setString(1, film.getFilmtitel());
			pstmt.setString(2, film.getBeschreibung());
			pstmt.setString(3, film.getDetails());
			return film;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	public void delete(Film film) {

		Connection con = DBConnection.connection();

		try {

			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM `film` WHERE (`film_id` = " + film.getID() + ")");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Vector<Film> findFilmeByKinokette(Kinokette kinokette) {

		// Verbindung zur Datenbank aufbauen.

		Connection con = DBConnection.connection();
		// Neuen Vector instantiieren, in welchem Film-Objekte gespeichert werden.

		Vector<Film> vectorfilm = new Vector<Film>();
		try {

			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT * FROM `Kinokette_Film`LEFT JOIN `Film` ON `Kinokette_Film`.`film_id`=`Film`.`film_id` WHERE (`kinokette_id` = " + kinokette.getID() + " ) ORDER BY `film_id`");

			/*
			 * Befüllen des result sets
			 */
			while (rs.next()) {
				// Es werden für jedes Umfrageeintrag-Objekt die nötigen Attribute gesetzt
				Film f = new Film();
				f.setID(rs.getInt("film_id"));
				f.setFilmtitel(rs.getString("Filmtitel"));
				f.setBeschreibung(rs.getString("Beschreibung"));
				f.setDetails(rs.getString("Details"));
				vectorfilm.add(f);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vectorfilm;
	}

}