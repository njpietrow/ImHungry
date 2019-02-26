package classes;
import java.util.ArrayList;

public class Recipe extends Result {

	private int prepTime;
	private int cookTime;
	private String imgURL;
	private ArrayList<String> ingredients;
	private ArrayList<String> instructions;
	
	public Recipe(String name, int prepTime, int cookTime, String imgURL, ArrayList<String> ingredients, ArrayList<String> instructions) {
		super.setName(name);
		this.prepTime = prepTime;
		this.cookTime = cookTime;
		this.imgURL = imgURL;
		this.ingredients = ingredients;
		this.instructions = instructions;
	}
	
	public int getPrepTime() {
		return prepTime;
	}

	public void setPrepTime(int prepTime) {
		this.prepTime = prepTime;
	}

	public int getCookTime() {
		return cookTime;
	}

	public void setCookTime(int cookTime) {
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
}
