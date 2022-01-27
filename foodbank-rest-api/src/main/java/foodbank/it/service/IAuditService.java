package foodbank.it.service;

import java.util.List;
import java.util.Optional;

import foodbank.it.persistence.model.Audit;
import foodbank.it.web.dto.AuditReportDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAuditService {
	
Optional<Audit> findByAuditId(int auditId);
	
	Audit save(Audit audit) ;

    Page<Audit> findAll(SearchAuditCriteria searchCriteria, Pageable pageable);	
   

    void delete(int auditId) throws Exception;

	List<AuditReportDto> report(String shortBankName, String reportType);


}
