/**
 * 
 */
package de.hdm.swprakt.cinemates.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;

import de.hdm.swprakt.cinemates.shared.bo.Nutzer;

/**
 * Diese Klasse dient zur Darstellung des Headers der Applikation. Sie beinhaltet das Logo der Applikation,
 * sowie den Namen 
 * @author alina
 * @version 1.0
 *
 */
public class Header extends HorizontalPanel {
	
	/* Diese privaten Attribute stellen Widgtes dar, deren Inhalte Variable sind. 
	 * Sie werden benötigt, um die Inhalte im weiteren Verlauf sinnvolll zu struktutieren. 
	 * Es werden dazu später ClickHandler auf die Widgets implementiert.
	 */
	private Button adminButton;
	private Button planerButton;
	private Anchor adminLink;
	private Anchor planerLink;
	private PopupPanel nutzerBearbeitungspanel;
	private Button nutzerkontoAnzeigenButton;
	private Button nutzerkontoBearbeitenButton;
	private Button logoutButton;
	private Image logo;
	
	
	
	/*
	 * Beim Anzeigen werden die Widgets erzeugt. Alle werden in einem
	 * Raster angeordnet, dessen Größe sich aus dem Platzbedarf der enthaltenen
	 * Widgets bestimmt.
	 */
	
	public void onLoad() {
		/*+ Zunächst die Instantiierung der einzelenen Widgets/Buttons
		 * 
		 */
		adminButton = new Button("Kinoadministration");
		planerButton = new Button("Kinobesuchsplanung");
		nutzerkontoAnzeigenButton = new Button("Nutzerkonto anzeigen");
		nutzerkontoBearbeitenButton = new Button ("Nutzerkonto bearbeiten");
		logoutButton = new Button("Logout");
		logo = new Image("images/CineMates Logo.jpg");
		logo.setWidth("100px");

		
		nutzerBearbeitungspanel = new PopupPanel();
		/**
		 * Ich weiß, dass es so gewünscht war, dass hier schon der "echte" Nutzername des aktuellen Nutzers steht,
		 * da aber LoginService noch nicht implementiert ist, können wir schlecht die Instanz des 
		 * jeweiligen Nutzers ansprechen. Wir benötigen hier einen statischen Parameter und "getNutzername" ist non-static. 
		 */
		nutzerBearbeitungspanel.setTitle("Nutzerkonto");
		
		/** Hinzufügen der Buttons/Widgtes zum HorizontalPanel
		 * 
		 */
		this.add(logo);
		this.add(planerButton);
		this.add(adminButton);
		nutzerBearbeitungspanel.add(nutzerkontoAnzeigenButton);
		nutzerBearbeitungspanel.add(nutzerkontoBearbeitenButton);
		nutzerBearbeitungspanel.add(logoutButton);
		this.add(nutzerBearbeitungspanel);
		
		/** Auf den Button, welcher zunächst den Nutzernamen darstellen soll, soll ein Panel "gelegt" werden,
		 * welches es dem Nutzer ermöglicht, sich sein Nutzerkonto anzeigen zu lassen
		 * ,es zu bearbeiten, sowie sich auszuloggen.
		 * 
		 */
		
		/** Hinzufügen der ClickHandler zu den Buttons
		 * 
		 */
		adminButton.addClickHandler(new AdminClickHandler());
		planerButton.addClickHandler(new PlanerClickHandler());
		nutzerkontoAnzeigenButton.addClickHandler(new NutzerAnzeigenClickHandler());
		nutzerkontoBearbeitenButton.addClickHandler(new NutzerBearbeitenClickHandler());
		logoutButton.addClickHandler(new LogoutClickHandler());
		
		
		
		
		
		
		
	}
	
	/**
	 *************************
	 *Abschnitt der Click-Handler
	 *************************
	 */
	
	/** Für ClickHandler-Klassen bietet sich das Konzept der nested classes an, da sie nicht weiter benötigt werden
	 * als an dieser Stelle. Sie implementieren das, von GWT bereitgestellte, Interface <code> ClickHandler </code>
	 *
	 */
	private class AdminClickHandler implements ClickHandler{

		/** Der Nutzer soll zunächst auf die Seite der Kinobesuchsplanung geführt werden.
		 *  Klickt er dann nochmals auf Button "Kinobesuchsplanung", so wird die Seite neu geladen.
		 */
		@Override
		public void onClick(ClickEvent event) {
		Window.Location.reload();
		
			
		}
	}
		private class PlanerClickHandler implements ClickHandler{
			
			/** Klickt der Nutzer auf den Button der mit "Kinoadministration" beschriftet ist,
			 *  so wird er auf diese Seite weitergeleitet.
			 */

			@Override
			public void onClick(ClickEvent event) {
				Window.Location.assign("Kinoadministration.html");
				
			}
			
		
		}
		/** Klickt der Nutzer auf den Button der mit "Nutzerkonto anzeigen" beschriftet ist,
		 *  so wird ihm sein Nutzerkonto angezeigt.
		 */
		private class NutzerAnzeigenClickHandler implements ClickHandler {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
			
}			/** Klickt der Nutzer auf den Button der mit "Nutzerkonto bearbeiten" beschriftet ist,
		 *      so bekommt er die Möglichkeit, sein Nutzerkonto zu bearbeiten.
		 */
			private class NutzerBearbeitenClickHandler implements ClickHandler{

				@Override
				public void onClick(ClickEvent event) {
					// TODO Auto-generated method stub
					
				}
				
			
		}
			/** Klickt der Nutzer auf den Button der mit "Logout" beschriftet ist,
			 *  so wird er ausgeloggt und landet wieder auf der Login-Page. 
			 */
			private class LogoutClickHandler implements ClickHandler {

				@Override
				public void onClick(ClickEvent event) {
					Window.Location.assign("Loginpage.html");
					
				}
				
			}
			
	
}

