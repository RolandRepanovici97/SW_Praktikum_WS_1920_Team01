/**
 * 
 */
package de.hdm.swprakt.cinemates.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Diese Klasse erweitert das Vertical Panel und dient zur Darstellung des
 * Footers. Hier ist ein Hinweis auf das Copyright und das Impressum zu finden.
 * 
 * @author alina
 *
 */
public class Footer extends VerticalPanel {

	// Initalisierung der benötigten Widgets
	private Label label;
	private ImpressumDialogBox impressumDialogBox = null;

	private Button impressumButton = null;
	private HorizontalPanel horizontalPanel = null;

	public void onLoad() {
		super.onLoad();

		// Instantiierung der Widgets
		label = new Label();
		horizontalPanel = new HorizontalPanel();
		impressumButton = new Button("Impressum");

		// ClickHandler für den mit "Impressum" beschrifteten Button
		impressumButton.addClickHandler(new ImpressumClickHandler());

		// Hinzufügen der Widgets zu den Panels
		horizontalPanel.add(label);
		horizontalPanel.add(impressumButton);
		this.add(horizontalPanel);

	}

	/*
	 * ***************************************************************************
	 * ABSCHNITT Nested Classes
	 * ***************************************************************************
	 */

	/**
	 * Diese Klasse erweitert die "rohe" HTML Klasse und dient zur Darstellung der
	 * Copyrighthinweise
	 * 
	 * @author alina
	 *
	 */

	class Label extends HTML {

		// Konstruktor
		public Label() {
			super();

			// Setzen der Inhalte
			this.setHTML("&copy; Software-Praktikum Team 01 | WS 19/20");
		}
	}

	/**
	 * Diese Klasse erweitert die "rohe" HTML Klasse und dient zur Darstellung der
	 * Inhalte unseres Impressums. Hier sind Referenzen auf den Studiengang und auf
	 * das Impressum der Hochschule zu finden.
	 * 
	 * @author alina
	 *
	 */
	class Impressum extends HTML {

		// Konstruktor
		public Impressum() {
			super();

			// Setzen der Inhalte
			this.setHTML(("<div class='Impressum'>" + "</br>" + "<b>Hochschule der Medien</b>" + "</br>"
					+ "<b>Wirtschaftsinformatik und digitale Medien</b></br>" + "Nobelstra&#223e 10</br>"
					+ "70569 Stuttgart</br></br>"
					+ "Kontakt</br>Telefon: 0711 8923-3242</br> E-Mail: <A HREF=\"mailto:info-wi7@hdm-stuttgart.de\" target=\"_top\">info-wi7@hdm-stuttgart.de "
					+ "<br><A HREF=\"http://www.wi.hdm-stuttgart.de/" + "\"_blank\">Website des Studiengangs</A>"
					+ "<br><A HREF=\"https://www.hdm-stuttgart.de/"
					+ "impressum\"TARGET=\"_blank\">Impressum der Hochschule</A>" + "</div>"));

		}

	}

	/**
	 * Diese Klasse erweitert die Klasse DialogBox. Klickt der Nutzer den
	 * Impressumbutton an so erscheint ihm das Impressum in Form einer Dialogbox.
	 * 
	 * @author alina
	 *
	 */

	class ImpressumDialogBox extends DialogBox {

		// Instantiierung der Widgets

		private Button schließenButton = new Button();
		private VerticalPanel verticalPanel = new VerticalPanel();
		private Impressum impressum = new Impressum();

		// Konstruktor
		public ImpressumDialogBox() {

			this.setGlassEnabled(true);
			schließenButton.setHTML("<i class=\"far fa-window-close\"></i>");

			// Hinzufügen des ClickHandlers zum Schließen Button
			schließenButton.addClickHandler(new SchliessenClickHandler());

			// Hinzufügen der Widgets zu den Panels
			verticalPanel.add(impressum);
			verticalPanel.add(schließenButton);
			this.add(verticalPanel);
			// Zentrale Darstellungs der Dialogbox
			this.center();

		}

		/**
		 * Diese Klasse implementiert das Interface ClickHandler und dient zur
		 * Interaktion des Nutzers mit dem Schließen-Button
		 * 
		 * @author alina
		 *
		 */

		class SchliessenClickHandler implements ClickHandler {

			@Override
			public void onClick(ClickEvent event) {

				// Dialogbox wird wieder geschlossen
				ImpressumDialogBox.this.hide();
			}

		}
	}

	/**
	 * Diese Klasse implementiert das Interface ClickHandler und dient zur
	 * Interaktion des Nutzers mit dem Impressum-Button
	 * 
	 * @author alina
	 *
	 */
	class ImpressumClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {

			// neue ImpressumDialogBox wird instantiiert
			impressumDialogBox = new ImpressumDialogBox();

		}

	}

}
