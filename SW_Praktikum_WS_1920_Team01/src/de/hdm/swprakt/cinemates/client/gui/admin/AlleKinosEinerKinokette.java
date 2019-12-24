package de.hdm.swprakt.cinemates.client.gui.admin;

import java.util.Vector;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.swprakt.cinemates.client.ClientSideSettings;
import de.hdm.swprakt.cinemates.shared.KinoAdministrationAsync;
import de.hdm.swprakt.cinemates.shared.bo.Kino;
import de.hdm.swprakt.cinemates.shared.bo.Kinokette;

public class AlleKinosEinerKinokette extends Showcase {

	Kinokette kinokette = null;
	private Showcase showcase = null;
	 @Override
	  protected void run() {
	    

	    KinoAdministrationAsync kinoAdministration = ClientSideSettings.getKinoAdministration();

	    kinoAdministration.getAllKinoOfKinokette(kinokette, new GetAllKinoOfKinoketteCallback(this.showcase,kinokette));
	    
	   // bankVerwaltung.getAllCustomers(new GetAllCustomersCallback(this));
	  }
	
	 class GetAllKinoOfKinoketteCallback implements AsyncCallback<Vector<Kino>>{
			private Showcase showcase = null;
			
			private Kinokette kinokette = null;
			
			public GetAllKinoOfKinoketteCallback(Showcase c, Kinokette kinokette) {
			this.showcase= c;
			this.kinokette=kinokette;
			}

	 
	 
	 
	@Override
	public void onFailure(Throwable caught) {
		Window.alert("Fehler bei der Abfrage"+ caught.getMessage());
	}


	@Override
	public void onSuccess(Vector<Kino> kinos) {
		if(kinos!= null) {
			KinoAdministrationAsync	kinoAdministration = ClientSideSettings.getKinoAdministration();
			
			for(Kino k: kinos) {
				Window.alert("Fordere alle Kinos für die Kinokette"+k.getAdresse()+k.getBeschreibung()+k.getKinoname());
				
				
			}
		}
		
	}
	
	
	}
	

	 
	 
	 
	
}



