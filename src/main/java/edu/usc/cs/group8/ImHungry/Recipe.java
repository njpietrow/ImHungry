package edu.usc.cs.group8.ImHungry;
import java.util.ArrayList;

public class Recipe extends Result {

	private String prepTime;
	private String cookTime;
	private String imgURL;
	private ArrayList<String> ingredients;
	private ArrayList<String> instructions;
	
	public Recipe(String name, String prepTime, String cookTime, String imgURL, ArrayList<String> ingredients, ArrayList<String> instructions) {
		super.setName(name);
		this.prepTime = prepTime;
		this.cookTime = cookTime;
		this.imgURL = imgURL;
		this.ingredients = ingredients;
		this.instructions = instructions;
	}
	
	public String getPrepTime() {
		return prepTime;
	}

	public void setPrepTime(String prepTime) {
		this.prepTime = prepTime;
	}

	public String getCookTime() {
		return cookTime;
	}

	public void setCookTime(String cookTime) {
		this.cookTime = cookTime;
	}

	public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

	public ArrayList<String> getIngredients() {
		return ingredients;
	}

	public void setIngredients(ArrayList<String> ingredients) {
		this.ingredients = ingredients;
	}

	public ArrayList<String> getInstructions() {
		return instructions;
	}

	public void setInstructions(ArrayList<String> instructions) {
		this.instructions = instructions;
	}
	
	public String toString() {
		return super.getName() + "\n" + prepTime + "\n" + cookTime + "\n" + ingredients + "\n" + instructions + "\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cookTime == null) ? 0 : cookTime.hashCode());
		result = prime * result + ((imgURL == null) ? 0 : imgURL.hashCode());
		result = prime * result + ((ingredients == null) ? 0 : ingredients.hashCode());
		result = prime * result + ((instructions == null) ? 0 : instructions.hashCode());
		result = prime * result + ((prepTime == null) ? 0 : prepTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recipe other = (Recipe) obj;
		if (cookTime == null) {
			if (other.cookTime != null)
				return false;
		} else if (!cookTime.equals(other.cookTime))
			return false;
		if (imgURL == null) {
			if (other.imgURL != null)
				return false;
		} else if (!imgURL.equals(other.imgURL))
			return false;
		if (ingredients == null) {
			if (other.ingredients != null)
				return false;
		} else if (!ingredients.equals(other.ingredients))
			return false;
		if (instructions == null) {
			if (other.instructions != null)
				return false;
		} else if (!instructions.equals(other.instructions))
			return false;
		if (prepTime == null) {
			if (other.prepTime != null)
				return false;
		} else if (!prepTime.equals(other.prepTime))
			return false;
		return true;
	}
	
	
}
