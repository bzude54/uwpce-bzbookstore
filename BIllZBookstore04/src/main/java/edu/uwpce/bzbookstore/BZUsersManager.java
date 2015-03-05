package edu.uwpce.bzbookstore;

import java.util.Map;

public interface BZUsersManager {
	
	Map<Integer, BZUserInfo> getUsers();	
	
	void setUsers(Map<Integer, BZUserInfo> users);

	void setSingleUser(BZUserInfo newuser);

	BZUserInfo getSingleUserByUsername(String username);

	BZUserInfo getSingleUserById(int userid);

	void updateUser(BZUserInfo userinfo);
	
	boolean deleteUser(int userId);

}
