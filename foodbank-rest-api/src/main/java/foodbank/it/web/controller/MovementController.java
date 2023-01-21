package foodbank.it.web.controller;
import foodbank.it.persistence.model.*;
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
    @GetMapping("movementsmonthlybankdepot/")
    public Iterable<MovementMonthlyCountbyBankDepot> findAllMonthlyByBankDepot(
            @RequestParam(required = false) String idCompany,
            @RequestParam(required = false) String lowRange,
            @RequestParam(required = false) String highRange) {
        SearchMovementCriteria searchCriteria = new SearchMovementCriteria();
        searchCriteria.setIdCompany(idCompany);
        searchCriteria.setLowRange(lowRange);
        searchCriteria.setHighRange(highRange);
        return movementService.findMonthlyByBankDepot(searchCriteria);
    }
    @GetMapping("movementsdailybank/")
    public Iterable<MovementDailyCountByBank> findAllDailyByBank(
            @RequestParam(required = false) String idCompany,
            @RequestParam(required = false) String lowRange,
            @RequestParam(required = false) String highRange,
            @RequestParam(required = false) String lastDays) {
        SearchMovementCriteria searchCriteria = new SearchMovementCriteria();
        searchCriteria.setIdCompany(idCompany);
        searchCriteria.setLowRange(lowRange);
        searchCriteria.setHighRange(highRange);
        Integer lastDaysInteger = Optional.ofNullable(lastDays).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
        searchCriteria.setLastDays(lastDaysInteger);
        return movementService.findDailyByBank(searchCriteria);
    }
    @GetMapping("movementsdailybankdepot/")
    public Iterable<MovementDailyCountByBankDepot> findAllDailyByBankDepot(
            @RequestParam(required = false) String idCompany,
            @RequestParam(required = false) String lowRange,
            @RequestParam(required = false) String highRange,
            @RequestParam(required = false) String lastDays) {
        SearchMovementCriteria searchCriteria = new SearchMovementCriteria();
        searchCriteria.setIdCompany(idCompany);
        searchCriteria.setLowRange(lowRange);
        searchCriteria.setHighRange(highRange);
        Integer lastDaysInteger = Optional.ofNullable(lastDays).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
        searchCriteria.setLastDays(lastDaysInteger);
        return movementService.findDailyByBankDepot(searchCriteria);
    }
    @GetMapping("movementsdaily/")
    public Iterable<MovementDaily> findAllDaily(
            @RequestParam(required = false) String idCompany,
            @RequestParam(required = false) String lowRange,
            @RequestParam(required = false) String highRange,
            @RequestParam(required = false) String lastDays) {
        SearchMovementCriteria searchCriteria = new SearchMovementCriteria();
        searchCriteria.setIdCompany(idCompany);
        searchCriteria.setLowRange(lowRange);
        searchCriteria.setHighRange(highRange);
        Integer lastDaysInteger = Optional.ofNullable(lastDays).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
        searchCriteria.setLastDays(lastDaysInteger);
        return movementService.findDailySummaries(searchCriteria);
    }
}
