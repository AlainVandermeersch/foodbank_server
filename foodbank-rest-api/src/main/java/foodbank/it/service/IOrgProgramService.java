package foodbank.it.service;

import foodbank.it.persistence.model.OrgProgram;

import java.util.Optional;

public interface IOrgProgramService {
	Optional<OrgProgram> findByLienDis(int lienDis);
	OrgProgram save(OrgProgram OrgProgram);
    void deleteByLienDis(int lienDis);

}
