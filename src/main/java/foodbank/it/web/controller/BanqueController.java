package foodbank.it.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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

import foodbank.it.persistence.model.Banque;
import foodbank.it.service.IBanqueService;
import foodbank.it.web.dto.BanqueDto;

@RestController

public class BanqueController {
    
    private IBanqueService BanqueService;
    
    public BanqueController(IBanqueService BanqueService) {
        this.BanqueService = BanqueService;
       
    }
    @CrossOrigin
    @GetMapping("banque/{bankId}")
    public BanqueDto findOne(@PathVariable Integer bankId) {
        Banque entity = BanqueService.findByBankId(bankId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return convertToDto(entity);
    }
    
    @CrossOrigin
    @GetMapping("banques/")
    public Collection<BanqueDto> findAll() {
        Iterable<Banque> allBanques = this.BanqueService.findAll();
        List<BanqueDto> BanqueDtos = new ArrayList<>();
        allBanques.forEach(p -> BanqueDtos.add(convertToDto(p)));
        return BanqueDtos;
    }
    @CrossOrigin
    @PutMapping("banque/{bankId}")
    public BanqueDto updateBanque(@PathVariable("bankId") Integer bankId, @RequestBody BanqueDto updatedBanque) {
        Banque BanqueEntity = convertToEntity(updatedBanque);
        return this.convertToDto(this.BanqueService.save(BanqueEntity));
    }
    @CrossOrigin
    @DeleteMapping("banque/{bankId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBanque(@PathVariable("bankId") Integer bankId) {
        BanqueService.delete(bankId);
    }
    @CrossOrigin
    @PostMapping("banque/")
    @ResponseStatus(HttpStatus.CREATED)
    public BanqueDto create(@RequestBody BanqueDto newBanque) {
        Banque entity = convertToEntity(newBanque);
        // Alain todo later entity.setDateCreated(LocalDate.now());
        Banque Banque = this.BanqueService.save(entity);        
        return this.convertToDto(Banque);
    }
    protected BanqueDto convertToDto(Banque entity) {
        BanqueDto dto = new BanqueDto(entity.getBankId(), entity.getBankShortName(), entity.getBankName(), entity.getNrEntr(), entity.getBankMail(), entity.getActif(), entity.getComGest(), entity.getLastvisit(), entity.getNomPres(), entity.getNomVp(), entity.getNomSec(), entity.getNomTres(), entity.getNomIt(), entity.getNomLog(), entity.getNomRh(),
            entity.getNomSh(), entity.getNomPp(), entity.getNomAsso(), entity.getNomAppro(), entity.getNomPubrel(), entity.getNomCeo(), entity.getNomFead(), entity.getAdresse(), entity.getCp(), entity.getLocalite(), entity.getBankTel(), entity.getBankGsm(), entity.getAdresseDepotPrinc(), entity.getCpDepotPrinc(), entity.getCityDepotPrinc(),
            entity.getDepPrincTel(), entity.getSsAdresse(), entity.getSsCp(), entity.getSsCity(), entity.getSsTel(), entity.getRegio(), entity.getWebsite(), entity.getBank());       
        return dto;
    }

    protected Banque convertToEntity(BanqueDto dto) {
        Banque banque = new Banque( dto.getBankShortName(), dto.getBankName(), dto.getNrEntr(), dto.getBankMail(), dto.getActif(), dto.getComGest(), dto.getLastvisit(), dto.getNomPres(), dto.getNomVp(), dto.getNomSec(), dto.getNomTres(), dto.getNomIt(), dto.getNomLog(), dto.getNomRh(),
            dto.getNomSh(), dto.getNomPp(), dto.getNomAsso(), dto.getNomAppro(), dto.getNomPubrel(), dto.getNomCeo(), dto.getNomFead(), dto.getAdresse(), dto.getCp(), dto.getLocalite(), dto.getBankTel(), dto.getBankGsm(), dto.getAdresseDepotPrinc(), dto.getCpDepotPrinc(), dto.getCityDepotPrinc(),
            dto.getDepPrincTel(), dto.getSsAdresse(), dto.getSsCp(), dto.getSsCity(), dto.getSsTel(), dto.getRegio(), dto.getWebsite(), dto.getBank());       
        if (!StringUtils.isEmpty(dto.getBankId())) {
            banque.setBankId(dto.getBankId());
        }
        return banque;
    }


}
