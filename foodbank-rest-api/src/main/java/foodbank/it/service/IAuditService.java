package foodbank.it.service;

import java.util.Optional;

import foodbank.it.persistence.model.Audit;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAuditService {
	
Optional<Audit> findByAuditId(int auditId);
	
	Audit save(Audit audit) ;

    Page<Audit> findAll(SearchAuditCriteria searchCriteria, Pageable pageable);	
   

    void delete(int auditId) throws Exception;


}
