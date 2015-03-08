package edu.uwpce.bzbookstore;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BZAddressesManagerImpl implements BZAddressesManager {
	
	Map<String, BZAddress> addresses;
	
	public BZAddressesManagerImpl() {
		this.addresses = new ConcurrentHashMap<String, BZAddress>();
	}

	@Override
	public Map<String, BZAddress> getAddresses() {
		return this.addresses;
	}

	@Override
	public void setAddresses(Map<String, BZAddress> addresses) {
		this.addresses = addresses;
	}

	@Override
	public BZAddress getAddress(String type) {
		BZAddress address = addresses.get(type);		
		return address;
	}

	@Override
	public void addAddress(BZAddress address) {
		addresses.put(address.getAddressType(), address);
	}

	@Override
	public void updateAddress(BZAddress address) {
		BZAddress checkaddr = addresses.get(address.getAddressType());
		if (checkaddr != null) {
			addresses.remove(address.getAddressType());
		}
		addresses.put(address.getAddressType(), address);
	}

	@Override
	public boolean deleteAddress(String type) {
		boolean deleteSuccess = false;
		BZAddress checkaddr = addresses.get(type);
		if (checkaddr != null) {
			addresses.remove(type);
			deleteSuccess = true;
		}
		return deleteSuccess;
	}

}
