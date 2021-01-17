package foodbank.it.persistence.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import foodbank.it.persistence.model.Banque;

public interface IBanqueRepository extends CrudRepository<Banque, Integer>{
    Optional<Banque> findByBankId(int bankId);
    Optional<Banque> findByBankShortName(String bankShortName );
    void deleteByBankId(int bankId);

}
