package foodbank.it.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import foodbank.it.persistence.model.Client;
import foodbank.it.persistence.repository.IClientRepository;
import foodbank.it.service.IClientService;
@Service
public class ClientServiceImpl implements IClientService{

	private IClientRepository ClientRepository;
	
	public ClientServiceImpl(IClientRepository ClientRepository) {
        this.ClientRepository = ClientRepository;
    }
	@Override
    public Optional<Client> findByIdClient(int idClient) {
        return ClientRepository.findByIdClient(idClient);
    }

    @Override
    public Client save(Client Client) {        
        return ClientRepository.save(Client);
    }

    @Override
    public Iterable<Client> findAll() {
                return ClientRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(int idClient) {
        ClientRepository.deleteByIdClient(idClient);
        
    }
   
	@Override
	public Iterable<Client> findByBanqueObjectBankShortName( String bankShortName) {
		return ClientRepository.findByBanqueObjectBankShortName( bankShortName);
	}
	@Override
	public Iterable<Client> findByLienDis(int lienDis) {
		
		return ClientRepository.findByLienDis(lienDis);
	}
}
