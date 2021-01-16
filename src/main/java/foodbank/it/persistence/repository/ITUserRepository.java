package foodbank.it.persistence.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import foodbank.it.persistence.model.TUser;



public interface ITUserRepository extends CrudRepository<TUser, Integer>{

    void deleteByIdUser(String idUser);

    Optional<TUser> findByIdUser(String idUser);

	Iterable<TUser> findByIdCompany(String idCompany);
	
	Iterable<TUser> findByIdOrg(int idOrg);	
}
