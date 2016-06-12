package metricapp.utility.stateTransitionUtils.metricTransition;

import metricapp.entity.Element;
import metricapp.entity.Entity;
import metricapp.entity.State;
import metricapp.exception.IllegalStateTransitionException;
import metricapp.exception.NotFoundException;
import metricapp.utility.stateTransitionUtils.AbstractStateTransitionFactory;
import metricapp.utility.stateTransitionUtils.StateTransitionCommand;

public class MetricStateTransitionFactory extends AbstractStateTransitionFactory {

	public MetricStateTransitionFactory(Entity entity) {
		super(entity);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Element before, after;
		before = new Element();
		after = new Element();
		before.setState(State.OnUpdate);
		after.setState(State.Pending);
		StateTransitionCommand command ;
		try {
			command = AbstractStateTransitionFactory.getFactory(Entity.Metric).transition(before, after);
			command.execute();
		} catch (IllegalStateTransitionException | NotFoundException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
