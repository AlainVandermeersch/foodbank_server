package foodbank.it.web.controller;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Arrays;

import javax.transaction.Transactional;

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

import foodbank.it.persistence.model.Organisation;
import foodbank.it.service.IOrganisationService;
import foodbank.it.web.dto.OrganisationDto;

@RestController

public class OrganisationController {
	
	private IOrganisationService OrganisationService;
    
    public OrganisationController(IOrganisationService OrganisationService) {
        this.OrganisationService = OrganisationService;
       
    }
    @CrossOrigin
    @GetMapping("organisation/{idDis}")
    public OrganisationDto findOne(@PathVariable Integer idDis) {
        Organisation entity = OrganisationService.findByIdDis(idDis)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return convertToDto(entity);
    }
    
    @CrossOrigin
    @GetMapping("organisations/")
    public Collection<OrganisationDto> find( @RequestParam(required = false) String bankShortName ,@RequestParam(required = false) String idDis) {
        Iterable<Organisation> selectedOrganisations = null;
        List<OrganisationDto> OrganisationDtos = new ArrayList<>();
        if (bankShortName == null) {
        	if (idDis == null) {
        		selectedOrganisations = this.OrganisationService.findAll();
        		selectedOrganisations.forEach(p -> OrganisationDtos.add(convertToDto(p)));
        	}
        	else {
        		Optional<Organisation> myOrganisation = this.OrganisationService.findByIdDis(Integer.parseInt(idDis));
        		myOrganisation.ifPresent(org-> OrganisationDtos.add(convertToDto( org)));
        	}
        	
        }
        else {
        	selectedOrganisations = this.OrganisationService.findByBanqueObjectBankShortName(bankShortName);
        	// selectedOrganisations = this.OrganisationService.findAll();
        	selectedOrganisations.forEach(p -> OrganisationDtos.add(convertToDto(p)));
        }
        
        
        return OrganisationDtos;
    }
    @CrossOrigin
    @PutMapping("organisation/{idDis}")
    public OrganisationDto updateOrganisation(@PathVariable("idDis") Integer idDis, @RequestBody OrganisationDto updatedOrganisation) {
        Organisation OrganisationEntity = convertToEntity(updatedOrganisation);
        return this.convertToDto(this.OrganisationService.save(OrganisationEntity));
    }
    @CrossOrigin
    @DeleteMapping("organisation/{idDis}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrganisation(@PathVariable("idDis") Integer idDis) {
        OrganisationService.delete(idDis);
    }
    @CrossOrigin
    @PostMapping("organisation/")
    @ResponseStatus(HttpStatus.CREATED)
    public OrganisationDto create(@RequestBody OrganisationDto newOrganisation) {
        Organisation entity = convertToEntity(newOrganisation);
        // Alain todo later entity.setDateCreated(LocalDate.now());
        Organisation Organisation = this.OrganisationService.save(entity);        
        return this.convertToDto(Organisation);
    }
    protected OrganisationDto convertToDto(Organisation entity) {
        OrganisationDto dto = new OrganisationDto(entity.getIdDis(), entity.getRefInt(), entity.getBirbCode(), entity.getLienDepot(),
				entity.getSociete(), entity.getAdresse(), entity.getStatut(), entity.getEmail(),  entity.getCp(), entity.getLocalite(),
				entity.getPays(), entity.getTva(), entity.getWebsite(), entity.getTel(), entity.getGsm(), entity.getDaten(), 
				entity.getRegion(), entity.getIban(), entity.getClassique(), entity.getBic(), entity.getActif(), entity.getCivilite(), entity.getNom(),
				entity.getPrenom(), entity.getCiviliteVp(), entity.getPrenomVp(), entity.getNomVp(), entity.getTelVp(), entity.getGsmVp(),
				entity.getCiviliteSec(), entity.getPrenomSec(), entity.getNomSec(), entity.getTelSec(), entity.getGsmSec(), entity.getCiviliteTres(),
				entity.getPrenomTres(), entity.getNomTres(), entity.getTelTres(), entity.getGsmTres(), entity.getEmailPres(), entity.getEmailVp(),
				entity.getEmailSec(), entity.getEmailTres(), entity.getTelPres(), entity.getGsmPres(), entity.getDisprog(), entity.getAfsca(),
				entity.getWebauthority(), entity.getLangue(), entity.getLastvisit(), entity.getNbrefix(), entity.getCpasyN(), entity.getLienCpas(),
				entity.getBirbyN(), entity.getDepyN(), entity.getLogBirb(), entity.getActComp1(), entity.getActComp2(), entity.getActComp3(),
				entity.getActComp4(), entity.getActComp5(), entity.getActComp6(), entity.getActComp7(), entity.getNrTournee(), entity.getSusp(),
				entity.getStopSusp(), entity.getRem(), entity.getMsonac(), entity.getClasseFbba1(), entity.getClasseFbba2(), entity.getClasseFbba3(),
				entity.getNFam(), entity.getNPers(), entity.getNNour(), entity.getNBebe(), entity.getNEnf(), entity.getNAdo(), entity.getNEq(), entity.getNSen(), entity.getDepPrinc(),
				entity.getGestBen(), entity.getTourneeJour(), entity.getTourneeSem(), entity.getColdis(), entity.getLienGd(), entity.getLienGs(),
				entity.getMontCot(), entity.getAntenne(), entity.getAfsca1(), entity.getAfsca2(), entity.getAfsca3(), entity.getNrFead(),
				entity.getTourneeMois(), entity.getDistrListPdt(), entity.getDistrListVp(), entity.getDistrListSec(), entity.getDistrListTres(),
				entity.getAdresse2(), entity.getCp2(), entity.getLocalite2(), entity.getPays2(), entity.getDateReg(), entity.getFax(), entity.getFeadN(),
				entity.getRemLivr(), entity.getCotAnnuelle(), entity.getCotMonths(), entity.getCotSup(), entity.getCotMonthsSup(), entity.getDepotram(),
				entity.getLupdUserName(), entity.getLupdTs(),entity.getBanqueObject().getBankShortName(),entity.getBanqueObject().getBankName()  );    
        return dto;
    }

    protected Organisation convertToEntity(OrganisationDto dto) {
        Organisation organisation = new Organisation( dto.getIdDis(), dto.getRefInt(), dto.getBirbCode(), dto.getLienDepot(),
				dto.getSociete(), dto.getAdresse(), dto.getStatut(), dto.getEmail(), dto.getCp(), dto.getLocalite(),
				dto.getPays(), dto.getTva(), dto.getWebsite(), dto.getTel(), dto.getGsm(), dto.getDaten(), 
				dto.getRegion(), dto.getIban(), dto.getClassique(), dto.getBic(), dto.getActif(), dto.getCivilite(), dto.getNom(),
				dto.getPrenom(), dto.getCiviliteVp(), dto.getPrenomVp(), dto.getNomVp(), dto.getTelVp(), dto.getGsmVp(),
				dto.getCiviliteSec(), dto.getPrenomSec(), dto.getNomSec(), dto.getTelSec(), dto.getGsmSec(), dto.getCiviliteTres(),
				dto.getPrenomTres(), dto.getNomTres(), dto.getTelTres(), dto.getGsmTres(), dto.getEmailPres(), dto.getEmailVp(),
				dto.getEmailSec(), dto.getEmailTres(), dto.getTelPres(), dto.getGsmPres(), dto.getDisprog(), dto.getAfsca(),
				dto.isWebauthority(), dto.getLangue(), dto.getLastvisit(), dto.getNbrefix(), dto.getCpasyN(), dto.getLienCpas(),
				dto.getBirbyN(), dto.getDepyN(), dto.getLogBirb(), dto.getActComp1(), dto.getActComp2(), dto.getActComp3(),
				dto.getActComp4(), dto.getActComp5(), dto.getActComp6(), dto.getActComp7(), dto.getNrTournee(), dto.getSusp(),
				dto.getStopSusp(), dto.getRem(), dto.getMsonac(), dto.getClasseFbba1(), dto.getClasseFbba2(), dto.getClasseFbba3(),
				dto.getnFam(), dto.getnPers(), dto.getnNour(), dto.getnBebe(), dto.getnEnf(), dto.getnAdo(), dto.getnEq(), dto.getnSen(), dto.getDepPrinc(),
				dto.getGestBen(), dto.getTourneeJour(), dto.getTourneeSem(), dto.getColdis(), dto.getLienGd(), dto.getLienGs(),
				dto.getMontCot(), dto.getAntenne(), dto.getAfsca1(), dto.getAfsca2(), dto.getAfsca3(), dto.getNrFead(),
				dto.getTourneeMois(), dto.getDistrListPdt(), dto.getDistrListVp(), dto.getDistrListSec(), dto.getDistrListTres(),
				dto.getAdresse2(), dto.getCp2(), dto.getLocalite2(), dto.getPays2(), dto.getDateReg(), dto.getFax(), dto.getFeadN(),
				dto.getRemLivr(), dto.getCotAnnuelle(), dto.getCotMonths(), dto.getCotSup(), dto.getCotMonthsSup(), dto.getDepotram(),
				dto.getLupdUserName(), dto.getLupdTs());       
        if (!StringUtils.isEmpty(dto.getIdDis())) {
            organisation.setIdDis(dto.getIdDis());
        }
        return organisation;
    }


}
