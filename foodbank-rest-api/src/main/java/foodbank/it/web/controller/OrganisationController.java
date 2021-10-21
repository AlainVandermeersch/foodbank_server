package foodbank.it.web.controller;

import foodbank.it.persistence.model.OrgProgram;
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
    	
    	OrgProgram progOfOrg;
       	
    	Optional<OrgProgram> prog = this.OrgProgramService.findByLienDis(idDis);
    		if (prog.isPresent()) progOfOrg = prog.get() ;
    		else	progOfOrg = new OrgProgram(o.getIdDis(),o.getLienBanque(),o.getLienDepot());
       
         return convertToDto(o,progOfOrg,1);

    }
    

    @GetMapping("organisations/")
    public Collection<OrganisationDto> find( @RequestParam String offset, @RequestParam String rows, 
    		@RequestParam String sortField, @RequestParam String sortOrder, 
    		@RequestParam(required = false) String societe, @RequestParam(required = false) String adresse,
     		@RequestParam(required = false) String nomDepot,@RequestParam(required = false) String lienDepot,
     		@RequestParam(required = false) Boolean isDepot,@RequestParam(required = false) Boolean isBirb,
     		@RequestParam(required = false) Boolean agreed,
     		@RequestParam(required = false) Boolean actif,@RequestParam(required = false) String refint,
    		@RequestParam(required = false) String lienBanque ,
    		@RequestParam(required = false) String idDis) {
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
        // Note daten field means is reverse of Agreed
        Boolean daten = null;
        if (agreed != null) {
        	if (agreed == true) daten = false;
        	if ( agreed == false) daten = true;
        }
        Integer lienBanqueInteger = Optional.ofNullable(lienBanque).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
        Integer lienDepotInteger = Optional.ofNullable(lienDepot).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null); 
        
		SearchOrganisationCriteria criteria = new SearchOrganisationCriteria(societe, adresse, daten, actif, nomDepot, lienBanqueInteger, lienDepotInteger,isDepot,isBirb,refint);
		selectedOrganisations = this.OrganisationService.findAll(criteria,pageRequest);
		long totalElements = selectedOrganisations.getTotalElements();
       
		selectedOrganisations.forEach(o -> {
			OrgProgram progOfOrg = null;

			Optional<OrgProgram> prog = this.OrgProgramService.findByLienDis(o.getIdDis());
			if (prog.isPresent()) progOfOrg = prog.get() ;
			else	progOfOrg = new OrgProgram(o.getIdDis(),o.getLienBanque(),o.getLienDepot());
			OrganisationDtos.add(convertToDto(o,progOfOrg,totalElements));
		});
          
     
        return OrganisationDtos;
    }
  
    @GetMapping("orgsummaries/")
    public Collection<OrganisationSummaryDto> findSummaries( 
    		@RequestParam(required = false) String societe, 
    		@RequestParam(required = false) String lienBanque ) {
        Page<Organisation> selectedOrganisations = null;

        Pageable pageRequest = PageRequest.of(0, 300, Sort.by("societe").ascending());
       
        Integer lienBanqueInteger = Optional.ofNullable(lienBanque).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);        
        
		SearchOrganisationSummariesCriteria criteria = new SearchOrganisationSummariesCriteria(societe, lienBanqueInteger);
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
          OrgProgram entityProgr = convertToEntityProgram(updatedOrganisation);
           Organisation Organisation = this.OrganisationService.save(entity);  
           System.out.printf("We updated an Organisation with id: %d nom: %s in bank with id: %d\n", Organisation.getIdDis(), Organisation.getSociete(), Organisation.getLienBanque());
           // set Liendis from updated Org into OrgProgram object
           entityProgr.setLienDis(Organisation.getIdDis());
           OrgProgram OrgProgram = this.OrgProgramService.save(entityProgr);     
           return this.convertToDto(Organisation, OrgProgram,1);
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
       OrgProgram entityProgr = convertToEntityProgram(newOrganisation);
        Organisation Organisation = this.OrganisationService.save(entity); 
        System.out.printf("We created an Organisation with id: %d nom: %s in bank with id: %d\n", Organisation.getIdDis(), Organisation.getSociete(), Organisation.getLienBanque());
        // set Liendis from created Org into OrgProgram object
        entityProgr.setLienDis(Organisation.getIdDis());
        OrgProgram OrgProgram = this.OrgProgramService.save(entityProgr);     
        return this.convertToDto(Organisation, OrgProgram,1);
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
    	OrganisationSummaryDto dto = new OrganisationSummaryDto(entity.getIdDis(),entity.getSociete());
    	return dto;
    }
   
	protected OrganisationDto convertToDto(Organisation entity,OrgProgram entityOrgProgram,long totalRecords) {
		boolean booLuam= entityOrgProgram.getLuam() == 1;
		boolean booLupm= entityOrgProgram.getLupm() == 1;
		boolean booTuam= entityOrgProgram.getTuam() == 1;
		boolean booTupm= entityOrgProgram.getTupm() == 1;
		boolean booWeam= entityOrgProgram.getWeam() == 1;
		boolean booWepm= entityOrgProgram.getWepm() == 1;
		boolean booTham= entityOrgProgram.getTham() == 1;
		boolean booThpm= entityOrgProgram.getThpm() == 1;
		boolean booFram= entityOrgProgram.getFram() == 1;
		boolean booFrpm= entityOrgProgram.getFrpm() == 1;
		boolean booSaam= entityOrgProgram.getSaam() == 1;
		boolean booSapm= entityOrgProgram.getSapm() == 1;
		boolean booSuam= entityOrgProgram.getSunam() == 1;
		boolean booSupm= entityOrgProgram.getSunpm() == 1;
		boolean booPorc =entityOrgProgram.getPorc() == 1;
		boolean booLegFrais = entityOrgProgram.getLegFrais() == 1;
		boolean booCongel = entityOrgProgram.getCongel() == 1;
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
		  // Note daten field means is reverse of Agreed
		boolean booAgreed= entity.getDaten() == 0;
		
        OrganisationDto dto = new OrganisationDto(entity.getIdDis(), entity.getRefInt(), entity.getBirbCode(), entity.getLienDepot(),
				entity.getSociete(), entity.getAdresse(), entity.getStatut().trim(), entity.getEmail(),  entity.getCp(), entity.getLocalite(),
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
				entity.getAdresse2(), entity.getCp2(), entity.getLocalite2(), entity.getPays2(), entity.getDateReg(), entity.getFax(), entity.getFeadN(),
				entity.getRemLivr(), entity.getCotAnnuelle(), entity.getCotMonths(), entity.getCotSup(), entity.getCotMonthsSup(), entity.getDepotram(),
				entity.getLupdUserName(), entity.getLupdTs(),entity.getLienBanque(),entity.getNomDepot(),
				booLuam,
    			booLupm,
    			booTuam,
    			booTupm,
    			booWeam,
    			booWepm,
    			booTham,
    			booThpm,
    			booFram,
    			booFrpm,
    			booSaam,
    			booSapm,
    			booSuam,
    			booSupm,
    			entityOrgProgram.getReluam(),
    			entityOrgProgram.getRelupm(),
    			entityOrgProgram.getRetuam(),
    			entityOrgProgram.getRetupm(),
    			entityOrgProgram.getReweam(),
    			entityOrgProgram.getRewepm(),
    			entityOrgProgram.getRetham(),
    			entityOrgProgram.getRethpm(),
    			entityOrgProgram.getRefram(),
    			entityOrgProgram.getRefrpm(),
    			entityOrgProgram.getResaam(),
    			entityOrgProgram.getResapm(),
    			entityOrgProgram.getResunam(),
    			entityOrgProgram.getResunpm(),
    			booPorc,
    			booLegFrais,
    			booCongel,
    			entityOrgProgram.getCongelCap(),
    			entityOrgProgram.getAuditor(),
    			entityOrgProgram.getDateAudit(),
    			entityOrgProgram.getLastAudit(),
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
				dto.getAdresse2(), dto.getCp2(), dto.getLocalite2(), dto.getPays2(), dto.getDateReg(), dto.getFax(), dto.getFeadN(),
				dto.getRemLivr(), dto.getCotAnnuelle(), dto.getCotMonths(), dto.getCotSup(), dto.getCotMonthsSup(), dto.getDepotram(),
				dto.getLupdUserName(),dto.getLienBanque(),dto.getNomDepot());       
        if (!StringUtils.isEmpty(dto.getIdDis())) {
            myOrganisation.setIdDis(dto.getIdDis());
        }
        return myOrganisation;
    }
    private OrgProgram convertToEntityProgram(OrganisationDto dto) {
    	
       	 OrgProgram myOrgProgram = new OrgProgram(
       			dto.getIdDis(), 
            		dto.getLienBanque(),
        			dto.getLienDepot(),
        			(int) (dto.getLuam() ? 1 : 0),
         			(int) (dto.getLupm() ? 1 : 0),
         			(int) (dto.getTuam() ? 1 : 0),
         			(int) (dto.getTupm() ? 1 : 0),
         			(int) (dto.getWeam() ? 1 : 0),
         			(int) (dto.getWepm() ? 1 : 0),
         			(int) (dto.getTham() ? 1 : 0),
         			(int) (dto.getThpm() ? 1 : 0),
         			(int) (dto.getFram() ? 1 : 0),
         			(int) (dto.getFrpm() ? 1 : 0),
         			(int) (dto.getSaam() ? 1 : 0),
         			(int) (dto.getSapm() ? 1 : 0),
         			(int) (dto.getSunam() ? 1 : 0),
         			(int) (dto.getSunpm() ? 1 : 0),
        			dto.getReluam(),
        			dto.getRelupm(),
        			dto.getRetuam(),
        			dto.getRetupm(),
        			dto.getReweam(),
        			dto.getRewepm(),
        			dto.getRetham(),
        			dto.getRethpm(),
        			dto.getRefram(),
        			dto.getRefrpm(),
        			dto.getResaam(),
        			dto.getResapm(),
        			dto.getResunam(),
        			dto.getResunpm(),
        			(int) (dto.getPorc() ? 1 : 0),
         			(int) (dto.getLegFrais() ? 1 : 0),
         			(int) (dto.getCongel() ? 1 : 0),
        			dto.getCongelCap(),
        			dto.getAuditor(),
        			dto.getDateAudit(),
        			dto.getLastAudit()
       			 );
       			 if (!StringUtils.isEmpty(dto.getIdDis())) {
       		            myOrgProgram.setLienDis(dto.getIdDis());
       		        }
       		        return myOrgProgram;		 
   	}


}
