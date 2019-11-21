/**
 * 
 */
package de.hdm.swprakt.cinemates.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.swprakt.cinemates.shared.KinoBesuchsplanung;

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

}
