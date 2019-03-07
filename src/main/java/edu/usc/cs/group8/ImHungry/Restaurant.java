package edu.usc.cs.group8.ImHungry;

import java.io.Serializable;

public class Restaurant extends Result implements Serializable{
	/*
	 * Recipe.java
	 * The container for information parsed by RestaurantGetter to be passed into the front end.
	 * Author: Nick Pietrow
	 * USC ID: 5425773820
	 * Email: pietrow@usc.edu
	 */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * This is the raw information that will be parsed from the Json from a Google Nearby place search
	 * (see RecipeGetter)
	 */
	private String id;
	
	/*
	 * These will be calculated as from a Google Place details search
	 */
	private double rating;
	private int priceRange;
	private String websiteURL;
	private String address;
	private String phoneNum;
	/*
	 * This will be calculated as from a Google Distance Matrix search
	 */
	private int driveTime;
	private String mapURL;
	

	
	public Restaurant(String name, int driveTime, String websiteURL, String address, String phoneNum, int priceRange, double rating, String id) {
		super.setName(name);
		this.driveTime = driveTime;
		this.websiteURL = websiteURL;
		this.address = address;
		this.phoneNum = phoneNum;
		this.priceRange = priceRange;
		this.rating =rating;
		this.id = id;
	}
	
	public Restaurant(String name, String id) {
		super.setName(name);
		this.id= id;
		this.driveTime = -1;
		this.websiteURL = "";
		this.address = "";
		this.phoneNum = "";
		this.priceRange = -1;
		this.rating = 0.0;
	}
	

	public Restaurant() {
		// TODO Auto-generated constructor stub
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getMapURL() {
		return mapURL;
	}

	public void setMapURL(String mapURL) {
		this.mapURL = mapURL;
	}
	
	
}
