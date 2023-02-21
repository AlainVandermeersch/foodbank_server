package foodbank.it.web.controller;

import foodbank.it.persistence.model.Audit;
import foodbank.it.service.IAuditService;
import foodbank.it.service.SearchAuditCriteria;
import foodbank.it.web.dto.AuditDto;
import foodbank.it.web.dto.AuditReportDto;
import foodbank.it.web.dto.AuditUserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController

public class AuditController {
	private IAuditService AuditService;
	private  boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
    
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
     		@RequestParam(required = false) String idDis,@RequestParam(required = false) String bankShortName) {
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
        Integer idDisInteger = null;
        
        if (this.isNumeric(idDis)) {
        	idDisInteger = Integer.parseInt(idDis);
        }


		SearchAuditCriteria criteria = new SearchAuditCriteria();
		criteria.setUser(user);
		criteria.setBankShortName(bankShortName);
		criteria.setIdDis(idDisInteger);
		criteria.setSociete(societe);
		criteria.setUserName(userName);
		criteria.setFromDate(fromDate);
		criteria.setToDate(toDate);
		criteria.setRights(rights);
		criteria.setIsJavaApp(isJavaApp);
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
				entity.getApplication(),entity.getSociete(),entity.getBankShortName(),entity.getUserName(),entity.getRights(),totalRecords);
		return dto;
	}
    @GetMapping("auditreport/")
    public List<AuditReportDto> AuditReport(
    		@RequestParam(required = false) String bankShortName,
    		@RequestParam(required = false) String fromDate,
    		@RequestParam(required = false) String toDate,
    		@RequestParam(required = false) String reportType
    		) {
    	
    	List<AuditReportDto> listAudits = this.AuditService.report(bankShortName,fromDate,toDate,reportType);
    	
		return listAudits;
    
    	
    	  
    }
	@GetMapping("audituserreport/")
	public List<AuditUserDto> AuditUserReport(
			@RequestParam(required = false) String bankShortName,
			@RequestParam(required = false) String fromDate,
			@RequestParam(required = false) String toDate
	) {
		List<AuditUserDto> listAudits = this.AuditService.reportUsers(bankShortName,fromDate,toDate);
		return listAudits;
	}
	@GetMapping("auditusers/")
	public Collection<AuditUserDto> find(@RequestParam String offset, @RequestParam String rows,
										 @RequestParam String sortField, @RequestParam String sortOrder,
										 @RequestParam(required = false) String societe,
										 @RequestParam(required = false) String idUser,
										 @RequestParam(required = false) String userName,
										 @RequestParam(required = false) String fromDate,
										 @RequestParam(required = false) String toDate,
										 @RequestParam(required = false) String idDis,
										 @RequestParam(required = false) String bankShortName) {
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
		Integer idDisInteger = null;

		if (this.isNumeric(idDis)) {
			idDisInteger = Integer.parseInt(idDis);
		}


		SearchAuditCriteria criteria = new SearchAuditCriteria();
		criteria.setUser(idUser);
		criteria.setBankShortName(bankShortName);
		criteria.setIdDis(idDisInteger);
		criteria.setSociete(societe);
		criteria.setUserName(userName);
		criteria.setFromDate(fromDate);
		criteria.setToDate(toDate);

		List<AuditUserDto> selectedAuditUserDtos = this.AuditService.findUsersPaged(criteria, pageRequest);
		return selectedAuditUserDtos;

	}

}
