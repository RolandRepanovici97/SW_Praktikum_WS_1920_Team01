package de.hdm.swprakt.cinemates.server.db;


public class DateConverter {
	
	public java.sql.Timestamp aktuellerTimestamp() {
		
		java.util.Date aktuellerZeitpunkt = new java.util.Date();
		return new java.sql.Timestamp(aktuellerZeitpunkt.getTime());
	}

	
	public java.util.Date convertTimestampToDate(java.sql.Timestamp timestamp) {
		return new java.util.Date(timestamp.getTime());
	}
	
	public java.util.Date convertDatumUndUhrzeitToDate(java.sql.Date datum, java.sql.Time uhrzeit){
		
		java.util.Date date = new java.util.Date(datum.getTime() + uhrzeit.getTime());
		date.setHours(date.getHours()+1);
		return date;
		
	}
	
	public java.sql.Date convertJavaDateToSQLDate(java.util.Date date) {

		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		return sqlDate;
		
	}
	
	public java.sql.Time convertJavaDateToSQLTime(java.util.Date date){
		
		java.sql.Time sqlTime = new java.sql.Time(date.getTime());
		return sqlTime;
		
	}
}
