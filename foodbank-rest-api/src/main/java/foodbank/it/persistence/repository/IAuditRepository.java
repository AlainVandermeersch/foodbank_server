package foodbank.it.persistence.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import foodbank.it.persistence.model.Audit;

public interface IAuditRepository extends PagingAndSortingRepository<Audit, Integer>{
	Optional<Audit> findByAuditId(int auditId);
    void deleteByAuditId(int auditId);

}
