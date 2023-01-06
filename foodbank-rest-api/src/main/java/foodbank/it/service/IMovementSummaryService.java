package foodbank.it.service;

import foodbank.it.persistence.model.MovementSummary;

public interface IMovementSummaryService {
    Iterable<MovementSummary> findByMonth(String month);


}
