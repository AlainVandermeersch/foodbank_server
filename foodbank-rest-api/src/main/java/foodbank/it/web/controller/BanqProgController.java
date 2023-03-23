package foodbank.it.web.controller;

import foodbank.it.persistence.model.BanqProg;
import foodbank.it.service.IBanqProgService;
import foodbank.it.web.dto.BanqProgDto;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController

public class BanqProgController {
	
	private IBanqProgService BanqProgService;
	    
    public BanqProgController(IBanqProgService BanqProgService) {
        this.BanqProgService = BanqProgService;      
    }

    @GetMapping("banqprog/{lienBanque}")
    public BanqProgDto findOne(@PathVariable Integer lienBanque) {
        BanqProg entity = BanqProgService.findByLienBanque(lienBanque)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return convertToDto(entity);
    }
    

    @GetMapping("banqprogs/")
    public Collection<BanqProgDto> findAll( ) {
         List<BanqProgDto> BanqProgDtos = new ArrayList<>();
        
       	 Iterable<BanqProg> selectedBanqProgs = this.BanqProgService.findAll();
        		selectedBanqProgs.forEach(p -> BanqProgDtos.add(convertToDto(p)));
        
        
        
        return BanqProgDtos;
    }

    @PutMapping("banqprog/{lienBanque}")
    public BanqProgDto updateBanqProg(@PathVariable("lienBanque") Integer lienBanque, @RequestBody BanqProgDto updatedBanqProg) {
        BanqProg BanqProgEntity = convertToEntity(updatedBanqProg);
        return this.convertToDto(this.BanqProgService.save(BanqProgEntity));
    }

    @DeleteMapping("banqprog/{lienBanque}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBanqProg(@PathVariable("lienBanque") Integer lienBanque) {
        BanqProgService.delete(lienBanque);
    }

    @PostMapping("banqprog/")
    @ResponseStatus(HttpStatus.CREATED)
    public BanqProgDto create(@RequestBody BanqProgDto newBanqProg) {
        BanqProg entity = convertToEntity(newBanqProg);
        BanqProg BanqProg = this.BanqProgService.save(entity);        
        return this.convertToDto(BanqProg);
    }
    protected BanqProgDto convertToDto(BanqProg entity) {
    	boolean booluam = entity.getLuam() == 1;
    	boolean boolupm = entity.getLupm() == 1;
    	boolean bootuam = entity.getTuam() == 1;
    	boolean bootupm = entity.getTupm() == 1;
    	boolean booweam = entity.getWeam() == 1;
    	boolean boowepm = entity.getWepm() == 1;
    	boolean bootham = entity.getTham() == 1;
    	boolean boothpm = entity.getThpm() == 1;
    	boolean boofram = entity.getFram() == 1;
    	boolean boofrpm = entity.getFrpm() == 1;
    	boolean boosaam = entity.getSaam() == 1;
    	boolean boosapm = entity.getSapm() == 1;
    	boolean boosunam = entity.getSunam() == 1;
    	boolean boosunpm = entity.getSunpm() == 1;
    	boolean booCotAnnuelle = entity.getCotAnnuelle() == 1;
    	boolean booCotSup = entity.getCotSup() == 1;
    	
        BanqProgDto dto = new BanqProgDto();
        dto.setLienBanque(entity.getLienBanque());
        dto.setLuam(booluam);
        dto.setLupm(boolupm);
        dto.setTuam(bootuam);
        dto.setTupm(bootupm);
        dto.setWeam(booweam);
        dto.setWepm(boowepm);
        dto.setTham(bootham);
        dto.setThpm(boothpm);
        dto.setFram(boofram);
        dto.setFrpm(boofrpm);
        dto.setSaam(boosaam);
        dto.setSapm(boosapm);
        dto.setSunam(boosunam);
        dto.setSunpm(boosunpm);
        dto.setReluam(entity.getReluam());
        dto.setRelupm( entity.getRelupm());
        dto.setRetuam( entity.getRetuam());
        dto.setRetupm( entity.getRetupm());
        dto.setReweam( entity.getReweam());
        dto.setRewepm(entity.getRewepm());
        dto.setRetham( entity.getRetham());
        dto.setRethpm( entity.getRethpm());
        dto.setRefram( entity.getRefram());
        dto.setRefrpm( entity.getRefrpm());
        dto.setResaam( entity.getResaam());
        dto.setResapm(entity.getResapm());
        dto.setResunam( entity.getResunam());
        dto.setResunpm( entity.getResunpm());
        dto.setCotAnnuelle(booCotAnnuelle);
        dto.setCotAmount(entity.getCotAmount());
        dto.setCotSup(booCotSup);
        dto.setCotAmountSup(entity.getCotAmountSup());
        dto.setBankShortName(entity.getBankShortName() );
        dto.setCotTextFr(entity.getCotTextFr());
        dto.setCotTextNl(entity.getCotTextNl());
        return dto;
    }

    protected BanqProg convertToEntity(BanqProgDto dto) {
    	    	    
    	BanqProg entity = new BanqProg();
        entity.setLienBanque(dto.getLienBanque());
        entity.setLuam((int) (dto.isLuam() ? 1 : 0));
        entity.setLupm((int) (dto.isLupm() ? 1 : 0));
        entity.setTuam((int) (dto.isTuam() ? 1 : 0));
        entity.setTupm((int) (dto.isTupm() ? 1 : 0));
        entity.setWeam((int) (dto.isWeam() ? 1 : 0));
        entity.setWepm((int) (dto.isWepm() ? 1 : 0));
        entity.setTham((int) (dto.isTham() ? 1 : 0));
        entity.setThpm((int) (dto.isThpm() ? 1 : 0));
        entity.setFram((int) (dto.isFram() ? 1 : 0));
        entity.setFrpm((int) (dto.isFrpm() ? 1 : 0));
        entity.setSaam((int) (dto.isSaam() ? 1 : 0));
        entity.setSapm((int) (dto.isSapm() ? 1 : 0));
        entity.setSunam((int) (dto.isSunam() ? 1 : 0));
        entity.setSunpm((int) (dto.isSunpm() ? 1 : 0));
        entity.setReluam(dto.getReluam());
        entity.setRelupm(dto.getRelupm());
        entity.setRetuam(dto.getRetuam());
        entity.setRetupm(dto.getRetupm());
        entity.setReweam(dto.getReweam());
        entity.setRewepm(dto.getRewepm());
        entity.setRetham(dto.getRetham());
        entity.setRethpm(dto.getRethpm());
        entity.setRefram(dto.getRefram());
        entity.setRefrpm(dto.getRefrpm());
        entity.setResaam(dto.getResaam());
        entity.setResapm(dto.getResapm());
        entity.setResunam(dto.getResunam());
        entity.setResunpm(dto.getResunpm());
        entity.setCotAnnuelle((short) (dto.getCotAnnuelle()  ? 1 : 0));
        entity.setCotAmount(dto.getCotAmount());
        entity.setCotSup((short) (dto.getCotSup()  ? 1 : 0));
        entity.setCotAmountSup(dto.getCotAmountSup() );
        entity.setCotTextFr(dto.getCotTextFr());
        entity.setCotTextNl(dto.getCotTextNl());
        return entity;
    }


}