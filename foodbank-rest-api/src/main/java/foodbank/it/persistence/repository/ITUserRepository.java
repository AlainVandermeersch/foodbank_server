package foodbank.it.persistence.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import foodbank.it.persistence.model.TUser;



public interface ITUserRepository extends PagingAndSortingRepository<TUser, Integer>{

    void deleteByIdUser(String idUser);
    Optional<TUser> findByIdUser(String idUser);
	Iterable<TUser> findByLienBanque(Short lienBanque);
	Iterable<TUser> findByIdOrg(Integer idOrgInteger);
	Iterable<TUser> findByLienBat(int lienBat);    

	
}
