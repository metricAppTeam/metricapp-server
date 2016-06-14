package mappingTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DTOToMeasurementGoalTest.class, DTOToMetricTest.class, MeasurementGoalToDTOTest.class,
		MetricToDTOTest.class, QuestionDTOToQuestion.class, QuestionToQuestionDTO.class })

public class AllTests {

}
