package foodbank.it.service;

import foodbank.it.persistence.model.TypeEmploi;

import java.util.Optional;

public interface ITypeEmploiService {
    Optional<TypeEmploi> findByJobNr(int jobNr);
    TypeEmploi save(TypeEmploi TypeEmploi);
    Iterable<TypeEmploi> findAll(Integer lienBanque,Boolean actif,String language);
    void deleteByJobNr(int jobNr) throws Exception;
}
