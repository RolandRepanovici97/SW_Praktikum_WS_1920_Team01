/**
 * 
 */
package de.hdm.swprakt.cinemates.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.swprakt.cinemates.server.db.GruppeMapper;
import de.hdm.swprakt.cinemates.server.db.NutzerMapper;
import de.hdm.swprakt.cinemates.server.db.OwnedBusinessObjectMapper;
import de.hdm.swprakt.cinemates.server.db.UmfrageMapper;
import de.hdm.swprakt.cinemates.server.db.UmfrageeintragMapper;
import de.hdm.swprakt.cinemates.server.db.VotumMapper;
import de.hdm.swprakt.cinemates.shared.KinoAdministration;
import de.hdm.swprakt.cinemates.shared.KinoBesuchsplanung;
import de.hdm.swprakt.cinemates.shared.bo.Nutzer;
import de.hdm.thies.bankProjekt.server.db.AccountMapper;
import de.hdm.thies.bankProjekt.server.db.CustomerMapper;
import de.hdm.thies.bankProjekt.server.db.TransactionMapper;

/**
 * Diese Klasse stellt die Implementierungsklasse des Interface {@link KinoBesuchsplanung} dar. 
 * Sie beinhaltet die komplette Applikationslogik, welche zur Planung eines Kinobesuchs benötigt wird. 
 * (Anlage und Verwaltung von Gruppen, Umfragen, etc.) Sie benötigt Zugriff auf die  <code> KinoAdministration </code>,
 * da diese Methoden bereitstellt, welche für die Kinobesuchsplanung relevant sind. 
 * 
 * @author alina
 * @version 1.0
 *
 */
public class KinoBesuchsplanungImpl extends RemoteServiceServlet implements KinoBesuchsplanung {
/** Zugriff auf KinoAdministration
 * 
 */
	
private KinoAdministration administration = null;

/** Der Kinobesucher benötigt Zuriff auf die Daten rund um eine Umfrage, Gruppe etc.
 *  Dieser Zugriff wird über die jeweiligen Mapper realisiert. 
 *  */
private NutzerMapper nutzerMapper = null;
private GruppeMapper gruppeMapper = null;
private UmfrageMapper umfrageMapper = null;
private UmfrageeintragMapper umfrageeintragMapper = null;
private VotumMapper votumMapper = null;
private OwnedBusinessObjectMapper ownedBusinessObjectMapper = null;


public KinoBesuchsplanungImpl() throws IllegalArgumentException {
    
  }

 
  @Override
public void init() throws IllegalArgumentException {
    
    this.nutzerMapper = NutzerMapper.nutzerMapper();
    this.gruppeMapper = GruppeMapper.gruppeMapper();
    this.umfrageMapper = UmfrageMapper.umfrageMapper();
    this.umfrageeintragMapper = UmfrageeintragMapper.umfrageeintragMapper();
    this.votumMapper = VotumMapper.votumMapper();
    this.ownedBusinessObjectMapper = OwnedBusinessObjectMapper.ownedBusinessObjectMapper();
    
    KinoAdministrationImpl kai = new KinoAdministrationImpl();
    kai.init();
    this.administration = kai;
  }
  
@Override
public Nutzer findNutzerByEmail(String email) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Nutzer insertNutzer(Nutzer nutzer) {
	
	//Einfügen eines neuen OwnedBusinessObjects
	//Einfügen des Nutzers in die Nutzerdatenbank mit zugehöriger OwnedBusinessObject Referenz
	// TODO Auto-generated method stub
	return null;
}

}
