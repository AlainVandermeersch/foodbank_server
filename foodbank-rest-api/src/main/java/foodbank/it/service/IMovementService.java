package foodbank.it.service;

import foodbank.it.persistence.model.MovementDailyCountByBank;
import foodbank.it.persistence.model.MovementMonthlyCountbyBank;
import java.util.List;

public interface IMovementService {
     List<MovementMonthlyCountbyBank> findMonthlyByBank(SearchMovementCriteria searchCriteria);
     List<MovementDailyCountByBank> findDailyByBank(SearchMovementCriteria searchCriteria);
}
