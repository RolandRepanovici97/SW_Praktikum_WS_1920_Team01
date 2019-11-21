package de.hdm.swprakt.cinemates.shared.bo;

import java.util.Vector;

public class Gruppe extends BusinessObject{
	
	private String gruppenname;
	private int ersteller_id;
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

	public int getErstellerId() {
		return ersteller_id;
	}

	public void setErstellerId(int ersteller_id) {
		this.ersteller_id = ersteller_id;
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
	
	
		
}
