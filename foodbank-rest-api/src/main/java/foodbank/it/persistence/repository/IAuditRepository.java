package foodbank.it.persistence.repository;


import org.springframework.data.repository.PagingAndSortingRepository;

import foodbank.it.persistence.model.Audit;

public interface IAuditRepository extends PagingAndSortingRepository<Audit, Integer>{
  

}
