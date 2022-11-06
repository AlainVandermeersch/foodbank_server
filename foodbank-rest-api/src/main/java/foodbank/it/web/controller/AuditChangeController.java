package foodbank.it.web.controller;

import foodbank.it.persistence.model.AuditChange;
import foodbank.it.service.IAuditChangeService;
import foodbank.it.service.SearchAuditChangeCriteria;
import foodbank.it.web.dto.AuditChangeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;


@RestController

public class AuditChangeController {
    private IAuditChangeService AuditChangeService;
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

    public AuditChangeController(IAuditChangeService AuditChangeService) {
        this.AuditChangeService = AuditChangeService;

    }
    @GetMapping("auditchanges/")
    public Collection<AuditChangeDto> find(@RequestParam String offset, @RequestParam String rows,
                                           @RequestParam String sortField, @RequestParam String sortOrder,
                                           @RequestParam(required = false) String societe,@RequestParam(required = false) String user,
                                           @RequestParam(required = false) String userName,
                                           @RequestParam(required = false) String entity,@RequestParam(required = false) String entity_key,
                                           @RequestParam(required = false) String action,
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


        SearchAuditChangeCriteria criteria = new SearchAuditChangeCriteria( user,bankShortName,idDisInteger, societe, userName ,
                entity,entity_key, action, fromDate, toDate);
        Page<AuditChange> selectedAuditChanges = this.AuditChangeService.findAll(criteria, pageRequest);
        long totalElements = selectedAuditChanges.getTotalElements();

        return selectedAuditChanges.stream()
                .map(AuditChange -> convertToDto(AuditChange, totalElements))
                .collect(Collectors.toList());

    }
    @PostMapping("auditchange/")
    @ResponseStatus(HttpStatus.CREATED)
    public AuditChangeDto create(@RequestBody AuditChangeDto newAuditChange) throws Exception {
        AuditChange entity = convertToEntity(newAuditChange);
        AuditChange createdAuditChange = this.AuditChangeService.save(entity);
        return this.convertToDto(createdAuditChange,1);
    }
    private AuditChange convertToEntity(AuditChangeDto dto) {
        AuditChange myAuditChange = new AuditChange( dto.getAuditId(), dto.getUser(), dto.getBankId(), dto.getIdDis(),
                dto.getEntity(),dto.getEntity_key(),dto.getAction());
        return myAuditChange;
    }
    private AuditChangeDto convertToDto(AuditChange entity,long totalRecords) {
        AuditChangeDto dto = new AuditChangeDto(entity.getAuditId(), entity.getUser(),entity.getDateIn(), entity.getBankId(), entity.getIdDis(),
                entity.getEntity(),entity.getEntity_key(),entity.getAction(),entity.getSociete(),entity.getBankShortName(),entity.getUserName(),totalRecords);
        return dto;
    }


}
