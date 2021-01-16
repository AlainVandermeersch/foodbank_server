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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import foodbank.it.persistence.model.TUser;
import foodbank.it.service.ITUserService;
import foodbank.it.web.dto.TUserDto;

@RestController

public class TUserController {

    private ITUserService TUserService;
    
    public TUserController(ITUserService TUserService) {
        this.TUserService = TUserService;
       
    }

    //
    @CrossOrigin
    @GetMapping(value = "user/{idUser}")
    public TUserDto findOne(@PathVariable String idUser) {
        TUser entity = TUserService.findByIdUser(idUser)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return convertToDto(entity);
    }
   

    @CrossOrigin
    @GetMapping("users/")     
    public Collection<TUserDto> find( @RequestParam(required = false) String idCompany,@RequestParam(required = false) String idOrg )  {
        Iterable<TUser> selectedTUsers = null;
        if (idCompany == null) {
        	if (idOrg == null) selectedTUsers = this.TUserService.findAll();
        	else selectedTUsers = this.TUserService.findByIdOrg(Integer.parseInt(idOrg));
        }
        else selectedTUsers = this.TUserService.findByIdCompany(idCompany);
        List<TUserDto> TUserDtos = new ArrayList<>();
        selectedTUsers.forEach(p -> TUserDtos.add(convertToDto(p)));
        return TUserDtos;
    }
    
    @CrossOrigin
    @PutMapping("user/{idUser}")
    public TUserDto updateTUser(@PathVariable("idUser") String idUser, @RequestBody TUserDto updatedTUser) {
        TUser TUserEntity = convertToEntity(updatedTUser);
        return this.convertToDto(this.TUserService.save(TUserEntity));
    }
    @CrossOrigin
    @DeleteMapping("user/{idUser}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTUser(@PathVariable("idUser") String idUser) {
        TUserService.delete(idUser);
    }
    @CrossOrigin
    @PostMapping("user/")
    @ResponseStatus(HttpStatus.CREATED)
    public TUserDto create(@RequestBody TUserDto newTUser) {
        TUser entity = convertToEntity(newTUser);
        // Alain todo later entity.setDateCreated(LocalDate.now());
        TUser TUser = this.TUserService.save(entity);        
        return this.convertToDto(TUser);
    }


    protected TUserDto convertToDto(TUser entity) {
        TUserDto dto = new TUserDto(entity.getIdUser(), entity.getUserName(), entity.getIdCompany(), entity.getIdOrg(), entity.getIdLanguage(), entity.getLienBat(), entity.getActif(), entity.getRights(), entity.getPassword(), entity.getDepot(), entity.getDroit1(), entity.getEmail(), entity.getGestBen(), entity.getGestInv(), entity.getGestFead(), entity.getGestAsso(),
            entity.getGestCpas(), entity.getGestMemb(), entity.getGestDon(), entity.getLienBanque(), entity.getLienCpas());       
        return dto;
    }

    protected TUser convertToEntity(TUserDto dto) {
        TUser tUser = new TUser(dto.getIdUser(), dto.getUserName(), dto.getIdCompany(), dto.getIdOrg(), dto.getIdLanguage(), dto.getLienBat(), dto.getActif(), dto.getRights(), dto.getPassword(), dto.getDepot(), dto.getDroit1(), dto.getEmail(), dto.getGestBen(), dto.getGestInv(), dto.getGestFead(), dto.getGestAsso(),
            dto.getGestCpas(), dto.getGestMemb(), dto.getGestDon(), dto.getLienBanque(), dto.getLienCpas());
       
        return tUser;
    }

   

}
