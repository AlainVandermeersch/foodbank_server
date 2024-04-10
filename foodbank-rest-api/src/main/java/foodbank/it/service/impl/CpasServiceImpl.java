package foodbank.it.service.impl;

import foodbank.it.persistence.model.Cpas;
import foodbank.it.persistence.repository.ICpasRepository;
import foodbank.it.service.ICpasService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
@Service
public class CpasServiceImpl implements ICpasService{
    
    private ICpasRepository CpasRepository;

    public CpasServiceImpl(ICpasRepository CpasRepository) {
        this.CpasRepository = CpasRepository;
    }

	@Override
	public Optional<Cpas> findByCpasId(int cpasId) {
		return CpasRepository.findByCpasId(cpasId);
	}

	@Override
	public Cpas save(Cpas Cpas) {
		return CpasRepository.save(Cpas);
		
	}
	

	@Override
	@Transactional
	public void delete(int cpasId) {
		CpasRepository.deleteByCpasId(cpasId);
		
	}

	@Override
	public Page<Cpas> findByLbanqueAndCpasNameContaining(short lbanque, String searchValue, Pageable pageRequest) {
		return this.CpasRepository.findByLbanqueAndCpasNameContaining(lbanque,searchValue,pageRequest);
	}

	@Override
	public Page<Cpas> findByLbanqueAndCpasZipStartsWith(short lbanque, String searchValue, Pageable pageRequest) {
		return this.CpasRepository.findByLbanqueAndCpasZipStartsWith(lbanque,searchValue,pageRequest);
	}

	@Override
	public Page<Cpas> findAll(Pageable pageable) {
		return CpasRepository.findAll(pageable);
	}

	@Override
	public Page<Cpas> findByCpasNameContaining(String search, Pageable pageRequest) {
		return CpasRepository.findByCpasNameContaining(search, pageRequest);
	}

	@Override
	public Page<Cpas> findByCpasZipStartsWith(String search, Pageable pageRequest) {
		return CpasRepository.findByCpasZipStartsWith(search, pageRequest);
	}

	@Override
	public Page<Cpas> findByLbanque(short lbanque, Pageable pageRequest) {
		return CpasRepository.findByLbanque(lbanque, pageRequest);
	}

}
