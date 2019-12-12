/**
 * 
 */
package de.hdm.swprakt.cinemates.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.swprakt.cinemates.shared.bo.Gruppe;
import de.hdm.swprakt.cinemates.shared.bo.Nutzer;
import de.hdm.swprakt.cinemates.shared.bo.Umfrage;
import de.hdm.swprakt.cinemates.shared.bo.Umfrageeintrag;
import de.hdm.swprakt.cinemates.shared.bo.Votum;

/**
 * Das asynchrone Gegenstück des Interface {@link KinoBesuchsplanung}
 * @author alina 
 * @version 1.0
 *
 */
public interface KinoBesuchsplanungAsync {
	
	void init(AsyncCallback<Void> callback);

	public void findNutzerByEmail(String email, AsyncCallback<Nutzer> callback);
	
	public void createNutzer(Nutzer nutzer, AsyncCallback<Nutzer> callback);
	
	public void createGruppe(Gruppe gruppe, AsyncCallback<Gruppe> callback);

	public void showAllUmfrage(AsyncCallback<Vector<Umfrage>> callback);

	public void createUmfrage(String umfragenname, AsyncCallback<Umfrage> callback);

	public void showVotumOfUmfrageeintrag(Umfrageeintrag umfrageeintrag, AsyncCallback<Vector<Votum>> callback);

	public void editUmfrage(Umfrage umfrage, String umfragenname, String beschreibung, AsyncCallback<Umfrage> callback);

	public void deleteUmfrage(Umfrage umfrage, AsyncCallback<Void> callback);

	public void abstimmen(Umfrageeintrag umfrageeintrag, Boolean istMöglicherTermin, AsyncCallback<Votum> callback);

	public void umfrageergebnisseAnzeigen(Umfrage umfrage, AsyncCallback<Vector<Umfrageeintrag>> callback);

	public void bestesErgebnisErmitteln(Umfrage umfrage, AsyncCallback<Umfrageeintrag> callback);

	public void deleteGruppe(Gruppe gruppe, AsyncCallback<Void> callback);

	public void deleteUmfrageeintrag(Umfrageeintrag umfrageeintrag, AsyncCallback<Void> callback);

	public void deleteVotum(Votum votum, AsyncCallback<Void> callback);


	
	
}
