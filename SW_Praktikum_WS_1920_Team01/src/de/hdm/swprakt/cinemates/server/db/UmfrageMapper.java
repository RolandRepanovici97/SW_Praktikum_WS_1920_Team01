/**
 * 
 */
package de.hdm.swprakt.cinemates.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.swprakt.cinemates.shared.bo.Nutzer;
import de.hdm.swprakt.cinemates.shared.bo.Umfrage;
import de.hdm.swprakt.cinemates.shared.bo.Umfrageeintrag;
import de.hdm.swprakt.cinemates.shared.bo.Votum;

/**
 * Diese Mapperklasse bildet <code>Umfrage</code> Objekte auf eine
 * relationale Datenbank ab. 
 * Sie beinhalt Methoden, um Umfrageobjekte anzeigen, anlegen, editieren und löschen zu können.
 * Es können hier sowohl Objekte der Klasse <code>Umfrage</code> in Datenbankstrukturen,
 * als auch Datenbankstrukturen in Objekte der Klasse <code>Umfrage</code> umgewandelt werden.
 * 
 * @author alina
 * @author roland
 * @version 1.0
 *
 */
public class UmfrageMapper extends OwnedBusinessObjectMapper {

	/**Die Klasse <code>Umfrage</code> wird wie jede andere Mapperklasse 
	 * nur ein einziges mal instantiiert. Die Variable umfrageMapper speichert diese Instanz.
	 * Wir stellen die einmalige Initalisierung durch den Bezeichner <code>static</code> sicher. 
	 * 
	 */
	private static UmfrageMapper umfrageMapper = null;
	private static DateConverter dc = new DateConverter();

	/** 
	 * Ein geschützter Konstruktor verhindert die Möglichkeit, mit <code>new</code>
	 * neue Instanzen der Klasse <code>UmfrageMapper</code> zu erzeugen.
	 * 
	 */
	protected UmfrageMapper(){

	}
	/** Mithilfe dieser statischen Methode kann die einzige Instanz der Klasse <code>UmfrageMapper</code>
	 * erzeugt werden. Die Methode beinhaltet die Prüfung, ob bereits ein UmfrageMapper-Objekt erzeugt ist,
	 * dadurch wird sichergestellt, dass immer nur ein Objekt dieser Klasse erzeugt ist.
	 * Ist bereits ein <code>UmfrageMapper</code> - Objekt erzeugt worden, so geben wir die Referenz auf dieses zurück.
	 * 
	 * @return das einzige <code>UmfrageMapper</code> - Objekt
	 */

	public static UmfrageMapper umfrageMapper() {
		if (umfrageMapper == null) {
			umfrageMapper = new UmfrageMapper();
		}

		return umfrageMapper;
	}

	/**
	 * Suchen einer Umfrage mithilfe seiner ID. Die ID ist eindeutig, es wird genau
	 * ein Objekt der Klasse <code >Umfrage</code>zurückgegeben. 
	 * 
	 * @param id (Siehe Primärschlüsselattribut der Tabelle Umfrage in der DB)
	 * @return Umfrage-Objekt, das dem übergebenen Schlüssel entspricht. 
	 * Ist kein entsprechender Tupel in der DB vorhanden, so geben wir null zurück.
	 */



	public Umfrage findByID(int id) {

		//Verbindung zur Datenbank aufbauen.
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `umfrage` LEFT JOIN `ownedbusinessobject` ON `umfrage`.`bo_id` = `ownedbusinessobject`.`bo_id` WHERE (`umfrage_id`= " + id + ") ");

			/* Da ID Primaerschlüssel ist, kann max. nur ein Tupel zurückgegeben werden.
			 * Prüfe, ob ein Ergebnis vorliegt.
			 */
			if (rs.next()) {
				// Ergebnis-Tupel in Objekt umwandeln
				Umfrage umfrage = new Umfrage();
				umfrage.setErstellungszeitpunkt(dc.convertTimestampToDate(rs.getTimestamp("Erstellungszeitpunkt")));
				umfrage.setOwnerID(rs.getInt("owner_id"));
				umfrage.setID(rs.getInt("umfrage_id"));
				umfrage.setBeschreibung(rs.getString("beschreibung"));
				umfrage.setFilmID(rs.getInt("film_id"));
				umfrage.setUmfragenname(rs.getString("Umfragename"));
				umfrage.setDatum(dc.convertSQLDateToJavaDate(rs.getDate("Datum")));

				return umfrage;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Vector<Umfrage> findAllUmfrage() {
		//Verbindung zur Datenbank aufbauen.
		Connection con = DBConnection.connection();
		Vector <Umfrage> vectorumfrage = new Vector <Umfrage>();
		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM `umfrage` LEFT JOIN `ownedbusinessobject` ON `umfrage`.`bo_id` = `ownedbusinessobject`.`bo_id` ORDER BY umfrage_id");


			/* Befüllen des result sets
			 */
			while (rs.next()) {
				// Es werden für jedes Umfrage-Objekt die nötigen Attribute gesetzt
				Umfrage umfrage = new Umfrage();
				umfrage.setErstellungszeitpunkt(dc.convertTimestampToDate(rs.getTimestamp("Erstellungszeitpunkt")));
				umfrage.setOwnerID(rs.getInt("owner_id"));
				umfrage.setID(rs.getInt("umfrage_id"));
				umfrage.setBeschreibung(rs.getString("beschreibung"));
				umfrage.setFilmID(rs.getInt("film_id"));
				umfrage.setUmfragenname(rs.getString("Umfragename"));
				umfrage.setDatum(dc.convertSQLDateToJavaDate(rs.getDate("Datum")));
				vectorumfrage.add(umfrage);

			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return vectorumfrage;
	}

	public Vector <Umfrage> findByErsteller (Nutzer nutzer){

		/**
		 * Verbindung zur Datenabnk aufbauen:
		 */
		Connection con = DBConnection.connection();

		/**
		 * Neuen Vector instantiieren, in welchem Umfrage-Objekte gespeichert werden.
		 */
		Vector <Umfrage> vectorumfrage = new Vector <Umfrage>();

		try {
			/**
			 * Leeres SQL-Statement (JDBC) anlegen.
			 */
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `umfrage` LEFT JOIN `ownedbusinessobject` ON `umfrage`.`bo_id`= `ownedbusinessobject`.`bo_id` WHERE (`owner_id` = " + nutzer.getID() + " ) ORDER BY `umfrage_id`");

			/**
			 * Befüllen des result sets
			 */
			while (rs.next()) {
				/**
				 * Es werden für jedes Umfrage-Objekt die nötigen Attribute gesetzt
				 */
				Umfrage umfrage = new Umfrage();
				umfrage.setErstellungszeitpunkt(dc.convertTimestampToDate(rs.getTimestamp("Erstellungszeitpunkt")));
				umfrage.setOwnerID(rs.getInt("owner_id"));
				umfrage.setID(rs.getInt("umfrage_id"));
				umfrage.setBeschreibung(rs.getString("beschreibung"));
				umfrage.setFilmID(rs.getInt("film_id"));
				umfrage.setUmfragenname(rs.getString("Umfragename"));
				umfrage.setDatum(dc.convertSQLDateToJavaDate(rs.getDate("Datum")));
				vectorumfrage.add(umfrage);
			}

		}
		catch(SQLException e) {
			e.printStackTrace();
		}

		return vectorumfrage;

	}
	/**Aktualisieren eines Umfrageobjekts in der Datenbank. Achtung: Es können nicht alle Attribute aktualisiert werden.
	 * @param umfrage
	 * @return Ein (überarbeitetes) Objekt der Klasse <Umfrage>
	 */
	public Umfrage update (Umfrage umfrage) {

		/**
		 * Verbindung zur Datenbank aufbauen.
		 */
		Connection con = DBConnection.connection();

		try {

			PreparedStatement pstmt = con.prepareStatement("UPDATE 'umfrage' SET 'beschreibung' = ? 'umfragename' = ? WHERE ('umfrageeintrag_id =" + umfrage.getID());
			pstmt.setString(1, umfrage.getBeschreibung());
			pstmt.setString(2, umfrage.getUmfragenname());
			pstmt.executeUpdate();
			return umfrage;

		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Löschen eines Umfrage-Objekts in der Datenbank. Hier wird auch der die delete-Methode der Superklasse
	 * <code>OwnedBusinessObject </code> aufgerufen. Das Umfrageobjekt, welches auch ein OwnedBusinessObject ist,
	 * wird auch aus der Tabelle OwnedBusinessObject gelöscht.
		 @param Objekt der Klasse <code>Umfrage</code>
	 * 
	 */

	public void delete (Umfrage umfrage) {

		/**
		 * Verbindung zur Datenbank aufbauen.
		 */
		Connection con = DBConnection.connection();

		try {
			/** Wir arbeiten hier mit AutoCommits, da wir die Funktion einer Transaktion realisieren möchten.
			 * Initial wir der AutoCommit auf false gesetzt.
			 * 
			 */
			con.setAutoCommit(false);

			int bo_id = findBoIDOf(umfrage);

			super.delete(bo_id, con);

			/**
			 * Leeres SQL-Statement (JDBC) anlegen
			 */
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM `umfrage` WHERE (`umfrage_id` =" + umfrage.getID() + ")" );
			con.commit();


		}

		catch(SQLException e) {
			e.printStackTrace();
			/** Wir wollen dass der delete-Befehl ganz oder gar nicht ausgeführt wird, daher wird hier auf
			 * mögliche Probleme geprüft. Sollten Probleme auftreten, kann die Transaktion nicht 
			 * ausgeführt werden.
			 */
			if (con != null) {
				try {
					// System.err.print("Transaktion wird nicht ausgeführt");
					con.rollback();
				} catch(SQLException exc) {
					exc.printStackTrace();
				}

			}

		} finally {
			/** Sind keine Probleme aufgetreten, so setzen wir den AutoCommit auf true. Die Transaktion ist nun vollständig
			 * durchlaufen.
			 */
			try {
				con.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();

			}}
	}

	/**
	 * Einfügen eines Umfrage-Objekts in die Datenbank. Hier wird auch der die insert-Methode der Superklasse
	 * <code>OwnedBusinessObject </code> aufgerufen. Das Umfrageobjekt(oder vielmehr die
	 * Referenz darauf), welches auch ein OwnedBusinessObject ist,
	 * wird auch in die Tabelle OwnedBusinessObject eingetragen.
	 * @return Ein Objekt der Klasse <Umfrage>
	 */

	public Umfrage insert (Umfrage umfrage) {

		/**
		 * Verbindung zur Datenbank aufbauen
		 */
		Connection con = DBConnection.connection();

		try {
			/** Wir arbeiten hier mit AutoCommits, da wir die Funktion einer Transaktion realisieren möchten.
			 * Initial wir der AutoCommit auf false gesetzt.
			 * 
			 */
			con.setAutoCommit(false);
			int bo_id = super.insert(umfrage, con);

			/**
			 * Leeres SQL-Statement (JDBC) anlegen.
			 */
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MAX(umfrage_id) AS `maxid` FROM `umfrage`");

			// Befüllen des Result-Sets
			if (rs.next()) {
				umfrage.setID(rs.getInt("maxid") + 1);
			}

			PreparedStatement pstmt = con.prepareStatement("INSERT INTO `umfrage` (`umfrage_id`, `bo_id`, `film_id`, `Umfragename`, `Datum`, `Beschreibung`) VALUES (?, ?, ?, ?, ?, ?) ");
			pstmt.setInt(1, umfrage.getID());
			pstmt.setInt(2, bo_id);
			pstmt.setInt(3, umfrage.getFilmID());
			pstmt.setString(4, umfrage.getUmfragenname());
			pstmt.setDate(5, dc.convertJavaDateToSQLDate(umfrage.getDatum()));
			pstmt.setString(6, umfrage.getBeschreibung());
			pstmt.executeUpdate();

			con.commit();
		}
		catch(SQLException e) {
			e.printStackTrace();
			/** Wir wollen dass der insert-Befehl ganz oder gar nicht ausgeführt wird, daher wird hier auf
			 * mögliche Probleme geprüft. Sollten Probleme auftreten, kann die Transaktion nicht 
			 * ausgeführt werden.
			 */
			if (con != null) {
				try {
					// System.err.print("Transaktion wird nicht ausgeführt");
					con.rollback();
				} catch(SQLException exc) {
					exc.printStackTrace();
				}

				return null;
			}

		} finally {

			/** Sind keine Probleme aufgetreten, so setzen wir den AutoCommit auf true. Die Transaktion ist nun vollständig
			 * durchlaufen.
			 */
			try {
				con.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		//Zuletzt geben wir unser Umfrageobjekt zurück.
		return umfrage;
	}



	public Umfrage getUmfrageByUmfrageeintrag (Umfrageeintrag umfrageeintrag) {

		/**
		 * Verbindung zur Datenbank aufbauen.
		 */
		Connection con = DBConnection.connection();


		try {
			/**
			 * Leeres SQL-Statement (JDBC) anelgen
			 */
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(" SELECT * FROM 'umfrage' JOIN 'umfrageeintrag' WHERE 'umfrage.umfrage_id' = 'umfrageeintrag.umfrage_id' ORDER BY 'umfrage_id' " );

			if (rs.next()) {
				Umfrage umfrage = new Umfrage();
				umfrage.setID(rs.getInt("umfrage_id"));
				umfrage.setOwnerID(rs.getInt("owner_id"));
				umfrage.setBeschreibung(rs.getString("beschreibung"));
				umfrage.setUmfragenname(rs.getString("umfragename"));

				return umfrage;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}


	public Umfrage findByUmfragenname (String umfragenname) {

		/**
		 * Verbindung zur Datenbank aufbauen.
		 */
		Connection con = DBConnection.connection();

		try {
			/**
			 * Leeres SQL-Statement (JDBC) anelgen
			 */
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `umfrage` WHERE (`umfragename` = " + umfragenname + ")");

			if(rs.next()) {
				Umfrage umfrage = new Umfrage();
				umfrage.setErstellungszeitpunkt(dc.convertTimestampToDate(rs.getTimestamp("Erstellungszeitpunkt")));
				umfrage.setID(rs.getInt("umfrage_id"));
				umfrage.setOwnerID(rs.getInt("owner_id"));
				umfrage.setBeschreibung(rs.getString("beschreibung"));
				umfrage.setUmfragenname(rs.getString("umfragename"));


				return umfrage;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}


	public Vector <Umfrage> findByGruppename (String gruppenname) {

		/**
		 * Verbindung zur Datenbank aufbauen.
		 */
		Connection con = DBConnection.connection();

		try {
			/**
			 * Leeres SQL-Statement (JDBC) anelgen
			 */
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM 'umfrage' LEFT JOIN `gruppe` ON `umfrage`.`bo_id`= `gruppe`.`bo_id` ORDER BY 'gruppenname'");

			if(rs.next()) {
				Vector <Umfrage> vectorumfrage = new Vector <Umfrage>();
				Umfrage umfrage = new Umfrage();
				umfrage.setID(rs.getInt("umfrage_id"));
				umfrage.setOwnerID(rs.getInt("bo_id"));
				vectorumfrage.add(umfrage);

				return vectorumfrage;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	/** Dies ist eine Hilfsmethode. Sie ermöglicht uns, die bo_id eines OwnedBusinessObjects zu ermitteln.
	 * 
	 * @param umfrage
	 * @return bo_id
	 * @throws SQLException
	 */

	private int findBoIDOf (Umfrage umfrage) throws SQLException {

		Connection con = DBConnection.connection();
		int bo_id = 0;

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT `bo_id` FROM `umfrage` WHERE (`umfrage_id` = " +  umfrage.getID() + ")");

		if(rs.next()) {
			bo_id = rs.getInt("bo_id");
		}

		return bo_id;
	}

}





