package foodbank.it.web.controller;

import foodbank.it.persistence.model.ClientDependent;
import foodbank.it.service.IClientDependentService;
import foodbank.it.service.SearchClientDependentCriteria;
import foodbank.it.web.dto.ClientDependentDto;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController

public class ClientDependentController {
	
	private IClientDependentService ClientDependentService;
    
    public ClientDependentController(IClientDependentService ClientDependentService) {
        this.ClientDependentService = ClientDependentService;
       
    }

    @GetMapping("dependent/{idDep}")
    public ClientDependentDto findOne(@PathVariable Integer idDep) {
        ClientDependent entity = this.ClientDependentService.findByIdDep(idDep)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return convertToDto(entity);
    }
    
    

    @GetMapping("dependents/")
    public Iterable<ClientDependentDto> find(
			@RequestParam String lienMast,
			@RequestParam(required = false) String actif
			) {
    	List<ClientDependentDto> clientDependentDtos = new ArrayList<>();
		Integer lienMastInteger = Integer.parseInt(lienMast);
		Integer actifInteger = Optional.ofNullable(actif).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
		SearchClientDependentCriteria criteria = new SearchClientDependentCriteria(lienMastInteger, actifInteger);
		Iterable<ClientDependent> selectedClientDependents = this.ClientDependentService.findAll(criteria);
		selectedClientDependents.forEach(p -> clientDependentDtos.add(convertToDto(p)));
		return clientDependentDtos;

    }

    @PutMapping("dependent/{idDep}")
    public ClientDependentDto updateClientDependent(@PathVariable("idDep") Integer idDep, @RequestBody ClientDependentDto updatedClientDependent) {
        ClientDependent ClientDependentEntity = convertToEntity(updatedClientDependent);
        return this.convertToDto(this.ClientDependentService.save(ClientDependentEntity));
    }

    @DeleteMapping("dependent/{idDep}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClientDependent(@PathVariable("idDep") Integer idDep) {
        this.ClientDependentService.delete(idDep);
    }

    @PostMapping("dependent/")
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDependentDto create(@RequestBody ClientDependentDto newClientDependent) {
        ClientDependent entity = convertToEntity(newClientDependent);
        ClientDependent ClientDependent = this.ClientDependentService.save(entity);        
        return this.convertToDto(ClientDependent);
    }
    protected ClientDependentDto convertToDto(ClientDependent entity) {
        ClientDependentDto dto = new ClientDependentDto();
            dto.setIdDep(entity.getIdDep());
            dto.setLienDis(entity.getLienDis());
            dto.setLienMast(entity.getLienMast());
            dto.setPrenom(entity.getPrenom());
            dto.setNom(entity.getNom());
            dto.setDatenais(entity.getDatenais());
            dto.setDepTyp(entity.getDepTyp());
            dto.setActif(entity.getActif());
            dto.setDeleted(entity.getDeleted());
            dto.setLienBanque(entity.getLienBanque());
            dto.setRegio(entity.getRegio());
            dto.setCivilite(entity.getCivilite());
            dto.setEq(entity.getEq());

        return dto;
    }

    protected ClientDependent convertToEntity(ClientDependentDto dto) {
        ClientDependent cDep = new ClientDependent();
            cDep.setIdDep(dto.getIdDep());
            cDep.setLienDis(dto.getLienDis());
            cDep.setLienMast(dto.getLienMast());
            cDep.setPrenom(dto.getPrenom());
            cDep.setNom(dto.getNom());
            cDep.setDatenais(dto.getDatenais());
            cDep.setDepTyp(dto.getDepTyp());
            cDep.setActif(dto.isActif());
            cDep.setDeleted(dto.isDeleted());
            cDep.setLienBanque(dto.getLienBanque());
            cDep.setRegio(dto.getRegio());
            cDep.setCivilite(dto.getCivilite());
            cDep.setEq(dto.getEq());

        return cDep;
    }

}
