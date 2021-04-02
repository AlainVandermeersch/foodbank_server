package foodbank.it.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

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
import foodbank.it.web.dto.ClientDependentDto;

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
        return convertToDto(entity);
    }
    
    
    @CrossOrigin
    @GetMapping("clientdependents/")
    public Collection<ClientDependentDto> find( @RequestParam(required = false) String lienMast) {       
        List<ClientDependentDto> ClientDependentDtos = new ArrayList<>();
		Iterable<ClientDependent> selectedClientDependents = null;
		Integer lienMastInteger = Optional.ofNullable(lienMast).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
        if (lienMastInteger == null) {	
        	selectedClientDependents = this.ClientDependentService.findAll();
        	selectedClientDependents.forEach(p -> ClientDependentDtos.add(convertToDto(p)));
        }
        else {
        	selectedClientDependents = this.ClientDependentService.findByLienMast(lienMastInteger);
        }
        selectedClientDependents.forEach(p -> ClientDependentDtos.add(convertToDto(p)));
       
        return ClientDependentDtos;
    }
    @CrossOrigin
    @PutMapping("clientdependent/{idDep}")
    public ClientDependentDto updateClientDependent(@PathVariable("idDep") Integer idDep, @RequestBody ClientDependentDto updatedClientDependent) {
        ClientDependent ClientDependentEntity = convertToEntity(updatedClientDependent);
        return this.convertToDto(this.ClientDependentService.save(ClientDependentEntity));
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
        return this.convertToDto(ClientDependent);
    }
    protected ClientDependentDto convertToDto(ClientDependent entity) {
        ClientDependentDto dto = new ClientDependentDto(entity.getIdDep(),entity.getLienDis(), entity.getLienMast(),entity.getPrenom(), entity.getNom(), entity.getDatenais(),
    			entity.getDepTyp(), entity.getActif(), entity.getDeleted(), entity.getLienBanque(), entity.getRegio(), entity.getCivilite(), entity.getEq()
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
