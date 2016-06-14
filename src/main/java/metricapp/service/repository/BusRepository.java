package metricapp.service.repository;


import metricapp.dto.bus.BusRequestDTO;
import metricapp.dto.bus.BusResponseDTO;
import metricapp.dto.bus.PointerBusDTO;
import metricapp.exception.BusException;
import metricapp.service.spec.repository.BusInterface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

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

    private ArrayList<String> fillContent(String field0, String field1, String field2){
        ArrayList<String> content = new ArrayList<String>(3);
        content.add(0, field0);
        content.add(1, field1);
        content.add(2, field2);

        return content;
    }


    public String read(PointerBusDTO pointerBusDTO) throws BusException{
        return rawPost(fillContent(phaseBus, readFromBus, pointerBusDTO.toJsonString()) );
    }

    public String update(PointerBusDTO pointerBusDTO) throws BusException{
        return rawPost(fillContent(phaseBus, updateToBus, pointerBusDTO.toJsonString()) );
    }

    public String create(PointerBusDTO pointerBusDTO) throws BusException{
        return rawPost(fillContent(phaseBus, createBus, pointerBusDTO.toJsonString()) );
    }

    public String rollback(PointerBusDTO pointerBusDTO) throws BusException{
        return rawPost(fillContent(phaseBus, rollbackBus, pointerBusDTO.toJsonString()) );
    }

    public String delete(PointerBusDTO pointerBusDTO) throws BusException{
        return rawPost(fillContent(phaseBus, deleteFromBus, pointerBusDTO.toJsonString()) );
    }



}
