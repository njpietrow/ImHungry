package edu.usc.cs.group8.ImHungry;

public class User {
	public String name; 
	public ListManager mg; 

	public User(String name, ListManager manage)
	{
		this.name = name;
		mg = manage; 
	}
	public User()
	{
		name = null;
		mg = new ListManager(); 
	}
	public ListManager getLists() {
		// TODO Auto-generated method stub
		 return mg; 
	}
	public String getName() {
		return name;
	}
	public void setName(String newname)
	{
		name = newname; 
	}
	public void setLists(ListManager mg)
	{
		this.mg = mg; 
	}
	public Query getLastSearch() {
		if (mg.getQuickAccess().isEmpty()) {
			return null;
		}
		return mg.getQuickAccess().get(mg.getQuickAccess().size()-1);
	}
	
}
