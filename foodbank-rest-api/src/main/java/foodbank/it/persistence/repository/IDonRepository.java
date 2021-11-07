package foodbank.it.persistence.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import foodbank.it.persistence.model.Don;

public interface IDonRepository  extends PagingAndSortingRepository<Don, Integer>{
	Optional<Don> findByIdDon(int idDon);
    void deleteByIdDon(int idDon);
}


