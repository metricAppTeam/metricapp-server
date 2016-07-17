package metricapp.dto.user.simple;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import metricapp.dto.DTO;

@Getter
@Setter
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class UserSimpleDTO extends DTO implements Serializable {

	private static final long serialVersionUID = -8509362739633491580L;
	
	private String id;
	private String username;
	private String password;
	
	public UserSimpleDTO() {
		super();
	}
	
}
