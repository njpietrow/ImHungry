
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
