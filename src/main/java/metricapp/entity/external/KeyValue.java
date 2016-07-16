package metricapp.entity.external;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class KeyValue {

	public String key;
	
	public String value;
	
	public KeyValue(String value){
		this.key = "key";
		this.value = value;
	}

}
