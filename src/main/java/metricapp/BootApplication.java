package metricapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import metricapp.dto.MetricDTO;
import metricapp.entity.metric.Metric;
import metricapp.service.ModelMapperFactory;
import metricapp.service.spec.ModelMapperFactoryInterface;

@SpringBootApplication
public class BootApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}

}
