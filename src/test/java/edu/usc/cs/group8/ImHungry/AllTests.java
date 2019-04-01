package edu.usc.cs.group8.ImHungry;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AppTest.class, DataPersistenceTest.class, IHManageList.class, IHSearchTest.class,
		ListManagerTest.class, QuickAccessTest.class, RecipeGetterTest.class, RecipeTest.class,
		RestaurantGetterTest.class, RestaurantTest.class, UserLoginTest.class })

public class AllTests {

}
