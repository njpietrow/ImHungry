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

Then("I should I should see {int} restaurant results for {string} on the results page") do |int, string|
  pending # Write code here that turns the phrase above into concrete actions
end

Then("I should see {int} recipe results for {string} on the results page") do |int, string|
  pending # Write code here that turns the phrase above into concrete actions
end


