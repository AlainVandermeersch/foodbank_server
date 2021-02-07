package foodbank.it.web.controller;

import static java.util.Objects.isNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Arrays;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import foodbank.it.persistence.model.Banque;
import foodbank.it.persistence.model.Membre;
import foodbank.it.service.IBanqueService;
import foodbank.it.service.IMembreService;
import foodbank.it.web.dto.MembreDto;

@RestController

public class MembreController {
	
	private IMembreService MembreService;
	private IBanqueService BanqueService;
    
    public MembreController(IMembreService MembreService, IBanqueService BanqueService) {
        this.MembreService = MembreService;
        this.BanqueService = BanqueService;
    }
    @CrossOrigin
    @GetMapping("membre/{batId}")
    public MembreDto findOne(@PathVariable Integer batId) {
        Membre entity = MembreService.findByBatId(batId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return convertToDto(entity,1);
    }
    
    @CrossOrigin
    @GetMapping("membres/")
    public Collection<MembreDto> find(@RequestParam String offset, @RequestParam String rows, @RequestParam(required = false) String bankShortName ,@RequestParam(required = false) String lienDis) {
    	int intOffset = Integer.parseInt(offset);
    	int intRows = Integer.parseInt(rows);
    	int pageNumber=intOffset/intRows; // Java throws away remainder of division
        int pageSize = intRows;
        Page<Membre> selectedMembres = null;
        List<MembreDto> MembreDtos = new ArrayList<>();
        if (bankShortName == null) {
        	if (lienDis == null) {
        		selectedMembres = this.MembreService.findAll(PageRequest.of(pageNumber, pageSize));
        		long totalRecords = selectedMembres.getTotalElements();
        		selectedMembres.forEach(p -> MembreDtos.add(convertToDto(p,totalRecords)));
        	}
        	else {
        		selectedMembres = this.MembreService.findByLienDis(lienDis, PageRequest.of(pageNumber, pageSize));
        		long totalRecords = selectedMembres.getTotalElements();
        		selectedMembres.forEach(p -> MembreDtos.add(convertToDto(p,totalRecords)));
        	}
        	
        }
        else {
        	selectedMembres = this.MembreService.findByBanqueObjectBankShortName(bankShortName,PageRequest.of(pageNumber, pageSize));
        	long totalRecords = selectedMembres.getTotalElements();
        	selectedMembres.forEach(p -> MembreDtos.add(convertToDto(p,totalRecords)));
        }
        
        
        return MembreDtos;
    }
    @CrossOrigin
    @PutMapping("membre/{batId}")
    public MembreDto updateMembre(@PathVariable("batId") Integer batId, @RequestBody MembreDto updatedMembre) {
        Membre MembreEntity = convertToEntity(updatedMembre);
        return this.convertToDto(this.MembreService.save(MembreEntity),1);
    }
    @CrossOrigin
    @DeleteMapping("membre/{batId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMembre(@PathVariable("batId") Integer batId) {
        MembreService.delete(batId);
    }
    @CrossOrigin
    @PostMapping("membre/")
    @ResponseStatus(HttpStatus.CREATED)
    public MembreDto create(@RequestBody MembreDto newMembre) {
        Membre entity = convertToEntity(newMembre);
        // Alain todo later entity.setDateCreated(LocalDate.now());
        Membre Membre = this.MembreService.save(entity);        
        return this.convertToDto(Membre,1);
    }
    protected MembreDto convertToDto(Membre entity,long totalRecords) {
    	String bankShortName = "";
    	String bankName = "";
    	Banque banqueObject = entity.getBanqueObject();
    	if ( ! isNull(banqueObject)) {
    		bankShortName = banqueObject.getBankShortName();
    		bankName = banqueObject.getBankName();
    	} 
        MembreDto dto = new MembreDto(entity.getBatId(),entity.getLienDis(), entity.getNom(), entity.getPrenom(), entity.getAddress(),
				entity.getCity(), entity.getZip(), entity.getTel(), entity.getGsm(),  entity.getBatmail(), entity.getVeh(),
				entity.getVehTyp(), entity.getVehImm(), entity.getFonction(), entity.getCa(), entity.getAg(), entity.getCg(),entity.getCivilite(), 
				entity.getPays(), entity.getActif(), entity.getAuthority(), entity.getDatmand(), entity.getRem(), entity.getLastVisit(), entity.getBen(),
				entity.getCodeAcces(), entity.getNrCodeAcces(), entity.getLangue(), entity.getDatedeb(), entity.getDateFin(), entity.getDeleted(),
				entity.getTypEmploi(), entity.getDateNaissance(), entity.getNnat(), entity.getDateContrat(), entity.getLDep(), bankShortName,bankName,totalRecords  );    
        return dto;
    }

    protected Membre convertToEntity(MembreDto dto) {
    	Banque banqueObject = this.BanqueService.findByBankShortName(dto.getBankShortName()).get();
    	    
    	Membre myMembre = new Membre( dto.getBatId(),dto.getLienDis(), dto.getNom(), dto.getPrenom(), dto.getAddress(),
				dto.getCity(), dto.getZip(), dto.getTel(), dto.getGsm(),  dto.getBatmail(), dto.getVeh(),
				dto.getVehTyp(), dto.getVehImm(), dto.getFonction(), dto.getCa(), dto.getAg(), dto.getCg(),dto.getCivilite(), 
				dto.getPays(), dto.getActif(), dto.getAuthority(), dto.getDatmand(), dto.getRem(), dto.getLastVisit(), dto.getBen(),
				dto.getCodeAcces(), dto.getNrCodeAcces(), dto.getLangue(), dto.getDatedeb(), dto.getDateFin(), dto.getDeleted(),
				dto.getTypEmploi(), dto.getDateNaissance(), dto.getNnat(), dto.getDateContrat(), dto.getLDep(),banqueObject);       
        if (!StringUtils.isEmpty(dto.getBatId())) {
            myMembre.setBatId(dto.getBatId());
        }
        return myMembre;
    }


}

	
