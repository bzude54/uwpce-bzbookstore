package edu.uwpce.bzbookstore;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BZUsersManagerImpl implements BZUsersManager {
	
    private static final Logger logger = LoggerFactory.getLogger(BZUsersManagerImpl.class);
	
	private static int USERID = 100;
	private Map<Integer, BZUserInfo> users = new ConcurrentHashMap<>();
		

	@Override
	public Map<Integer, BZUserInfo> getUsers() {
		return users;
	}

	@Override
	public void setUsers(Map<Integer, BZUserInfo> users) {
		this.users = users;
		
	}

	@Override
	public BZUserInfo getSingleUserById(int userid) {
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

	@Override
	public BZUserInfo getSingleUserByUsername(String username) {
		BZUserInfo userresult = null;
		for (BZUserInfo user : users.values()) {
			if (user.getUserName().equals(username)){
				userresult = user;
			}
		}
		return userresult;
	}

	@Override
	public void updateUser(BZUserInfo userinfo) {
		BZUserInfo checkuser = this.getSingleUserById(userinfo.getUserId());
		if (checkuser != null){
				users.remove(checkuser.getUserId());
		}
		users.put(userinfo.getUserId(), userinfo);
	}

	@Override
	public boolean deleteUser(int userId) {
		boolean deleteSuccess = false;
		if (this.getSingleUserById(userId) != null) {
			users.remove(userId);
			deleteSuccess = true;
		}
		return deleteSuccess;
	}
	

}
