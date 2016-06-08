# metricapp-server
Project for course Software Systems and Internet Services Engineering 2016

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
	


