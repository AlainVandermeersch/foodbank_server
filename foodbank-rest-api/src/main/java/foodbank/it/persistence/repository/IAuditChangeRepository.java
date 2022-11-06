package foodbank.it.persistence.repository;

import foodbank.it.persistence.model.AuditChange;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IAuditChangeRepository extends PagingAndSortingRepository<AuditChange, Integer>{


}
