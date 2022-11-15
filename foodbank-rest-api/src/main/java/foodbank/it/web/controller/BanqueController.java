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
    	
    	List<BanqueCount> listBanqueCount = this.BanqueService.reportOrgCount(filter);
    	    	
		return listBanqueCount;       	
    	  
    }
    @GetMapping("banqueMembreCount/")
    public List<BanqueOrgCountDto> MembreReport() {
    	
    	List<BanqueOrgCountDto> listBanqueCount = this.BanqueService.reportMembreCount();
    	    	
		return listBanqueCount;       	
    	  
    }
    @GetMapping("banqueUserCount/")
    public List<BanqueOrgCountDto> UserReport() {
    	
    	List<BanqueOrgCountDto> listBanqueCount = this.BanqueService.reportTUserCount();
    	    	
		return listBanqueCount;       	
    	  
    }
    @GetMapping("banqueOrgClientReport/")
    public List<BanqueClientReportDto> OrgClientReport() {
    	
    	List<BanqueClientReportDto> orgReports = this.BanqueService.reportOrgClients();
    	    	
		return orgReports;       	
    	  
    }
    @GetMapping("banqueOrgFeadReport/")
    public List<BanqueFeadReportDto> OrgFeadeport() {

        List<BanqueFeadReportDto> orgReports = this.BanqueService.reportOrgFead();

        return orgReports;

    }
    protected BanqueDto convertToDto(Banque entity) {
        BanqueDto dto = new BanqueDto(entity.getBankId(), entity.getBankShortName(), entity.getBankName(), entity.getNrEntr(), entity.getBankMail(), entity.getActif(), entity.getComGest(), entity.getLastvisit(), entity.getIdMemberPres(), entity.getIdMemberVp(), entity.getIdMemberSec(), entity.getIdMemberTres(), entity.getIdMemberIt(), entity.getIdMemberLog(), entity.getIdMemberRh(),
            entity.getIdMemberSh(), entity.getIdMemberPp(), entity.getIdMemberAsso(), entity.getIdMemberAppro(),
            entity.getIdMemberPubrel(), entity.getIdMemberCeo(), entity.getIdMemberFead(),entity.getIdMemberQual() ,
            entity.getAdresse(), entity.getCp(), entity.getLocalite(), entity.getBankTel(), entity.getBankGsm(), entity.getAdresseDepotPrinc(), entity.getCpDepotPrinc(), entity.getCityDepotPrinc(),
            entity.getDepPrincTel(), entity.getSsAdresse(), entity.getSsCp(), entity.getSsCity(), entity.getSsTel(), entity.getRegio(), entity.getWebsite(), entity.getBank());
        return dto;
    }

    protected Banque convertToEntity(BanqueDto dto) {
        Banque banque = new Banque( dto.getBankId(),dto.getBankShortName(), dto.getBankName(), dto.getNrEntr(), dto.getBankMail(), dto.getActif(), dto.getComGest(),  dto.getIdMemberPres(), dto.getIdMemberVp(), dto.getIdMemberSec(), dto.getIdMemberTres(), dto.getIdMemberIt(), dto.getIdMemberLog(), dto.getIdMemberRh(),
            dto.getIdMemberSh(), dto.getIdMemberPp(), dto.getIdMemberAsso(), dto.getIdMemberAppro(),
            dto.getIdMemberPubrel(), dto.getIdMemberCeo(), dto.getIdMemberFead(), dto.getIdMemberQual(),
            dto.getAdresse(), dto.getCp(), dto.getLocalite(), dto.getBankTel(), dto.getBankGsm(), dto.getAdresseDepotPrinc(), dto.getCpDepotPrinc(), dto.getCityDepotPrinc(),
            dto.getDepPrincTel(), dto.getSsAdresse(), dto.getSsCp(), dto.getSsCity(), dto.getSsTel(), dto.getRegio(), dto.getWebsite(), dto.getBank());
        if (!StringUtils.isEmpty(dto.getBankId())) {
            banque.setBankId(dto.getBankId());
        }
        return banque;
    }


}
