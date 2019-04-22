
Feature: Recipe ImHungry
	Testing on the recipe page after clicking on first recipe link on results page
Background: 
	Given I am on the recipe page after clicking on first recipe link on results page

Scenario: Printable Version
	Then I should see a button called "Printable Version"
Scenario: Back to Results
	Then I should see a button called "Back to Results"
Scenario: Add to List
	Then I should see a button called "Add to List" 

Scenario: Add to Grocery List button 
	Then I should see a button called "Add to Grocery List"

Scenario: Adding to Grocery List 
	When I click on "Add to Grocery List"
	And I am logged in 
	Then I should see the ingredients for the recipe in my grocery list 

Scenario: Back to Results 
	When I click on "Back to Results"
	Then I should be on the results page 