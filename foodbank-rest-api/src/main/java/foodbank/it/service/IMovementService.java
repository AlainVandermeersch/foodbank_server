package foodbank.it.service;

import foodbank.it.persistence.model.MovementBankCount;
import java.util.List;

public interface IMovementService {
     List<MovementBankCount> findAll(SearchMovementCriteria searchCriteria);
}
