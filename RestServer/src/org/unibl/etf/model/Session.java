package org.unibl.etf.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Session implements Serializable {
	
	private String loginTime;
	private String logoutTime;
	
	public Session() {
		super();
	}

	public Session(String loginTime, String logoutTime) {
		super();
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((logoutTime == null) ? 0 : logoutTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Session other = (Session) obj;
		if (logoutTime == null) {
			if (other.logoutTime != null)
				return false;
		} else if (!logoutTime.equals(other.logoutTime))
			return false;
		return true;
	}

}
