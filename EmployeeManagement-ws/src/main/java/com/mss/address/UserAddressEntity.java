package com.mss.address;

public class UserAddressEntity {

	private int id;
	
	private String userid;

	private String address;

	private String state;
	
    private	int pincode;

	public UserAddressEntity() {
		super();
	}

	public UserAddressEntity(int id, String userid, String address, String state, int pincode) {
		super();
		this.id = id;
		this.userid = userid;
		this.address = address;
		this.state = state;
		this.pincode = pincode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	
	

	
}
