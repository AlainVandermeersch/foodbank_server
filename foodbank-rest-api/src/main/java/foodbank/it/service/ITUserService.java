package foodbank.it.service;

import foodbank.it.persistence.model.TUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ITUserService {
		Optional<TUser> findByIdUser(String idUser);	
		Page<TUser> findPaged(SearchTUserCriteria searchCriteria, Pageable pageable);
		List<TUser> findAll(SearchTUserCriteria searchCriteria);
		Iterable<TUser> findByLienBanque(Short lienBanque);
		TUser save(TUser TUser,  boolean booCreateMode) throws Exception;
	    void delete(String idUser);
		Iterable<TUser> findByIdOrg(Integer idOrgInteger);
		Iterable<TUser> findByLienBat(int lienBat);
		Iterable<TUser> findByLienDepot(String lienDepot);
}
	

