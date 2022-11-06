package foodbank.it.persistence.repository;

import foodbank.it.persistence.model.Don;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface IDonRepository  extends PagingAndSortingRepository<Don, Integer>{
	Optional<Don> findByIdDon(int idDon);
    void deleteByIdDon(int idDon);
}


