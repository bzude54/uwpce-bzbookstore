package edu.uwpce.bzude;

import java.util.Map;

public interface BZUserManager {
	
	public Map<String, BZUserInfo> getUsers();	
	
	public void setUsers(Map<String, BZUserInfo> users);

	public void setSingleUser(String userName, BZUserInfo newuser);

	public BZUserInfo getSingleUser(String userName);

}
