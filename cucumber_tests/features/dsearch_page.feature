Feature: Search ImHungry
	Testing on the default search page 

Background: 
	Given I am on the default search page 

Scenario: "Feed Me" Button
	Then the Feed Me button is on the search page

Scenario: "Log In" Button
	Then the Log In button is on the search page 

Scenario: "Sign Up" Button
	Then the Sign Up button is on the search page 

Scenario: Input box
	Then an input box with default text Enter Food is on the search page 

Scenario: Basic Pizza Search 
	When I type "pizza" in the input box
	And I click on the Feed Me button 
	Then I should I should be on the "IH Search" page 
	
	Scenario: Success sign up
 	When I click on Signup button
 	And enter credentials "TestUs", "testpa", and "testpa"
 	And click on signup
	And I click on Login button again
	And enter credentials "TestUs" and "testpa" again
	And click on login again
	Then stay on search page again
	
Scenario: Success login
	When I click on Login button
	And enter credentials "GJHalfond" and "Scrum"
	And click on login
	Then stay on search page
	
Scenario: Successful collage
 When I login "GJHalfond" and "Scrum"
 And I type "pizza" in the input box again
 Then I click on the Feed Me button again
 Then I should see collage with recent pizza search
 
	