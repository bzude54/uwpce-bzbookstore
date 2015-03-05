package edu.uwpce.bzbookstore;

import java.util.List;

public interface BZAddressesManager {
	
	List<BZAddress> getAddresses();
	
	void setAddresses(List<BZAddress> addresses);
	
	BZAddress getAddress(String type);
	
	void addAddress(BZAddress address);

	void updateAddress(BZAddress address);
	
	boolean deleteAddress(String type);
}
