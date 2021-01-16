package foodbank.it.service;
import java.util.Optional;

import foodbank.it.persistence.model.Banque;

public interface IBanqueService {
    Optional<Banque> findByBankId(int bankId);

    Banque save(Banque Banque);

    Iterable<Banque> findAll();

    void delete(int bankId);
}
