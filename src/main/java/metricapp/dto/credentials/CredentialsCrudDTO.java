package metricapp.dto.credentials;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import metricapp.dto.SimpleDTO;
import metricapp.entity.credentials.Credentials;

@Getter
@Setter
@EqualsAndHashCode(callSuper=true)
public class CredentialsCrudDTO extends SimpleDTO {
	
	private static final long serialVersionUID = 6692876246420298578L;
	
	private long count;	
	public ArrayList<CredentialsDTO> credentialsDTO;	
	
	public CredentialsCrudDTO() {
		this.setCredentialsDTO(new ArrayList<CredentialsDTO>());
	}
	
	public void addCredentialsDTO(CredentialsDTO credentials) {
		if (this.credentialsDTO == null) {
			this.credentialsDTO = new ArrayList<CredentialsDTO>();
		}
		this.credentialsDTO.add(credentials);		
		this.count = this.credentialsDTO.size();
	}
	
	public void addAllCredentialsDTO(List<CredentialsDTO> credentials) {
		if (this.credentialsDTO == null) {
			this.credentialsDTO = new ArrayList<CredentialsDTO>();
		}
		for (CredentialsDTO c : credentials) {
			this.credentialsDTO.add(c);
		}
		this.count = this.credentialsDTO.size();
	}
	
	public void addCredentials(Credentials credentials, ModelMapper mapper) {
		if (this.credentialsDTO == null) {
			this.credentialsDTO = new ArrayList<CredentialsDTO>();
		}
		CredentialsDTO credentialsDTO = mapper.map(credentials, CredentialsDTO.class);
		this.credentialsDTO.add(credentialsDTO);		
		this.count = this.credentialsDTO.size();
	}
	
	public void addAllAnalytics(List<Credentials> credentials, ModelMapper mapper) {
		if (this.credentialsDTO == null) {
			this.credentialsDTO = new ArrayList<CredentialsDTO>();
		}
		for (Credentials a : credentials) {
			CredentialsDTO credentialsDTO = mapper.map(a, CredentialsDTO.class);
			this.credentialsDTO.add(credentialsDTO);
		}
		this.count = this.credentialsDTO.size();
	}
}
