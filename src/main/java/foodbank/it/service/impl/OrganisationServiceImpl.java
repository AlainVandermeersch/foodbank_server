package foodbank.it.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import foodbank.it.persistence.model.Organisation;
import foodbank.it.persistence.repository.IOrganisationRepository;
import foodbank.it.service.IOrganisationService;
@Service
public class OrganisationServiceImpl implements IOrganisationService{

	private IOrganisationRepository OrganisationRepository;
	
	public OrganisationServiceImpl(IOrganisationRepository OrganisationRepository) {
        this.OrganisationRepository = OrganisationRepository;
    }
	@Override
    public Optional<Organisation> findByIdDis(int idDis) {
        return OrganisationRepository.findByIdDis(idDis);
    }

    @Override
    public Organisation save(Organisation Organisation) {        
        return OrganisationRepository.save(Organisation);
    }

    @Override
    public Iterable<Organisation> findAll() {
                return OrganisationRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(int idDis) {
        OrganisationRepository.deleteByIdDis(idDis);
        
    }
	@Override
	public Iterable<Organisation> findByLienBanque(short lienBanque) {
		return OrganisationRepository.findByLienBanque(lienBanque);
	}


	
}
