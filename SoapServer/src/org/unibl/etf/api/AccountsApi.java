package org.unibl.etf.api;

import org.unibl.etf.model.User;
import org.unibl.etf.service.AccountsService;

public class AccountsApi {
	
	public User createAccount(String firstName, String lastName) {
		return AccountsService.createAccount(firstName, lastName);
	}
	
	public boolean blockUnblockAccount(User user) {
		return AccountsService.blockUnblockAccount(user);
	}

}
