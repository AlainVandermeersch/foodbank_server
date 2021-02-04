package foodbank.it.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Arrays;

import javax.transaction.Transactional;

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
        return convertToDto(entity);
    }
    
    @CrossOrigin
    @GetMapping("membres/")
    public Collection<MembreDto> find( @RequestParam(required = false) String bankShortName ,@RequestParam(required = false) String batId) {
    	int pageNumber=0;
        int pageSize = 10;
        Iterable<Membre> selectedMembres = null;
        List<MembreDto> MembreDtos = new ArrayList<>();
        if (bankShortName == null) {
        	if (batId == null) {
        		selectedMembres = this.MembreService.findAll(PageRequest.of(pageNumber, pageSize));
        		selectedMembres.forEach(p -> MembreDtos.add(convertToDto(p)));
        	}
        	else {
        		Optional<Membre> myMembre = this.MembreService.findByBatId(Integer.parseInt(batId));
        		myMembre.ifPresent(org-> MembreDtos.add(convertToDto( org)));
        	}
        	
        }
        else {
        	selectedMembres = this.MembreService.findByBanqueObjectBankShortName(bankShortName,PageRequest.of(pageNumber, pageSize));
        	// selectedMembres = this.MembreService.findAll();
        	selectedMembres.forEach(p -> MembreDtos.add(convertToDto(p)));
        }
        
        
        return MembreDtos;
    }
    @CrossOrigin
    @PutMapping("membre/{batId}")
    public MembreDto updateMembre(@PathVariable("batId") Integer batId, @RequestBody MembreDto updatedMembre) {
        Membre MembreEntity = convertToEntity(updatedMembre);
        return this.convertToDto(this.MembreService.save(MembreEntity));
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
        return this.convertToDto(Membre);
    }
    protected MembreDto convertToDto(Membre entity) {
        MembreDto dto = new MembreDto(entity.getBatId(),entity.getLienDis(), entity.getNom(), entity.getPrenom(), entity.getAddress(),
				entity.getCity(), entity.getZip(), entity.getTel(), entity.getGsm(),  entity.getBatmail(), entity.getVeh(),
				entity.getVehTyp(), entity.getVehImm(), entity.getFonction(), entity.getCa(), entity.getAg(), entity.getCg(),entity.getCivilite(), 
				entity.getPays(), entity.getActif(), entity.getAuthority(), entity.getDatmand(), entity.getRem(), entity.getLastVisit(), entity.getBen(),
				entity.getCodeAcces(), entity.getNrCodeAcces(), entity.getLangue(), entity.getDatedeb(), entity.getDateFin(), entity.getDeleted(),
				entity.getTypEmploi(), entity.getDateNaissance(), entity.getNnat(), entity.getDateContrat(), entity.getLDep(), entity.getBanqueObject().getBankShortName(),entity.getBanqueObject().getBankName()  );    
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

	
