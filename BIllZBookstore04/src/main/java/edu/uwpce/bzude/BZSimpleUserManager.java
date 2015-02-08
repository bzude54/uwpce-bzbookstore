package edu.uwpce.bzude;

import java.util.Map;

public class BZSimpleUserManager implements BZUserManager {
	
	private Map<String, BZUserInfo> users;
		

	@Override
	public Map<String, BZUserInfo> getUsers() {
		return users;
	}

	@Override
	public void setUsers(Map<String, BZUserInfo> users) {
		this.users = users;
		
	}

	@Override
	public BZUserInfo getSingleUser(String username) {
		BZUserInfo user = null;
		if (users != null && users.containsKey(username)) {
			user = users.get(username);
		}
		return user;
	}


	@Override
	public void setSingleUser(String key, BZUserInfo user) {
		users.put(key, user);
		
	}

}
