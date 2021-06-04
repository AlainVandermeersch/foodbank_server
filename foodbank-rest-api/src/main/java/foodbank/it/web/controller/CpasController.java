package foodbank.it.web.controller;

import foodbank.it.persistence.model.Cpas;
import foodbank.it.service.ICpasService;
import foodbank.it.web.dto.CpasDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@RestController

public class CpasController {
    
    private ICpasService CpasService;
    
    public CpasController(ICpasService CpasService) {
        this.CpasService = CpasService;
       
    }

    @GetMapping("cpas/{cpasId}")
    public CpasDto findOne(@PathVariable Integer cpasId) {
        Cpas entity = CpasService.findByCpasId(cpasId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return convertToDto(entity,1);
    }
   

    @GetMapping("cpases/")
    public Collection<CpasDto> find( @RequestParam String offset, @RequestParam String rows, 
    		@RequestParam String sortField, @RequestParam String sortOrder, 
    		@RequestParam(required = false) String searchField,@RequestParam(required = false) String searchValue) {
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
        
        Page<Cpas> selectedCpass = null;
        List<CpasDto> CpasDtos = new ArrayList<>();
        if (searchField == null) searchField = "";
        
        switch(searchField) {
        	case "cpasName":
        		selectedCpass = this.CpasService.findByCpasNameContaining(searchValue,pageRequest);
				long totalRecordsName = selectedCpass.getTotalElements();
				selectedCpass.forEach(p -> CpasDtos.add(convertToDto(p, totalRecordsName)));
        		break;
        	case "cpasZip":
        		selectedCpass = this.CpasService.findByCpasZipStartsWith(searchValue,pageRequest);
				long totalRecordsZip = selectedCpass.getTotalElements();
				selectedCpass.forEach(p -> CpasDtos.add(convertToDto(p, totalRecordsZip)));
        		break;
        	default:
        		selectedCpass = this.CpasService.findAll(pageRequest);
				long totalRecords = selectedCpass.getTotalElements();
				selectedCpass.forEach(p -> CpasDtos.add(convertToDto(p, totalRecords)));
        }
        return CpasDtos;
    }

    @PutMapping("cpas/{cpasId}")
    public CpasDto updateCpas(@PathVariable("cpasId") Integer cpasId, @RequestBody CpasDto updatedCpas) {
        Cpas CpasEntity = convertToEntity(updatedCpas);
        return this.convertToDto(this.CpasService.save(CpasEntity),1);
    }

    @DeleteMapping("cpas/{cpasId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCpas(@PathVariable("cpasId") Integer cpasId) {
        CpasService.delete(cpasId);
    }

    @PostMapping("cpas/")
    @ResponseStatus(HttpStatus.CREATED)
    public CpasDto create(@RequestBody CpasDto newCpas) {
        Cpas entity = convertToEntity(newCpas);
        // Alain todo later entity.setDateCreated(LocalDate.now());
        Cpas Cpas = this.CpasService.save(entity);        
        return this.convertToDto(Cpas,1);
    }
    protected CpasDto convertToDto(Cpas entity,long totalRecords) {
        CpasDto dto = new CpasDto(entity.getCpasId(),entity.getCpasZip(),  entity.getCpasName(), entity.getCpasMail(), entity.getCpasStreet(),  entity.getCpasTel(), entity.getCpasGsm(), entity.getCpasContactName(), entity.getCpasContactSurname(),entity.getCivilite(), 
        		entity.getRem(), entity.getPassword(), entity.getLBanque(), entity.getLangue(), totalRecords);       
        return dto;
    }

    protected Cpas convertToEntity(CpasDto dto) {
        Cpas cpas = new Cpas( dto.getCpasId(),dto.getCpasZip(), dto.getCpasName(), dto.getCpasMail(), dto.getCpasStreet(), dto.getCpasTel(),  dto.getCpasGsm(), dto.getCpasContactName(),dto.getCpasContactSurname(),  dto.getCivilite(), 
        		dto.getRem(), dto.getPassword(), dto.getLBanque(), dto.getLangue());       
        if (!StringUtils.isEmpty(dto.getCpasId())) {
            cpas.setCpasId(dto.getCpasId());
        }
        return cpas;
    }


}

