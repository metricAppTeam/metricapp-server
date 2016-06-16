/**
 * This package includes all configuration classes used by Spring to offer us automated services.
 * There are two mongo configuration, one is for local use of Mongo, useful for debug and release version when this application will run on a virtual machine that has a mongoDb installed instance;
 * the other one is to use our MongoDb located on mLab service provider. Credential of this service are situated in application.properties file.
 *
 * To switch from a configuration to another you have just to delete @Configuration annotation from the old one and put to the new one.
 */
package metricapp.configuration;