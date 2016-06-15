package metricapp.entity.measurementGoal;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import metricapp.entity.Element;
import metricapp.entity.external.PointerBus;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.EqualsAndHashCode;
import metricapp.entity.AbstractGoal;
import metricapp.entity.external.OrganizationalGoal;
import metricapp.utility.RandomGenerator;

@Document
@Data
@EqualsAndHashCode(callSuper=true)
public class MeasurementGoal extends Element {
	
	public MeasurementGoal(){
		this.interpretationModel = new InterpretationModel();
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
	
	private Iterable<PointerBus> contexts;
	
	private Iterable<PointerBus> assumptions;
	
	private InterpretationModel interpretationModel;
	
	private Iterable<PointerBus> metrics;
	
	private Iterable<PointerBus> questions;
	
    private String metricatorId;

	private ArrayList<String> questionersId;
		
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
