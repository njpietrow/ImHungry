Feature: Find login page

Given(/^I am on the Default Page$/) do
  visit 'http://localhost:8080'
end

Then("I should see Feed me button") do
  expect(page).to have_content("Feed Me!")
end

Then("I should see query box with correct prompt text") do
  expect(page).to have_selector("search_query")
end

Then("amount box has default value 5") do
  expect(page).to have_selector("ex2[value='5']")
end

Then("I should see text {string}") do |string|
  expect(page).to have_content string
end

When("I enter {string} in the search box") do |string|
  fill_in 'search_query', :with => string
end

When("I enter {string} in the number box") do |string|
  fill_in 'ex2', :with => string
end

When("press search") do
  click_on("Submit")
end

When("click Ok") do
  click_on("Ok")
end

Then("I should be on {string} page") do |string|
  expect(page).to have_current_path("/" + string)
end

Then("I should see {int} results") do |int|
  expect(page).to have_content("Distance", count:int)
end
