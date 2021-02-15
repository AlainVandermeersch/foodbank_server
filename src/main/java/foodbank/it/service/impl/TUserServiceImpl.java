package foodbank.it.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

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
    public Iterable<TUser> findAll() {
        return TUserRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(String idUser) {
        TUserRepository.deleteByIdUser(idUser);
    }
    
	
	@Override
	public Iterable<TUser> findByIdCompany(String idCompany) {
		return  TUserRepository.findByIdCompany(idCompany);
	}


	@Override
	public Iterable<TUser> findByIdOrg(int idOrg) {
		return  TUserRepository.findByIdOrg(idOrg);
	}

    

}
