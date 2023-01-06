package foodbank.it.service.impl;

import foodbank.it.persistence.model.MovementSummary;
import foodbank.it.persistence.repository.IMovementSummaryRepository;
import foodbank.it.service.IMovementSummaryService;

public class MovementSummaryServiceImpl implements IMovementSummaryService {
    private IMovementSummaryRepository movementSummaryRepository;

    public MovementSummaryServiceImpl(IMovementSummaryRepository movementSummaryRepository) {
        this.movementSummaryRepository = movementSummaryRepository;
    }

    @Override
    public Iterable<MovementSummary> findByMonth(String month) {
        return this.movementSummaryRepository.findByMonth(month);
    }



}
