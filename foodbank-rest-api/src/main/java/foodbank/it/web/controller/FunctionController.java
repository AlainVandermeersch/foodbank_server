package foodbank.it.web.controller;
import foodbank.it.persistence.model.Function;
import foodbank.it.web.dto.FunctionDto;
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
import foodbank.it.service.IFunctionService;

import java.util.Collection;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
public class FunctionController {
    private IFunctionService FunctionService;

    public FunctionController(IFunctionService functionService) {
        this.FunctionService = functionService;
    }
    @GetMapping("function/{funcId}")
    public FunctionDto findOne(@PathVariable Integer funcId) {
        Function entity = FunctionService.findByFuncId(funcId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return convertToDto(entity);
    }
    @GetMapping("functions/")
    public Collection<FunctionDto> find(
            @RequestParam(required = false) Boolean actif,
            @RequestParam(required = false) String lienBanque) {
        Integer lienBanqueInteger = Optional.ofNullable(lienBanque).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
        List<FunctionDto> functionDtos = new ArrayList<>();
        Iterable<Function> selectedFunctions = this.FunctionService.findAll(lienBanqueInteger,actif);
        selectedFunctions.forEach(p -> functionDtos.add(convertToDto(p)));
        return functionDtos;
    }
    @PutMapping("function/{funcId}")
    public FunctionDto updateFunction(@PathVariable("funcId") Integer funcId, @RequestBody FunctionDto updatedFunction) {
        Function entity = convertToEntity(updatedFunction);
        boolean booCreateMode = false;
        try {
            Function function = this.FunctionService.save(entity,booCreateMode);
            return this.convertToDto(function);
        }
        catch (Exception ex) {
            String errorMsg = ex.getMessage();
            System.out.println(errorMsg);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMsg);
        }
    }
    @PostMapping("function/")
    @ResponseStatus(HttpStatus.CREATED)
    public FunctionDto create(@RequestBody FunctionDto newFunction) {
        Function entity = convertToEntity(newFunction);

        boolean booCreateMode = true;
        try {
            Function function = this.FunctionService.save(entity,booCreateMode);
            return this.convertToDto(function);
        }
        catch (Exception ex) {
            String errorMsg = ex.getMessage();
            System.out.println(errorMsg);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMsg);
        }
    }
    @DeleteMapping("function/{funcId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMembre(@PathVariable("funcId") Integer funcId) {
        try {
            this.FunctionService.delete(funcId);
        }
        catch (Exception ex) {
            String errorMsg = ex.getMessage();
            System.out.println(errorMsg);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMsg);
        }
    }
    private Function convertToEntity(FunctionDto dto) {
        Function myFunction = new Function(dto.getFuncId(), dto.getFonctionName(), dto.getFonctionNameNl(),
                dto.getDefinitionFonction(),(short) (dto.getActif() ? 1 : 0), dto.getRem(), dto.getLienBanque(), dto.getLienDis());
        return myFunction;
    }

    private FunctionDto convertToDto(Function entity) {
        boolean booActif= entity.getActif() == 1;
        FunctionDto dto = new FunctionDto(entity.getFuncId(), entity.getFonctionName(), entity.getFonctionNameNl(),
        entity.getDefinitionFonction(),booActif, entity.getRem(), entity.getLienBanque(), entity.getLienDis());
        return dto;
    }
}
