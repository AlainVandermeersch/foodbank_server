package foodbank.it.web.controller;

import foodbank.it.service.IPopulationService;
import foodbank.it.web.dto.PopulationReportDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class PopulationController {
	 private IPopulationService PopulationService;
	 public PopulationController(IPopulationService PopulationService) {
	        this.PopulationService = PopulationService;
	 }
	@GetMapping("populationReport/")
    public List<PopulationReportDto> PopulationReport(
			@RequestParam( required = false) String lienBanque	) {
		Integer lienBanqueInteger = Optional.ofNullable(lienBanque).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
		List<PopulationReportDto> listPopulations = this.PopulationService.report(lienBanqueInteger);
    	
		return listPopulations;
    
    	
    	  
    }

}
