package rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dto.GoalDTO;
import service.Application;

@RestController
@RequestMapping("/goal")
public class GoalRest {
    
	
		@RequestMapping("/getGoal")
	    public GoalDTO getGoal(@RequestParam(value="id") String id) {
	        return new GoalDTO(id, "descrizione goal");
	    }
	    
	    @RequestMapping(method  = RequestMethod.GET)
	    public GoalDTO index(){
	    	return new GoalDTO();
	    }
	    
	    @RequestMapping(method  = RequestMethod.POST)
	    public ResponseEntity<?> new_goal( @RequestBody GoalDTO goal){
	    	System.out.println("un goal è stato ricevuto dal server:" + goal.getDef());
	    	return null;
	    }
	    
	    public static void main(String[] args) {
	        SpringApplication.run(Application.class, args);
	    }	
	
}
