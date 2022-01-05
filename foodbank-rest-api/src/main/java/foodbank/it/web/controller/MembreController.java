package foodbank.it.web.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
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

import foodbank.it.persistence.model.Membre;
import foodbank.it.service.IMembreService;
import foodbank.it.service.SearchMembreCriteria;
import foodbank.it.service.SearchMailListCriteria;
import foodbank.it.web.dto.MembreDto;
import foodbank.it.web.dto.MailAddressDto;

@RestController

public class MembreController {
	
	private IMembreService MembreService;
		
    
    public MembreController(IMembreService MembreService) {
        this.MembreService = MembreService;     
       
    }

    @GetMapping("membre/{batId}")
    public MembreDto findOne(@PathVariable Integer batId) {
        Membre entity = MembreService.findByBatId(batId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return convertToDto(entity,1);
    }
    

    @GetMapping("membres/")
    public Collection<MembreDto> find(@RequestParam String offset, @RequestParam String rows, 
    		@RequestParam String sortField, @RequestParam String sortOrder, 
    		@RequestParam(required = false) String nom, 
     		@RequestParam(required = false) String address,@RequestParam(required = false) String zip, 
     		@RequestParam(required = false) String city,@RequestParam(required = false) String lienDepot ,
     		@RequestParam(required = false) Boolean actif,
    		@RequestParam(required = false) String lienBanque ,@RequestParam(required = false) String lienDis) {
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
        Integer lienDisInteger = Optional.ofNullable(lienDis).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
        Integer lienDepotInteger = Optional.ofNullable(lienDepot).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
		SearchMembreCriteria criteria = new SearchMembreCriteria(nom, actif,address, zip,city, lienBanqueInteger, lienDisInteger,lienDepotInteger);
		Page<Membre> selectedMembres = this.MembreService.findAll(criteria, pageRequest);
		long totalElements = selectedMembres.getTotalElements();

		return selectedMembres.stream()
				.map(Membre -> convertToDto(Membre, totalElements))
				.collect(Collectors.toList());
    }
   
    
	@PutMapping("membre/{batId}")
    public MembreDto updateMembre(@PathVariable("batId") Integer batId, @RequestBody MembreDto updatedMembre) {
        Membre entity = convertToEntity(updatedMembre);
        boolean booCreateMode = false;
        try {
            Membre Membre = this.MembreService.save(entity,booCreateMode);        
            return this.convertToDto(Membre,1);
            }
            catch (Exception ex) {
            	String errorMsg = ex.getMessage();
            	System.out.println(errorMsg);
        		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMsg);
            }
    }

    @DeleteMapping("membre/{batId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMembre(@PathVariable("batId") Integer batId) {
    	 try {
    		 MembreService.delete(batId);
    	 }
         catch (Exception ex) {
         	String errorMsg = ex.getMessage();
         	System.out.println(errorMsg);
     		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMsg);
         }
    }

    @PostMapping("membre/")
    @ResponseStatus(HttpStatus.CREATED)
    public MembreDto create(@RequestBody MembreDto newMembre) {
        Membre entity = convertToEntity(newMembre);
        
        boolean booCreateMode = true;
        try {
        Membre Membre = this.MembreService.save(entity,booCreateMode);        
        return this.convertToDto(Membre,1);
        }
        catch (Exception ex) {
        	String errorMsg = ex.getMessage();
        	System.out.println(errorMsg);
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMsg);
        }
    }
 
    protected MembreDto convertToDto(Membre entity,long totalRecords) {   
    	boolean booActif= entity.getActif() == 1;
    	
        MembreDto dto = new MembreDto(entity.getBatId(),entity.getLienDis(), entity.getNom(), entity.getPrenom(), entity.getAddress(),
				entity.getCity(), entity.getZip(), entity.getTel(), entity.getGsm(),  entity.getBatmail(), entity.getVeh(),
				entity.getVehTyp(), entity.getVehImm(), entity.getFonction(), entity.getCa(), entity.getAg(), entity.getCg(),entity.getCivilite(), 
				entity.getPays(), booActif, entity.getAuthority(), entity.getDatmand(), entity.getRem(),  entity.getBen(),
				entity.getCodeAcces(), entity.getNrCodeAcces(), entity.getLangue(), entity.getDatedeb(), entity.getDateFin(), entity.getDeleted(),
				entity.getTypEmploi(), entity.getDateNaissance(), entity.getNnat(), entity.getDateContrat(), entity.getLDep(),entity.getLastVisit(),entity.getLienBanque(),entity.getSociete(),totalRecords  );    
        return dto;
    }

    protected Membre convertToEntity(MembreDto dto) {  
    	
    	    	    
    	Membre myMembre = new Membre( dto.getBatId(),dto.getLienDis(), dto.getNom(), dto.getPrenom(), dto.getAddress(),
				dto.getCity(), dto.getZip(), dto.getTel(), dto.getGsm(),  dto.getBatmail(), dto.getVeh(),
				dto.getVehTyp(), dto.getVehImm(), dto.getFonction(), dto.getCa(), dto.getAg(), dto.getCg(),dto.getCivilite(), 
				dto.getPays(), (short) (dto.getActif() ? 1 : 0), dto.getAuthority(), dto.getDatmand(), dto.getRem(),  dto.getBen(),
				dto.getCodeAcces(), dto.getNrCodeAcces(), dto.getLangue(), dto.getDatedeb(), dto.getDateFin(), dto.getDeleted(),
				dto.getTypEmploi(), dto.getDateNaissance(), dto.getNnat(), dto.getDateContrat(), dto.getLDep(),dto.getLienBanque());       
        if (!StringUtils.isEmpty(dto.getBatId())) {
            myMembre.setBatId(dto.getBatId());
        } 
        return myMembre;
    }


}

	
