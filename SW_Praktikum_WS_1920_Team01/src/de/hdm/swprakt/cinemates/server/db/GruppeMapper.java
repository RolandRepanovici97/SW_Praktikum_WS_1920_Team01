/**
 * 
 */
package de.hdm.swprakt.cinemates.server.db;

import java.util.Vector;

import de.hdm.swprakt.cinemates.shared.bo.Gruppe;

/**
 * @author
 *
 */
public class GruppeMapper {

	private static GruppeMapper gruppeMapper = null;
	
	protected GruppeMapper() {
		
	}
	
	public static GruppeMapper gruppeMapper() {
		
		if(gruppeMapper == null) {
			gruppeMapper = new GruppeMapper();
		}
		
			return gruppeMapper;
		
	}
	
	public Vector<Gruppe> findAll(){
		
	}
	
	public Gruppe findByID(int id) {
		
	}
	
	public Gruppe insert (Gruppe gruppe, OwnedBusinessObject obo) {
		
	}
	
	public Gruppe update (Gruppe gruppe) {
		
	}
	
	public void delete (Gruppe gruppe) {
		
	}
	
	public void insertGruppenzugehörigkeit (int userid, int gruppenid) {
		
	}
	
	
	public void deleteGruppenzugehörigkeit (int userid, int gruppenid) {
		
	}
	
	public void deleteGruppenmitglieder (Gruppe gruppe) {
		
	}
	
	public Vector<Gruppe> getGruppenOf (Nutzer nutzer){
		
	}
	
	public Vector<Gruppe> getGruppenOf (Umfrage umfrage){
		
	}
}
