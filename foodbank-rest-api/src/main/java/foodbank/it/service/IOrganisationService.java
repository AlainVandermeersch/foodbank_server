package foodbank.it.service;

import foodbank.it.persistence.model.Organisation;
import foodbank.it.web.dto.OrgMemberReportDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface IOrganisationService {
	Optional<Organisation> findByIdDis(int idDis);
	
	Organisation save(Organisation Organisation);

    void delete(int idDis)  throws Exception;
	
	Page<Organisation> findAll(SearchOrganisationCriteria searchCriteria, Pageable pageable);
    Iterable<Organisation> findAll();
    Iterable<Organisation> findByLienBanque(Short lienBanque);
	Page<Organisation> findSummaries(SearchOrganisationSummariesCriteria searchCriteria, Pageable pageable);
	List<Organisation> OrgClientReport(Integer lienBanque);
	List<OrgMemberReportDto> OrgMemberReport(Integer lienBanque);
	String getAnomalies(Organisation organisation);

}
