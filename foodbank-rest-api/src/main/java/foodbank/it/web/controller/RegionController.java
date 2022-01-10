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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import foodbank.it.persistence.model.Region;
import foodbank.it.service.IRegionService;
import foodbank.it.web.dto.RegionDto;

@RestController

public class RegionController {
	
	private IRegionService RegionService;
	    
    public RegionController(IRegionService RegionService) {
        this.RegionService = RegionService;      
    }

    @GetMapping("region/{regId}")
    public RegionDto findOne(@PathVariable Integer regId) {
        Region entity = RegionService.findByRegId(regId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return convertToDto(entity);
    }
    

    @GetMapping("regions/")
    public Collection<RegionDto> find( 
    		@RequestParam(required = false) String lienBanque,
    		@RequestParam(required = false) String bankShortName
    		) {
        Iterable<Region> selectedRegions = null;
        List<RegionDto> RegionDtos = new ArrayList<>();
        if (lienBanque == null) {
        	if (bankShortName == null) {
        		selectedRegions = this.RegionService.findAll();
        	
        	} else {
        		selectedRegions = this.RegionService.findByBankShortName(bankShortName);
        	}
         }
        else {
        	selectedRegions = this.RegionService.findByBankLink(Short.parseShort(lienBanque));        	
        }
    	selectedRegions.forEach(p -> RegionDtos.add(convertToDto(p)));
        
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
    	
        RegionDto dto = new RegionDto(entity.getRegId(),entity.getRegName(),entity.getBankLink(),entity.getBankShortName() );    
        return dto;
    }

    protected Region convertToEntity(RegionDto dto) {
    	    	    
    	Region myRegion = new Region( dto.getRegId(),dto.getRegName(),dto.getBankLink());       
        if (!StringUtils.isEmpty(dto.getRegId())) {
            myRegion.setRegId(dto.getRegId());
        }
        return myRegion;
    }


}


