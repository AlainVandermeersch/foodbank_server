package foodbank.it.service;

import foodbank.it.persistence.model.*;

import java.util.List;

public interface IMovementService {

     List<MovementDaily> findDailyMovements(SearchMovementCriteria searchCriteria);
     List<MovementMonthly> findMonthlyMovements(SearchMovementCriteria searchCriteria);
}
