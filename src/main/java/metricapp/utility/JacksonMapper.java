package metricapp.utility;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * This class is a utility, wired with Spring, that offers a lightweight converter for object to json string, and viceversa
 *
 *
 * According to Jackson Documentation max performance of massive multithread use of mapper could be reached only
 * with the separated use of writer and reader.
 *
 * Jackson Documentation permits to use mapper with a single instance, it is ThreadSafe
 *
 * Example of use:
 * jacksonMapper.toJson(myDTO) -> return String of Json
 */

@Getter
public class JacksonMapper {

    private ObjectMapper mapper;

    private ObjectWriter writer;

    private ObjectReader reader;

    /**
     * this method is the Spring implementation of Singleton.
     * According to Jackson Documentation max performance of massive multithread use of mapper could be reached only
     * with the separated use of writer and reader.
     *
     * Jackson Documentation permits to use mapper with a single instance, it is ThreadSafe
     */
    @Autowired
    private void initialize(){
        mapper = new ObjectMapper();
        writer = mapper.writerWithDefaultPrettyPrinter();
        reader = mapper.reader();
    }

    /**
     * this method is a simple wrapper of writeValueAsString of writer. 
     *
     * @param object that you want to convert
     * @return String json formatted
     * @throws JsonProcessingException
     */
    public String toJson(Object object) throws JsonProcessingException {
       return this.getWriter().writeValueAsString(object);
    }


}
