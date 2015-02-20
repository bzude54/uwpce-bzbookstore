package edu.uwpce.bzude;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BZSimpleUserManager implements BZUserManager {
	
    private static final Logger logger = LoggerFactory.getLogger(BZSimpleUserManager.class);
	
	private static int USERID = 100;
	private Map<Integer, BZUserInfo> users;
		

	@Override
	public Map<Integer, BZUserInfo> getUsers() {
		return users;
	}

	@Override
	public void setUsers(Map<Integer, BZUserInfo> users) {
		this.users = users;
		
	}

	@Override
	public BZUserInfo getSingleUser(int userid) {
		BZUserInfo user = null;
		if (users != null && users.containsKey(userid)) {
			user = users.get(userid);
		}
		return user;
	}


	@Override
	public void setSingleUser(BZUserInfo user) {
		int userId = ++USERID;
		user.setUserId(userId);
		users.put(user.getUserId(), user);
		
	}

}
