package foodbank.it.persistence.repository;

import foodbank.it.persistence.model.MovementSummary;
import foodbank.it.persistence.model.MovementSummaryKey;
import org.springframework.data.repository.CrudRepository;

public interface IMovementSummaryRepository extends CrudRepository<MovementSummary, MovementSummaryKey> {
    Iterable<MovementSummary> findByMonth(String month);


}
