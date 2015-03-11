package edu.uwpce.bzbookstore;

import java.util.List;
import java.util.Map;

public interface BZAddressesService {
	
	List<BZAddress> getAddresses(int userid);
	
	void setAddresses(int userid, List<BZAddress> addresses);
	
	BZAddress getAddress(int userid, String type);
	
	void addAddress(int userid, BZAddress address);

	void updateAddress(int userid, BZAddress address);
	
	boolean deleteAddresses(int userid);
	
	boolean deleteAddress(int userid, String type);

}
