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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		long temp;
		temp = Double.doubleToLongBits(distance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + driveTime;
		result = prime * result + ((phoneNum == null) ? 0 : phoneNum.hashCode());
		result = prime * result + priceRange;
		result = prime * result + ((websiteURL == null) ? 0 : websiteURL.hashCode());
		return result;
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
		if (Double.doubleToLongBits(distance) != Double.doubleToLongBits(other.distance))
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
