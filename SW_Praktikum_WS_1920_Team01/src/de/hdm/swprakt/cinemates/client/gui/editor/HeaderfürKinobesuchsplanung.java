/**
 * 
 */
package de.hdm.swprakt.cinemates.client.gui.editor;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Diese Klasse dient zur Darstellung des Headers der Applikation. Sie beinhaltet das Logo der Applikation,
 * sowie den Namen 
 * @author alina
 * @version 1.0
 *
 */
public class HeaderfürKinobesuchsplanung extends HorizontalPanel {

	/* Diese privaten Attribute stellen Widgtes dar, deren Inhalte Variable sind. 
	 * Sie werden benötigt, um die Inhalte im weiteren Verlauf sinnvolll zu struktutieren. 
	 * Es werden dazu später ClickHandler auf die Widgets implementiert.
	 */
	private Button adminButton;
	private Button planerButton;
	private Button nutzer;
	private ListBox nutzerkontolistbox;
	private ListBox nutzerkontolistbox2;
	private Label nutzerbeschriftung;
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
		adminButton.getElement().setId("adminbutton");
		nutzer = new Button("Nutzer");
		nutzer.setStyleName("nutzer");
		nutzer.setHTML("<i class=\"fas fa-user\"></i>");
		nutzer.setHeight("100px");
		nutzer.setWidth("100px");
		nutzer.getElement().setId("NutzerButton");
		planerButton = new Button("Kinobesuchsplanung");
		planerButton.getElement().setId("planerbutton");
		nutzerbeschriftung = new Label("Nutzerkonto");

		nutzerkontolistbox = new ListBox(true);
		nutzerkontolistbox2 = new ListBox(false);

		nutzerkontolistbox2.setTitle("Nutzerkonto");
		nutzerkontolistbox2.addItem("--Bitte auswählen--");
		nutzerkontolistbox2.addItem("Nutzerkonto bearbeiten");
		nutzerkontolistbox2.addItem("Logout");
		nutzerkontolistbox.setVisibleItemCount(2);
		nutzerkontolistbox2.setVisibleItemCount(1);
		nutzerkontolistbox2.getElement().setId("nutzerlistbox");
		logo = new Image("images/CineMates Logo.jpg");
		logo.setWidth("100px");




		/** Hinzufügen der Buttons/Widgtes zum HorizontalPanel
		 * 
		 */
		this.add(logo);
		this.add(planerButton);
		this.add(adminButton);
		this.add(nutzer);

		this.add(nutzerbeschriftung);

		this.add(nutzerkontolistbox2);


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


		/** Klickt der Nutzer auf den Button der mit "Kinoadministration" beschriftet ist,
		 *  so wird er auf diese Seite weitergeleitet.
		 */

		@Override
		public void onClick(ClickEvent event) {
			Window.Location.assign("KinoAdministration.html");



		}
	}
	private class PlanerClickHandler implements ClickHandler{

		/** Der Nutzer soll zunächst auf die Seite der Kinobesuchsplanung geführt werden.
		 *  Klickt er dann nochmals auf Button "Kinobesuchsplanung", so wird die Seite neu geladen.
		 */

		@Override
		public void onClick(ClickEvent event) {
			Window.Location.reload();
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
		/** Klickt der Nutzer auf den Button der mit "Logout" beschriftet ist,
		 *  so wird er ausgeloggt und landet wieder auf der Login-Page. 
		 */
		private class LogoutClickHandler implements ClickHandler {

			@Override
			public void onClick(ClickEvent event) {
				Window.Location.assign("Loginpage.html");

			}

		}}}
