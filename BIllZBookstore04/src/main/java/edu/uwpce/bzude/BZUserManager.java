package edu.uwpce.bzude;

import java.util.Map;

public interface BZUserManager {
	
	public Map<Integer, BZUserInfo> getUsers();	
	
	public void setUsers(Map<Integer, BZUserInfo> users);

	public void setSingleUser(BZUserInfo newuser);

	public BZUserInfo getSingleUser(int userid);

}
