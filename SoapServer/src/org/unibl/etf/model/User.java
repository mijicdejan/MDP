package org.unibl.etf.model;

import java.io.Serializable;
import java.util.HashMap;

@SuppressWarnings("serial")
public class User implements Serializable {
	
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private boolean blocked;
	private Session[] sessions;
	
	public User() {
		super();
	}

	public User(String firstName, String lastName, String username, String password,
			boolean blocked) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.blocked = blocked;
	}

	public User(String firstName, String lastName, String username, String password,
			boolean blocked, Session[] sessions) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.blocked = blocked;
		this.sessions = sessions;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public Session[] getSessions() {
		return sessions;
	}

	public void setSessions(Session[] sessions) {
		this.sessions = sessions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	public HashMap<String, String> toMap(){
		HashMap<String, String> obj = new HashMap<>();
		obj.put("username", username);
		obj.put("password", password);
		obj.put("firstName", firstName);
		obj.put("lastName", lastName);
		obj.put("blocked", blocked ? "true" : "false");
		return obj;
	}

}
