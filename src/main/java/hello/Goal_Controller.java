package hello;


import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONPObject;

@RestController
@RequestMapping("/goal")
public class Goal_Controller {


    @RequestMapping("/get_goal")
    public Goal_json get_goal(@RequestParam(value="id") String id) {
        return new Goal_json(id, "descrizione goal");
    }
    
    @RequestMapping(method  = RequestMethod.POST)
    public ResponseEntity<?> new_goal( @RequestBody Goal_json goal){
    	System.out.println("un goal è stato ricevuto dal server:" + goal.getDef());
    	return null;
    }
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
}
