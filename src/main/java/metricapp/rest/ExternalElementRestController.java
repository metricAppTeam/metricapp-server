package metricapp.rest;

import metricapp.dto.externalElements.*;
import metricapp.entity.external.PointerBus;
import metricapp.exception.BusException;
import metricapp.exception.NotFoundException;
import metricapp.service.spec.controller.ExternalElementsGetterInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(("/external"))
public class ExternalElementRestController {

    @Autowired
    private ExternalElementsGetterInterface externalElementsGetterController;

    @RequestMapping(value = "/contextfactor",method = RequestMethod.GET)
    public ResponseEntity<ContextFactorDTO> getContextFactorDTO(@RequestParam(value = "id", defaultValue = "NA") String id,
                                                                @RequestParam(value = "version", defaultValue = "NA") String version){
        ContextFactorDTO dto = new ContextFactorDTO();
        PointerBus pointerBus = new PointerBus();
        try {
            if (!version.equals("NA") && !id.equals("NA")) {
                pointerBus.setBusVersion(version);
                pointerBus.setObjIdLocalToPhase(id);
                dto = externalElementsGetterController.getContextFactorByPointerBus(pointerBus);
                return new ResponseEntity<ContextFactorDTO>(dto, HttpStatus.OK);
            }
            if (!id.equals("NA")) {
                pointerBus.setObjIdLocalToPhase(id);
                dto = externalElementsGetterController.getContextFactorByPointerBus(pointerBus);
                return new ResponseEntity<ContextFactorDTO>(dto, HttpStatus.OK);
            } else {
                return new ResponseEntity<ContextFactorDTO>(HttpStatus.BAD_REQUEST);
            }
        } catch (IOException e) {
            //dto.setError(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<ContextFactorDTO>(dto, HttpStatus.BAD_REQUEST);
        } catch (BusException e) {
            //dto.setError(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<ContextFactorDTO>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (NotFoundException e) {
            //dto.setError(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<ContextFactorDTO>(dto, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            //dto.setError(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<ContextFactorDTO>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/assumption",method = RequestMethod.GET)
    public ResponseEntity<AssumptionDTO> getAssumptionDTO(@RequestParam(value = "id", defaultValue = "NA") String id,
                                                          @RequestParam(value = "version", defaultValue = "NA") String version){
        AssumptionDTO dto = new AssumptionDTO();
        PointerBus pointerBus = new PointerBus();
        try {
            if (!version.equals("NA") && !id.equals("NA")) {
                pointerBus.setBusVersion(version);
                pointerBus.setObjIdLocalToPhase(id);
                dto = externalElementsGetterController.getAssumptionByPointerBus(pointerBus);
                return new ResponseEntity<AssumptionDTO>(dto, HttpStatus.OK);
            }
            if (!id.equals("NA")) {
                pointerBus.setObjIdLocalToPhase(id);
                dto = externalElementsGetterController.getAssumptionByPointerBus(pointerBus);
                return new ResponseEntity<AssumptionDTO>(dto, HttpStatus.OK);
            } else {
                return new ResponseEntity<AssumptionDTO>(HttpStatus.BAD_REQUEST);
            }
        } catch (IOException e) {
            //dto.setError(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<AssumptionDTO>(dto, HttpStatus.BAD_REQUEST);
        } catch (BusException e) {
            //dto.setError(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<AssumptionDTO>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (NotFoundException e) {
            //dto.setError(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<AssumptionDTO>(dto, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            //dto.setError(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<AssumptionDTO>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/organizationalgoal",method = RequestMethod.GET)
    public ResponseEntity<OrganizationalGoalDTO> getOrganizationalGoalDTO(@RequestParam(value = "id", defaultValue = "NA") String id,
                                                                          @RequestParam(value = "version", defaultValue = "NA") String version){
        OrganizationalGoalDTO dto = new OrganizationalGoalDTO();
        PointerBus pointerBus = new PointerBus();
        try {
            if (!version.equals("NA") && !id.equals("NA")) {
                pointerBus.setBusVersion(version);
                pointerBus.setObjIdLocalToPhase(id);
                dto = externalElementsGetterController.getOrganizationalGoalByPointerBus(pointerBus);
                return new ResponseEntity<OrganizationalGoalDTO>(dto, HttpStatus.OK);
            }
            if (!id.equals("NA")) {
                pointerBus.setObjIdLocalToPhase(id);
                dto = externalElementsGetterController.getOrganizationalGoalByPointerBus(pointerBus);
                return new ResponseEntity<OrganizationalGoalDTO>(dto, HttpStatus.OK);
            } else {
                return new ResponseEntity<OrganizationalGoalDTO>(HttpStatus.BAD_REQUEST);
            }
        } catch (IOException e) {
            //dto.setError(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<OrganizationalGoalDTO>(dto, HttpStatus.BAD_REQUEST);
        } catch (BusException e) {
            //dto.setError(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<OrganizationalGoalDTO>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (NotFoundException e) {
            //dto.setError(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<OrganizationalGoalDTO>(dto, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            //dto.setError(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<OrganizationalGoalDTO>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/instanceproject",method = RequestMethod.GET)
    public ResponseEntity<InstanceProjectDTO> getInstanceProjectDTO(@RequestParam(value = "id", defaultValue = "NA") String id,
                                                                    @RequestParam(value = "version", defaultValue = "NA") String version){
        InstanceProjectDTO dto = new InstanceProjectDTO();
        PointerBus pointerBus = new PointerBus();
        try {
            if (!version.equals("NA") && !id.equals("NA")) {
                pointerBus.setBusVersion(version);
                pointerBus.setObjIdLocalToPhase(id);
                dto = externalElementsGetterController.getInstanceProjectByPointerBus(pointerBus);
                return new ResponseEntity<InstanceProjectDTO>(dto, HttpStatus.OK);
            }
            if (!id.equals("NA")) {
                pointerBus.setObjIdLocalToPhase(id);
                dto = externalElementsGetterController.getInstanceProjectByPointerBus(pointerBus);
                return new ResponseEntity<InstanceProjectDTO>(dto, HttpStatus.OK);
            } else {
                return new ResponseEntity<InstanceProjectDTO>(HttpStatus.BAD_REQUEST);
            }
        } catch (IOException e) {
            //dto.setError(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<InstanceProjectDTO>(dto, HttpStatus.BAD_REQUEST);
        } catch (BusException e) {
            //dto.setError(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<InstanceProjectDTO>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (NotFoundException e) {
            //dto.setError(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<InstanceProjectDTO>(dto, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            //dto.setError(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<InstanceProjectDTO>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/measurementgoal",method = RequestMethod.GET)
    public ResponseEntity<ExternalElementsDTO> getExternalElementsDTO(@RequestParam(value = "id", defaultValue = "NA") String id){
        ExternalElementsDTO dto = new ExternalElementsDTO();

        try {
            if (!id.equals("NA")) {
                dto = externalElementsGetterController.getMeasurementGoalExternalElements(id);
                return new ResponseEntity<ExternalElementsDTO>(dto, HttpStatus.OK);
            } else {
                return new ResponseEntity<ExternalElementsDTO>(HttpStatus.BAD_REQUEST);
            }
        } catch (IOException e) {
            dto.setError(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<ExternalElementsDTO>(dto, HttpStatus.BAD_REQUEST);
        } catch (BusException e) {
            dto.setError(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<ExternalElementsDTO>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            dto.setError(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<ExternalElementsDTO>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
