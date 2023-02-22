package foodbank.it.web.controller;

import foodbank.it.service.IBirbCatService;
import foodbank.it.persistence.model.BirbCat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
@RestController
public class BirbCatController {

    private IBirbCatService BirbCatService;

    public BirbCatController(IBirbCatService BirbCatService) {
        this.BirbCatService = BirbCatService;

    }

    @GetMapping("birbcat/{birbId}")
    public BirbCat findOne(@PathVariable Integer birbId) {
        BirbCat entity = BirbCatService.findByBirbId(birbId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return entity;
    }
    @GetMapping("birbcats/")
    public Iterable<BirbCat> findAll() {
        return BirbCatService.findAll();
    }
    @PutMapping("birbcat/{birbId}")
    public BirbCat updateBirbCat(@PathVariable("birbId") Integer birbId, @RequestBody BirbCat updatedBirbCat) throws Exception {
        return this.BirbCatService.save(updatedBirbCat);
    }
    @PostMapping("birbcat/")
    public BirbCat createBirbCat(@RequestBody BirbCat newBirbCat) {
        return this.BirbCatService.save(newBirbCat);
    }
}
