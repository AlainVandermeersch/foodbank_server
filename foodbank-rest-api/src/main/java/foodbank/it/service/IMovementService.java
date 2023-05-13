package foodbank.it.service;

import foodbank.it.persistence.model.*;

import java.util.List;

public interface IMovementService {
     List<MovementMonthlyCountbyBank> findMonthlyByBank(SearchMovementCriteria searchCriteria);
     List<MovementMonthlyCountbyBankDepot> findMonthlyByBankDepot(SearchMovementCriteria searchCriteria);

     List<MovementDailyCountByBank> findDailyByBank(SearchMovementCriteria searchCriteria);
     List<MovementDailyCountByBankDepot> findDailyByBankDepot(SearchMovementCriteria searchCriteria);
     List<MovementDaily> findDailyMovements(SearchMovementCriteria searchCriteria);
     List<MovementMonthly> findMonthlyMovements(SearchMovementCriteria searchCriteria);
     List<MovementSummaryByIdOrg> findSummaryByIdOrg(boolean byMonth, SearchMovementCriteria searchCriteria);
}
