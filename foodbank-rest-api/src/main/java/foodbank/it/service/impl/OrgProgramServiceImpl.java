package foodbank.it.service.impl;

import foodbank.it.persistence.model.OrgProgram;
import foodbank.it.persistence.repository.IOrgProgramRepository;
import foodbank.it.service.IOrgProgramService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class OrgProgramServiceImpl implements IOrgProgramService {

	private IOrgProgramRepository OrgProgramRepository;
	
	public OrgProgramServiceImpl(IOrgProgramRepository OrgProgramRepository) {
        this.OrgProgramRepository = OrgProgramRepository;
    }
	@Override
    public Optional<OrgProgram> findByLienDis(int lienDis) {
        return OrgProgramRepository.findByLienDis(lienDis);
    }

    @Override
    public OrgProgram save(OrgProgram OrgProgram) {        
        return OrgProgramRepository.save(OrgProgram);
    }

    @Override
    @Transactional
    public void deleteByLienDis(int lienDis) {
    	// in some situations the org program was created but not saved when delete was called
    	Optional<OrgProgram> orgProgram = this.OrgProgramRepository.findByLienDis(lienDis);
		    orgProgram.ifPresent( myOrgProg -> {
				 this.OrgProgramRepository.deleteByLienDis(lienDis);
		 });
        
        
    }
}
