package metricapp.utility;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * This class is a utility, wired with Spring, that offers a lightweight converter for object to json string, and viceversa
 *
 * 
 * According to Jackson Documentation max performance of massive multithread use of mapper could be reached only
 * with the separated use of writer and reader.
 *
 * Jackson Documentation permits to use mapper with a single instance, it is ThreadSafe
 */
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


}
