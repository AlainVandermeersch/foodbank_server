package foodbank.it.service;

import foodbank.it.persistence.model.MovementDailyCount;
import foodbank.it.persistence.model.MovementMonthlyCount;
import java.util.List;

public interface IMovementService {
     List<MovementMonthlyCount> findAllMonthly(SearchMovementCriteria searchCriteria);
     List<MovementDailyCount> findAllDaily(SearchMovementCriteria searchCriteria);
}
