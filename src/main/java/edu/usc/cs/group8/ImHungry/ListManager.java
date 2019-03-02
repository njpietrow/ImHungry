package edu.usc.cs.group8.ImHungry;

import java.util.ArrayList;

public class ListManager {
	
	private ArrayList<Result> favorites;
	private ArrayList<Result> toExplore;
	private ArrayList<Result> doNotShow;
	
	private static ListManager singleton;
	
	private ListManager() {
		favorites = new ArrayList<Result>();
		toExplore = new ArrayList<Result>();
		doNotShow = new ArrayList<Result>();
	}
	
	public static ListManager getInstance() {
		if (singleton == null) {
			singleton = new ListManager();
		}
		return singleton;
	}
	
	public void addToFavorites(Result r) {
		favorites.add(r);
	}
	
	public void addToToExplore(Result r) {
		toExplore.add(r);
	}
	
	public void addToDoNotShow(Result r) {
		doNotShow.add(r);
	}
	
	public void removeFromFavorites(Result r) {
		if (favoritesContains(r))
			favorites.remove(r);
	}
	
	public void removeFromToExplore(Result r) {
		if (toExploreContains(r)){
			toExplore.remove(r);
		}
	}
	
	public void removeFromDoNotShow(Result r) {
		if (doNotShowContains(r)) {
			doNotShow.remove(r);
		}
	}
	
	public boolean favoritesContains(Result r) {
		return favorites.contains(r);
	}
	
	public boolean toExploreContains(Result r) {
		return toExplore.contains(r);
	}
	
	public boolean doNotShowContains(Result r) {
		return doNotShow.contains(r);
	}
	
	public ArrayList<Result> getFavorites(){
		return favorites;
	}
	
	public ArrayList<Result> getToExplore(){
		return toExplore;
	}
	
	public ArrayList<Result> getDoNotShow(){
		return doNotShow;
	}

}
