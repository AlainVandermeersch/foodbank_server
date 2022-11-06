package foodbank.it.persistence.repository;

import foodbank.it.persistence.model.Banque;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IBanqueRepository extends CrudRepository<Banque, Integer>{
    Optional<Banque> findByBankId(int bankId);
    Optional<Banque> findByBankShortName(String bankShortName );
    Iterable<Banque> findByActif(short actif);
    void deleteByBankId(int bankId);

    Iterable<Banque> findByBankIdLessThan(int i);
}
