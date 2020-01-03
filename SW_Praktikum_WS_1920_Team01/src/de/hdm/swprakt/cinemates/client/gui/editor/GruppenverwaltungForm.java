//package de.hdm.swprakt.cinemates.client.gui.editor;
//
//import java.util.Vector;
//
//import com.google.gwt.user.client.ui.HTML;
//import com.google.gwt.user.client.ui.TabPanel;
//
//
///**
// * Diese Klasse stellt eine Erweiterung des TabPanels dar und wird verwendet, 
// * um dem angemeldeten Nutzer die Möglichkeit
// * zu bieten zwischen 3 Gruppe Forms zu navigieren: Anzeigen, Erstellen und Editieren. 
// * @author roland
// *
// */
//
//public class GruppenverwaltungForm extends TabPanel{
//
//	/*
//	 * ***************************************************************************
//	 * ABSCHNITT: Hier wird der erste Teil der GruppenverwaltungForm implementiert.
//	 * Hier werden alle Gruppen des angemeldeten Nutzers angezeigt. Der Nutzer hat
//	 * hier die Möglichkeit durch Klicken alle Gruppen zu zeigen wo er angemeldet
//	 * ist, eine neue Gruppe zu erstellen und zu editieren. Ein "Zurück" Button 
//	 * ermöglicht hier auch zu den letzten besuchten Funktion zuzugreifen. 
//	 * ***************************************************************************
//	 */
//
//
//	
//	/**
//	 * Erstellung der 3 Form Objekte für die Navigation zwischen Tabs.
//	 */
//
//	GruppenAnzeigenForm gaf = new GruppenAnzeigenForm();
//	GruppeErstellenForm gef = new GruppeErstellenForm();
//	GruppeEditierenForm gedf = new GruppeEditierenForm();
//
//	
//	
//
//	public void onLoad() {
//		
//		super.onLoad();
//
//		this.add(gaf, "Anzeigen");
//		this.add(gef, "Erstellen");
//		this.add(gedf, "Editieren");
//
//		
//		this.selectTab(1);
//		
//		
//
//	}
//
//
//}
