package edu.usc.cs.group8.ImHungry;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	AppTest.class, 
	IHManageListTest.class, 
	IHSearchTest.class, 
	ListManagerTest.class,
	RecipeGetterTest.class,  
	RestaurantGetterTest.class, 
	RecipeTest.class, 
	RestaurantTest.class 
	})

public class AllTests {

}
