package edu.uwpce.bzbookstore;

public class BZAddressImpl implements BZAddress {
	

	private String streetAddress;
	private String city;
	private String state;
	private String zipcode;
	private String addressType;
	
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddressType() {
		return addressType;
	}
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	@Override
	public String getCity() {
		return this.city;
	}
	@Override
	public void setCity(String city) {
		this.city = city;
	}

}
