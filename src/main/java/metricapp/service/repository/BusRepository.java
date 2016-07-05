package metricapp.service.repository;


import com.fasterxml.jackson.core.JsonProcessingException;

import metricapp.dto.bus.BusRequestDTO;
import metricapp.dto.bus.BusResponseDTO;
import metricapp.entity.Entity;
import metricapp.entity.external.PointerBus;
import metricapp.entity.external.RichPointerBus;
import metricapp.exception.BusException;
import metricapp.service.spec.repository.BusInterface;
import metricapp.utility.JacksonMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

/**
 * This repository is the lowest level before the connection with Bus Rest Interface.
 * Method in this class are almost the same described in Bus Team Documentation on their Bus.
 */
@Service
public class BusRepository implements BusInterface {

    @Value("${bus.url}")
    private String urlBus;

    @Value("${bus.tag}")
    private String tagBus;

    @Value("${bus.originAdress}")
    private String originAdressBus;

    @Value("${bus.resolvedAdress}")
    private String resolvedAdressBus;

    @Value("${bus.id}")
    private String idBus;

    @Value("${bus.phase}")
    private String phaseBus;

    @Value("${bus.readFromBus}")
    private String readFromBus;

    @Value("${bus.updateToBus}")
    private String updateToBus;

    @Value("${bus.createBus}")
    private String createBus;

    @Value("${bus.deleteFromBus}")
    private String deleteFromBus;

    @Value("${bus.rollbackBus}")
    private String rollbackBus;

    @Autowired
    private JacksonMapper mapper;

    /**
     * lowest level method to interact with bus, it requires only the type of request and the content. there's no "null check"
     *
     * @param content Array with 3 elements, it contains the phase number, the request (read, update, delete, rollback) and the content string
     * @return the String content sended by the Bus. Tipically it is in JSON syntax, but it can contain Error String
     * @throws BusException
     */
    private ArrayList<String> rawPost(ArrayList<String> content) throws BusException{

        BusRequestDTO requestDTO = new BusRequestDTO();
        requestDTO.setContent(content);
        requestDTO.setId(idBus);
        requestDTO.setOriginAdress(originAdressBus);
        requestDTO.setResolvedAdress(resolvedAdressBus);
        requestDTO.setTag(tagBus);


        RestTemplate rest = new RestTemplate();
        ResponseEntity<BusResponseDTO> responseDTO;
        System.out.println(requestDTO.toString());
        try{
            responseDTO = rest.postForEntity(urlBus, requestDTO, BusResponseDTO.class);
        }catch (RestClientException e){
            throw new BusException(e);
        }

        if(! responseDTO.getStatusCode().is2xxSuccessful()){
            throw new BusException("Null response from Bus");
        }
        
        return responseDTO.getBody().getContent();
    }


    /**
     * Simple function to create and fill the content array.
     * field2 is converted to string with JacksonMapper
     * @see JacksonMapper
     *
     * @param field1 identifies the kind of the request
     * @param field2 identifies the request
     * @return the filled array of elements
     */
    private ArrayList<String> fillContent(String field1, PointerBus field2) throws JsonProcessingException {
        ArrayList<String> content = new ArrayList<String>(3);
        content.add(0, phaseOfPointerPayload(field2));
        content.add(1, field1);
        content.add(2, mapper.toJson(field2));

        return content;
    }


    /**
     * this method permits to interact with the bus through a read request.
     * There's no null parameters check.
     *
     * @param pointerBus
     * @return the content of the response, it's a String in Json Syntax typically and needs to be converted
     * @throws BusException if available append the previous exception to the last one
     * @throws JsonProcessingException if the dto is not printable like json
     */
    public ArrayList<String> read(PointerBus pointerBus) throws BusException, JsonProcessingException {
        return rawPost(fillContent(readFromBus, pointerBus) );
    }


    /**
     * this method permits to interact with the bus through a update request.
     * There's no null parameters check.
     *
     * @param pointerBus
     * @return the content of the response, it's a String in Json Syntax typically and needs to be converted
     * @throws BusException if available append the previous exception to the last one
     * @throws JsonProcessingException if the dto is not printable like json
     */
    public ArrayList<String> update(RichPointerBus pointerBus) throws BusException, JsonProcessingException {
        return rawPost(fillContent(updateToBus, pointerBus) );
    }

    /**
     * this method permits to interact with the bus through a create request.
     * There's no null parameters check.
     *
     * @param pointerBus, of type RichPointerBus, it contains the payload too
     * @return the content of the response, it's a String in Json Syntax typically and needs to be converted
     * @throws BusException if available append the previous exception to the last one
     * @throws JsonProcessingException if the dto is not printable like json
     */
    public ArrayList<String> create(RichPointerBus pointerBus) throws BusException, JsonProcessingException {
    	return rawPost(fillContent(createBus, pointerBus) );
    }

    /**
     * this method permits to interact with the bus through a rollback request.
     * There's no null parameters check.
     *
     * @param pointerBus, of type RichPointerBus, it contains the payload too
     * @return the content of the response, it's a String in Json Syntax typically and needs to be converted
     * @throws BusException if available append the previous exception to the last one
     * @throws JsonProcessingException if the dto is not printable like json
     */
    public ArrayList<String> rollback(PointerBus pointerBus) throws BusException, JsonProcessingException {
        return rawPost(fillContent(rollbackBus, pointerBus) );
    }

    /**
     * this method permits to interact with the bus through a delete request.
     * There's no null parameters check.
     *
     * @param pointerBus
     * @return the content of the response, it's a String.
     * @throws BusException if available append the previous exception to the last one
     * @throws JsonProcessingException if the dto is not printable like json
     */
    public ArrayList<String> delete(PointerBus pointerBus) throws BusException, JsonProcessingException {
        return rawPost(fillContent(deleteFromBus, pointerBus) );
    }
    
    /**
     * this method is used to grab the phase of the requested or sended element. 
     * e.g. : A metric is sended to bus, the correct phase value is "phase2", otherwise a context factor is grabbed the correct phase value is "phase1".
     * Phase values are in a field of enumeration Entity. phase value identifies the owner of the element.  
     * @param pointerBus
     * @return string phasex, where x is in {1,2,3,4,5,6}
     */
    private String phaseOfPointerPayload(PointerBus pointerBus){
    	return Entity.valueOf(pointerBus.getTypeObj()).getPhase();
    }



}
