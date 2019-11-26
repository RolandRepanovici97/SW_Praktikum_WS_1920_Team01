package de.hdm.swprakt.cinemates.shared.bo;

import java.util.Vector;

public class Gruppe extends OwnedBusinessObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String gruppenname;
	private Vector<Integer> gruppenmitglieder;
	
	public Gruppe() {
		super();
	}

	public String getGruppenname() {
		return gruppenname;
	}

	public void setGruppenname(String gruppenname) {
		this.gruppenname = gruppenname;
	}
	
	
	public void addGrupenmitglied(int nutzerkonto_id) {
		gruppenmitglieder.add(nutzerkonto_id);
	}
	
	public Vector<Integer> getGruppenmitglieder(){
		return gruppenmitglieder;
	}
	
	public void setGruppenmitglieder(Vector<Integer> gruppenmitglieder) {
		this.gruppenmitglieder = gruppenmitglieder;
	}

	@Override
	public String toString() {
		return super.toString() + "; Gruppenname: " + this.gruppenname;
	}
	
	
	
		
}
