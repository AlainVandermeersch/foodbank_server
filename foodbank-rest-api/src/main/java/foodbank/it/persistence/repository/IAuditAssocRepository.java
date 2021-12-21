package foodbank.it.persistence.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import foodbank.it.persistence.model.AuditAssoc;

public interface IAuditAssocRepository  extends PagingAndSortingRepository<AuditAssoc, Long>{
	
}
