package foodbank.it.web.controller;
import static java.util.Objects.isNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import foodbank.it.persistence.model.Banque;
import foodbank.it.persistence.model.Region;
import foodbank.it.service.IBanqueService;
import foodbank.it.service.IRegionService;
import foodbank.it.web.dto.RegionDto;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@RestController

public class RegionController {
	
	private IRegionService RegionService;
	private IBanqueService BanqueService;
    
    public RegionController(IRegionService RegionService, IBanqueService BanqueService) {
        this.RegionService = RegionService;
        this.BanqueService = BanqueService;
    }
    @CrossOrigin
    @GetMapping("region/{regId}")
    public RegionDto findOne(@PathVariable Integer regId) {
        Region entity = RegionService.findByRegId(regId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return convertToDto(entity);
    }
    
    @CrossOrigin
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
    @CrossOrigin
    @PutMapping("region/{regId}")
    public RegionDto updateRegion(@PathVariable("regId") Integer regId, @RequestBody RegionDto updatedRegion) {
        Region RegionEntity = convertToEntity(updatedRegion);
        return this.convertToDto(this.RegionService.save(RegionEntity));
    }
    @CrossOrigin
    @DeleteMapping("region/{regId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRegion(@PathVariable("regId") Integer regId) {
        RegionService.delete(regId);
    }
    @CrossOrigin
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


