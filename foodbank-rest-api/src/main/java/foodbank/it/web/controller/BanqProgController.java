package foodbank.it.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import foodbank.it.persistence.model.BanqProg;
import foodbank.it.service.IBanqProgService;
import foodbank.it.web.dto.BanqProgDto;

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
    	
        BanqProgDto dto = new BanqProgDto(entity.getLienBanque(),booluam, boolupm, bootuam, bootupm, booweam,
    			boowepm, bootham, boothpm, boofram, boofrpm, boosaam, boosapm, boosunam, boosunpm,  
    			entity.getReluam(), entity.getRelupm(), entity.getRetuam(), entity.getRetupm(), entity.getReweam(),
    			entity.getRewepm(), entity.getRetham(), entity.getRethpm(), entity.getRefram(), entity.getRefrpm(), entity.getResaam(), 
    			entity.getResapm(),	entity.getResunam(), entity.getResunpm(),
    			booCotAnnuelle,  entity.getCotAmount(),  booCotSup,  entity.getCotAmountSup(),
    			entity.getBankShortName() );    
        return dto;
    }

    protected BanqProg convertToEntity(BanqProgDto dto) {
    	    	    
    	BanqProg myBanqProg = new BanqProg( dto.getLienBanque(),
    			(int) (dto.isLuam() ? 1 : 0), (int) (dto.isLupm() ? 1 : 0), (int) (dto.isTuam() ? 1 : 0), (int) (dto.isTupm() ? 1 : 0), (int) (dto.isWeam() ? 1 : 0),
    			(int) (dto.isWepm() ? 1 : 0), (int) (dto.isTham() ? 1 : 0), (int) (dto.isThpm() ? 1 : 0), (int) (dto.isFram() ? 1 : 0), (int) (dto.isFrpm() ? 1 : 0), (int) (dto.isSaam() ? 1 : 0), 
    			(int) (dto.isSapm() ? 1 : 0),	(int) (dto.isSunam() ? 1 : 0), (int) (dto.isThpm() ? 1 : 0),
    			dto.getReluam(), dto.getRelupm(), dto.getRetuam(), dto.getRetupm(), dto.getReweam(),
    			dto.getRewepm(), dto.getRetham(), dto.getRethpm(), dto.getRefram(), dto.getRefrpm(), dto.getResaam(), 
    			dto.getResapm(),	dto.getResunam(), dto.getResunpm(),
    			(short) (dto.getCotAnnuelle()  ? 1 : 0),  dto.getCotAmount(), (short) (dto.getCotSup()  ? 1 : 0),  dto.getCotAmountSup() );       
        if (!StringUtils.isEmpty(dto.getLienBanque())) {
            myBanqProg.setLienBanque(dto.getLienBanque());
        }
        return myBanqProg;
    }


}