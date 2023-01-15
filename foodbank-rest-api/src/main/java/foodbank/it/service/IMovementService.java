package foodbank.it.service;

import foodbank.it.persistence.model.MovementSummaryCount;
import java.util.List;

public interface IMovementService {
     List<MovementSummaryCount> findAll(SearchMovementCriteria searchCriteria);
}
