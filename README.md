# metricapp-server
Project for course Software Systems and Internet Services Engineering 2016

[Server Code Documentation](http://metricappteam.github.io/metricapp-server/javadocs/)

[Github's site](http://metricappteam.github.io/metricapp-server/)

[Deployed Server](http://qips.sweng.uniroma2.it/metricapp-server/)

## Requirements
This is a Maven Project
	
	you need Maven to resolve dependencies and run it. 

	JDK 8+ installed as default

## Build

	/$PROJECT_PATH/mvn install
	/$PROJECT_PATH/mvn test
	/$PROJECT_PATH/mvn package (it includes install and tests)

## Import in Eclipse
	
	file->import...
	existing Maven Project
	select $PROJECT_PATH
	project->maven->install

## Common difficulties
	
	Maven Build can fail due to Java JDK.
	Please ensure you have installed JAVA 1.8 JDK in your machine, and it is setted by default.

## Start

	with Embedded Tomcat

		after you have compiled the project and resolved dependencies with Maven, you have to execute /main/metricapp-server/BootApplication

	with External Tomcat

		project is compiled in .War file. 
		Cause of /main/metricapp-server/ServletInitializer it can be automatically executed and imported on a Tomcat Server.

# Available Rest Interfaces
	
	/metric
	/question
	/measurementGoal
	/user
	
# HTTP Error Codes for REST Metric
| Method      | #           | Code  | e.g. |  
| --------------- |-----| -----|:--------------------------------------------------|
| GET   | 400 | BAD_REQUEST | id cannot be null |  
| GET	 | 500 | INTERNAL_SERVER_ERROR | 	generic error | 
| GET	 | 404 | NOT_FOUND	 | metric not found | 
| DELETE | 	400 | BAD_REQUEST | 	not valid id | 
| DELETE | 	403 | FORBIDDEN | 	a metric must be in state of Suspended before you can delete it | 
| DELETE | 	500 | INTERNAL_SERVER_ERROR | 	generic error | 
| PUT | 	400 | BAD_REQUEST | 	 id null, metricator null | 
| PUT | 	404 | NOT_FOUND | 	metric not found | 
| PUT | 	409 | CONFLICT | 	version number of your submitted object is not correct | 
| PUT | 	403 | FORBIDDEN | 	illegal state transition | 
| PUT | 	500 | INTERNAL_SERVER_ERROR | 	generic error | 
| POST | 	500 | INTERNAL_SERVER_ERROR | 	generic error | 
| POST | 	400 | BAD_REQUEST	 | metric must be in state of Created, metric has no id, invalid fields | 

# HTTP Error Codes for REST Measurement Goal
| Method |  #  | Code |  e.g. | 
| --------------- |-----| -----|:--------------------------------------------------|
| GET | 	400 | 	BAD_REQUEST | 	Id cannot be null | 
| GET | 	500 | 	INTERNAL_SERVER_ERROR | 	Generic error | 
| GET | 	404 | 	NOT_FOUND | 	Measurement Goal not found | 
| DELETE | 	400 | 	BAD_REQUEST | 	Invalid field id | 
| DELETE | 	403 | 	FORBIDDEN | 	A Measurement Goal must be in state of Suspended before you can delete it | 
| DELETE | 	500 | 	INTERNAL_SERVER_ERROR | 	Generic error | 
| PUT | 	400 | 	BAD_REQUEST | 	Id null, metricator null | 
| PUT | 	404 | 	NOT_FOUND | 	Measurement Goal not found | 
| PUT | 	403 | 	FORBIDDEN | 	Illegal state transition | 
| PUT | 	500 | 	INTERNAL_SERVER_ERROR | 	Generic error | 
| POST | 	500 | 	INTERNAL_SERVER_ERROR | 	Generic error | 
| POST | 	400 | 	BAD_REQUEST | 	A Measurement Goal must be in state of Created, Measurement Goal has no id, invalid fields | 
    
# HTTP Error Codes for REST External Elements
| Method |  #  | Code |  e.g. | 
| --------------- |-----| -----|:--------------------------------------------------|
| GET | 	400 | 	BAD_REQUEST | 	Id cannot be null | 
| GET | 	500 | 	INTERNAL_SERVER_ERROR | 	Error in bus interrogation | 
| GET | 	404 | 	NOT_FOUND | 	entity not found | 


