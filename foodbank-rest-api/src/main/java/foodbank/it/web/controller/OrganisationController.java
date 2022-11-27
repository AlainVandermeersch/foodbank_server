package foodbank.it.web.controller;

import foodbank.it.persistence.model.Organisation;
import foodbank.it.persistence.repository.IDepotRepository;
import foodbank.it.service.IOrgProgramService;
import foodbank.it.service.IOrganisationService;
import foodbank.it.service.SearchOrganisationCriteria;
import foodbank.it.service.SearchOrganisationSummariesCriteria;
import foodbank.it.web.dto.OrgClientReportDto;
import foodbank.it.web.dto.OrgMemberReportDto;
import foodbank.it.web.dto.OrganisationDto;
import foodbank.it.web.dto.OrganisationSummaryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@RestController

public class OrganisationController {
	
	private final IOrganisationService OrganisationService;
	private final IOrgProgramService OrgProgramService;

	private final IDepotRepository DepotRepository;
	private  boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	    
    public OrganisationController(
    		IOrganisationService OrganisationService,
    		IOrgProgramService OrgProgramService ,
			IDepotRepository DepotRepository) {
        this.OrganisationService = OrganisationService;  
        this.OrgProgramService = OrgProgramService;
		this.DepotRepository = DepotRepository;
    }

    @GetMapping("organisation/{idDis}")
    public OrganisationDto findOne(@PathVariable Integer idDis) {
    	Organisation o = OrganisationService.findByIdDis(idDis)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));    	
    	
         return convertToDto(o,1);

    }
    

    @GetMapping("organisations/")
    public Collection<OrganisationDto> find( @RequestParam String offset, @RequestParam String rows, 
    		@RequestParam String sortField, @RequestParam String sortOrder, 
    		@RequestParam(required = false) String societe, @RequestParam(required = false) String adresse,
     		@RequestParam(required = false) String nomDepot,@RequestParam(required = false) String lienDepot,
			 @RequestParam(required = false) String nomDepotRamasse,@RequestParam(required = false) String birbCode,
     		@RequestParam(required = false) Boolean isDepot,@RequestParam(required = false) Boolean isFead,
     		@RequestParam(required = false) Boolean agreed,@RequestParam(required = false) String regId,
     		@RequestParam(required = false) Boolean actif,@RequestParam(required = false) String refint,
     		@RequestParam(required = false) Boolean gestBen,@RequestParam(required = false) Boolean feadN,
     		@RequestParam(required = false) Boolean cotAnnuelle,@RequestParam(required = false) Boolean cotSup,
     		@RequestParam(required = false) String classeFBBA,@RequestParam(required = false) String statut,
     		@RequestParam(required = false) String bankShortName,@RequestParam(required = false) Boolean hasLogins,
     		@RequestParam(required = false) String lienBanque,@RequestParam(required = false) String idDis) {
        Page<Organisation> selectedOrganisations = null;
        List<OrganisationDto> OrganisationDtos = new ArrayList<>();
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
        
        Integer regIdInteger = Optional.ofNullable(regId).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
        Integer classeFBBAInteger = Optional.ofNullable(classeFBBA).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
        Integer lienBanqueInteger = Optional.ofNullable(lienBanque).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
        Integer lienDepotInteger = null; 
        if (lienDepot != null && this.isNumeric(lienDepot)) {
        	lienDepotInteger = Integer.parseInt(lienDepot );
        }
        Integer idDisInteger = null;
        
        if (idDis != null && this.isNumeric(idDis)) {
        	idDisInteger = Integer.parseInt(idDis );
        }

		SearchOrganisationCriteria criteria = new SearchOrganisationCriteria(idDisInteger,regIdInteger, classeFBBAInteger,societe, adresse, agreed, actif, 
				nomDepot, nomDepotRamasse,lienBanqueInteger, lienDepotInteger,isDepot, isFead,birbCode,refint,cotAnnuelle,cotSup,statut,gestBen,feadN,bankShortName,hasLogins);
		selectedOrganisations = this.OrganisationService.findAll(criteria,pageRequest);
		long totalElements = selectedOrganisations.getTotalElements();
       
		selectedOrganisations.forEach(o -> {			
			OrganisationDtos.add(convertToDto(o,totalElements));
		});
          
     
        return OrganisationDtos;
    }
    @GetMapping("orgsummary/{idDis}")
    public OrganisationSummaryDto findOneSummary(@PathVariable Integer idDis) {
    	Organisation o = OrganisationService.findByIdDis(idDis)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));    	
       
         return convertToSummaryDto(o);

    }
    
  
    @GetMapping("orgsummaries/")
    public Collection<OrganisationSummaryDto> findSummaries( 
    		@RequestParam(required = false) String societe, 
    		@RequestParam(required = false) String lienBanque ,
    		@RequestParam(required = false) String bankShortName,
    		@RequestParam(required = false) String lienDepot,
    		@RequestParam(required = false) Boolean agreed,
    		@RequestParam(required = false) Boolean actif,
    		@RequestParam(required = false) Boolean isDepot,
			@RequestParam(required = false)  Boolean depotMissing,
    		@RequestParam(required = false) Boolean cotType,
    		@RequestParam(required = false) String regId,
    		@RequestParam(required = false) Boolean feadN
    		) {
        Page<Organisation> selectedOrganisations = null;

        Pageable pageRequest = PageRequest.of(0, 300, Sort.by("societe").ascending());
       
        Integer lienBanqueInteger = Optional.ofNullable(lienBanque).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);  
        Integer lienDepotInteger = Optional.ofNullable(lienDepot).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
		Integer regIdInteger = Optional.ofNullable(regId).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
		Boolean isDepotGlobal = isDepot;
		if (depotMissing != null)
		{
			isDepotGlobal = true;
		}
		SearchOrganisationSummariesCriteria criteria = new SearchOrganisationSummariesCriteria(societe, lienBanqueInteger,lienDepotInteger,
				isDepotGlobal,agreed,actif,cotType,regIdInteger,feadN,bankShortName);
		selectedOrganisations = this.OrganisationService.findSummaries(criteria,pageRequest);
		
		List<OrganisationSummaryDto> organisationSummaryDtos = new ArrayList<>();
		{
			selectedOrganisations.forEach(o -> {
				if (depotMissing != null) {
					String depotId = String.valueOf(o.getIdDis());
					boolean depotPresent = this.DepotRepository.findByIdDepot(depotId).isPresent();
					if (depotPresent == false) {
						organisationSummaryDtos.add(convertToSummaryDto(o));
					}

				}
				else {
					organisationSummaryDtos.add(convertToSummaryDto(o));
				}
			});
	
		}
		return organisationSummaryDtos;
        
    }
    @CrossOrigin
    @PutMapping("organisation/{idDis}")
    public OrganisationDto updateOrganisation(@PathVariable("idDis") Integer idDis, @RequestBody OrganisationDto updatedOrganisation) {
    	  Organisation entity = convertToEntity(updatedOrganisation);
           Organisation Organisation = this.OrganisationService.save(entity);  
           System.out.printf("We updated an Organisation with id: %d nom: %s in bank with id: %d\n", Organisation.getIdDis(), Organisation.getSociete(), Organisation.getLienBanque());

           return this.convertToDto(Organisation, 1);
    }

    @DeleteMapping("organisation/{idDis}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrganisation(@PathVariable("idDis") Integer idDis) {
    	 try {
    		 this.OrganisationService.delete(idDis);
    		 this.OrgProgramService.deleteByLienDis(idDis);
    	 }
         catch (Exception ex) {
         	String errorMsg = ex.getMessage();
         	System.out.println(errorMsg);
     		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMsg);
         }
        
    }

    @PostMapping("organisation/")
    @ResponseStatus(HttpStatus.CREATED)
    public OrganisationDto create(@RequestBody OrganisationDto newOrganisation) {
        Organisation entity = convertToEntity(newOrganisation);
        Organisation Organisation = this.OrganisationService.save(entity); 
        System.out.printf("We created an Organisation with id: %d nom: %s in bank with id: %d\n", Organisation.getIdDis(), Organisation.getSociete(), Organisation.getLienBanque());
          return this.convertToDto(Organisation, 1);
    }
    
    @GetMapping("orgreport/members/")
    public List<OrgMemberReportDto> AllOrgMemberReport(@RequestParam(required = false) String lienBanque) {
    	Integer lienBanqueInteger = Optional.ofNullable(lienBanque).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
    	List<OrgMemberReportDto> lista = this.OrganisationService.OrgMemberReport(lienBanqueInteger);
    
    	 return lista;
    	  
    }
    @GetMapping("orgreport/clients/")
    public List<OrgClientReportDto> AllOrgClientReport(@RequestParam(required = false) String lienBanque) {
    	Integer lienBanqueInteger = Optional.ofNullable(lienBanque).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
    	List<Organisation> listOrgs = this.OrganisationService.OrgClientReport(lienBanqueInteger);
    	List<OrgClientReportDto> orgClientReportDtos = new ArrayList<>();
		{
			listOrgs.forEach(o -> {
				orgClientReportDtos.add(convertToClientReportDto(o));
			});
	
		}
		return orgClientReportDtos;
    
    	
    	  
    }

    @GetMapping("orgreport/orgclients/")
    public OrgClientReportDto OneOrgClientReport(@RequestParam String idDis) {
    	int lienDisInteger = Integer.parseInt(idDis);
    	Organisation o = OrganisationService.findByIdDis(lienDisInteger)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	
    	return convertToClientReportDto(o);	
	  
    }	
    			
    private OrgClientReportDto convertToClientReportDto(Organisation o) {
    	OrgClientReportDto dto = new OrgClientReportDto(o.getSociete(), o.getNPers(), o.getNFam(), o.getNNour(), o.getNBebe(), o.getNEnf(),
    			o.getNAdo(), o.getN1824(), o.getNSen());
		return dto;
	}

	protected OrganisationSummaryDto convertToSummaryDto(Organisation entity) {
    	OrganisationSummaryDto dto = new OrganisationSummaryDto(entity.getIdDis(),entity.getSociete(),entity.getBankShortName());
    	return dto;
    }
   
	protected OrganisationDto convertToDto(Organisation entity,long totalRecords) {
		boolean booIsMsonac = entity.getMsonac() == 1;
		boolean booCpasyN = entity.getCpasyN() == 1;
		boolean booGestBen = entity.getGestBen() == 1;
		boolean booDepPrinc = entity.getDepPrinc() == 1;
		boolean booDistrListPdt = entity.getDistrListPdt() == 1;
		boolean booDistrListVp = entity.getDistrListVp() == 1;
		boolean booDistrListSec = entity.getDistrListSec() == 1;
		boolean booDistrListTres = entity.getDistrListTres() == 1;
		boolean booBirbyN = entity.getBirbyN() == 1;
		boolean booDepyN = entity.getDepyN() == 1;
		boolean booActif = entity.getActif() == 1;
		boolean booSusp = entity.getSusp() == 1;
		boolean booCotAnnuelle = entity.getCotAnnuelle() == 1;
		boolean booCotSup = entity.getCotSup() == 1;
		boolean booFeadN = entity.getFeadN() == 1;
		  // Note daten field means is reverse of Agreed
		boolean booAgreed = false;
		if (entity.getDaten() != null) {
			booAgreed= entity.getDaten() == 0;
		}
		String strStatut = "";
		if ( entity.getStatut() != null) {
			strStatut = entity.getStatut().trim();
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		String lastVisit = "";
		if (entity.getLastvisit() != null) {
			lastVisit = entity.getLastvisit().format(formatter);
		}
		String lupTs = "";
		if (entity.getLupdTs() != null) {
			lupTs = entity.getLupdTs().format(formatter);
		}
	 // antenneOrgName is the name of the parent organisation
	  String antenneOrgName ="";
		// birb is the old name for fead - we are retrieving the organisation Fead Code here
	  String birbCode = entity.getBirbCode();
	  // the Fead Code 1 has a special meaning: this organisation is a "Antenne" aka a subsidiary of a parent organisation
	  if ((birbCode != null ) && (birbCode.equals("1")  )) {
		  Integer antenne = entity.getAntenne();
		  // antenne field should contain the Fead code of the parent organisation
		  if ((antenne != null) && (antenne > 1)) {
			  // find the parent organisation
			  Optional<Organisation> orgAntenne = this.OrganisationService.findByIdDis(antenne);
			  if (orgAntenne.isPresent()) antenneOrgName = orgAntenne.get().getIdDis() + " " + orgAntenne.get().getSociete();
		  }
	  }

		String anomalies = this.OrganisationService.getAnomalies(entity);

        OrganisationDto dto = new OrganisationDto();
				dto.setIdDis(entity.getIdDis());
				dto.setRefInt(entity.getRefInt());
				dto.setBirbCode(entity.getBirbCode());
				dto.setLienDepot(entity.getLienDepot());
				dto.setSociete(entity.getSociete());
				dto.setAdresse(entity.getAdresse());
				dto.setStatut(strStatut);
				dto.setEmail(entity.getEmail());
				dto.setCp(entity.getCp());
				dto.setLocalite(entity.getLocalite());
				dto.setPays(entity.getPays());
				dto.setTva(entity.getTva());
				dto.setWebsite(entity.getWebsite());
				dto.setTel(entity.getTel());
				dto.setGsm(entity.getGsm());
				dto.setAgreed(booAgreed);
				dto.setBanque(entity.getBanque());
				dto.setRegion(entity.getRegion());
				dto.setIban(entity.getIban());
				dto.setClassique(entity.getClassique());
				dto.setBic(entity.getBic());
				dto.setActif(booActif);
				dto.setCivilite(entity.getCivilite());
				dto.setNom(entity.getNom());
				dto.setPrenom(entity.getPrenom());
				dto.setCiviliteVp(entity.getCiviliteVp());
				dto.setPrenomVp(entity.getPrenomVp());
				dto.setNomVp(entity.getNomVp());
				dto.setTelVp(entity.getTelVp());
				dto.setGsmVp(entity.getGsmVp());
				dto.setCiviliteSec(entity.getCiviliteSec());
				dto.setPrenomSec(entity.getPrenomSec());
				dto.setNomSec(entity.getNomSec());
				dto.setTelSec(entity.getTelSec());
				dto.setGsmSec(entity.getGsmSec());
				dto.setCiviliteTres(entity.getCiviliteTres());
				dto.setPrenomTres(entity.getPrenomTres());
				dto.setNomTres(entity.getNomTres());
				dto.setTelPres(entity.getTelTres());
				dto.setGsmTres(entity.getGsmTres());
				dto.setEmailPres(entity.getEmailPres());
				dto.setEmailVp(	entity.getEmailVp());
				dto.setEmailSec(entity.getEmailSec());
				dto.setEmailTres(entity.getEmailTres());
				dto.setTelPres(entity.getTelPres());
				dto.setGsmPres(entity.getGsmPres());
				dto.setDisprog(entity.getDisprog());
				dto.setAfsca(entity.getAfsca());
				dto.setWebauthority(entity.getWebauthority());
				dto.setLangue(entity.getLangue());
				dto.setLastvisit(lastVisit);
				dto.setNbrefix(entity.getNbrefix());
				dto.setCpasyN(booCpasyN);
				dto.setLienCpas(entity.getLienCpas());
				dto.setBirbyN(booBirbyN);
				dto.setDepyN(booDepyN);
				dto.setLogBirb(entity.getLogBirb());
				dto.setActComp1(entity.getActComp1());
				dto.setActComp2(entity.getActComp2());
				dto.setActComp3(entity.getActComp3());
				dto.setActComp4(entity.getActComp4());
				dto.setActComp5(entity.getActComp5());
				dto.setActComp6(entity.getActComp6());
				dto.setActComp7(entity.getActComp7());
				dto.setNrTournee(entity.getNrTournee());
				dto.setSusp(booSusp);
				dto.setStopSusp(entity.getStopSusp());
				dto.setRem(entity.getRem());
				dto.setMsonac(booIsMsonac);
				dto.setClasseFbba1(entity.getClasseFbba1());
				dto.setClasseFbba2(entity.getClasseFbba2());
				dto.setClasseFbba3(entity.getClasseFbba3());
				dto.setnFam(entity.getNFam());
				dto.setnPers(entity.getNPers());
				dto.setnNour(entity.getNNour());
				dto.setnBebe(entity.getNBebe());
				dto.setnEnf(entity.getNEnf());
				dto.setnAdo(entity.getNAdo());
				dto.setN1824(entity.getN1824());
				dto.setnEq(entity.getNEq());
				dto.setnSen(entity.getNSen());
				dto.setDepPrinc(booDepPrinc);
				dto.setGestBen(booGestBen);
				dto.setTourneeJour(entity.getTourneeJour());
				dto.setTourneeSem(entity.getTourneeSem());
				dto.setColdis(entity.getColdis());
				dto.setLienGd(entity.getLienGd());
				dto.setLienGs(entity.getLienGs());
				dto.setMontCot(entity.getMontCot());
				dto.setAntenne(entity.getAntenne());
				dto.setAfsca1(entity.getAfsca1());
				dto.setAfsca2(entity.getAfsca2());
				dto.setAfsca3(entity.getAfsca3());
				dto.setNrFead(entity.getNrFead());
				dto.setTourneeMois(entity.getTourneeMois());
				dto.setDistrListPdt(booDistrListPdt);
				dto.setDistrListVp(booDistrListVp);
				dto.setDistrListSec(booDistrListSec);
				dto.setDistrListTres(booDistrListTres);
				dto.setAdresse2(entity.getAdresse2());
				dto.setCp2(entity.getCp2());
				dto.setLocalite2(entity.getLocalite2());
				dto.setPays2(entity.getPays2());
				dto.setDateReg(entity.getDateReg());
				dto.setFax(entity.getFax());
				dto.setFeadN(booFeadN);
				dto.setRemLivr(entity.getRemLivr());
				dto.setCotAnnuelle(booCotAnnuelle);
				dto.setCotMonths(entity.getCotMonths());
				dto.setCotSup(booCotSup);
				dto.setCotMonthsSup(entity.getCotMonthsSup());
				dto.setDepotram(entity.getDepotram());
				dto.setLupdUserName(entity.getLupdUserName());
				dto.setLupdTs(lupTs);
				dto.setLienBanque(entity.getLienBanque());
				dto.setNomDepot(entity.getNomDepot());
				dto.setNomDepotRamasse(entity.getNomDepotRamasse());
    			dto.setBankShortName(entity.getBankShortName());
				dto.setAntenneOrgName(antenneOrgName);
    			dto.setNbLogins(entity.getNbLogins());
				dto.setAnomalies(anomalies);
    			dto.setTotalRecords(totalRecords);
        return dto;
    }

    protected Organisation convertToEntity(OrganisationDto dto) {
    	  // Note daten field means is reverse of Agreed
    	  // Organisation name field ( societe) is always converted to uppercase
		Organisation org = new Organisation();
		org.setIdDis(dto.getIdDis());
		org.setRefInt(dto.getRefInt());
		org.setBirbCode(dto.getBirbCode());
		org.setLienDepot(dto.getLienDepot());
		org.setSociete(dto.getSociete());
		org.setAdresse(dto.getAdresse());
		org.setStatut(dto.getStatut());
		org.setEmail(dto.getEmail());
		org.setCp(dto.getCp());
		org.setLocalite(dto.getLocalite());
		org.setPays(dto.getPays());
		org.setTva(dto.getTva());
		org.setWebsite(dto.getWebsite());
		org.setTel(dto.getTel());
		org.setGsm(dto.getGsm());
		org.setDaten((short) (dto.getAgreed() ? 0 : 1));
		org.setBanque(dto.getBanque());
		org.setRegion(dto.getRegion());
		org.setIban(dto.getIban());
		org.setClassique(dto.getClassique());
		org.setBic(dto.getBic());
		org.setActif((short) (dto.getActif() ? 1 : 0));
		org.setCivilite(dto.getCivilite());
		org.setNom(dto.getNom());
		org.setPrenom(dto.getPrenom());
		org.setCiviliteVp(dto.getCiviliteVp());
		org.setPrenomVp(dto.getPrenomVp());
		org.setNomVp(dto.getNomVp());
		org.setTelVp(dto.getTelVp());
		org.setGsmVp(dto.getGsmVp());
		org.setCiviliteSec(dto.getCiviliteSec());
		org.setPrenomSec(dto.getPrenomSec());
		org.setNomSec(dto.getNomSec());
		org.setTelSec(dto.getTelSec());
		org.setGsmSec(dto.getGsmSec());
		org.setCiviliteTres(dto.getCiviliteTres());
		org.setPrenomTres(dto.getPrenomTres());
		org.setNomTres(dto.getNomTres());
		org.setTelPres(dto.getTelTres());
		org.setGsmTres(dto.getGsmTres());
		org.setEmailPres(dto.getEmailPres());
		org.setEmailVp(	dto.getEmailVp());
		org.setEmailSec(dto.getEmailSec());
		org.setEmailTres(dto.getEmailTres());
		org.setTelPres(dto.getTelPres());
		org.setGsmPres(dto.getGsmPres());
		org.setDisprog(dto.getDisprog());
		org.setAfsca(dto.getAfsca());
		org.setWebauthority(dto.isWebauthority());
		org.setLastvisit(LocalDateTime.now());
		org.setLangue(dto.getLangue());
		org.setNbrefix(dto.getNbrefix());
		org.setCpasyN((short) (dto.getCpasyN() ? 1 : 0));
		org.setLienCpas(dto.getLienCpas());
		org.setBirbyN((short) (dto.isBirbyN() ? 1 : 0));
		org.setDepyN((short) (dto.getDepyN() ? 1 : 0));
		org.setLogBirb(dto.getLogBirb());
		org.setActComp1(dto.getActComp1());
		org.setActComp2(dto.getActComp2());
		org.setActComp3(dto.getActComp3());
		org.setActComp4(dto.getActComp4());
		org.setActComp5(dto.getActComp5());
		org.setActComp6(dto.getActComp6());
		org.setActComp7(dto.getActComp7());
		org.setNrTournee(dto.getNrTournee());
		org.setSusp((short) (dto.getSusp() ? 1 : 0));
		org.setStopSusp(dto.getStopSusp());
		org.setRem(dto.getRem());
		org.setMsonac((short) (dto.getMsonac() ? 1 : 0));
		org.setClasseFbba1(dto.getClasseFbba1());
		org.setClasseFbba2(dto.getClasseFbba2());
		org.setClasseFbba3(dto.getClasseFbba3());
		org.setNFam(dto.getnFam());
		org.setNPers(dto.getnPers());
		org.setNNour(dto.getnNour());
		org.setNBebe(dto.getnBebe());
		org.setNEnf(dto.getnEnf());
		org.setNAdo(dto.getnAdo());
		org.setN1824(dto.getN1824());
		org.setNEq(dto.getnEq());
		org.setNSen(dto.getnSen());
		org.setDepPrinc((short) (dto.getDepPrinc() ? 1 : 0));
		org.setGestBen((short) (dto.getGestBen() ? 1 : 0));
		org.setTourneeJour(dto.getTourneeJour());
		org.setTourneeSem(dto.getTourneeSem());
		org.setColdis(dto.getColdis());
		org.setLienGd(dto.getLienGd());
		org.setLienGs(dto.getLienGs());
		org.setMontCot(dto.getMontCot());
		org.setAntenne(dto.getAntenne());
		org.setAfsca1(dto.getAfsca1());
		org.setAfsca2(dto.getAfsca2());
		org.setAfsca3(dto.getAfsca3());
		org.setNrFead(dto.getNrFead());
		org.setTourneeMois(dto.getTourneeMois());
		org.setDistrListPdt((short) (dto.getDistrListPdt() ? 1 : 0));
		org.setDistrListVp((short) (dto.getDistrListVp() ? 1 : 0));
		org.setDistrListSec((short) (dto.getDistrListSec() ? 1 : 0));
		org.setDistrListTres((short) (dto.getDistrListTres() ? 1 : 0));
		org.setAdresse2(dto.getAdresse2());
		org.setCp2(dto.getCp2());
		org.setLocalite2(dto.getLocalite2());
		org.setPays2(dto.getPays2());
		org.setDateReg(dto.getDateReg());
		org.setFax(dto.getFax());
		org.setFeadN((short) (dto.isFeadN() ? 1 : 0));
		org.setRemLivr(dto.getRemLivr());
		org.setCotAnnuelle((short) (dto.isCotAnnuelle() ? 1 : 0));
		org.setCotMonths(dto.getCotMonths());
		org.setCotSup((int) (dto.isCotSup() ? 1 : 0));
		org.setCotMonthsSup(dto.getCotMonthsSup());
		org.setDepotram(dto.getDepotram());
		org.setLupdUserName(dto.getLupdUserName());
		org.setLienBanque(dto.getLienBanque());

        return org;
    }
    


}
