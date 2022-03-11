package foodbank.it.service;
import java.util.List;
import foodbank.it.web.dto.PopulationReportDto;
public interface IPopulationService {
	List<PopulationReportDto> report();

}
