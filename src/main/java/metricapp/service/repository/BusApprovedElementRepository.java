package metricapp.service.repository;

import metricapp.service.spec.repository.BusApprovedElementInterface;

/**
 * This repository offers method to send and grab our Elements to and from BUS. 
 * <p>
 * Supported items:
 * <ul>
 * <li>Metric
 * <li>Question
 * <li>MeasurementGoal
 * </ul>
 * 
 * Bus Storage contains only Approved Elements, and only Approved Elements can be send to Bus.
 *
 */
public class BusApprovedElementRepository implements BusApprovedElementInterface{
	
	
}
