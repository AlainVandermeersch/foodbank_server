package foodbank.it.web.controller;

import foodbank.it.persistence.model.Banque;
import foodbank.it.persistence.model.Region;
import foodbank.it.service.IBanqueService;
import foodbank.it.service.IRegionService;
import foodbank.it.web.dto.RegionDto;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.Objects.isNull;

@RestController

public class RegionController {
	
	private IRegionService RegionService;
	private IBanqueService BanqueService;
    
    public RegionController(IRegionService RegionService, IBanqueService BanqueService) {
        this.RegionService = RegionService;
        this.BanqueService = BanqueService;
    }

    @GetMapping("region/{regId}")
    public RegionDto findOne(@PathVariable Integer regId) {
        Region entity = RegionService.findByRegId(regId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return convertToDto(entity);
    }
    

    @GetMapping("regions/")
    public Collection<RegionDto> find( @RequestParam(required = false) String bankShortName ) {
        Iterable<Region> selectedRegions = null;
        List<RegionDto> RegionDtos = new ArrayList<>();
        if (bankShortName == null) {
        		selectedRegions = this.RegionService.findAll();
        		selectedRegions.forEach(p -> RegionDtos.add(convertToDto(p)));
         }
        else {
        	selectedRegions = this.RegionService.findByBanqueObjectBankShortName(bankShortName);
        	// selectedRegions = this.RegionService.findAll();
        	selectedRegions.forEach(p -> RegionDtos.add(convertToDto(p)));
        }
        
        
        return RegionDtos;
    }

    @PutMapping("region/{regId}")
    public RegionDto updateRegion(@PathVariable("regId") Integer regId, @RequestBody RegionDto updatedRegion) {
        Region RegionEntity = convertToEntity(updatedRegion);
        return this.convertToDto(this.RegionService.save(RegionEntity));
    }

    @DeleteMapping("region/{regId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRegion(@PathVariable("regId") Integer regId) {
        RegionService.delete(regId);
    }

    @PostMapping("region/")
    @ResponseStatus(HttpStatus.CREATED)
    public RegionDto create(@RequestBody RegionDto newRegion) {
        Region entity = convertToEntity(newRegion);
        // Alain todo later entity.setDateCreated(LocalDate.now());
        Region Region = this.RegionService.save(entity);        
        return this.convertToDto(Region);
    }
    protected RegionDto convertToDto(Region entity) {
    	String bankShortName = "";
    	String bankName = "";
    	Banque banqueObject = entity.getBanqueObject();
    	if ( ! isNull(banqueObject)) {
    		bankShortName = banqueObject.getBankShortName();
    		bankName = banqueObject.getBankName();
    	} 
        RegionDto dto = new RegionDto(entity.getRegId(),entity.getRegName(),bankShortName, bankName );    
        return dto;
    }

    protected Region convertToEntity(RegionDto dto) {
    	Banque banqueObject = this.BanqueService.findByBankShortName(dto.getBankShortName()).get();
    	    
    	Region myRegion = new Region( dto.getRegId(),dto.getRegName(),banqueObject);       
        if (!StringUtils.isEmpty(dto.getRegId())) {
            myRegion.setRegId(dto.getRegId());
        }
        return myRegion;
    }


}


