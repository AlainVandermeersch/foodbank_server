package foodbank.it.service;

import foodbank.it.web.dto.PopulationReportDto;

import java.util.List;
public interface IPopulationService {
	List<PopulationReportDto> report(Integer lienBanque);

}
