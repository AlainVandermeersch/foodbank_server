package foodbank.it.web.controller;
import foodbank.it.persistence.model.MovementDailyCountByBank;
import foodbank.it.persistence.model.MovementMonthlyCountbyBank;
import foodbank.it.service.IMovementService;
import foodbank.it.service.SearchMovementCriteria;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
@RestController
public class MovementController {
    private IMovementService movementService;
    public MovementController(IMovementService movementService) {
        this.movementService = movementService;
    }
    @GetMapping("movementsmonthlybank/")
    public Iterable<MovementMonthlyCountbyBank> findAllMonthlyByBank(
            @RequestParam(required = false) String idCompany,
            @RequestParam(required = false) String lowRange,
            @RequestParam(required = false) String highRange) {
        SearchMovementCriteria searchCriteria = new SearchMovementCriteria();
        searchCriteria.setIdCompany(idCompany);
        searchCriteria.setLowRange(lowRange);
        searchCriteria.setHighRange(highRange);
        return movementService.findMonthlyByBank(searchCriteria);
    }
    @GetMapping("movementsdailybank/")
    public Iterable<MovementDailyCountByBank> findAllDailyByBank(
            @RequestParam(required = false) String idCompany,
            @RequestParam(required = false) String lowRange,
            @RequestParam(required = false) String highRange) {
        SearchMovementCriteria searchCriteria = new SearchMovementCriteria();
        searchCriteria.setIdCompany(idCompany);
        searchCriteria.setLowRange(lowRange);
        searchCriteria.setHighRange(highRange);
        return movementService.findDailyByBank(searchCriteria);
    }
}
