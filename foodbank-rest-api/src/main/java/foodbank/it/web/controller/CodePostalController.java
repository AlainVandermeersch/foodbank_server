package foodbank.it.web.controller;

import foodbank.it.persistence.model.CodePostal;
import foodbank.it.service.ICodePostalService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@RestController
public class CodePostalController {
    private ICodePostalService CodePostalService;

    public CodePostalController(ICodePostalService CodePostalService) {
        this.CodePostalService = CodePostalService;
    }
    @GetMapping("zipcode/{zipCode}")
    public CodePostal findOne(@PathVariable int zipCode) {
        return CodePostalService.findByZipCode(zipCode) .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    @GetMapping("zipcodes/{lCpas}")
    public Collection<CodePostal> find(@PathVariable int lCpas) {
        return (Collection<CodePostal>) CodePostalService.findByLCpas(lCpas);
    }
    @PutMapping("zipcode/{zipcode}")
    public CodePostal updateCodePostal(@PathVariable("zipcode") Integer zipcode, @RequestBody CodePostal updatedCodePostal) {
        return CodePostalService.save(updatedCodePostal);
    }

    @DeleteMapping("zipcode/{zipcode}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCodePostal(@PathVariable("zipcode") Integer zipcode) {
        CodePostalService.delete(zipcode);
    }

    @PostMapping("zipcode/")
    @ResponseStatus(HttpStatus.CREATED)
    public CodePostal create(@RequestBody CodePostal newCodePostal) {
        CodePostal CodePostal = this.CodePostalService.save(newCodePostal);
        return CodePostal;
    }

}
