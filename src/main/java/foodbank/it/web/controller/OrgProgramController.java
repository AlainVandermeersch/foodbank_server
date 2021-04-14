package foodbank.it.web.controller;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import foodbank.it.persistence.model.OrgProgram;
import foodbank.it.service.IOrgProgramService;
import foodbank.it.web.dto.OrgProgramDto;

@RestController
public class OrgProgramController {
	private IOrgProgramService OrgProgramService;
    
    public OrgProgramController(IOrgProgramService OrgProgramService) {
        this.OrgProgramService = OrgProgramService;        
    }
    @CrossOrigin
    @GetMapping("orgProgram/{lienDis}")
    public OrgProgramDto findOne(@PathVariable Integer lienDis) {
        OrgProgram entity = OrgProgramService.findByLienDis(lienDis)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return convertToDto(entity);
    }
    @CrossOrigin
    @PutMapping("orgProgram/{lienDis}")
    public OrgProgramDto updateOrgProgram(@PathVariable("lienDis") Integer lienDis, @RequestBody OrgProgramDto updatedOrgProgram) {
        OrgProgram OrgProgramEntity = convertToEntity(updatedOrgProgram);
        return this.convertToDto(this.OrgProgramService.save(OrgProgramEntity));
    }
    @CrossOrigin
    @DeleteMapping("orgProgram/{lienDis}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrgProgram(@PathVariable("lienDis") Integer lienDis) {
        OrgProgramService.deleteByLienDis(lienDis);
    }
    @CrossOrigin
    @PostMapping("orgProgram/")
    @ResponseStatus(HttpStatus.CREATED)
    public OrgProgramDto create(@RequestBody OrgProgramDto newOrgProgram) {
        OrgProgram entity = convertToEntity(newOrgProgram);
        OrgProgram OrgProgram = this.OrgProgramService.save(entity);        
        return this.convertToDto(OrgProgram);
    }
    
    private OrgProgram convertToEntity(OrgProgramDto dto) {
    	 OrgProgram myOrgProgram = new OrgProgram(
    			dto.getLienDis(), 
         		dto.getLienBanque(),
     			dto.getLienDepot(),
     			dto.getLuam(),
     			dto.getLupm(),
     			dto.getTuam(),
     			dto.getTupm(),
     			dto.getWeam(),
     			dto.getWepm(),
     			dto.getTham(),
     			dto.getThpm(),
     			dto.getFram(),
     			dto.getFrpm(),
     			dto.getSaam(),
     			dto.getSapm(),
     			dto.getSunam(),
     			dto.getSunpm(),
     			dto.getReluam(),
     			dto.getRelupm(),
     			dto.getRetuam(),
     			dto.getRetupm(),
     			dto.getReweam(),
     			dto.getRewepm(),
     			dto.getRetham(),
     			dto.getRethpm(),
     			dto.getRefram(),
     			dto.getRefrpm(),
     			dto.getResaam(),
     			dto.getResapm(),
     			dto.getResunam(),
     			dto.getResunpm(),
     			dto.getPorc(),
     			dto.getLegFrais(),
     			dto.getCongel(),
     			dto.getCongelCap(),
     			dto.getAuditor(),
     			dto.getDateAudit(),
     			dto.getLastAudit()
    			 );
    			 if (!StringUtils.isEmpty(dto.getLienDis())) {
    		            myOrgProgram.setLienDis(dto.getLienDis());
    		        }
    		        return myOrgProgram;		 
	}
	protected OrgProgramDto convertToDto(OrgProgram entity) {
        OrgProgramDto dto = new OrgProgramDto(
        		entity.getLienDis(), 
        		entity.getLienBanque(),
    			entity.getLienDepot(),
    			entity.getLuam(),
    			entity.getLupm(),
    			entity.getTuam(),
    			entity.getTupm(),
    			entity.getWeam(),
    			entity.getWepm(),
    			entity.getTham(),
    			entity.getThpm(),
    			entity.getFram(),
    			entity.getFrpm(),
    			entity.getSaam(),
    			entity.getSapm(),
    			entity.getSunam(),
    			entity.getSunpm(),
    			entity.getReluam(),
    			entity.getRelupm(),
    			entity.getRetuam(),
    			entity.getRetupm(),
    			entity.getReweam(),
    			entity.getRewepm(),
    			entity.getRetham(),
    			entity.getRethpm(),
    			entity.getRefram(),
    			entity.getRefrpm(),
    			entity.getResaam(),
    			entity.getResapm(),
    			entity.getResunam(),
    			entity.getResunpm(),
    			entity.getPorc(),
    			entity.getLegFrais(),
    			entity.getCongel(),
    			entity.getCongelCap(),
    			entity.getAuditor(),
    			entity.getDateAudit(),
    			entity.getLastAudit());    
        return dto;
    }

}
