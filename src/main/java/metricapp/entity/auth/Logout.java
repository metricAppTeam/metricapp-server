package metricapp.entity.auth;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Document
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Logout {
	
	@Id
	private String response;
	
}
