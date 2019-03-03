package edu.usc.cs.group8.ImHungry;
import java.util.ArrayList;

public class Recipe extends Result {

	private String prepTimeText;
	private String cookTimeText;
	private String imgURL;
	private ArrayList<String> ingredients;
	private ArrayList<String> instructions;
	
	private int prepTimeInt;
	private int cookTimeInt;
	
	public Recipe(String name, String prepTime, String cookTime, String imgURL, ArrayList<String> ingredients, ArrayList<String> instructions) {
		super.setName(name);
		this.prepTimeText = prepTime;
		this.cookTimeText = cookTime;
		this.imgURL = imgURL;
		this.ingredients = ingredients;
		this.instructions = instructions;
		prepTimeInt = parsePrepTime(prepTimeText);
		cookTimeInt = parsePrepTime(cookTimeText);
	}
	
	private int parsePrepTime(String text) {
		int parsedValue = 0;
		try {
			parsedValue = Integer.parseInt(text);
		} catch (Exception e) {
			try {
				parsedValue = 0;
				for (int i = 0; i < text.length(); i++) {
					if (text.charAt(i) < '0' || text.charAt(i) > '9') {
						continue;
					}
					int j = i;
					while (text.charAt(j) >= '0' && text.charAt(j) <= '9') {
						j++;
					}
					int factor = -1;
					switch (text.charAt(j)) {
						case 'D':
							factor = 1440;
							break;
						case 'H':
							factor = 60;
							break;
						case 'M':
							factor = 1;
							break;
						default:
							factor = 0;
							break;
					}
					parsedValue += Integer.parseInt(text.substring(i, j)) * factor;
					i = j;
				}
			} catch (Exception f) {
				parsedValue = 0;
			}
		}
		return parsedValue;
	}

	public int getPrepTime() {
		return prepTimeInt;
	}

	public void setPrepTime(String prepTime) {
		this.prepTimeText = prepTime;
		prepTimeInt = parsePrepTime(prepTimeText);
	}

	public int getCookTime() {
		return cookTimeInt;
	}

	public void setCookTime(String cookTime) {
		this.cookTimeText = cookTime;
		cookTimeInt = parsePrepTime(cookTimeText);
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
		return super.getName() + "\n" + prepTimeInt + "\n" + cookTimeInt + "\n" + ingredients + "\n" + instructions + "\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cookTimeInt;
		result = prime * result + ((imgURL == null) ? 0 : imgURL.hashCode());
		result = prime * result + ((ingredients == null) ? 0 : ingredients.hashCode());
		result = prime * result + ((instructions == null) ? 0 : instructions.hashCode());
		result = prime * result + prepTimeInt;
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
		if (cookTimeInt != other.cookTimeInt)
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
		if (cookTimeInt != other.cookTimeInt)
			return false;
		return true;
	}
	
	
}
