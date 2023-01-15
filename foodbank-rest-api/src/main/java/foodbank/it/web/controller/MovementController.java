package foodbank.it.web.controller;
import foodbank.it.persistence.model.MovementSummaryCount;
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
    @GetMapping("movements/")
    public Iterable<MovementSummaryCount> findAll(@RequestParam String scope,
                                                  @RequestParam(required = false) String idCompany,
                                                  @RequestParam(required = false) String lienDis) {
        SearchMovementCriteria searchCriteria = new SearchMovementCriteria();
        searchCriteria.setScope(scope);
        Integer lienDisInteger = Optional.ofNullable(lienDis).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
        searchCriteria.setIdCompany(idCompany);
        searchCriteria.setLienDis(lienDisInteger);
        return movementService.findAll(searchCriteria);
    }
}
