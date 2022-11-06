package foodbank.it.persistence.repository;


import foodbank.it.persistence.model.Audit;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IAuditRepository extends PagingAndSortingRepository<Audit, Integer>{
  

}
