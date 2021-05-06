package foodbank.it.service;
import java.util.Optional;

import foodbank.it.persistence.model.Banque;

public interface IBanqueService {
    Optional<Banque> findByBankId(int bankId);
    Optional<Banque> findByBankShortName(String bankShortName);

    Banque save(Banque Banque);

    Iterable<Banque> findAll();
    Iterable<Banque> findByActif(short actif);

    void delete(int bankId) throws Exception;
}
