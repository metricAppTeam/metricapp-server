package metricapp.service.repository;


import metricapp.dto.bus.BusRequestDTO;
import metricapp.dto.bus.BusResponseDTO;
import metricapp.dto.bus.PointerBusDTO;
import metricapp.exception.BusException;
import metricapp.service.spec.repository.BusInterface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

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

    /**
     * lowest level method to interact with bus, it requires only the type of request and the content. there's no "null check"
     *
     * @param content Array with 3 elements, it contains the phase number, the request (read, update, delete, rollback) and the content string
     * @return the String content sended by the Bus. Tipically it is in JSON syntax, but it can contain Error String
     * @throws BusException
     */
    private String rawPost(ArrayList<String> content) throws BusException{

        BusRequestDTO requestDTO = new BusRequestDTO();
        requestDTO.setContent(content);
        requestDTO.setId(idBus);
        requestDTO.setOriginAdress(originAdressBus);
        requestDTO.setResolvedAdress(resolvedAdressBus);
        requestDTO.setTag(tagBus);

        RestTemplate rest = new RestTemplate();
        ResponseEntity<BusResponseDTO> responseDTO;

        try{
            responseDTO = rest.postForEntity(this.urlBus, requestDTO, BusResponseDTO.class);
        }catch (RestClientException e){
            throw new BusException(e);
        }

        if(! responseDTO.getStatusCode().is2xxSuccessful()){
            throw new BusException("Null response from Bus");
        }

        return responseDTO.getBody().getContent();
    }


    /**
     * Simple function to create and fill the content array
     *
     * @param field0 identifies the phase number, typically it's fixed
     * @param field1 identifies the kind of the request
     * @param field2 identifies the request
     * @return the filled array of elements
     */
    private ArrayList<String> fillContent(String field0, String field1, String field2){
        ArrayList<String> content = new ArrayList<String>(3);
        content.add(0, field0);
        content.add(1, field1);
        content.add(2, field2);

        return content;
    }


    /**
     * this method permits to interact with the bus through a read request.
     * There's no null parameters check.
     *
     * @param pointerBusDTO
     * @return the content of the response, it's a String in Json Syntax typically and needs to be converted
     * @throws BusException if available append the previous exception to the last one
     */
    public String read(PointerBusDTO pointerBusDTO) throws BusException{
        return rawPost(fillContent(phaseBus, readFromBus, pointerBusDTO.toJsonString()) );
    }

    /**
     * this method permits to interact with the bus through a update request.
     * There's no null parameters check.
     *
     * @param pointerBusDTO
     * @return the content of the response, it's a String in Json Syntax typically and needs to be converted
     * @throws BusException if available append the previous exception to the last one
     */
    public String update(PointerBusDTO pointerBusDTO) throws BusException{
        return rawPost(fillContent(phaseBus, updateToBus, pointerBusDTO.toJsonString()) );
    }

    /**
     * this method permits to interact with the bus through a create request.
     * There's no null parameters check.
     *
     * @param pointerBusDTO
     * @return the content of the response, it's a String in Json Syntax typically and needs to be converted
     * @throws BusException if available append the previous exception to the last one
     */
    public String create(PointerBusDTO pointerBusDTO) throws BusException{
        return rawPost(fillContent(phaseBus, createBus, pointerBusDTO.toJsonString()) );
    }

    /**
     * this method permits to interact with the bus through a rollback request.
     * There's no null parameters check.
     *
     * @param pointerBusDTO
     * @return the content of the response, it's a String in Json Syntax typically and needs to be converted
     * @throws BusException if available append the previous exception to the last one
     */
    public String rollback(PointerBusDTO pointerBusDTO) throws BusException{
        return rawPost(fillContent(phaseBus, rollbackBus, pointerBusDTO.toJsonString()) );
    }

    /**
     * this method permits to interact with the bus through a delete request.
     * There's no null parameters check.
     *
     * @param pointerBusDTO
     * @return the content of the response, it's a String.
     * @throws BusException if available append the previous exception to the last one
     */
    public String delete(PointerBusDTO pointerBusDTO) throws BusException{
        return rawPost(fillContent(phaseBus, deleteFromBus, pointerBusDTO.toJsonString()) );
    }



}
