package foodbank.it.service;
import java.util.Optional;

import foodbank.it.persistence.model.Cpas;

public interface ICpasService {
    Optional<Cpas> findByCpasId(int cpasId);
    Cpas save(Cpas Cpas);
    Iterable<Cpas> findAll();   
    void delete(int cpasId);
	Iterable<Cpas> findByCpasZip(String cpasZip);
}
