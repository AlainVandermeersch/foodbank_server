package foodbank.it.persistence.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import foodbank.it.persistence.model.AuditChange;

public interface IAuditChangeRepository extends PagingAndSortingRepository<AuditChange, Integer>{


}
