package foodbank.it.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import foodbank.it.persistence.model.ClientDependent;
import foodbank.it.persistence.repository.IClientDependentRepository;
import foodbank.it.service.IClientDependentService;
@Service
public class ClientDependentServiceImpl implements IClientDependentService{
	
	private IClientDependentRepository ClientDependentRepository;
	
	public ClientDependentServiceImpl(IClientDependentRepository ClientDependentRepository) {
        this.ClientDependentRepository = ClientDependentRepository;
    }

	@Override
	public Optional<ClientDependent> findByIdDep(int idDep) {
		return this.ClientDependentRepository.findByIdDep(idDep);
	}

	@Override
	public ClientDependent save(ClientDependent clientDependent) {
		return this.ClientDependentRepository.save(clientDependent);
	}

	@Override
	public Iterable<ClientDependent> findAll() {
		return this.ClientDependentRepository.findAll();
	}

	@Override
	public Iterable<ClientDependent> findByActif(short actif) {
		return this.ClientDependentRepository.findByActif(actif);
	}

	@Override
	@Transactional
	public void delete(int idDep) {
		this.ClientDependentRepository.deleteByIdDep(idDep);
		
	}

	@Override
	public Iterable<ClientDependent> findByLienMast(int lienMast) {
		return this.ClientDependentRepository.findByLienMast(lienMast);
	}

}
