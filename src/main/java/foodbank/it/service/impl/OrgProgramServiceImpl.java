package foodbank.it.service.impl;

import java.util.Optional;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import foodbank.it.persistence.model.OrgProgram;
import foodbank.it.persistence.repository.IOrgProgramRepository;
import foodbank.it.service.IOrgProgramService;

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
        OrgProgramRepository.deleteByLienDis(lienDis);
        
    }
}
