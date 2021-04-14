package foodbank.it.service;

import java.util.Optional;

import foodbank.it.persistence.model.OrgProgram;

public interface IOrgProgramService {
	Optional<OrgProgram> findByLienDis(int lienDis);
	OrgProgram save(OrgProgram OrgProgram);
    void deleteByLienDis(int lienDis);

}
