package foodbank.it.service;

import foodbank.it.persistence.model.Audit;
import foodbank.it.persistence.model.AuditUser;
import foodbank.it.web.dto.AuditReportDto;
import foodbank.it.web.dto.AuditUserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IAuditService {
	
Optional<Audit> findByAuditId(int auditId);
	
	Audit save(Audit audit) ;

    Page<Audit> findPaged(SearchAuditCriteria searchCriteria, Pageable pageable);
   

    void delete(int auditId) throws Exception;

	List<AuditReportDto> report( String bankShortName, String fromDateString, String toDateString, String reportType);
    List<AuditUserDto> reportUsers(String bankShortName, String fromDateString, String toDateString);


    List<AuditUserDto> findUsersPaged(SearchAuditCriteria criteria, Pageable pageRequest);
    List<AuditUserDto> findUsers(SearchAuditCriteria criteria);
}
