package edu.uwpce.bzbookstore;

import java.util.List;
import java.util.Map;

public interface BZAddressesManager {
	
	Map<String, BZAddress> getAddresses();
	
	void setAddresses(Map<String, BZAddress> addresses);
	
	BZAddress getAddress(String type);
	
	void addAddress(BZAddress address);

	void updateAddress(BZAddress address);
	
	boolean deleteAddress(String type);
}
