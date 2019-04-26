
Feature: Results ImHungry
	Testing on the results page after pizza search
Background: 
	Given I am on the results page after pizza search 

Scenario: Has Collage
	Then I should see an image collage on the IH Search Page 

Scenario: Restaurant Results
	Then I should see a table of Restaurant Results
Scenario: Recipe Results
	Then I should see a table of Recipe Results

Scenario: Pagination works
	When Pagination test shows 0 to 5 of 10
	And I click on next button
	Then Pagination text should change to 6 to 10 of 10
	
Scenario: Has recent query collage
	Then I should see collage with recent pizza search

Scenario: Dropdown Bar
	Then I should see a dropdown bar for list management
	
Scenario: Manage List
	Then I should see a button called "Manage List"
	
Scenario: Back to Search
	Then I should see a button called "Back to Search" 

Scenario: Click on Recipe
	When I click on the first recipe result
	Then I should be on the recipe page 
