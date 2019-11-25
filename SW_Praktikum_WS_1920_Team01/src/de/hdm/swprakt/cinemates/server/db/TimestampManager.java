package de.hdm.swprakt.cinemates.server.db;

import java.sql.Timestamp;
import java.util.Date;

public class TimestampManager {
	
	public Timestamp aktuellerTimestamp() {
		
		Date aktuellerZeitpunkt = new Date();
		return new Timestamp(aktuellerZeitpunkt.getTime());
	}

	
	public Date convertTimestampToDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}
}
