package metricapp.dto.user.simple;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserSimpleDTO implements Serializable {

	private static final long serialVersionUID = -8509362739633491580L;
	
	private String id;
	private String username;
	private String password;
	
}
