package edu.uwpce.bzbookstore;

public interface BZAddress {

	public String getStreetAddress();
	
	void setStreetAddress(String streetAddress);

	String getState();
	
	void setState(String state);
	
	String getZipcode();
	
	void setZipcode(String zipcode);
	
	String getAddressType();
	
	void setAddressType(String addressType);

}
