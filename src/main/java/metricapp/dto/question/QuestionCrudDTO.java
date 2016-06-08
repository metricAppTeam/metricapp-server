package metricapp.dto.question;

import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import metricapp.dto.MessageDTO;

@Getter
@Setter
@EqualsAndHashCode(callSuper=true)
public class QuestionCrudDTO extends MessageDTO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 76448360483795694L;
	
	private List<QuestionDTO> questionList;
	
	public QuestionCrudDTO(){
		this.setQuestionList(new ArrayList<QuestionDTO>());
	}
	
	public void addQuestionToList(QuestionDTO questionDTO){
		try{
			questionList.add(questionDTO);
		}
		catch(NullPointerException e){
			e.printStackTrace();
		}
	}
}
