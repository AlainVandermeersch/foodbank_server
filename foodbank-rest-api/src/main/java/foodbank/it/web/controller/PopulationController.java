package foodbank.it.web.controller;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import foodbank.it.web.dto.PopulationReportDto;

import foodbank.it.service.IPopulationService;

@RestController
public class PopulationController {
	 private IPopulationService PopulationService;
	
	@GetMapping("PopulationReport/")
    public List<PopulationReportDto> PopulationReport(    		
    		) {
    	
    	List<PopulationReportDto> listPopulations = this.PopulationService.report();
    	
		return listPopulations;
    
    	
    	  
    }

}
