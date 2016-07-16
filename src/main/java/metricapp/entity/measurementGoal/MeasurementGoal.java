package metricapp.entity.measurementGoal;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import metricapp.entity.Element;
import metricapp.entity.external.PointerBus;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import metricapp.entity.external.OrganizationalGoal;
import metricapp.utility.RandomGenerator;

@Document
@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
public class MeasurementGoal extends Element {
	
	public MeasurementGoal(){
		this.interpretationModel = new InterpretationModel();
		this.assumptions = new ArrayList<PointerBus>();
		this.contextFactors= new ArrayList<PointerBus>();
		this.questionersId= new ArrayList<String>();
		this.questions= new ArrayList<PointerBus>();
		this.metrics= new ArrayList<PointerBus>();
		
		//this.contexts = new ArrayList<Context>();
		//this.assumptions = new ArrayList<Assumption>();
		//this.metrics = new ArrayList<Metric>();
		//this.questioners = new ArrayList<Questioner>();
		//this.metricator = new Metricator();
	}
	
	private PointerBus organizationalGoalId;
	
	private String object;
	
	private String purpose;
	
	private String qualityFocus;
	
	private String viewPoint;
	
	private List<PointerBus> contextFactors;
	
	private List<PointerBus> assumptions;
	
	private InterpretationModel interpretationModel;
	
	private List<PointerBus> metrics;
	
	private List<PointerBus> questions;
	
    private String metricatorId;

	private List<String> questionersId;
		
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
		}
		
		MeasurementGoal measurementGoal = new MeasurementGoal();
		try {
			measurementGoal.randomAttributes();
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | NoSuchMethodException
				| SecurityException | ClassNotFoundException | InstantiationException e) {
			e.printStackTrace();
		}

		measurementGoal.setInterpretationModel(interpretationModel);
		
		return measurementGoal;
	}
	
}
