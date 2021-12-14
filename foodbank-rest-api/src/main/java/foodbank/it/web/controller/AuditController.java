package foodbank.it.web.controller;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import foodbank.it.persistence.model.Audit;
import foodbank.it.service.IAuditService;
import foodbank.it.service.SearchAuditCriteria;
import foodbank.it.web.dto.AuditDto;

@RestController

public class AuditController {
	private IAuditService AuditService;
    
    public AuditController(IAuditService AuditService) {
        this.AuditService = AuditService;
       
    }
    @GetMapping("audits/")
    public Collection<AuditDto> find(@RequestParam String offset, @RequestParam String rows, 
    		@RequestParam String sortField, @RequestParam String sortOrder, 
    		@RequestParam(required = false) String societe,@RequestParam(required = false) String user, 
    		@RequestParam(required = false) Boolean isJavaApp,@RequestParam(required = false) String userName, 
    		@RequestParam(required = false) String rights,
    		@RequestParam(required = false) String fromDate,@RequestParam(required = false) String toDate,
     		@RequestParam(required = false) String idDis,@RequestParam(required = false) String shortBankName) {
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
        Integer idDisInteger = Optional.ofNullable(idDis).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
        
        SearchAuditCriteria criteria = new SearchAuditCriteria( user,shortBankName,idDisInteger, societe, userName ,
        		rights, fromDate, toDate, isJavaApp);
        Page<Audit> selectedAudits = this.AuditService.findAll(criteria, pageRequest);
		long totalElements = selectedAudits.getTotalElements();

		return selectedAudits.stream()
				.map(Audit -> convertToDto(Audit, totalElements))
				.collect(Collectors.toList());

     }
    @PostMapping("audit/")
    @ResponseStatus(HttpStatus.CREATED)
    public AuditDto create(@RequestBody AuditDto newAudit) throws Exception {
        Audit entity = convertToEntity(newAudit);
        Audit createdAudit = this.AuditService.save(entity);        
        return this.convertToDto(createdAudit,1);
    }
    private Audit convertToEntity(AuditDto dto) {
    	Audit myAudit = new Audit( dto.getAuditId(), dto.getUser(), dto.getIpAddress(), dto.getIdDis());
    	 return myAudit;
    }
    private AuditDto convertToDto(Audit entity,long totalRecords) {
		AuditDto dto = new AuditDto(entity.getAuditId(), entity.getUser(),entity.getDateIn(), entity.getIpAddress(), entity.getIdDis(),
				entity.getApplication(),entity.getSociete(),entity.getShortBankName(),entity.getUserName(),entity.getRights(),totalRecords);
		return dto;
	}

}
