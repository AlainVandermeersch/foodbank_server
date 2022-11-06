package foodbank.it.web.controller;

import foodbank.it.persistence.model.AuditAssoc;
import foodbank.it.service.IAuditAssocService;
import foodbank.it.service.SearchAuditAssocCriteria;
import foodbank.it.web.dto.AuditAssocDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController

public class AuditAssocController {
	private IAuditAssocService AuditAssocService;
    
    public AuditAssocController(IAuditAssocService AuditAssocService) {
        this.AuditAssocService = AuditAssocService;
       
    }

    @GetMapping("orgaudit/{auditId}")
    public AuditAssocDto findOne(@PathVariable Integer auditId) {
        AuditAssoc entity = AuditAssocService.findByAuditId(auditId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return convertToDto(entity, 1);
    }
    

    @GetMapping("orgaudits/")
    public Collection<AuditAssocDto> find(@RequestParam String offset, @RequestParam String rows, 
    		@RequestParam String sortField, @RequestParam String sortOrder, 
    		@RequestParam(required = false) String lienBanque,@RequestParam(required = false) String lienDep ) {
    	int intOffset = Integer.parseInt(offset);
    	int intRows = Integer.parseInt(rows);
    	int pageNumber=intOffset/intRows; // Java throws away remainder of division
        int pageSize = intRows;
        Pageable pageRequest = null;
        if (sortOrder.equals("1")) {
        	pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortField).ascending());
        }
        else {
        	pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortField).descending());
        }
        Integer lienBanqueInteger = Optional.ofNullable(lienBanque).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
        Integer lienDepInteger = Optional.ofNullable(lienDep).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
        
        SearchAuditAssocCriteria criteria = new SearchAuditAssocCriteria(lienBanqueInteger,lienDepInteger );
        Page<AuditAssoc> selectedAuditAssocs = this.AuditAssocService.findAll(criteria, pageRequest);
		long totalElements = selectedAuditAssocs.getTotalElements();

		return selectedAuditAssocs.stream()
				.map(AuditAssoc -> convertToDto(AuditAssoc, totalElements))
				.collect(Collectors.toList());
       
    }

   
	@PutMapping("orgaudit/{auditId}")
    public AuditAssocDto updateAuditAssoc(@PathVariable("auditId") Long auditId, @RequestBody AuditAssocDto updatedAuditAssoc) throws Exception {
        AuditAssoc AuditAssocEntity = convertToEntity(updatedAuditAssoc);
        return this.convertToDto(this.AuditAssocService.save(AuditAssocEntity),1);
    }

    @DeleteMapping("orgaudit/{auditId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuditAssoc(@PathVariable("auditId") Long auditId) throws Exception {
    	try {
        AuditAssocService.delete(auditId);
    	}
        catch (Exception ex) {
         	String errorMsg = ex.getMessage();
         	System.out.println(errorMsg);
     		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMsg);
         }
    }

    @PostMapping("orgaudit/")
    @ResponseStatus(HttpStatus.CREATED)
    public AuditAssocDto create(@RequestBody AuditAssocDto newAuditAssoc) throws Exception {
        AuditAssoc entity = convertToEntity(newAuditAssoc);
        AuditAssoc createdAuditAssoc = this.AuditAssocService.save(entity);        
        return this.convertToDto(createdAuditAssoc,1);
    }
    private AuditAssoc convertToEntity(AuditAssocDto dto) {
    	AuditAssoc myAuditAssoc = new AuditAssoc( dto.getAuditId(), dto.getLienDis(), dto.getLienDep(), dto.getAuditorNr(), dto.getDemunisYNRem(), dto.getHygCheck(), dto.getServUsage(),
    			dto.getProbSug(), dto.getAuditDate(),(dto.isBenefCheck() ? "Y" : "N"));
    	 return myAuditAssoc;
    }

	private AuditAssocDto convertToDto(AuditAssoc entity,long totalRecords) {
		boolean booBenefChecked= entity.getBenefCheck() == "Y";
		AuditAssocDto dto = new AuditAssocDto(entity.getAuditId(), entity.getLienDis(), entity.getLienDep(), entity.getAuditorNr(), entity.getDemunisYNRem(), entity.getHygCheck(), entity.getServUsage(),
    			entity.getProbSug(), entity.getAuditDate(),booBenefChecked, 
    			entity.getSociete(), entity.getDepotName(), entity.getLienBanque(),entity.getAuditorName(),totalRecords);
		return dto;
	}

}
