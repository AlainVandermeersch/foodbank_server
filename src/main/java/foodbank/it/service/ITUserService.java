package foodbank.it.service;
import java.util.Optional;

import foodbank.it.persistence.model.TUser;

public interface ITUserService {
    Optional<TUser> findByIdUser(String idUser);
    
    Iterable<TUser> findByIdCompany(String idCompany); 
    
    Iterable<TUser> findByIdOrg(int idOrg);	
    
    TUser save(TUser TUser);

    Iterable<TUser> findAll();

    void delete(String idUser);

   

}
