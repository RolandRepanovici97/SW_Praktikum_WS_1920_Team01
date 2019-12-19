package de.hdm.swprakt.cinemates.client.gui.admin;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Grid;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;

import com.google.gwt.user.client.ui.TextBox;

import de.hdm.swprakt.cinemates.shared.bo.Kino;


public class SpielplanForm extends HorizontalPanel {



	FilmForm ffm = new FilmForm();

	
	 /**
	   * A simple data type that represents a contact.
	   */
	  private static class Spielplan {
	    private final String kinoname;
	    private final String filmname;
	    private final Date spielzeit;

	    public Spielplan(String kinoname, String filmname, Date spielzeit) {
	      this.kinoname = kinoname;
	      this.filmname = filmname;
	      this.spielzeit = spielzeit;
	    }
	  
	  
	    /**
	     * The list of data to display.
	     */
	    private static final List<Spielplan> SPIELPLAENE = Arrays.asList(
	    		new Spielplan("Ömer","Hallo", new Date(80,4,12)));
	       
	       
	  }

	
	public void onLoad() {
		
		super.onLoad();
		
		// Create a CellTable.
	    CellTable<Spielplan> table = new CellTable<Spielplan>();
	//   table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

	    
	    
	    
	    

		
		//	    // Add a text column to show the name.
	    TextColumn<Spielplan> kinonameColumn = new TextColumn<Spielplan>() {
	      @Override
	      public String getValue(Spielplan object) {
	        return object.kinoname;
	      }
	    };
	    table.addColumn(kinonameColumn, "Kino");
		
	    
	    
	    // Add a text column to show the name.
	    TextColumn<Spielplan> filmnameColumn = new TextColumn<Spielplan>() {
	      @Override
	      public String getValue(Spielplan object) {
	        return object.filmname;
	      }
	    };
	    table.addColumn(filmnameColumn, "Film");
	    
	    
	    // Add a date column to show the Spielzeit
	    DateCell dateCell = new DateCell();
	    Column<Spielplan, Date> dateColumn = new Column<Spielplan, Date>(dateCell) {
	      @Override
	      public Date getValue(Spielplan object) {
	        return object.spielzeit;
	      }
	    };
	    table.addColumn(dateColumn, "Zeitpunkt");

	  
	    
	//    table.setWidth("60%", true);

		/*
		 * Festlegen der Gesamtanzahl der Zeilen
		 */

	//    table.setRowCount(SPIELPLAENE.size(), true);

		/*
		 * Daten in die Tabelle hinzufügen
		 */

	//    table.setRowData(0, SPIELPLAENE);

		
	//	this.add(table);
		
		
		

	}
}
