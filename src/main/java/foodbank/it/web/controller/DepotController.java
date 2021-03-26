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

import foodbank.it.persistence.model.Depot;
import foodbank.it.service.IDepotService;
import foodbank.it.web.dto.DepotDto;

@RestController

public class DepotController {
	
	private IDepotService DepotService;
		    
    public DepotController(IDepotService DepotService) {
        this.DepotService = DepotService;      
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
    public Collection<DepotDto> find( @RequestParam(required = false) String searchValue) {
        Iterable<Depot> selectedDepots = null;
        List<DepotDto> DepotDtos = new ArrayList<>();
        if (searchValue == null) {
        	selectedDepots = this.DepotService.findAll();
        }
        else {
        	selectedDepots = this.DepotService.findByNomContaining(searchValue);
        }
       	selectedDepots.forEach(p -> DepotDtos.add(convertToDto(p)));
        
        
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
    	
    	boolean booDepPrinc= entity.getDepPrinc() == 1;
    	boolean booActif= entity.getActif() == 1;
    	boolean booDepFead= entity.getDepFead() == 1;
    	
        DepotDto dto = new DepotDto(entity.getIdDepot(), entity.getNom(), entity.getAdresse(), entity.getAdresse2(), entity.getCp(), entity.getVille(),
    			entity.getTelephone(), entity.getContact(), entity.getEmail(), entity.getMemo(), booDepPrinc, booActif, booDepFead,
    			entity.getLienBanque() );    
        return dto;
    }

    protected Depot convertToEntity(DepotDto dto) {
    	    	    
    	Depot myDepot = new Depot( dto.getIdDepot(), dto.getNom(), dto.getAdresse(), dto.getAdresse2(), dto.getCp(), dto.getVille(),
    			dto.getTelephone(), dto.getContact(), dto.getEmail(), dto.getMemo(), 
    			(short) (dto.getDepPrinc() ? 1 : 0), (short) (dto.getActif() ? 1 : 0) , (short) (dto.getDepFead() ? 1 : 0),dto.getLienBanque());  
    	
        if (!StringUtils.isEmpty(dto.getIdDepot())) {
            myDepot.setIdDepot(dto.getIdDepot());
        }
        return myDepot;
    }


}

