package edu.usc.cs.group8.ImHungry;

public class Restaurant extends Result {

	private int driveTime;
	private double rating;
	private int priceRange;
	private String websiteURL;
	private String address;
	private String phoneNum;
	private String id;

	
	public Restaurant(String name, int driveTime, String websiteURL, String address, String phoneNum, int priceRange) {
		super.setName(name);
		this.driveTime = driveTime;
		this.websiteURL = websiteURL;
		this.address = address;
		this.phoneNum = phoneNum;
		this.priceRange = priceRange;
	}
	
	public Restaurant(String name, String address, int priceRange) {
		super.setName(name);
		driveTime = -1;
		websiteURL = null;
		this.address = address;
		phoneNum = null;
		this.priceRange = priceRange;
	}
	public Restaurant(String name, String id) {
		super.setName(name);
		this.id= id;
	}
	

	public int getDriveTime() {
		return driveTime;
	}

	public void setDriveTime(int driveTime) {
		this.driveTime = driveTime;
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

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Restaurant other = (Restaurant) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (driveTime != other.driveTime)
			return false;
		if (phoneNum == null) {
			if (other.phoneNum != null)
				return false;
		} else if (!phoneNum.equals(other.phoneNum))
			return false;
		if (priceRange != other.priceRange)
			return false;
		if (websiteURL == null) {
			if (other.websiteURL != null)
				return false;
		} else if (!websiteURL.equals(other.websiteURL))
			return false;
		return true;
	}
	
	
}
