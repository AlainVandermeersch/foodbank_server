package foodbank.it.web.controller;

import foodbank.it.persistence.model.Function;
import foodbank.it.persistence.model.Membre;
import foodbank.it.persistence.repository.IFunctionRepository;
import foodbank.it.service.IMembreService;
import foodbank.it.service.SearchMembreCriteria;
import foodbank.it.web.dto.MembreDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController

public class MembreController {
	
	private IMembreService MembreService;
	private IFunctionRepository FunctionRepository;
		
    
    public MembreController(IMembreService MembreService, IFunctionRepository FunctionRepository) {
        this.MembreService = MembreService;
		this.FunctionRepository = FunctionRepository;
    }

    @GetMapping("membre/{batId}")
    public MembreDto findOne(@PathVariable Integer batId) {
        Membre entity = MembreService.findByBatId(batId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return convertToDto(entity,1);
    }
    @GetMapping("membresall/")
    public Collection<MembreDto> findAll(
			@RequestParam(required = false) String nom, @RequestParam(required = false) String batmail,
			@RequestParam(required = false) String address,@RequestParam(required = false) String zip,
			@RequestParam(required = false) String city,@RequestParam(required = false) String lienDepot ,
			@RequestParam(required = false) Boolean actif,@RequestParam(required = false) String bankShortName,
			@RequestParam(required = false) String hasAnomalies,@RequestParam(required = false) Boolean classicBanks,
			@RequestParam(required = false) String fonction ,@RequestParam(required = false) String telgsm ,
			@RequestParam(required = false) String lienBanque ,@RequestParam(required = false) String lienDis,
			@RequestParam(required = false) String lDep	) { // lDep is a specific flag for bank members assigned to a depot)
    	List<Membre> selectedMembres;
		Integer lienBanqueInteger = Optional.ofNullable(lienBanque).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
		Integer lienDisInteger = Optional.ofNullable(lienDis).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
		Integer lienDepotInteger = Optional.ofNullable(lienDepot).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
		Integer lienDepInteger = Optional.ofNullable(lDep).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
		Integer fonctionInteger = Optional.ofNullable(fonction).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
		SearchMembreCriteria  criteria= new SearchMembreCriteria();
		criteria.setNom(nom);
		criteria.setActif(actif);
		criteria.setAddress(address);
		criteria.setZip(zip);
		criteria.setCity(city);
		criteria.setBatmail(batmail);
		criteria.setLienBanque(lienBanqueInteger);
		criteria.setLienDis(lienDisInteger);
		criteria.setLienDepot(lienDepotInteger);
		criteria.setlDep(lienDepInteger);
		criteria.setBankShortName(bankShortName);
		criteria.setTelgsm(telgsm);
		criteria.setFonction(fonctionInteger);
		criteria.setHasAnomalies(hasAnomalies);
		criteria.setClassicBanks(classicBanks);
    	selectedMembres = (List<Membre>) this.MembreService.findAll(criteria);


    	return selectedMembres.stream()
				.map(Membre -> convertToDto(Membre, selectedMembres.size()))
				.collect(Collectors.toList());
    }

    @GetMapping("membres/")
    public Collection<MembreDto> findPaged(@RequestParam String offset, @RequestParam String rows,
    		@RequestParam String sortField, @RequestParam String sortOrder, 
    		@RequestParam(required = false) String nom, @RequestParam(required = false) String batmail,
     		@RequestParam(required = false) String address,@RequestParam(required = false) String zip, 
     		@RequestParam(required = false) String city,@RequestParam(required = false) String lienDepot ,
     		@RequestParam(required = false) Boolean actif,@RequestParam(required = false) String bankShortName,
     		@RequestParam(required = false) String hasAnomalies,@RequestParam(required = false) Boolean classicBanks,
	        @RequestParam(required = false) String fonction ,@RequestParam(required = false) String telgsm ,
    		@RequestParam(required = false) String lienBanque ,@RequestParam(required = false) String lienDis,
			@RequestParam(required = false) String lDep	) { // lDep is a specific flag for bank members assigned to a depot
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
		Integer lienDepInteger = Optional.ofNullable(lDep).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
		Integer fonctionInteger = Optional.ofNullable(fonction).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
		SearchMembreCriteria  criteria= new SearchMembreCriteria();
		criteria.setNom(nom);
		criteria.setActif(actif);
		criteria.setAddress(address);
		criteria.setZip(zip);
		criteria.setCity(city);
		criteria.setBatmail(batmail);
		criteria.setLienBanque(lienBanqueInteger);
		criteria.setLienDis(lienDisInteger);
		criteria.setLienDepot(lienDepotInteger);
		criteria.setlDep(lienDepInteger);
		criteria.setBankShortName(bankShortName);
		criteria.setTelgsm(telgsm);
		criteria.setFonction(fonctionInteger);
		criteria.setHasAnomalies(hasAnomalies);
		criteria.setClassicBanks(classicBanks);
		Page<Membre> selectedMembres = this.MembreService.findPaged(criteria, pageRequest);
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
		boolean booBen= entity.getBen() == 1;
		boolean booCa= entity.getCa() == 1;
		boolean booAg= entity.getAg() == 1;
		boolean booCg= entity.getCg() == 1;

		String functionNameFr = "";
		String functionNameNl = "";
		String functionType = "";
		Function functionOfMember = entity.getFonctionObj();
       if (functionOfMember != null) {
		   functionNameFr = functionOfMember.getFonctionName();
		   functionNameNl = functionOfMember.getFonctionNameNl();
		   functionType = functionOfMember.getBankShortName();
	   }
	   MembreDto dto = new MembreDto();
	   String dtoLastVisit = "";
		if (entity.getLastVisit() != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			dtoLastVisit = entity.getLastVisit().format(formatter);
		}
	   dto.setBatId(entity.getBatId());
	   dto.setLienDis(entity.getLienDis());
	   dto.setNom(entity.getNom());
	   dto.setPrenom(entity.getPrenom());
	   dto.setAddress(entity.getAddress());
	   dto.setCity(entity.getCity());
	   dto.setZip(entity.getZip());
	   dto.setTel(entity.getTel());
	   dto.setGsm(entity.getGsm());
	   dto.setBatmail(entity.getBatmail());
	   dto.setVeh(entity.getVeh());
	   dto.setVehTyp(entity.getVehTyp());
	   dto.setVehImm(entity.getVehImm());
	   dto.setFonction(entity.getFonction());
	   dto.setCa(booCa);
	   dto.setAg(booAg);
	   dto.setCg(booCg);
	   dto.setCivilite(entity.getCivilite());
	   dto.setPays(entity.getPays());
	   dto.setActif(booActif);
	   dto.setAuthority(entity.getAuthority());
	   dto.setDatmand(entity.getDatmand());
	   dto.setRem(entity.getRem());
	   dto.setBen(booBen);
	   dto.setCodeAcces(entity.getCodeAcces());
	   dto.setNrCodeAcces(entity.getNrCodeAcces());
	   dto.setLangue(entity.getLangue());
	   dto.setDatedeb(entity.getDatedeb());
	   dto.setDateFin(entity.getDateFin());
	   dto.setDeleted(entity.getDeleted());
	   dto.setTypEmploi(entity.getTypEmploi());
	   dto.setDateNaissance(entity.getDateNaissance());
	   dto.setNnat(entity.getNnat());
	   dto.setDateContrat(entity.getDateContrat());
	   dto.setLDep(entity.getLDep());
	   dto.setLastVisit(dtoLastVisit);
	   dto.setLienBanque(entity.getLienBanque());
	   dto.setSociete(entity.getSociete());
	   dto.setBankShortName(entity.getBankShortName());
	   dto.setFonctionType(functionType);
	   dto.setFonctionName(functionNameFr);
	   dto.setFonctionNameNl(functionNameNl);
	   dto.setNbUsers(entity.getNbUsers());
	   dto.setTotalRecords(totalRecords  );

	   return dto;
    }

    protected Membre convertToEntity(MembreDto dto) {
		Function functionOfMembre = null;
		Integer funcId = dto.getFonction();
		if (funcId != null) {
			Optional<Function> fonction = this.FunctionRepository.findByFuncId(funcId);
			if (fonction.isPresent()) functionOfMembre = fonction.get();
		}
    	Membre myMembre = new Membre();
			myMembre.setBatId(dto.getBatId());
			myMembre.setLienDis(dto.getLienDis());
			myMembre.setNom(dto.getNom());
			myMembre.setPrenom(dto.getPrenom());
			myMembre.setAddress(dto.getAddress());
			myMembre.setCity(dto.getCity());
			myMembre.setZip(dto.getZip());
			myMembre.setTel(dto.getTel());
			myMembre.setGsm(dto.getGsm());
			myMembre.setBatmail(dto.getBatmail());
			myMembre.setVeh(dto.getVeh());
			myMembre.setVehTyp(dto.getVehTyp());
			myMembre.setVehImm(dto.getVehImm());
			myMembre.setFonctionObj(functionOfMembre);
			myMembre.setCa((short) (dto.isCa() ? 1 : 0));
			myMembre.setAg((short) (dto.isAg() ? 1 : 0));
			myMembre.setCg((short) (dto.isCg() ? 1 : 0));
			myMembre.setCivilite(dto.getCivilite());
			myMembre.setPays(dto.getPays());
			myMembre.setActif((short) (dto.getActif() ? 1 : 0));
			myMembre.setAuthority(dto.getAuthority());
			myMembre.setDatmand(dto.getDatmand());
			myMembre.setRem(dto.getRem());
			myMembre.setBen((short) (dto.isBen() ? 1 : 0));
			myMembre.setCodeAcces(dto.getCodeAcces());
			myMembre.setNrCodeAcces(dto.getNrCodeAcces());
			myMembre.setLangue(dto.getLangue());
			myMembre.setDatedeb(dto.getDatedeb());
			myMembre.setDateFin(dto.getDateFin());
			myMembre.setDeleted(dto.getDeleted());
			myMembre.setTypEmploi(dto.getTypEmploi());
			myMembre.setDateNaissance(dto.getDateNaissance());
			myMembre.setNnat(dto.getNnat());
			myMembre.setDateContrat(dto.getDateContrat());
			myMembre.setLDep(dto.getldep());
			myMembre.setLienBanque(dto.getLienBanque());
			myMembre.setLastVisit(LocalDateTime.now()); // ignore last visit from dto, we need to update with current time

        return myMembre;
    }


}

	
