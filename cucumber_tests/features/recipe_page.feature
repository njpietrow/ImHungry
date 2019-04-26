
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
Scenario: Add to grocery list
	When I click on add to grocery list button
	Then I should stay on the page
	Then I click on grocery list link
	Then I should be on grocery list page
	Then I should see flour in grocery list
	