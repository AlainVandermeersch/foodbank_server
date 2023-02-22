package foodbank.it.service;

import foodbank.it.persistence.model.BirbCat;

import java.util.List;
import java.util.Optional;

public interface IBirbCatService {
    Optional<BirbCat> findByBirbId(int birbId);

    BirbCat save(BirbCat birbCat) ;

    List<BirbCat> findAll();


    void delete(int birbId) throws Exception;
}
