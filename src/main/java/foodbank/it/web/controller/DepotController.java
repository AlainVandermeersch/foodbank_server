package foodbank.it.web.controller;
import static java.util.Objects.isNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

import foodbank.it.persistence.model.Banque;
import foodbank.it.persistence.model.Depot;
import foodbank.it.service.IBanqueService;
import foodbank.it.service.IDepotService;
import foodbank.it.web.dto.DepotDto;

@RestController

public class DepotController {
	
	private IDepotService DepotService;
	private IBanqueService BanqueService;
	    
    public DepotController(IDepotService DepotService, IBanqueService BanqueService) {
        this.DepotService = DepotService;
        this.BanqueService = BanqueService;
    }
    @CrossOrigin
    @GetMapping("depot/{idDepot}")
    public DepotDto findOne(@PathVariable String idDepot) {
        Depot entity = DepotService.findByIdDepot(idDepot)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return convertToDto(entity);
    }
    
    @CrossOrigin
    @GetMapping("depots/")
    public Collection<DepotDto> find( @RequestParam(required = false) String bankShortName) {
        Iterable<Depot> selectedDepots = null;
        List<DepotDto> DepotDtos = new ArrayList<>();
        if (bankShortName == null) {
        		selectedDepots = this.DepotService.findAll();
        		selectedDepots.forEach(p -> DepotDtos.add(convertToDto(p)));
        }
        else {
        	selectedDepots = this.DepotService.findByBanqueObjectBankShortName(bankShortName);
        	selectedDepots.forEach(p -> DepotDtos.add(convertToDto(p)));
        }
        
        
        return DepotDtos;
    }
    @CrossOrigin
    @PutMapping("depot/{idDepot}")
    public DepotDto updateDepot(@PathVariable("idDepot") Integer idDepot, @RequestBody DepotDto updatedDepot) {
        Depot DepotEntity = convertToEntity(updatedDepot);
        return this.convertToDto(this.DepotService.save(DepotEntity));
    }
    @CrossOrigin
    @DeleteMapping("depot/{idDepot}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDepot(@PathVariable("idDepot") String idDepot) {
        DepotService.delete(idDepot);
    }
    @CrossOrigin
    @PostMapping("depot/")
    @ResponseStatus(HttpStatus.CREATED)
    public DepotDto create(@RequestBody DepotDto newDepot) {
        Depot entity = convertToEntity(newDepot);
        // Alain todo later entity.setDateCreated(LocalDate.now());
        Depot Depot = this.DepotService.save(entity);        
        return this.convertToDto(Depot);
    }
    protected DepotDto convertToDto(Depot entity) {
    	String bankShortName = "";
    	String bankName = "";
    	Banque banqueObject = entity.getBanqueObject();
    	if ( ! isNull(banqueObject)) {
    		bankShortName = banqueObject.getBankShortName();
    		bankName = banqueObject.getBankName();
    	} 
    	
        DepotDto dto = new DepotDto(entity.getIdDepot(), entity.getNom(), entity.getAdresse(), entity.getAdresse2(), entity.getCp(), entity.getVille(),
    			entity.getTelephone(), entity.getContact(), entity.getEmail(), entity.getMemo(), entity.getDepPrinc(), entity.getActif(), entity.getDepFead(),
    			bankShortName,bankName );    
        return dto;
    }

    protected Depot convertToEntity(DepotDto dto) {
    	Banque banqueObject = this.BanqueService.findByBankShortName(dto.getBankShortName()).get();
    	    
    	Depot myDepot = new Depot( dto.getIdDepot(), dto.getNom(), dto.getAdresse(), dto.getAdresse2(), dto.getCp(), dto.getVille(),
    			dto.getTelephone(), dto.getContact(), dto.getEmail(), dto.getMemo(), dto.getDepPrinc(), dto.getActif(), dto.getDepFead(),banqueObject);       
        if (!StringUtils.isEmpty(dto.getIdDepot())) {
            myDepot.setIdDepot(dto.getIdDepot());
        }
        return myDepot;
    }


}

