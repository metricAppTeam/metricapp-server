package restControllerTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ MeasurementGoalRestControllerTest.class, MetricRestControllerTest.class,
		QuestionRestControllerTest.class })

public class AllTests {

}
