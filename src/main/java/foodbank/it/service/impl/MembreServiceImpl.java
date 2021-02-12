package foodbank.it.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import foodbank.it.persistence.model.Membre;
import foodbank.it.persistence.repository.IMembreRepository;
import foodbank.it.service.IMembreService;
import org.springframework.data.domain.Pageable;
@Service
public class MembreServiceImpl implements IMembreService{

	private IMembreRepository MembreRepository;
	
	public MembreServiceImpl(IMembreRepository MembreRepository) {
        this.MembreRepository = MembreRepository;
    }
	@Override
    public Optional<Membre> findByBatId(int batId) {
        return MembreRepository.findByBatId(batId);
    }

    @Override
    public Membre save(Membre Membre) {        
        return MembreRepository.save(Membre);
    }

    @Override
    public Page<Membre> findAll(Pageable pageable) {
         return MembreRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public void delete(int batId) {
        MembreRepository.deleteByBatId(batId);
        
    }
   
	@Override
	public Page<Membre> findByBanqueObjectBankShortName( String bankShortName, Pageable pageable) {
		return MembreRepository.findByBanqueObjectBankShortName( bankShortName, pageable);
	
	}
	@Override
	public Page<Membre> findByLienDis(String lienDis, Pageable pageable) {
		return MembreRepository.findByLienDis(lienDis, pageable);
	}
	@Override
	public Page<Membre> findByNomContaining(String search, Pageable pageRequest) {
			return MembreRepository.findByNomContaining(search, pageRequest);
	}
	@Override
	public Page<Membre> findByLienDisAndNomContaining(String lienDis, String search, Pageable pageRequest) {
		return MembreRepository.findByLienDisAndNomContaining(lienDis, search, pageRequest);
	}
	@Override
	public Page<Membre> findByBanqueObjectBankShortNameAndNomContaining(String bankShortName, String search,
			Pageable pageRequest) {
		return MembreRepository.findByBanqueObjectBankShortNameAndNomContaining( bankShortName,search,
			pageRequest);
	}
	@Override
	public Page<Membre> findByPrenomContaining(String search, Pageable pageRequest) {
			return MembreRepository.findByPrenomContaining(search, pageRequest);
	}
	@Override
	public Page<Membre> findByLienDisAndPrenomContaining(String lienDis, String search, Pageable pageRequest) {
		return MembreRepository.findByLienDisAndPrenomContaining(lienDis, search, pageRequest);
	}
	@Override
	public Page<Membre> findByBanqueObjectBankShortNameAndPrenomContaining(String bankShortName, String search,
			Pageable pageRequest) {
		return MembreRepository.findByBanqueObjectBankShortNameAndPrenomContaining( bankShortName,search,
			pageRequest);
	}
	@Override
	public Page<Membre> findByAddressContaining(String search, Pageable pageRequest) {
			return MembreRepository.findByAddressContaining(search, pageRequest);
	}
	@Override
	public Page<Membre> findByLienDisAndAddressContaining(String lienDis, String search, Pageable pageRequest) {
		return MembreRepository.findByLienDisAndAddressContaining(lienDis, search, pageRequest);
	}
	@Override
	public Page<Membre> findByBanqueObjectBankShortNameAndAddressContaining(String bankShortName, String search,
			Pageable pageRequest) {
		return MembreRepository.findByBanqueObjectBankShortNameAndAddressContaining( bankShortName,search,
			pageRequest);
	}
	@Override
	public Page<Membre> findByZipContaining(String search, Pageable pageRequest) {
			return MembreRepository.findByZipContaining(search, pageRequest);
	}
	@Override
	public Page<Membre> findByLienDisAndZipContaining(String lienDis, String search, Pageable pageRequest) {
		return MembreRepository.findByLienDisAndZipContaining(lienDis, search, pageRequest);
	}
	@Override
	public Page<Membre> findByBanqueObjectBankShortNameAndZipContaining(String bankShortName, String search,
			Pageable pageRequest) {
		return MembreRepository.findByBanqueObjectBankShortNameAndZipContaining( bankShortName,search,
			pageRequest);
	}
	@Override
	public Page<Membre> findByCityContaining(String search, Pageable pageRequest) {
			return MembreRepository.findByCityContaining(search, pageRequest);
	}
	@Override
	public Page<Membre> findByLienDisAndCityContaining(String lienDis, String search, Pageable pageRequest) {
		return MembreRepository.findByLienDisAndCityContaining(lienDis, search, pageRequest);
	}
	@Override
	public Page<Membre> findByBanqueObjectBankShortNameAndCityContaining(String bankShortName, String search,
			Pageable pageRequest) {
		return MembreRepository.findByBanqueObjectBankShortNameAndCityContaining( bankShortName,search,
			pageRequest);
	}
}
	
