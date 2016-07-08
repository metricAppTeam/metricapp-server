package metricapp.configuration;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

@Configuration
//@PropertySource("application.properties")
public class MLabMongoConfiguration {

	@Value("${metricappdb.user}")
	protected String user;
	@Value("${metricappdb.password}")
	protected String password;
	@Value("${metricappdb.databaseName}")
	protected String database;
	@Value("${metricappdb.port}")
	protected Integer port;
	@Value("${metricappdb.address}")
	protected String host;
	
	
	protected String getDatabaseName() {
		return database;
	}

	public Mongo mongo() throws Exception {
		return new MongoClient();
	}

	@Bean
	public MongoDbFactory mongoDbFactory() throws Exception {
		
		MongoClient mongoClient = null;
		ServerAddress serverAddress = new ServerAddress(host,port);
		
		
		if(user!=null){
			// Set credentials
			MongoCredential credential = MongoCredential.createCredential(user, getDatabaseName(), password.toCharArray());
			System.out.println("mongoDb credentials: " + host + ":" + port + "/" + database+ "?user=" + user + "&pass=" + password);
			// Mongo Client
			mongoClient = new MongoClient(serverAddress, Arrays.asList(credential));
		}else{
			mongoClient = new MongoClient(serverAddress);
		}
		
		// Mongo DB Factory
		SimpleMongoDbFactory simpleMongoDbFactory = new SimpleMongoDbFactory(mongoClient, getDatabaseName());
		
		
		return simpleMongoDbFactory;

	}

	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
		
		mongoTemplate.setWriteConcern(WriteConcern.SAFE);
		
		return mongoTemplate;
	}



	
	
}
