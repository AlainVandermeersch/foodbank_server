package foodbank.it.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import foodbank.it.persistence.model.TUser;
import foodbank.it.persistence.repository.ITUserRepository;
import foodbank.it.service.ITUserService;

@Service
public class TUserServiceImpl implements ITUserService {

    private ITUserRepository TUserRepository;

    public TUserServiceImpl(ITUserRepository TUserRepository) {
        this.TUserRepository = TUserRepository;
    }

    
    @Override
    public Optional<TUser> findByIdUser(String idUser) {
        return TUserRepository.findByIdUser(idUser);
    }

    @Override
    public TUser save(TUser TUser) {
      /*  if (StringUtils.isEmpty(TUser.getIdUser()())) {
            TUser.setDateCreated(LocalDate.now());
        } */
        return TUserRepository.save(TUser);
    }

    @Override
    @Transactional
    public void delete(String idUser) {
        TUserRepository.deleteByIdUser(idUser);
    }


	@Override
	public Page<TUser> findAll(Pageable pageRequest) {
		return TUserRepository.findAll(pageRequest);
	}


	@Override
	public Page<TUser> findByIdCompany(String idCompany, Pageable pageRequest) {
		return TUserRepository.findByIdCompany(idCompany, pageRequest);
	}


	@Override
	public Page<TUser> findByIdOrg(int idOrg, Pageable pageRequest) {
		return TUserRepository.findByIdOrg(idOrg, pageRequest);
	}
    
	
	

    

}
