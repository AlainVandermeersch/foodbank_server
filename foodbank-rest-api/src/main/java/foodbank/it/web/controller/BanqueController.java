package foodbank.it.web.controller;

import foodbank.it.persistence.model.Banque;
import foodbank.it.persistence.model.BanqueCount;
import foodbank.it.service.IBanqueService;
import foodbank.it.web.dto.BanqueDto;
import foodbank.it.web.dto.BanqueFeadReportDto;
import foodbank.it.web.dto.BanqueOrgCountDto;
import foodbank.it.web.dto.BanqueClientReportDto;
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

public class BanqueController {

    private IBanqueService BanqueService;

    public BanqueController(IBanqueService BanqueService) {
        this.BanqueService = BanqueService;

    }

    @GetMapping("banque/{bankId}")
    public BanqueDto findOne(@PathVariable Integer bankId) {
        Banque entity = BanqueService.findByBankId(bankId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return convertToDto(entity);
    }

//    @RolesAllowed({"admin", "admin_banq"})
    @GetMapping(value = "banque/getByShortName/{bankShortName}")
    public BanqueDto findOne(@PathVariable String bankShortName) {
        Banque entity = BanqueService.findByBankShortName(bankShortName)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return convertToDto(entity);
    }


    @GetMapping("banques/")
    public Collection<BanqueDto> find(
            @RequestParam( required = false) String bankShortName,
            @RequestParam( required = false) Boolean classicBanks
    ) {
        List<BanqueDto> BanqueDtos = new ArrayList<>();
        if (bankShortName == null) {
               Iterable<Banque> selectedBanques = this.BanqueService.findAll(classicBanks);
                selectedBanques.forEach(p -> BanqueDtos.add(convertToDto(p)));
        }
        else {
        	Optional<Banque> myBanque = this.BanqueService.findByBankShortName(bankShortName);
    		myBanque.ifPresent(banque-> BanqueDtos.add(convertToDto( banque)));
        }


        return BanqueDtos;
    }

    @PutMapping("banque/{bankId}")
    public BanqueDto updateBanque(@PathVariable("bankId") Integer bankId, @RequestBody BanqueDto updatedBanque) {
        Banque BanqueEntity = convertToEntity(updatedBanque);
        return this.convertToDto(this.BanqueService.save(BanqueEntity));
    }

    @DeleteMapping("banque/{bankId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBanque(@PathVariable("bankId") Integer bankId) {
    	 try {
    		 BanqueService.delete(bankId);
    	 }
         catch (Exception ex) {
         	String errorMsg = ex.getMessage();
         	System.out.println(errorMsg);
     		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMsg);
         }

    }

    @PostMapping("banque/")
    @ResponseStatus(HttpStatus.CREATED)
    public BanqueDto create(@RequestBody BanqueDto newBanque) {
        Banque entity = convertToEntity(newBanque);
        // Alain todo later entity.setDateCreated(LocalDate.now());
        Banque Banque = this.BanqueService.save(entity);
        return this.convertToDto(Banque);
    }
    @GetMapping("banqueOrgCount/")
    public List<BanqueCount> OrgReport(
    		@RequestParam(required = false) String filter) {
    	
    	List<BanqueCount> listBanqueCount = this.BanqueService.reportOrgCount(filter, null);
    	    	
		return listBanqueCount;       	
    	  
    }
    @GetMapping("banqueMembreCount/")
    public List<BanqueOrgCountDto> MembreReport(
            @RequestParam( required = false) String bankShortName
    ) {
    	
    	List<BanqueOrgCountDto> listBanqueCount = this.BanqueService.reportMembreCount(bankShortName);
    	    	
		return listBanqueCount;       	
    	  
    }
    @GetMapping("banqueUserCount/")
    public List<BanqueOrgCountDto> UserReport(
            @RequestParam( required = false) String bankShortName
    ) {

    	List<BanqueOrgCountDto> listBanqueCount = this.BanqueService.reportTUserCount(bankShortName);

		return listBanqueCount;       	
    	  
    }
    @GetMapping("banqueOrgClientReport/")
    public List<BanqueClientReportDto> OrgClientReport(
            @RequestParam( required = false) String bankShortName
    ) {
    	
    	List<BanqueClientReportDto> orgReports = this.BanqueService.reportOrgClients(bankShortName);
    	    	
		return orgReports;       	
    	  
    }
    @GetMapping("banqueOrgFeadReport/")
    public List<BanqueFeadReportDto> OrgFeadeport(
            @RequestParam( required = false) String bankShortName
    ) {

    	List<BanqueFeadReportDto> orgReports = this.BanqueService.reportOrgFead(bankShortName);

        return orgReports;

    }
    protected BanqueDto convertToDto(Banque entity) {
        BanqueDto dto = new BanqueDto();
        String dtoLastVisit = "";
        if (entity.getLastvisit() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            dtoLastVisit = entity.getLastvisit().format(formatter);
        }
        dto.setBankId(entity.getBankId());
        dto.setBankShortName(entity.getBankShortName());
        dto.setBankName(entity.getBankName());
        dto.setNrEntr(entity.getNrEntr());
        dto.setBankMail(entity.getBankMail());
        dto.setActif(entity.getActif());
        dto.setComGest(entity.getComGest());
        dto.setLastvisit(dtoLastVisit);
        dto.setIdMemberPres(entity.getIdMemberPres());
        dto.setIdMemberVp(entity.getIdMemberVp());
        dto.setIdMemberSec(entity.getIdMemberSec());
        dto.setIdMemberTres(entity.getIdMemberTres());
        dto.setIdMemberIt(entity.getIdMemberIt());
        dto.setIdMemberLog(entity.getIdMemberLog());
        dto.setIdMemberRh(entity.getIdMemberRh());
        dto.setIdMemberSh(entity.getIdMemberSh());
        dto.setIdMemberPp(entity.getIdMemberPp());
        dto.setIdMemberAsso(entity.getIdMemberAsso());
        dto.setIdMemberAppro(entity.getIdMemberAppro());
        dto.setIdMemberPubrel(entity.getIdMemberPubrel());
        dto.setIdMemberCeo(entity.getIdMemberCeo());
        dto.setIdMemberFead(entity.getIdMemberFead());
        dto.setIdMemberQual(entity.getIdMemberQual() );
        dto.setAdresse(entity.getAdresse());
        dto.setCp(entity.getCp());
        dto.setLocalite(entity.getLocalite());
        dto.setBankTel(entity.getBankTel());
        dto.setBankGsm(entity.getBankGsm());
        dto.setAdresseDepotPrinc(entity.getAdresseDepotPrinc());
        dto.setCpDepotPrinc(entity.getCpDepotPrinc());
        dto.setCityDepotPrinc(entity.getCityDepotPrinc());
        dto.setDepPrincTel(entity.getDepPrincTel());
        dto.setSsAdresse(entity.getSsAdresse());
        dto.setSsCp(entity.getSsCp());
        dto.setSsCity(entity.getSsCity());
        dto.setSsTel(entity.getSsTel());
        int intRegio = (entity.getRegio() == null) ? 0 : entity.getRegio();
        dto.setRegio(intRegio);
        dto.setWebsite(entity.getWebsite());
        dto.setBank(entity.getBank());
        return dto;
    }

    protected Banque convertToEntity(BanqueDto dto) {
        Banque banque = new Banque();
        banque.setBankId(dto.getBankId());
        banque.setBankShortName(dto.getBankShortName());
        banque.setBankName(dto.getBankName());
        banque.setNrEntr(dto.getNrEntr());
        banque.setBankMail(dto.getBankMail());
        banque.setActif(dto.getActif());
        banque.setComGest(dto.getComGest());
        banque.setIdMemberPres(dto.getIdMemberPres());
        banque.setIdMemberVp(dto.getIdMemberVp());
        banque.setIdMemberSec(dto.getIdMemberSec());
        banque.setIdMemberTres(dto.getIdMemberTres());
        banque.setIdMemberIt(dto.getIdMemberIt());
        banque.setIdMemberLog(dto.getIdMemberLog());
        banque.setIdMemberRh(dto.getIdMemberRh());
        banque.setIdMemberSh(dto.getIdMemberSh());
        banque.setIdMemberPp(dto.getIdMemberPp());
        banque.setIdMemberAsso(dto.getIdMemberAsso());
        banque.setIdMemberAppro(dto.getIdMemberAppro());
        banque.setIdMemberPubrel(dto.getIdMemberPubrel());
        banque.setIdMemberCeo(dto.getIdMemberCeo());
        banque.setIdMemberFead(dto.getIdMemberFead());
        banque.setIdMemberQual(dto.getIdMemberQual());
        banque.setAdresse(dto.getAdresse());
        banque.setCp(dto.getCp());
        banque.setLocalite(dto.getLocalite());
        banque.setBankTel(dto.getBankTel());
        banque.setBankGsm(dto.getBankGsm());
        banque.setAdresseDepotPrinc(dto.getAdresseDepotPrinc());
        banque.setCpDepotPrinc(dto.getCpDepotPrinc());
        banque.setCityDepotPrinc(dto.getCityDepotPrinc());
        banque.setDepPrincTel(dto.getDepPrincTel());
        banque.setSsAdresse(dto.getSsAdresse());
        banque.setSsCp(dto.getSsCp());
        banque.setSsCity(dto.getSsCity());
        banque.setSsTel(dto.getSsTel());
        Integer integerRegio = (dto.getRegio() == 0) ? null : dto.getRegio();
        banque.setRegio(integerRegio);
        banque.setWebsite(dto.getWebsite());
        banque.setBank(dto.getBank());
        banque.setLastvisit(LocalDateTime.now()); // ignore last visit from dto, we need to update with current time

        return banque;
    }


}
