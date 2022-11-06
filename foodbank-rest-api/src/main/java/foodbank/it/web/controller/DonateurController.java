package foodbank.it.web.controller;

import foodbank.it.persistence.model.Donateur;
import foodbank.it.service.IDonateurService;
import foodbank.it.service.SearchDonateurCriteria;
import foodbank.it.web.dto.DonateurDto;
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

public class DonateurController {
	private IDonateurService DonateurService;
    
    public DonateurController(IDonateurService DonateurService) {
        this.DonateurService = DonateurService;
       
    }

    @GetMapping("donateur/{donateurId}")
    public DonateurDto findOne(@PathVariable Integer donateurId) {
        Donateur entity = DonateurService.findByDonateurId(donateurId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return convertToDto(entity, 1);
    }
    

    @GetMapping("donateurs/")
    public Collection<DonateurDto> find(@RequestParam String offset, @RequestParam String rows, 
    		@RequestParam String sortField, @RequestParam String sortOrder, 
    		@RequestParam(required = false) String nom,@RequestParam(required = false) String prenom, 
     		@RequestParam(required = false) String adresse,@RequestParam(required = false) String cp, 
     		@RequestParam(required = false) String city,@RequestParam(required = false) String lienBanque) {
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
        
        SearchDonateurCriteria criteria = new SearchDonateurCriteria(nom, prenom, adresse,cp,city,lienBanqueInteger);
        Page<Donateur> selectedDonateurs = this.DonateurService.findAll(criteria, pageRequest);
		long totalElements = selectedDonateurs.getTotalElements();

		return selectedDonateurs.stream()
				.map(Donateur -> convertToDto(Donateur, totalElements))
				.collect(Collectors.toList());

        
        
       
    }

   
	@PutMapping("donateur/{donateurId}")
    public DonateurDto updateDonateur(@PathVariable("donateurId") Integer donateurId, @RequestBody DonateurDto updatedDonateur) throws Exception {
        Donateur DonateurEntity = convertToEntity(updatedDonateur);
        return this.convertToDto(this.DonateurService.save(DonateurEntity),1);
    }

    @DeleteMapping("donateur/{donateurId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDonateur(@PathVariable("donateurId") Integer donateurId) throws Exception {
    	try {
        DonateurService.delete(donateurId);
    	}
        catch (Exception ex) {
         	String errorMsg = ex.getMessage();
         	System.out.println(errorMsg);
     		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMsg);
         }
    }

    @PostMapping("donateur/")
    @ResponseStatus(HttpStatus.CREATED)
    public DonateurDto create(@RequestBody DonateurDto newDonateur) throws Exception {
        Donateur entity = convertToEntity(newDonateur);
        Donateur createdDonateur = this.DonateurService.save(entity);        
        return this.convertToDto(createdDonateur,1);
    }
    private Donateur convertToEntity(DonateurDto dto) {
    	Donateur myDonateur = new Donateur( dto.getDonateurId(), dto.getLienBanque(), dto.getNom(), dto.getPrenom(), dto.getAdresse(), dto.getCp(), dto.getCity(),
    			dto.getPays(), dto.getTitre());
    	 return myDonateur;
    }

	private DonateurDto convertToDto(Donateur entity,long totalRecords) {
		DonateurDto dto = new DonateurDto(entity.getDonateurId(), entity.getLienBanque(), entity.getNom(), entity.getPrenom(), entity.getAdresse(), entity.getCp(), entity.getCity(),
    			entity.getPays(), entity.getTitre(),totalRecords);
		return dto;
	}

}
