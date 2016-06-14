package metricapp.entity.measurementGoal;

import static org.junit.Assert.fail;
import java.lang.reflect.InvocationTargetException;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.EqualsAndHashCode;
import metricapp.entity.AbstractGoal;
import metricapp.entity.external.OrganizationalGoal;
import metricapp.entity.metric.Metric;
import metricapp.entity.question.Question;
import metricapp.entity.stakeholders.Metricator;
import metricapp.entity.stakeholders.Questioner;
import metricapp.utility.RandomGenerator;

@Document
@Data
@EqualsAndHashCode(callSuper=false)
public class MeasurementGoal extends AbstractGoal{
	
	public MeasurementGoal(){
		this.interpretationModel = new InterpretationModel();
		//this.contexts = new ArrayList<Context>();
		//this.assumptions = new ArrayList<Assumption>();
		//this.metrics = new ArrayList<Metric>();
		//this.questioners = new ArrayList<Questioner>();
		//this.metricator = new Metricator();
	}
	
	private String organizationalGoalId;
	
	private String object;
	
	private String purpose;
	
	private String qualityFocus;
	
	private String viewPoint;
	
	private Iterable<String> contexts;
	
	private Iterable<String> assumptions;
	
	private InterpretationModel interpretationModel;
	
	private Iterable<String> metricIdList;
	
	private Iterable<String> questionIdList;
	
	@DBRef
	private Iterable<Metric> metrics;
	
	@DBRef
	private Iterable<Question> questions;
	
    private Metricator metricatorId;

	@DBRef
	private Iterable<Questioner> questioners;
		
	public static MeasurementGoal randomMeasurementGoal(){
		InterpretationModel interpretationModel = new InterpretationModel();
		interpretationModel.setFunctionJavascript(RandomGenerator.randomString());
		interpretationModel.setQueryNoSQL(RandomGenerator.randomString());
		
		OrganizationalGoal organizationalGoal = new OrganizationalGoal();
		try {
			organizationalGoal.randomAttributes();
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | NoSuchMethodException
				| SecurityException | ClassNotFoundException | InstantiationException e) {
			e.printStackTrace();
			fail("random attribute creation error");
		}
		
		MeasurementGoal measurementGoal = new MeasurementGoal();
		try {
			measurementGoal.randomAttributes();
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | NoSuchMethodException
				| SecurityException | ClassNotFoundException | InstantiationException e) {
			e.printStackTrace();
			fail("random attribute creation error");
		}

		measurementGoal.setInterpretationModel(interpretationModel);
		
		return measurementGoal;
	}
	
}
