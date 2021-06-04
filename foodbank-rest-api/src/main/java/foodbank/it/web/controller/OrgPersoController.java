package foodbank.it.web.controller;

import foodbank.it.persistence.model.OrgPerso;
import foodbank.it.service.IOrgPersoService;
import foodbank.it.service.SearchOrgPersoCriteria;
import foodbank.it.web.dto.OrgPersoDto;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController

public class OrgPersoController {
	
	private IOrgPersoService OrgPersoService;
   
   public OrgPersoController(IOrgPersoService OrgPersoService) {
       this.OrgPersoService = OrgPersoService;
      
   }

   @GetMapping("orgcontact/{orgPersId}")
   public OrgPersoDto findOne(@PathVariable Integer orgPersId) {
       OrgPerso entity = this.OrgPersoService.findByOrgPersId(orgPersId)
           .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
       return convertToDto(entity);
   }
   
   

   @GetMapping("orgcontacts/")
   public Iterable<OrgPersoDto> find(
			@RequestParam String lienAsso,
			@RequestParam(required = false) String deleted
			) {
   	List<OrgPersoDto> orgPersoDtos = new ArrayList<>();
		Integer lienAssoInteger = Integer.parseInt(lienAsso);
		Integer deletedInteger = Optional.ofNullable(deleted).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
		SearchOrgPersoCriteria criteria = new SearchOrgPersoCriteria(lienAssoInteger, deletedInteger);
		Iterable<OrgPerso> selectedOrgPersos = this.OrgPersoService.findAll(criteria);
		selectedOrgPersos.forEach(p -> orgPersoDtos.add(convertToDto(p)));
		return orgPersoDtos;

   }

   @PutMapping("orgcontact/{orgPersId}")
   public OrgPersoDto updateOrgPerso(@PathVariable("orgPersId") Integer orgPersId, @RequestBody OrgPersoDto updatedOrgPerso) {
       OrgPerso OrgPersoEntity = convertToEntity(updatedOrgPerso);
       return this.convertToDto(this.OrgPersoService.save(OrgPersoEntity));
   }

   @DeleteMapping("orgcontact/{orgPersId}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void deleteOrgPerso(@PathVariable("orgPersId") Integer orgPersId) {
       this.OrgPersoService.deleteByOrgPersId(orgPersId);
   }

   @PostMapping("orgcontact/")
   @ResponseStatus(HttpStatus.CREATED)
   public OrgPersoDto create(@RequestBody OrgPersoDto newOrgPerso) {
       OrgPerso entity = convertToEntity(newOrgPerso);
       OrgPerso OrgPerso = this.OrgPersoService.save(entity);        
       return this.convertToDto(OrgPerso);
   }
   protected OrgPersoDto convertToDto(OrgPerso entity) {
	   boolean deleted = entity.getDeleted() == 1;;
	   boolean distr = entity.getDistr() == 1;;;
       OrgPersoDto dto = new OrgPersoDto(entity.getOrgPersId(),entity.getCivilite(), entity.getLienAsso(), entity.getNom(), entity.getPrenom(), entity.getEmail(),
    		   entity.getTel(),entity.getGsm(),entity.getFonction(), deleted, distr);
			
       return dto;
   }

   protected OrgPerso convertToEntity(OrgPersoDto dto) {
       OrgPerso orgPerso = new OrgPerso( dto.getOrgPersId(),dto.getCivilite(), dto.getLienAsso(), dto.getNom(), dto.getPrenom(), dto.getEmail(),
    		   dto.getTel(),dto.getGsm(),dto.getFonction(), (int) (dto.isDeleted() ? 1 : 0),(int) (dto.isDistr() ? 1 : 0));       
       if (!StringUtils.isEmpty(dto.getOrgPersId())) {
           orgPerso.setOrgPersId(dto.getOrgPersId());
       }
       return orgPerso;
   }

}
