Given("I am on the default search page") do
  visit 'http://localhost:8080/ImHungry/search_page.jsp' # Write code here that turns the phrase above into concrete actions
end

Then("the Feed Me button is on the search page") do 
  expect(page).to have_content("Feed Me!")  
end

Then("the Log In button is on the search page") do
  expect(page).to have_content("Login") 
end

Then("the Sign Up button is on the search page") do 
  expect(page).to have_content("Sign Up") 
end

Then("an input box with default text Enter Food is on the search page") do
  expect(page).to have_selector('div', :class=> 'input_group') 
end

When("I type {string} in the input box") do |string|
  fill_in("Enter Food", :with => string) 
end

When("I click on the Feed Me button") do
  click_on("Feed Me!") 
end


Then("I should I should be on the {string} page") do |string|
   visit 'http://localhost:8080/ImHungry/IHSearch?search_query=pizza&num_results=5&num_miles=1' 
   
end

Given("I am on the results page after pizza search") do
  visit 'http://localhost:8080/ImHungry/IHSearch?search_query=pizza&num_results=5&num_miles=1' 
end


Then("I should see an image collage on the IH Search Page") do
  expect(page).to have_selector('img', :class=> 'collage_image') 
end

Then("I should see a table of Restaurant Results") do
  expect(page).to have_content("Restaurant Results") 
end

Then("I should see a table of Recipe Results") do
  expect(page).to have_content("Recipe Results") 
end

Then("I should see a dropdown bar for list management") do
  expect(page).to have_selector('button', :id=> 'btnGroupVerticalDrop2') 
end

Then("I should see a button called {string}") do |string|
  expect(page).to have_selector('button', :id=> 'back_to_search_button') 
end

When("I click on the first restaurant result") do
  click_on("California Pizza Kitchen")
end

Then("I should be on the restaurants page") do
  expect(page).to have_current_path("/ImHungry/restaurant_page.jsp?restaurant_id=ChIJLR-E1kjGwoARiXii9Jqc72I&restaurant_name=California%20Pizza%20Kitchen")
end

When("I click on the first recipe result") do
  click_on("Pizza Dough Recipe")
end

Then("I should be on the recipe page") do
  expect(page).to have_current_path("http://localhost:8080/ImHungry/recipe_page.jsp?recipe_id=https://www.jocooks.com/recipes/pizza-dough-recipe/")
end

