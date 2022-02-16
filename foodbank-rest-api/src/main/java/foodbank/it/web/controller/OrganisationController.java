package foodbank.it.web.controller;

import foodbank.it.persistence.model.Organisation;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@RestController

public class OrganisationController {
	
	private final IOrganisationService OrganisationService;
	private final IOrgProgramService OrgProgramService;
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
    		IOrgProgramService OrgProgramService ) {
        this.OrganisationService = OrganisationService;  
        this.OrgProgramService = OrgProgramService;
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
     		@RequestParam(required = false) Boolean isDepot,@RequestParam(required = false) Boolean isBirb,
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
				nomDepot, lienBanqueInteger, lienDepotInteger,isDepot,isBirb,refint,cotAnnuelle,cotSup,statut,gestBen,feadN,bankShortName,hasLogins);
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
    		@RequestParam(required = false) Boolean cotType,
    		@RequestParam(required = false) String regId,
    		@RequestParam(required = false) Boolean feadN
    		) {
        Page<Organisation> selectedOrganisations = null;

        Pageable pageRequest = PageRequest.of(0, 300, Sort.by("societe").ascending());
       
        Integer lienBanqueInteger = Optional.ofNullable(lienBanque).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);  
        Integer lienDepotInteger = Optional.ofNullable(lienDepot).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null); 
        Integer regIdInteger = Optional.ofNullable(regId).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
		SearchOrganisationSummariesCriteria criteria = new SearchOrganisationSummariesCriteria(societe, lienBanqueInteger,lienDepotInteger,
				isDepot,agreed,actif,cotType,regIdInteger,feadN,bankShortName);
		selectedOrganisations = this.OrganisationService.findSummaries(criteria,pageRequest);
		
		List<OrganisationSummaryDto> organisationSummaryDtos = new ArrayList<>();
		{
			selectedOrganisations.forEach(o -> {
				organisationSummaryDtos.add(convertToSummaryDto(o));
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
	
		
        OrganisationDto dto = new OrganisationDto(entity.getIdDis(), entity.getRefInt(), entity.getBirbCode(), entity.getLienDepot(),
				entity.getSociete(), entity.getAdresse(), strStatut , entity.getEmail(),  entity.getCp(), entity.getLocalite(),
				entity.getPays(), entity.getTva(), entity.getWebsite(), entity.getTel(), entity.getGsm(), booAgreed,entity.getBanque(), 
				entity.getRegion(), entity.getIban(), entity.getClassique(), entity.getBic(), booActif, entity.getCivilite(), entity.getNom(),
				entity.getPrenom(), entity.getCiviliteVp(), entity.getPrenomVp(), entity.getNomVp(), entity.getTelVp(), entity.getGsmVp(),
				entity.getCiviliteSec(), entity.getPrenomSec(), entity.getNomSec(), entity.getTelSec(), entity.getGsmSec(), entity.getCiviliteTres(),
				entity.getPrenomTres(), entity.getNomTres(), entity.getTelTres(), entity.getGsmTres(), entity.getEmailPres(), entity.getEmailVp(),
				entity.getEmailSec(), entity.getEmailTres(), entity.getTelPres(), entity.getGsmPres(), entity.getDisprog(), entity.getAfsca(),
				entity.getWebauthority(), entity.getLangue(), entity.getLastvisit(), entity.getNbrefix(),booCpasyN, entity.getLienCpas(),
				booBirbyN,booDepyN, entity.getLogBirb(), entity.getActComp1(), entity.getActComp2(), entity.getActComp3(),
				entity.getActComp4(), entity.getActComp5(), entity.getActComp6(), entity.getActComp7(), entity.getNrTournee(), booSusp,
				entity.getStopSusp(), entity.getRem(), booIsMsonac, entity.getClasseFbba1(), entity.getClasseFbba2(), entity.getClasseFbba3(),
				entity.getNFam(), entity.getNPers(), entity.getNNour(), entity.getNBebe(), entity.getNEnf(), entity.getNAdo(), entity.getN1824(),
				entity.getNEq(), entity.getNSen(), booDepPrinc,
				booGestBen, entity.getTourneeJour(), entity.getTourneeSem(), entity.getColdis(), entity.getLienGd(), entity.getLienGs(),
				entity.getMontCot(), entity.getAntenne(), entity.getAfsca1(), entity.getAfsca2(), entity.getAfsca3(), entity.getNrFead(),
				entity.getTourneeMois(), booDistrListPdt, booDistrListVp, booDistrListSec, booDistrListTres,
				entity.getAdresse2(), entity.getCp2(), entity.getLocalite2(), entity.getPays2(), entity.getDateReg(), entity.getFax(), booFeadN,
				entity.getRemLivr(), booCotAnnuelle, entity.getCotMonths(), booCotSup, entity.getCotMonthsSup(), entity.getDepotram(),
				entity.getLupdUserName(), entity.getLupdTs(),entity.getLienBanque(),entity.getNomDepot(),
				
    			entity.getBankShortName(),
    			entity.getNbLogins(),
    			totalRecords);   
        return dto;
    }

    protected Organisation convertToEntity(OrganisationDto dto) {
    	  // Note daten field means is reverse of Agreed	    
    	Organisation myOrganisation = new Organisation( dto.getIdDis(), dto.getRefInt(),  dto.getBirbCode(), dto.getLienDepot(),
				dto.getSociete(), dto.getAdresse(), dto.getStatut(), dto.getEmail(), dto.getCp(), dto.getLocalite(),
				dto.getPays(), dto.getTva(), dto.getWebsite(), dto.getTel(), dto.getGsm(), (short) (dto.getAgreed() ? 0 : 1), dto.getBanque(),
				dto.getRegion(), dto.getIban(), dto.getClassique(), dto.getBic(), (short) (dto.getActif() ? 1 : 0), dto.getCivilite(), dto.getNom(),
				dto.getPrenom(), dto.getCiviliteVp(), dto.getPrenomVp(), dto.getNomVp(), dto.getTelVp(), dto.getGsmVp(),
				dto.getCiviliteSec(), dto.getPrenomSec(), dto.getNomSec(), dto.getTelSec(), dto.getGsmSec(), dto.getCiviliteTres(),
				dto.getPrenomTres(), dto.getNomTres(), dto.getTelTres(), dto.getGsmTres(), dto.getEmailPres(), dto.getEmailVp(),
				dto.getEmailSec(), dto.getEmailTres(), dto.getTelPres(), dto.getGsmPres(), dto.getDisprog(), dto.getAfsca(),
				dto.isWebauthority(), dto.getLangue(),  dto.getNbrefix(),(short) (dto.getCpasyN() ? 1 : 0), dto.getLienCpas(),
				(short) (dto.isBirbyN() ? 1 : 0), (short) (dto.getDepyN() ? 1 : 0), dto.getLogBirb(), dto.getActComp1(), dto.getActComp2(), dto.getActComp3(),
				dto.getActComp4(), dto.getActComp5(), dto.getActComp6(), dto.getActComp7(), dto.getNrTournee(), (short) (dto.getSusp() ? 1 : 0),
				dto.getStopSusp(), dto.getRem(), (short) (dto.getMsonac() ? 1 : 0), dto.getClasseFbba1(), dto.getClasseFbba2(), dto.getClasseFbba3(),
				dto.getnFam(), dto.getnPers(), dto.getnNour(), dto.getnBebe(), dto.getnEnf(), dto.getnAdo(), dto.getN1824(),
				dto.getnEq(), dto.getnSen(), (short) (dto.getDepPrinc() ? 1 : 0),
				(short) (dto.getGestBen() ? 1 : 0), dto.getTourneeJour(), dto.getTourneeSem(), dto.getColdis(), dto.getLienGd(), dto.getLienGs(),
				dto.getMontCot(), dto.getAntenne(), dto.getAfsca1(), dto.getAfsca2(), dto.getAfsca3(), dto.getNrFead(),
				dto.getTourneeMois(), (short) (dto.getDistrListPdt() ? 1 : 0), (short) (dto.getDistrListVp() ? 1 : 0), (short) (dto.getDistrListSec() ? 1 : 0),(short) (dto.getDistrListTres() ? 1 : 0),
				dto.getAdresse2(), dto.getCp2(), dto.getLocalite2(), dto.getPays2(), dto.getDateReg(), dto.getFax(), (short) (dto.isFeadN() ? 1 : 0),
				dto.getRemLivr(), (short) (dto.isCotAnnuelle() ? 1 : 0), dto.getCotMonths(),(int) (dto.isCotSup() ? 1 : 0), dto.getCotMonthsSup(), dto.getDepotram(),
				dto.getLupdUserName(),dto.getLienBanque(),dto.getNomDepot());       
        if (!StringUtils.isEmpty(dto.getIdDis())) {
            myOrganisation.setIdDis(dto.getIdDis());
        }
        return myOrganisation;
    }
    


}
