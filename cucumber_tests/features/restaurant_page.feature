Feature: Restaurant ImHungry
	Testing on the recipe page after clicking on first restaurant link on results page
Background: 
	Given I am on the recipe page after clicking on first restaurant link on results page

Scenario: Printable Version
	Then I should see a button called "Printable Version"
Scenario: Back to Results
	Then I should see a button called "Back to Results"
Scenario: Add to List
	Then I should see a button called "Add to List" 

Scenario: Manage Lists
    When I click on the drop-down 
    And click on Favorites
    And click on Add to list 
    And I am logged in 
    Then I should add a Restaurant to my favorites list 

Scenario: Back to Results 
	When I click on "Back to Results"
	Then I should be on the results page 