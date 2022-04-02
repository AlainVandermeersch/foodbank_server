package foodbank.it.web.controller;
import foodbank.it.persistence.model.TypeEmploi;
import foodbank.it.web.dto.TypeEmploiDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import foodbank.it.service.ITypeEmploiService;

import java.util.Collection;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TypeEmploiController {
    private ITypeEmploiService TypeEmploiService;

    public TypeEmploiController(ITypeEmploiService functionService) {
        this.TypeEmploiService = functionService;
    }
    @GetMapping("membreemploitype/{jobNr}")
    public TypeEmploiDto findOne(@PathVariable Integer jobNr) {
        TypeEmploi entity = TypeEmploiService.findByJobNr(jobNr)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return convertToDto(entity);
    }
    @GetMapping("membreemploitypes/")
    public Collection<TypeEmploiDto> find(
            @RequestParam(required = false) Boolean actif,
            @RequestParam(required = false) String lienBanque,
            @RequestParam String language) {
        Integer lienBanqueInteger = Optional.ofNullable(lienBanque).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
        List<TypeEmploiDto> functionDtos = new ArrayList<>();
        Iterable<TypeEmploi> selectedTypeEmplois = this.TypeEmploiService.findAll(lienBanqueInteger,actif,language);
        selectedTypeEmplois.forEach(p -> functionDtos.add(convertToDto(p)));
        return functionDtos;
    }
    @PutMapping("membreemploitype/{jobNr}")
    public TypeEmploiDto updateTypeEmploi(@PathVariable("jobNr") Integer jobNr, @RequestBody TypeEmploiDto updatedTypeEmploi) {
        TypeEmploi entity = convertToEntity(updatedTypeEmploi);
        try {
            TypeEmploi function = this.TypeEmploiService.save(entity);
            return this.convertToDto(function);
        }
        catch (Exception ex) {
            String errorMsg = ex.getMessage();
            System.out.println(errorMsg);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMsg);
        }
    }
    @PostMapping("membreemploitype/")
    @ResponseStatus(HttpStatus.CREATED)
    public TypeEmploiDto create(@RequestBody TypeEmploiDto newTypeEmploi) {
        TypeEmploi entity = convertToEntity(newTypeEmploi);

        try {
            TypeEmploi function = this.TypeEmploiService.save(entity);
            return this.convertToDto(function);
        }
        catch (Exception ex) {
            String errorMsg = ex.getMessage();
            System.out.println(errorMsg);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMsg);
        }
    }
    @DeleteMapping("membreemploitype/{jobNr}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMembre(@PathVariable("jobNr") Integer jobNr) {
        try {
            this.TypeEmploiService.deleteByJobNr(jobNr);
        }
        catch (Exception ex) {
            String errorMsg = ex.getMessage();
            System.out.println(errorMsg);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMsg);
        }
    }
    private TypeEmploi convertToEntity(TypeEmploiDto dto) {
        TypeEmploi myTypeEmploi = new TypeEmploi(dto.getJobNr(), dto.getJobNameFr(), dto.getJobNameNl(),
                dto.getLienBanque(),(short) (dto.isActif() ? 1 : 0), dto.getJobNameEn(), dto.getJobNameGe());
        return myTypeEmploi;
    }

    private TypeEmploiDto convertToDto(TypeEmploi entity) {
        boolean booActif= entity.getActif() == 1;
        TypeEmploiDto dto = new TypeEmploiDto(entity.getJobNr(), entity.getJobNameFr(), entity.getJobNameNl(),
                entity.getLienBanque(),booActif, entity.getJobNameEn(), entity.getJobNameGe(), entity.getBankShortName());
        return dto;
    }
}

