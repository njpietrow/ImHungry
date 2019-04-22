Feature: List Management
	Testing on the favorites list manage page after adding a Restaurant from the restaurants page
Background: 
	Given I am on the favorites list manage page after adding a Restaurant from the restaurants page

Scenario: Move item
    When I select an item
    And I click the dropdown
    And I select To Explore 
    And I Click Move the selected Item 
    And I click the dropdown
    And I select To Explore
    And I click on Manage List 
    Then I should see the selected Item in To Explore 

Scenario: Delete Item
    When I select an item
    And I click on Delete the Selected Item
    Then I should not see the item on the list 

