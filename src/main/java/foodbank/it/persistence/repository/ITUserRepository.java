package foodbank.it.persistence.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import foodbank.it.persistence.model.TUser;



public interface ITUserRepository extends PagingAndSortingRepository<TUser, Integer>{

    void deleteByIdUser(String idUser);
    Optional<TUser> findByIdUser(String idUser);
    Page<TUser> findByIdCompany(String idCompany, Pageable pageRequest);
    Page<TUser> findByIdOrg(int idOrg, Pageable pageRequest);
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

	
}
