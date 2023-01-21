package foodbank.it.service;

import foodbank.it.persistence.model.MovementDaily;
import foodbank.it.persistence.model.MovementDailyCountByBank;
import foodbank.it.persistence.model.MovementMonthlyCountbyBank;
import foodbank.it.persistence.model.MovementDailyCountByBankDepot;
import foodbank.it.persistence.model.MovementMonthlyCountbyBankDepot;

import java.util.List;

public interface IMovementService {
     List<MovementMonthlyCountbyBank> findMonthlyByBank(SearchMovementCriteria searchCriteria);
     List<MovementMonthlyCountbyBankDepot> findMonthlyByBankDepot(SearchMovementCriteria searchCriteria);

     List<MovementDailyCountByBank> findDailyByBank(SearchMovementCriteria searchCriteria);
     List<MovementDailyCountByBankDepot> findDailyByBankDepot(SearchMovementCriteria searchCriteria);
     List<MovementDaily> findDailySummaries(SearchMovementCriteria searchCriteria);
}
