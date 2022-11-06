package foodbank.it.persistence.repository;

import foodbank.it.persistence.model.AuditAssoc;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IAuditAssocRepository  extends PagingAndSortingRepository<AuditAssoc, Long>{
	
}
