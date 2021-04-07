package foodbank.it.service;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import foodbank.it.persistence.model.TUser;

public interface ITUserService {
		Optional<TUser> findByIdUser(String idUser);	
		Page<TUser> findAll(SearchTUserCriteria searchCriteria, Pageable pageable);	    
	    TUser save(TUser TUser);
	    void delete(String idUser);	

	}
	

