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

import de.hdm.swprakt.cinemates.shared.bo.Umfrageeintrag;
import de.hdm.swprakt.cinemates.shared.bo.Votum;

/**
 * Diese Mapperklasse bildet <code>Votum</code> Objekte auf eine
 * relationale Datenbank ab. 
 * Sie beinhaltet Methoden, um Vota anzeigen, anlegen, editieren und löschen zu können.
 * Es können hier sowohl Objekte der Klasse <code>Votum</code> in Datenbankstrukturen,
 * als auch Datenbankstrukturen in Objekte der Klasse <code>Votum</code>  umgewandelt werden.
 * 
 * @author alina
 * @version 1.0
 *
 */
public class VotumMapper extends OwnedBusinessObjectMapper {
	/**Die Klasse <code>VotumMapper</code> wird wie jede andere Mapperklasse 
	 * nur ein einziges mal instantiiert. Die Variable votumMapper speichert diese Instanz.
	 * Wir stellen die einmalige Initalisierung durch den Bezeichner <code>static</code> sicher. 
	 * 
	 */
	private static VotumMapper votumMapper = null;
	private static DateConverter dc = new DateConverter();

	/** 
	 * Ein geschützter Konstruktor verhindert die Möglichkeit, mit <code>new</code>
	 * neue Instanzen der Klasse <code>VotumMapper</code> zu erzeugen.
	 * 
	 */
	protected VotumMapper() {

	}

	/** Mithilfe dieser statischen Methode kann die einzige Instanz der Klasse <code>VotumMapper</code>
	 * erzeugt werden. Die Methode beinhaltet die Prüfung, ob bereits ein VotumMapper-Objekt erzeugt ist,
	 * dadurch wird sichergestellt, dass immer nur ein Objekt dieser Klasse erzeugt ist.
	 * Ist bereits ein <code>VotumMapper</code> - Objekt erzeugt worden, so geben wir die Referenz auf dieses zurück.
	 * 
	 * @return das einzige <code>VotumMapper</code> - Objekt
	 */

	public static VotumMapper votumMapper() {
		if (votumMapper == null) {
			votumMapper = new VotumMapper();
		}

		return votumMapper;
	}

	/**
	 * Suchen eines Umfrageeintrags mithilfe seiner ID. Die ID ist eindeutig, es wird genau
	 * ein Objekt der Klasse <code >Umfrageeintrag </code>zurückgegeben. 
	 * 
	 * @param id (Siehe Primärschlüsselattribut der Tabelle Votum in der DB)
	 * @return Votum-Objekt, das dem übergebenen Schlüssel entspricht. 
	 * Ist kein entsprechender Tupel in der DB vorhanden, so geben wir null zurück.
	 */

	public Votum findByID(int id) {

		//Verbindung zur Datenbank aufbauen.
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM votum WHERE votum_id=" + id + "ORDER BY votum_id");

			/* Da ID Primaerschlüssel ist, kann max. nur ein Tupel zurückgegeben werden.
			 * Prüfe, ob ein Ergebnis vorliegt.
			 */
			if (rs.next()) {
				// Ergebnis-Tupel in Objekt umwandeln
				Votum votum = new Votum();
				votum.setID(rs.getInt("votum_id"));
				votum.setErstellungszeitpunkt(dc.convertTimestampToDate(rs.getTimestamp("Erstellungszeitpunkt")));
				votum.setOwnerID(rs.getInt("owner_id"));
				votum.setIstMöglicherTermin(rs.getBoolean("istMöglicherTermin"));
				votum.setUmfrageeintragID(rs.getInt("umfrageeintrag_id"));



				return votum;
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	/**
	 * Suchen aller Objekte der Klasse <code> Votum </code>
	 * @return Vector <Votum>, welcher alle Votum-Objekte beinhaltet
	 */

	public Vector <Votum> findAllVotum() {
		//Verbindung zur Datenbank aufbauen.

		Connection con = DBConnection.connection();
		// Neuen Vector instantiieren, in welchem unsere Votum-Objekte gespeichert werden
		Vector <Votum> vectorvotum = new Vector <Votum>();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM votum ORDER BY votum_id");

			/* Befüllen des result sets
			 */
			if (rs.next()) {
				/**Es werden für jedes Votum-Objekt die nötigen Attribute gesetzt. Dazu wird ein Objekt der Klasse <code>Votum</code> angelegt und dessen Attribute werden gesetzt. 
				Dies wird wiederholt. Das Ergebnis wird jeweils einem Vector hinzugefügt, welchen wir am Ende zurückgeben.
				 */
				Votum votum = new Votum();
				votum.setID(rs.getInt("votum_id"));
				votum.setErstellungszeitpunkt(dc.convertTimestampToDate(rs.getTimestamp("Erstellungszeitpunkt")));
				votum.setOwnerID(rs.getInt("bo_id"));
				votum.setIstMöglicherTermin(rs.getBoolean("istMöglicherTermin"));
				votum.setUmfrageeintragID(rs.getInt("umfrageeintrag_id"));
				vectorvotum.add(votum);



				return vectorvotum;
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Suchen aller Objekte der Klasse <code> Votum</code>, welche zu einem übergebenen Umfrageeintrag gehören.
	 * @param Objekt der Klasse <Umfrageeintrag>
	 * @return Vector <Votum>, welcher alle Votum-Objekte beinhaltet, welche dem übergebenen Umfrageeintrag gehören
	 */


	public Vector <Votum> findVotumByUmfrageeintrag(Umfrageeintrag umfrageeintrag) {
		//Verbindung zur Datenbank aufbauen.
		Connection con = DBConnection.connection();
		// Neuen Vector instantiieren, in welchem unsere Votum-Objekte gespeichert werden
		Vector <Votum> vectorvotum = new Vector <Votum>();
		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM votum WHERE votum.umfrageeintrag_id="+
					umfrageeintrag.getID()+ "ORDER BY umfrageeintrag_id");

			if (rs.next()) {
				/** Es werden für jedes Votum-Objekt die nötigen Attribute gesetzt.Dazu wird ein Objekt der Klasse <code>Votum</code> angelegt und dessen Attribute werden gesetzt. 
				Dies wird wiederholt. Das Ergebnis wird jeweils einem Vector hinzugefügt, welchen wir am Ende zurückgeben. */

				Votum votum = new Votum();
				votum.setID(rs.getInt("votum_id"));
				votum.setErstellungszeitpunkt(dc.convertTimestampToDate(rs.getTimestamp("Erstellungszeitpunkt")));
				votum.setOwnerID(rs.getInt("bo_id"));
				votum.setIstMöglicherTermin(rs.getBoolean("istMöglicherTermin"));
				votum.setUmfrageeintragID(rs.getInt("umfrageeintrag_id"));
				vectorvotum.add(votum);



				return vectorvotum;

			}


		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Einfügen eines Votum-Objekts in die Datenbank. Hier wird auch der die insert-Methode der Superklasse
	 * <code>OwnedBusinessObject </code> aufgerufen. Das Votumobjekt(oder vielmehr die
	 * Referenz darauf), welches auch ein OwnedBusinessObject ist,
	 * wird auch in die Tabelle OwnedBusinessObject eingetragen.
	 * @return Ein Objekt der Klasse <Votum>
	 */

	public Votum insert (Votum votum) {

		//Verbindung zur Datenbank aufbauen.

		Connection con = DBConnection.connection();

		try {
			/** Wir arbeiten hier mit AutoCommits, da wir die Funktion einer Transaktion realisieren möchten.
			 * Initial wir der AutoCommit auf false gesetzt.
			 * 
			 */
			con.setAutoCommit(false);
			int bo_id = super.insert(votum, con);

			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MAX(votum_id) AS maxid FROM votum");

			//Befüllen des Result-Sets
			if (rs.next()) {
				votum.setID(rs.getInt("maxid") + 1);
			}	

			PreparedStatement pstmt = con.prepareStatement("INSERT INTO votum(votum_id, bo_id, istMöglicherTermin,umfrageeintrag_id VALUES (?, ?, ?, ?) ");
			pstmt.setInt(1,votum.getID());
			pstmt.setInt(2, bo_id);
			pstmt.setBoolean(2, votum.getIstMöglicherTermin());
			pstmt.setInt(3, votum.getUmfrageeintragID());
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
		//Zuletzt geben wir unser Votumobjekt zurück.
		return votum;
	}



	/**
	 * Aktualisieren eines Votum-Objekts in der Datenbank. Achtung: Es können nicht alle Attribute aktualisiert werden.
	 * Es wurde auf weniger sinnvolle Möglichkeiten verzichtet.
	 * @return Ein (aktualisiertes) Objekt der Klasse <code>Votum</code>
	 */

	public Votum update (Votum votum) {

		//Verbindung zur Datenbank aufbauen.

		Connection con = DBConnection.connection();

		try {

			PreparedStatement pstmt = con.prepareStatement("UPDATE votum SET istMöglicherTermin =?");


			pstmt.setBoolean(1,votum.getIstMöglicherTermin());

			pstmt.executeUpdate();
			return votum;

		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Löschen eines Votum-Objekts in der Datenbank. Hier wird auch der die delete-Methode der Superklasse
	 * <code>OwnedBusinessObject </code> aufgerufen. Das Votumobjekt, welches auch ein OwnedBusinessObject ist,
	 * wird auch aus der Tabelle OwnedBusinessObject gelöscht.
		 @param Objekt der Klasse <code>Votum</code>
	 * 
	 */

	public void delete (Votum votum) {

		//Verbindung zur Datenbank aufbauen.

		Connection con = DBConnection.connection();

		try {
			/** Wir arbeiten hier mit AutoCommits, da wir die Funktion einer Transaktion realisieren möchten.
			 * Initial wir der AutoCommit auf false gesetzt.
			 * 
			 */
			con.setAutoCommit(false);

			int bo_id = findBoIDOf(votum);

			super.delete(bo_id, con);

			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("DELETE FROM votum WHERE votum_id=" + votum.getID());
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
	 * Löschen der Votum-Objekte, welche zu einem Umfrageeintrag gehören.
	 * @param Objekt der Klasse <code>Umfrageeintrag</code>
	 */

	public void deleteVotumByUmfrage (Umfrageeintrag umfrageeintrag) {

		//Verbindung zur Datenbank aufbauen.

		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("DELETE FROM votum WHERE umfrageeintrag_id=" + umfrageeintrag.getID());



		}
		catch(SQLException e) {
			e.printStackTrace();
		}


	}
	/** Dies ist eine Hilfsmethode. Sie ermöglicht uns, die bo_id eines OwnedBusinessObjects zu ermitteln.
	 * 
	 * @param votum
	 * @return bo_id
	 * @throws SQLException
	 */

	private int findBoIDOf (Votum votum) throws SQLException {

		Connection con = DBConnection.connection();
		int bo_id = 0;

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT `bo_id` FROM `votum` WHERE (`votum_id` = " +  votum.getID() + ")");

		if(rs.next()) {
			bo_id = rs.getInt("bo_id");
		}

		return bo_id;
	}



}