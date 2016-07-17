package metricapp.entity.stakeholders;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Document
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper=false)
@ToString
public class Team extends Person {
	
	@Id
	private String id;
	private String name;
	private String gridName;
	/*@CreatedDate
	private LocalDate tsCreate;
	@LastModifiedDate
	private LocalDate tsUpdate;*/
	private ArrayList<User> expert;
	private ArrayList<User> questioners;
	private ArrayList<User> metricators;
	private User extrauser;
	
	/*@JsonProperty("tsCreate")
	public void setTsCreate(String date) {
		if (date != null) {
			this.tsCreate = LocalDate.parse(date);
		}
	}

	@JsonProperty("tsUpdate")
	public void setTsUpdate(String date) {
		if (date != null) {
			this.tsUpdate = LocalDate.parse(date);
		}

	}

	@JsonIgnore
	public void setTsCreate(LocalDate date) {
		this.tsCreate = date;
	}

	@JsonIgnore
	public void setTsUpdate(LocalDate date) {
		this.tsUpdate = date;
	}

	@JsonGetter("tsCreate")
	public String getTsCreate(){
		if(tsUpdate == null){
			return null;
		}else{
			return tsCreate.toString();
		}
	}
	
	@JsonGetter("tsUpdate")
	public String getTsUpdate(){
		if(tsUpdate == null){
			return null;
		}else{
			return tsUpdate.toString();
		}
	}*/
}
