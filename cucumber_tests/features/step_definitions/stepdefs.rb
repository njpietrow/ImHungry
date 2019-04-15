Given("I am on the default search page") do
  visit 'http://localhost:8080/ImHungry/search_page.jsp' # Write code here that turns the phrase above into concrete actions
end
# #Feed me button

#Then("the Feed Me button is on the search page") do
#  expect(page).to have_content("Feed Me!")
#end
#

##Log in button
#Then("the Log In button is on the search page") do
#  expect(page).to have_content("Login")
#end
#

##Sign up button
#Then("the Sign Up button is on the search page") do
#  expect(page).to have_content("Sign Up")
#end
#

## input box
#Then("an input box with default text Enter Food is on the search page") do
#  expect(page).to have_selector('div', :class=> 'input_group')
#end
#

## basic pizza search
#When("I type {string} in the input box") do |string|
#  fill_in("Enter Food", :with => string)
#end
#
#When("I click on the Feed Me button") do
#  click_on("Feed Me!")
#end
#
#Then("I should I should be on the {string} page") do |string|
#   visit 'http://localhost:8080/ImHungry/IHSearch?search_query=pizza&num_results=5&num_miles=1'
#
#end

#Successful login
When("I click on Login button") do
    click_on("Login")
end

When("enter credentials {string} and {string}") do |string1, string2|
    fill_in("uname_login_id", :with => string1)
    fill_in("password_login_id", :with => string2)
    sleep(3)
end

When("click on login") do
    find("#btnLogin").click
    sleep(3)
end

Then("stay on search page") do
     expect(page).to have_content("Feed Me!")
end
#end of login tests

#Successful signup
When("I click on Signup button") do
    click_on("Sign Up")
end

When("enter credentials {string}, {string}, and {string}") do |string1, string2, string3|
    fill_in("uname1", :with => string1)
    fill_in("pwd_register", :with => string2)
    fill_in("pwd_verify_register", :with => string3)
    sleep(3)
end

When("click on signup") do
     find("#btnSignUp").click
end

When("I click on Login button again") do
    click_on("Login")
end

When("enter credentials {string} and {string} again") do |string1, string2|
    fill_in("uname_login_id", :with => string1)
    fill_in("password_login_id", :with => string2)
    sleep(3)
end

When("click on login again") do
    find("#btnLogin").click
    sleep(3)
end

Then("stay on search page again") do
    expect(page).to have_content("Feed Me!")
end
#End of signup tests

## Results page tests
#Given("I am on the results page after pizza search") do
#  visit 'http://localhost:8080/ImHungry/IHSearch?search_query=pizza&num_results=5&num_miles=1'
#end
#
#
#Then("I should see an image collage on the IH Search Page") do
#  expect(page).to have_selector('img', :class=> 'collage_image')
#end
#
#Then("I should see a table of Restaurant Results") do
#  expect(page).to have_content("Restaurant Results")
#end
#
#Then("I should see a table of Recipe Results") do
#  expect(page).to have_content("Recipe Results")
#end
#
#Then("I should see a dropdown bar for list management") do
#  expect(page).to have_selector('button', :id=> 'btnGroupVerticalDrop2')
#end
#
#Then("I should see a button called {string}") do |string|
#  expect(page).to have_selector('button', :id=> 'back_to_search_button')
#end
#
## When("I click on the first restaurant result") do
##   click_on("http://localhost:8080/ImHungry/restaurant_page.jsp?restaurant_id=ChIJLR-E1kjGwoARiXii9Jqc72I&restaurant_name=California Pizza Kitchen")
## end
#
## Then("I should be on the restaurants page") do
##   expect(page).to have_current_path("/ImHungry/restaurant_page.jsp?restaurant_id=ChIJLR-E1kjGwoARiXii9Jqc72I&restaurant_name=California%20Pizza%20Kitchen")
## end
#
#When("I click on the first recipe result") do
#  click_link("http://localhost:8080/ImHungry/recipe_page.jsp?recipe_id=https://www.jocooks.com/recipes/pizza-dough-recipe/")
#end
#
#Then("I should be on the recipe page") do
#  expect(page).to have_current_path("http://localhost:8080/ImHungry/recipe_page.jsp?recipe_id=https://www.jocooks.com/recipes/pizza-dough-recipe/")
#end
#
#Given("I am on the recipe page after clicking on first recipe results") do
#  visit 'http://localhost:8080/ImHungry/recipe_page.jsp?recipe_id=https://www.jocooks.com/recipes/pizza-dough-recipe/'
#end
#
#Then("the Printable Version button is on the recipe page") do
#  expect(page).to have_content("Printable Version")
#end
#
#Then("the Back to Results button is on the recipe page") do
#  expect(page).to have_content("Back to Results")
#end
#
#Then("the Add to List button is on the recipe page") do
#  expect(page).to have_content("Add to List")
#end
#
