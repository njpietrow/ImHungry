Feature: Grocery List
    Testing on the Grocery Lists page after adding ingredients from the recipe page
Background: Grocery List   
    Given I am on the Grocery Lists page after adding ingredients from the recipe page

Scenario: Remove items 
    When I select an item 
    And I click on Remove item
    Then I should not see the item on the list 