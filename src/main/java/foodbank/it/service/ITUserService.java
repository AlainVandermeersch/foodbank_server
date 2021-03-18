package foodbank.it.service;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import foodbank.it.persistence.model.TUser;

public interface ITUserService {
    Optional<TUser> findByIdUser(String idUser);
    Page<TUser> findAll(Pageable pageRequest);
    Page<TUser> findByIdCompany(String idCompany, Pageable pageRequest);
    Page<TUser> findByIdOrg(int idOrg, Pageable pageRequest);
    Page<TUser> findByIdUserContaining(String searchValue, Pageable pageRequest);
	Page<TUser> findByIdOrgAndIdUserContaining(int parseInt, String searchValue, Pageable pageRequest);
	Page<TUser> findByIdCompanyAndIdUserContaining(String idCompany, String searchValue, Pageable pageRequest);
    Page<TUser> findByUserNameContaining(String search, Pageable pageRequest);	
   	Page<TUser> findByIdCompanyAndUserNameContaining(String idCompany, String search,
   			Pageable pageRequest);
   	Page<TUser> findByIdOrgAndUserNameContaining(int idOrg, String search, Pageable pageRequest);
   	Page<TUser> findByIdLanguageStartsWith(String search, Pageable pageRequest);	
   	Page<TUser> findByIdCompanyAndIdLanguageStartsWith(String idCompany, String search,
   			Pageable pageRequest);
   	Page<TUser> findByIdOrgAndIdLanguageStartsWith(int idOrg, String search, Pageable pageRequest);
   	Page<TUser> findByEmailContaining(String search, Pageable pageRequest);	
   	Page<TUser> findByIdCompanyAndEmailContaining(String idCompany, String search,
   			Pageable pageRequest);
   	Page<TUser> findByIdOrgAndEmailContaining(int idOrg, String search, Pageable pageRequest);
   	Page<TUser> findByRightsContaining(String search, Pageable pageRequest);	
   	Page<TUser> findByIdCompanyAndRightsContaining(String idCompany, String search,
   			Pageable pageRequest);
   	Page<TUser> findByIdOrgAndRightsContaining(int idOrg, String search, Pageable pageRequest);
    
    TUser save(TUser TUser);
   
    void delete(String idUser);
	

   

}
