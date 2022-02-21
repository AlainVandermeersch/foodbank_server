package foodbank.it.web.controller;

import foodbank.it.persistence.model.OrgProgram;
import foodbank.it.service.IOrgProgramService;
import foodbank.it.web.dto.OrgProgramDto;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class OrgProgramController {
	private IOrgProgramService OrgProgramService;
    
    public OrgProgramController(IOrgProgramService OrgProgramService) {
        this.OrgProgramService = OrgProgramService;        
    }

    @GetMapping("orgprogram/{lienDis}")
    public OrgProgramDto findOne(@PathVariable Integer lienDis) {
        OrgProgram entity = OrgProgramService.findByLienDis(lienDis)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return convertToDto(entity);
    }

    @PutMapping("orgprogram/{lienDis}")
    public OrgProgramDto updateOrgProgram(@PathVariable("lienDis") Integer lienDis, @RequestBody OrgProgramDto updatedOrgProgram) {
        OrgProgram OrgProgramEntity = convertToEntity(updatedOrgProgram);
        return this.convertToDto(this.OrgProgramService.save(OrgProgramEntity));
    }

    @DeleteMapping("orgprogram/{lienDis}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrgProgram(@PathVariable("lienDis") Integer lienDis) {
        OrgProgramService.deleteByLienDis(lienDis);
    }

    @PostMapping("orgprogram/")
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
     			(int) (dto.getLuam() ? 1 : 0),
     			(int) (dto.getLupm() ? 1 : 0),
     			(int) (dto.getTuam() ? 1 : 0),
     			(int) (dto.getTupm() ? 1 : 0),
     			(int) (dto.getWeam() ? 1 : 0),
     			(int) (dto.getWepm() ? 1 : 0),
     			(int) (dto.getTham() ? 1 : 0),
     			(int) (dto.getThpm() ? 1 : 0),
     			(int) (dto.getFram() ? 1 : 0),
     			(int) (dto.getFrpm() ? 1 : 0),
     			(int) (dto.getSaam() ? 1 : 0),
     			(int) (dto.getSapm() ? 1 : 0),
     			(int) (dto.getSunam() ? 1 : 0),
     			(int) (dto.getSunpm() ? 1 : 0),
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
     			(int) (dto.getPorc() ? 1 : 0),
     			(int) (dto.getLegFrais() ? 1 : 0),
     			(int) (dto.getCongel() ? 1 : 0),
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
		boolean booLuam= entity.getLuam() == 1;
		boolean booLupm= entity.getLupm() == 1;
		boolean booTuam= entity.getTuam() == 1;
		boolean booTupm= entity.getTupm() == 1;
		boolean booWeam= entity.getWeam() == 1;
		boolean booWepm= entity.getWepm() == 1;
		boolean booTham= entity.getTham() == 1;
		boolean booThpm= entity.getThpm() == 1;
		boolean booFram= entity.getFram() == 1;
		boolean booFrpm= entity.getFrpm() == 1;
		boolean booSaam= entity.getSaam() == 1;
		boolean booSapm= entity.getSapm() == 1;
		boolean booSuam= entity.getSunam() == 1;
		boolean booSupm= entity.getSunpm() == 1;
		boolean booPorc =entity.getPorc() == 1;
		boolean booLegFrais = entity.getLegFrais() == 1;
		boolean booCongel = entity.getCongel() == 1;
        OrgProgramDto dto = new OrgProgramDto(
        		entity.getLienDis(), 
        		entity.getLienBanque(),
    			entity.getLienDepot(),
    			booLuam,
    			booLupm,
    			booTuam,
    			booTupm,
    			booWeam,
    			booWepm,
    			booTham,
    			booThpm,
    			booFram,
    			booFrpm,
    			booSaam,
    			booSapm,
    			booSuam,
    			booSupm,
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
    			booPorc,
    			booLegFrais,
    			booCongel,
    			entity.getCongelCap(),
    			entity.getAuditor(),
    			entity.getDateAudit(),
    			entity.getLastAudit());    
        return dto;
    }

}
