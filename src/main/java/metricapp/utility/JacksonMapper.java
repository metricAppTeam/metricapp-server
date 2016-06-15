package metricapp.utility;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;


/**
 * This class is a utility, wired with Spring, that offers a lightweight converter for object to json string, and viceversa
 *
 *
 * According to Jackson Documentation max performance of massive multithread use of mapper could be reached only
 * with the separated use of writer and reader.
 *
 * Jackson Documentation permits to use mapper with a single instance, it is ThreadSafe:
 * from Jackson Doc...
 * "ObjectReader/ObjectWriter: these are light-weight immutable objects that can be safely shared between threads (and thus reused) as well"
 *
 * Example of use:
 * jacksonMapper.toJson(myDTO) = return String of Json
 */

@Getter
@Service("JacksonMapper")
public class JacksonMapper {

    private ObjectMapper mapper;

    private ObjectWriter writer;

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
    }

    /**
     * this method is a simple wrapper of writeValueAsString of writer.
     *
     * Example of use:
     * jacksonMapper.toJson(myDTO) = return String of Json
     *
     * @param object that you want to convert
     * @return String json formatted
     * @throws JsonProcessingException
     */
    public String toJson(Object object) throws JsonProcessingException {
        return this.getWriter().writeValueAsString(object);
    }

    /**
     * this method return a ready to operate reader, it is customized for class.
     * It's easier to use function fromJson
     *
     * @param myClass
     * @return a Reader Object
     */
    public <T> ObjectReader getReader(Class<T> myClass){
        return this.getMapper().readerFor(myClass);
    }


    /**
     * Easier function to convert a string json to object
     *
     * @param json String of json
     * @param myClass this is the class of destination of the mapping
     * @return no cast needed, we do it for you.
     * @throws IOException if the conversion failed
     */
    public <T extends Object> T fromJson(String json, Class<T> myClass) throws IOException {
        return getReader(myClass).readValue(json);
    }

}
