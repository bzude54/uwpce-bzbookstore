package edu.uwpce.bzbookstore;

import java.util.List;

public class BZAddressesManagerImpl implements BZAddressesManager {
	
	List<BZAddress> addresses;

	@Override
	public List<BZAddress> getAddresses() {
		return this.addresses;
	}

	@Override
	public void setAddresses(List<BZAddress> addresses) {
		this.addresses = addresses;
	}

	@Override
	public BZAddress getAddress(String type) {
		BZAddress address = null;
		for (BZAddress checkaddr : addresses) {
			if (checkaddr.getAddressType().equals(type)) {
				address = checkaddr;
			}
		}
		return address;
	}

	@Override
	public void addAddress(BZAddress address) {
		addresses.add(address);
	}

	@Override
	public void updateAddress(BZAddress address) {
		for (BZAddress checkaddr : addresses) {
			if (checkaddr.getAddressType().equals(address.getAddressType())) {
				addresses.remove(checkaddr);
			}
		}
		addresses.add(address);
	}

	@Override
	public boolean deleteAddress(String type) {
		boolean deleteSuccess = false;
		for (BZAddress checkaddr : addresses) {
			if (checkaddr.getAddressType().equals(type)) {
				addresses.remove(checkaddr);
				deleteSuccess = true;
			}
		}
		return deleteSuccess;
	}

}
