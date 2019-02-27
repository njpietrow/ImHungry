package edu.usc.cs.group8.ImHungry;

public class Restaurant extends Result {

	private int driveTime;
	private double distance;
	private String websiteURL;
	private String address;
	private String phoneNum;
	private int priceRange;
	
	public Restaurant(String name, int driveTime, double distance, String websiteURL, String address, String phoneNum, int priceRange) {
		super.setName(name);
		this.driveTime = driveTime;
		this.distance = distance;
		this.websiteURL = websiteURL;
		this.address = address;
		this.phoneNum = phoneNum;
		this.priceRange = priceRange;
	}
	
	public Restaurant(String name, double distance, String address, int priceRange) {
		super.setName(name);
		driveTime = -1;
		this.distance = distance;
		websiteURL = null;
		this.address = address;
		phoneNum = null;
		this.priceRange = priceRange;
	}

	public int getDriveTime() {
		return driveTime;
	}

	public void setDriveTime(int driveTime) {
		this.driveTime = driveTime;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public String getWebsiteURL() {
		return websiteURL;
	}

	public void setWebsiteURL(String websiteURL) {
		this.websiteURL = websiteURL;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public int getPriceRange() {
		return priceRange;
	}

	public void setPriceRange(int priceRange) {
		this.priceRange = priceRange;
	}
	
	
}
