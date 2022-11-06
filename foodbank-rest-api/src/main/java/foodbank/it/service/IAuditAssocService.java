package foodbank.it.service;

import foodbank.it.persistence.model.AuditAssoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IAuditAssocService {
	
    Optional<AuditAssoc> findByAuditId(long auditAssocId);
	
	AuditAssoc save(AuditAssoc auditAssoc) ;

    Page<AuditAssoc> findAll(SearchAuditAssocCriteria searchCriteria, Pageable pageable);	
   

    void delete(long auditId) throws Exception;

}
