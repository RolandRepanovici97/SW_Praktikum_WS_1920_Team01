package de.hdm.swprakt.cinemates.shared.bo;

import java.io.Serializable;

public class Nutzer extends OwnedBusinessObject {

	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;
	  private String nutzername;
	  
	  //Folgende Attribute sind diejenigen, die vom Google Login Service bereitgestellt werden
	  private boolean loggedIn = false;
	  private String loginUrl;
	  private String logoutUrl;
	  private String nickname;
	  
	  public Nutzer() {
		  super();
	  }
	  
	  
	  public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getNutzername() {
		return nutzername;
	}


	public void setNutzername(String nutzername) {
		this.nutzername = nutzername;
	}


	public boolean isLoggedIn() {
	    return loggedIn;
	  }

	  public void setLoggedIn(boolean loggedIn) {
	    this.loggedIn = loggedIn;
	  }

	  public String getLoginUrl() {
	    return loginUrl;
	  }

	  public void setLoginUrl(String loginUrl) {
	    this.loginUrl = loginUrl;
	  }

	  public String getLogoutUrl() {
	    return logoutUrl;
	  }

	  public void setLogoutUrl(String logoutUrl) {
	    this.logoutUrl = logoutUrl;
	  }


	  public String getNickname() {
	    return nickname;
	  }

	  public void setNickname(String nickname) {
	    this.nickname = nickname;
	  }
	  
	  
	  public String toString() {
			/*
			 * Wir geben den Klassennamen gefolgt von der ID des Objekts zurück.
			 */
			return super.toString() + "; Email: " + this.email + "; Nutzername: " + this.nutzername;
		}
	  
	  
}
