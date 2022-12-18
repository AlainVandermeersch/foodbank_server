package foodbank.it.persistence.repository;

import foodbank.it.persistence.model.TUser;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;



public interface ITUserRepository extends PagingAndSortingRepository<TUser, Integer>{

    void deleteByIdUser(String idUser);
    Optional<TUser> findByIdUser(String idUser);
	Iterable<TUser> findByLienBanque(Short lienBanque);
	Iterable<TUser> findByIdOrg(Integer idOrgInteger);
	Iterable<TUser> findByLienBat(int lienBat);
	Iterable<TUser> findByLienDepot(String lienDepot);
}
