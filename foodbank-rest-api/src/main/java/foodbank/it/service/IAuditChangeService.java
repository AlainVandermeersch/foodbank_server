package foodbank.it.service;

import java.util.List;
import java.util.Optional;

import foodbank.it.persistence.model.AuditChange;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

    public interface IAuditChangeService {

        Optional<AuditChange> findByAuditId(int auditId);

        AuditChange save(AuditChange auditChange) ;

        Page<AuditChange> findAll(SearchAuditChangeCriteria searchCriteria, Pageable pageable);


        void delete(int auditId) throws Exception;

    }
