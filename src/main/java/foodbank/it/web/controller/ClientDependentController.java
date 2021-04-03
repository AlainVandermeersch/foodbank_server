package foodbank.it.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import foodbank.it.persistence.model.ClientDependent;
import foodbank.it.service.IClientDependentService;
import foodbank.it.service.SearchClientDependentCriteria;
import foodbank.it.web.dto.ClientDependentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@RestController

public class ClientDependentController {
	
	private IClientDependentService ClientDependentService;
    
    public ClientDependentController(IClientDependentService ClientDependentService) {
        this.ClientDependentService = ClientDependentService;
       
    }
    @CrossOrigin
    @GetMapping("clientdependent/{idDep}")
    public ClientDependentDto findOne(@PathVariable Integer idDep) {
        ClientDependent entity = this.ClientDependentService.findByIdDep(idDep)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return convertToDto(entity,1);
    }
    
    
    @CrossOrigin
    @GetMapping("clientdependents/")
    public Collection<ClientDependentDto> find(@RequestParam String offset, @RequestParam String rows,
			@RequestParam String sortField, @RequestParam String sortOrder,
			@RequestParam(required = false) String searchField, @RequestParam(required = false) String searchValue,
			@RequestParam(required = false) String lienMast, @RequestParam(required = false) String lienDis,
			@RequestParam(required = false) String actif
			) {
		int intOffset = Integer.parseInt(offset);
		int intRows = Integer.parseInt(rows);
		int pageNumber = intOffset / intRows; // Java throws away remainder of division
		int pageSize = intRows;
		Pageable pageRequest = null;

		if (sortOrder.equals("1")) {
			pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortField).ascending());
		} else {
			pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortField).descending());
		}

		Integer lienDisInteger = Optional.ofNullable(lienDis).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
		Integer lienMastInteger = Optional.ofNullable(lienMast).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
		Integer actifInteger = Optional.ofNullable(actif).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
		SearchClientDependentCriteria criteria = new SearchClientDependentCriteria(searchField, searchValue, lienDisInteger,lienMastInteger, actifInteger);
		Page<ClientDependent> selectedClientDependents = this.ClientDependentService.findAll(criteria, pageRequest);
		long totalElements = selectedClientDependents.getTotalElements();

		return selectedClientDependents.stream()
				.map(clientDependent -> convertToDto(clientDependent, totalElements))
				.collect(Collectors.toList());

    }
    @CrossOrigin
    @PutMapping("clientdependent/{idDep}")
    public ClientDependentDto updateClientDependent(@PathVariable("idDep") Integer idDep, @RequestBody ClientDependentDto updatedClientDependent) {
        ClientDependent ClientDependentEntity = convertToEntity(updatedClientDependent);
        return this.convertToDto(this.ClientDependentService.save(ClientDependentEntity),1);
    }
    @CrossOrigin
    @DeleteMapping("clientdependent/{idDep}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClientDependent(@PathVariable("idDep") Integer idDep) {
        this.ClientDependentService.delete(idDep);
    }
    @CrossOrigin
    @PostMapping("clientdependent/")
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDependentDto create(@RequestBody ClientDependentDto newClientDependent) {
        ClientDependent entity = convertToEntity(newClientDependent);
        ClientDependent ClientDependent = this.ClientDependentService.save(entity);        
        return this.convertToDto(ClientDependent, 1);
    }
    protected ClientDependentDto convertToDto(ClientDependent entity, long totalRecords) {
        ClientDependentDto dto = new ClientDependentDto(entity.getIdDep(),entity.getLienDis(), entity.getLienMast(),entity.getPrenom(), entity.getNom(), entity.getDatenais(),
    			entity.getDepTyp(), entity.getActif(), entity.getDeleted(), entity.getLienBanque(), entity.getRegio(), entity.getCivilite(), entity.getEq(),totalRecords
	);		
        return dto;
    }

    protected ClientDependent convertToEntity(ClientDependentDto dto) {
        ClientDependent clientDependent = new ClientDependent( dto.getIdDep(),dto.getLienDis(), dto.getLienMast(),dto.getPrenom(), dto.getNom(), dto.getDatenais(),
    			dto.isDepTyp(), dto.isActif(), dto.isDeleted(), dto.getLienBanque(), dto.getRegio(), dto.getCivilite(), dto.getEq());       
        if (!StringUtils.isEmpty(dto.getIdDep())) {
            clientDependent.setIdDep(dto.getIdDep());
        }
        return clientDependent;
    }

}
