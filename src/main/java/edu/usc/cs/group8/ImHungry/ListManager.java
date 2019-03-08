package edu.usc.cs.group8.ImHungry;

import java.util.ArrayList;

/*
 * ListManager.java
 * Class that contains the three predefined lists as well as specific functions for accessing them.
 * Author: Kevin Calaway
 * USC ID: 9724507315
 * Email: calaway@usc.edu
 */
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
	
	/*
	 * There is one list manager for the whole website.
	 */
	public static ListManager getInstance() {
		if (singleton == null) {
			singleton = new ListManager();
		}
		return singleton;
	}
	
	public void addToFavorites(Result r) {
		if (!favorites.contains(r)) favorites.add(r);
	}
	
	public void addToToExplore(Result r) {
		if (!toExplore.contains(r)) toExplore.add(r);
	}
	
	public void addToDoNotShow(Result r) {
		if (!doNotShow.contains(r)) doNotShow.add(r);
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
	
	public void removeFromFavorites(int index){
		if (favorites.size() > index)
			favorites.remove(index);
	}
	
	public void removeFromToExplore(int index){
		if (toExplore.size() > index)
			toExplore.remove(index);
	}
	
	public void removeFromDoNotShow(int index){
		if (doNotShow.size() > index)
			doNotShow.remove(index);
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
	
	public void reset() {
		favorites.clear();
		toExplore.clear();
		doNotShow.clear();
	}

}
