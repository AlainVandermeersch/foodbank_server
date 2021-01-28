package foodbank.it.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import foodbank.it.persistence.model.Cpas;
import foodbank.it.service.ICpasService;
import foodbank.it.web.dto.CpasDto;


@RestController

public class CpasController {
    
    private ICpasService CpasService;
    
    public CpasController(ICpasService CpasService) {
        this.CpasService = CpasService;
       
    }
    @CrossOrigin
    @GetMapping("cpas/{cpasId}")
    public CpasDto findOne(@PathVariable Integer cpasId) {
        Cpas entity = CpasService.findByCpasId(cpasId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return convertToDto(entity);
    }
   
    @CrossOrigin
    @GetMapping("cpass/")
    public Collection<CpasDto> find( @RequestParam(required = false) String cpasZip) {
        Iterable<Cpas> selectedCpass = null;
        if (cpasZip == null) {	
        	selectedCpass = this.CpasService.findAll();
        }
        else {
        	selectedCpass = this.CpasService.findByCpasZip(cpasZip);
        }
        List<CpasDto> CpasDtos = new ArrayList<>();
        selectedCpass.forEach(p -> CpasDtos.add(convertToDto(p)));
        return CpasDtos;
    }
    @CrossOrigin
    @PutMapping("cpas/{cpasId}")
    public CpasDto updateCpas(@PathVariable("cpasId") Integer cpasId, @RequestBody CpasDto updatedCpas) {
        Cpas CpasEntity = convertToEntity(updatedCpas);
        return this.convertToDto(this.CpasService.save(CpasEntity));
    }
    @CrossOrigin
    @DeleteMapping("cpas/{cpasId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCpas(@PathVariable("cpasId") Integer cpasId) {
        CpasService.delete(cpasId);
    }
    @CrossOrigin
    @PostMapping("cpas/")
    @ResponseStatus(HttpStatus.CREATED)
    public CpasDto create(@RequestBody CpasDto newCpas) {
        Cpas entity = convertToEntity(newCpas);
        // Alain todo later entity.setDateCreated(LocalDate.now());
        Cpas Cpas = this.CpasService.save(entity);        
        return this.convertToDto(Cpas);
    }
    protected CpasDto convertToDto(Cpas entity) {
        CpasDto dto = new CpasDto(entity.getCpasId(),entity.getCpasZip(),  entity.getCpasName(), entity.getCpasMail(), entity.getCpasStreet(), entity.getCpasTel(), entity.getCpasTel(), entity.getCpasGsm(), entity.getCpasContactName(), entity.getCivilite(), entity.getRem(), entity.getPassword(), entity.getLBanque(), entity.getLangue());       
        return dto;
    }

    protected Cpas convertToEntity(CpasDto dto) {
        Cpas cpas = new Cpas( dto.getCpasId(),dto.getCpasZip(), dto.getCpasName(), dto.getCpasMail(), dto.getCpasStreet(), dto.getCpasTel(), dto.getCpasTel(), dto.getCpasGsm(), dto.getCpasContactName(), dto.getCivilite(), dto.getRem(), dto.getPassword(), dto.getLBanque(), dto.getLangue());       
        if (!StringUtils.isEmpty(dto.getCpasId())) {
            cpas.setCpasId(dto.getCpasId());
        }
        return cpas;
    }


}

