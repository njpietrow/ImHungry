Given("I am on the default search page") do
  visit 'http://localhost:8080/ImHungry/search_page.jsp' # Write code here that turns the phrase above into concrete actions
end
 #Feed me button

Then("the Feed Me button is on the search page") do
  expect(page).to have_content("Feed Me!")
end


#Log in button
Then("the Log In button is on the search page") do
  expect(page).to have_content("Login")
end


#Sign up button
Then("the Sign Up button is on the search page") do
  expect(page).to have_content("Sign Up")
end


# input box
Then("an input box with default text Enter Food is on the search page") do
  expect(page).to have_selector('div', :class=> 'input_group')
end

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

#Successful login
When("I click on Login button") do
    click_on("Login")
end

When("enter credentials {string} and {string}") do |string1, string2|
    fill_in("uname_login_id", :with => string1)
    fill_in("password_login_id", :with => string2)
#    sleep(3)
end

When("click on login") do
    find("#btnLogin").click
#    sleep(3)
end

Then("stay on search page") do
     expect(page).to have_content("Feed Me!")
end
#end of login tests

# basic pizza search
When("I type {string} in the input box") do |string|
  fill_in("Enter Food", :with => string)
end

When("I click on the Feed Me button") do
  click_on("Feed Me!")
end

Then("I should I should be on the {string} page") do |string|
   visit 'http://localhost:8080/ImHungry/IHSearch?search_query=pizza&num_results=5&num_miles=5'

end

# Results page tests
#Given("I am on the results page after pizza search") do
#  visit 'http://localhost:8080/ImHungry/IHSearch?search_query=pizza&num_results=10&num_miles=5'
#  sleep(6)
#end
##
#Then("I should see an image collage on the IH Search Page") do
#  expect(page).to have_selector('img', :class=> 'collage_image')
#end
#
##test result table requirements
#Then("I should see a table of Restaurant Results") do
#  expect(page).to have_content("Restaurant Results")
#end
#
#Then("I should see a table of Recipe Results") do
#  expect(page).to have_content("Recipe Results")
#end
#
#When("Pagination test shows 0 to 5 of 10") do
#    expect(page).to have_content("Showing 1 to 5 of 10")
#end
#
#When("I click on next button") do
#    find("#recipe_results_table_next").click
#end
#
#Then("Pagination text should change to 6 to 10 of 10") do
#    expect(page).to have_content("Showing 6 to 10 of 10")
#end
#
##end of result table requirements
#
#Then(" I should see collage with recent pizza search") do
#    expect(page).to have_selector('div', :class=> 'q_info_container')
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
# When("I click on the first restaurant result") do
#   click_on("http://localhost:8080/ImHungry/restaurant_page.jsp?restaurant_id=ChIJLR-E1kjGwoARiXii9Jqc72I&restaurant_name=California Pizza Kitchen")
# end
#
# Then("I should be on the restaurants page") do
#   expect(page).to have_current_path("/ImHungry/restaurant_page.jsp?restaurant_id=ChIJLR-E1kjGwoARiXii9Jqc72I&restaurant_name=California%20Pizza%20Kitchen")
# end
#
#When("I click on the first recipe result") do
#  click_link("http://localhost:8080/ImHungry/recipe_page.jsp?recipe_id=https://www.jocooks.com/recipes/pizza-dough-recipe/")
#end
#
#Then("I should be on the recipe page") do
#  expect(page).to have_current_path("http://localhost:8080/ImHungry/recipe_page.jsp?recipe_id=https://www.jocooks.com/recipes/pizza-dough-recipe/")
#end
#
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
##Add to grocery list test
#When("I click on add to grocery list button")do
#    find("#grocery_list_button").click
#end
#
#Then("I should stay on the page")do
#    expect(page).to have_content("Grocery List")
#end
#
#Then("I click on grocery list link")do
#   find("#gListLink").click
#end
#
#Then("I should be on grocery list page")do
#     expect(page).to have_current_path("/ImHungry/grocery_list.jsp")
#end
#
#Then("I should see flour in grocery list")do
#    expect(page).to have_content("flour")
#end

















#end of add to grocery test

#Given("I am on the Grocery Lists page after adding ingredients from the recipe page") do
#  pending # Write code here that turns the phrase above into concrete actions
#end
#
#When("I select an item") do
#  pending # Write code here that turns the phrase above into concrete actions
#end
#
#When("I click on Remove item") do
#  pending # Write code here that turns the phrase above into concrete actions
#end
#
#Then("I should not see the item on the list") do
#  pending # Write code here that turns the phrase above into concrete actions
#end
#
#Given("I am on the favorites list manage page after adding a Restaurant from the restaurants page") do
#  pending # Write code here that turns the phrase above into concrete actions
#end
#
#When("I click the dropdown") do
#  pending # Write code here that turns the phrase above into concrete actions
#end
#
#When("I select To Explore") do
#  pending # Write code here that turns the phrase above into concrete actions
#end
#
#When("I Click Move the selected Item") do
#  pending # Write code here that turns the phrase above into concrete actions
#end
#
#When("I click on Manage List") do
#  pending # Write code here that turns the phrase above into concrete actions
#end
#
#Then("I should see the selected Item in To Explore") do
#  pending # Write code here that turns the phrase above into concrete actions
#end
#
#When("I click on Delete the Selected Item") do
#  pending # Write code here that turns the phrase above into concrete actions
#end
#
#Given("I am on the recipe page after clicking on first recipe link on results page") do
#  pending # Write code here that turns the phrase above into concrete actions
#end
#
#When("I click on {string}") do |string|
#  pending # Write code here that turns the phrase above into concrete actions
#end
#
#When("I am logged in") do
#  pending # Write code here that turns the phrase above into concrete actions
#end
#
#Then("I should see the ingredients for the recipe in my grocery list") do
#  pending # Write code here that turns the phrase above into concrete actions
#end
#
#Then("I should be on the results page") do
#  pending # Write code here that turns the phrase above into concrete actions
#end
#
#Given("I am on the recipe page after clicking on first restaurant link on results page") do
#  pending # Write code here that turns the phrase above into concrete actions
#end
#
#When("I click on the drop-down") do
#  pending # Write code here that turns the phrase above into concrete actions
#end
#
#When("click on Favorites") do
#  pending # Write code here that turns the phrase above into concrete actions
#end
#
#When("click on Add to list") do
#  pending # Write code here that turns the phrase above into concrete actions
#end
#
#Then("I should add a Restaurant to my favorites list") do
#  pending # Write code here that turns the phrase above into concrete actions
#end
#
#
