package foodbank.it.web.controller;

import foodbank.it.persistence.model.Organisation;
import foodbank.it.persistence.model.TUser;
import foodbank.it.service.IOrganisationService;
import foodbank.it.service.ITUserService;
import foodbank.it.service.SearchTUserCriteria;
import foodbank.it.web.dto.TUserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController

public class TUserController {

    private ITUserService TUserService;
    private IOrganisationService OrganisationService;
    
    public TUserController(ITUserService TUserService,IOrganisationService OrganisationService) {
        this.TUserService = TUserService;
        this.OrganisationService = OrganisationService;
    }

    //

    @GetMapping(value = "user/{idUser}")
    public TUserDto findOne(@PathVariable String idUser) {
        TUser entity = TUserService.findByIdUser(idUser)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return convertToDto(entity,(long) 1);
    }
   
   


    @GetMapping("users/")     
    public Collection<TUserDto> find( @RequestParam String offset, @RequestParam String rows, 
    		@RequestParam String sortField, @RequestParam String sortOrder, 
    		@RequestParam(required = false) String idUser,@RequestParam(required = false) String userName, 
    		@RequestParam(required = false) String idLanguage,@RequestParam(required = false) String email, 
    		@RequestParam(required = false) String rights,
    		@RequestParam(required = false) String lienBanque, @RequestParam(required = false) String idOrg )  {
    	int intOffset = Integer.parseInt(offset);
    	int intRows = Integer.parseInt(rows);
    	int pageNumber=intOffset/intRows; // Java throws away remainder of division
        int pageSize = intRows;
        Pageable pageRequest = null;
        if (sortOrder.equals("1")) {
        	pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortField).ascending());
        }
        else {
        	pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortField).descending());
        } 
        Integer lienBanqueInteger = Optional.ofNullable(lienBanque).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
        Integer idOrgInteger = Optional.ofNullable(idOrg).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
		SearchTUserCriteria criteria = new SearchTUserCriteria(idUser, userName,idLanguage,email,rights, lienBanqueInteger, idOrgInteger);
		Page<TUser> selectedTUsers = this.TUserService.findAll(criteria, pageRequest);
		long totalElements = selectedTUsers.getTotalElements();

		return selectedTUsers.stream()
				.map(TUser -> convertToDto(TUser, totalElements))
				.collect(Collectors.toList());
    }
    

    @PutMapping("user/{idUser}")
    public TUserDto updateTUser(@PathVariable("idUser") String idUser, @RequestBody TUserDto updatedTUser) {
        TUser TUserEntity = convertToEntity(updatedTUser);
        boolean booCreateMode = false;
        try {
        	TUser tuser = this.TUserService.save(TUserEntity,booCreateMode);
        	return this.convertToDto(tuser,(long) 1);
        }
        catch (Exception ex) {
        	String errorMsg = ex.getMessage();
        	System.out.println(errorMsg);
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMsg);
        }
    }

    @DeleteMapping("user/{idUser}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTUser(@PathVariable("idUser") String idUser) {
        TUserService.delete(idUser);
    }


    @PostMapping("user/")
    @ResponseStatus(HttpStatus.CREATED)
    public TUserDto create(@RequestBody TUserDto newTUser) {
        TUser entity = convertToEntity(newTUser);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        entity.setPassword(passwordEncoder.encode(newTUser.getPassword()));
        boolean booCreateMode = true;
        try {
        	TUser tuser = this.TUserService.save(entity,booCreateMode);
        	return this.convertToDto(tuser,(long) 1);
        }
        catch (Exception ex) {
        	String errorMsg = ex.getMessage();
        	System.out.println(errorMsg);
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMsg);
        }
    }


    protected TUserDto convertToDto(TUser entity,Long  totalRecords) {
    	
    	String societe ="";
    	Organisation orgOfUser = entity.getOrganisationObject();
    	if (orgOfUser != null) {
    		societe = orgOfUser.getSociete();
    	}
    	
    	boolean booActif= entity.getActif() == 1;
    	boolean booDroit1= entity.getDroit1() == 1;
    	boolean booGestBen = entity.getGestBen() == 1;
    	boolean booGestInv = entity.getGestInv() == 1;
    	boolean booGestFead = entity.getGestFead() == 1;
    	boolean booGestAsso = entity.getGestAsso() == 1;
    	boolean booGestCpas = entity.getGestCpas() == 1;
    	boolean booGestMemb = entity.getGestMemb() == 1;
    	boolean booGestDon = entity.getGestDon() == 1;
        TUserDto dto = new TUserDto(entity.getIdUser(), entity.getUserName(), entity.getIdCompany(), entity.getIdOrg(), entity.getIdLanguage(), entity.getLienBat(), booActif, entity.getRights(), entity.getPassword(), entity.getDepot(), booDroit1, entity.getEmail(), 
        		booGestBen, booGestInv, booGestFead, booGestAsso,
        		booGestCpas, booGestMemb, booGestDon, entity.getLienBanque(), entity.getLienCpas(),societe,totalRecords);       
        return dto;
    }

    protected TUser convertToEntity(TUserDto dto) {
    	
    	Organisation orgOfUser = null;
    	
    	Optional<Organisation> org = this.OrganisationService.findByIdDis(dto.getIdOrg());
    		if (org.isPresent() == true) orgOfUser = org.get() ;
        TUser tUser = new TUser(dto.getIdUser(), dto.getUserName(), dto.getIdCompany(), orgOfUser, dto.getIdLanguage(), dto.getLienBat(), 
        		(short) (dto.getActif() ? 1 : 0) , dto.getRights(), dto.getPassword(), dto.getDepot(), (short) (dto.getDroit1() ? 1 : 0) , dto.getEmail(), 
        		(short) (dto.getGestBen() ? 1 : 0) , (short) (dto.getGestInv() ? 1 : 0) , (short) (dto.getGestFead() ? 1 : 0) , (short) (dto.getGestAsso() ? 1 : 0) ,
        		(short) (dto.getGestCpas() ? 1 : 0) , (short) (dto.getGestMemb() ? 1 : 0) ,(short) (dto.getGestDon() ? 1 : 0) , dto.getLienBanque(), dto.getLienCpas());
       
        return tUser;
    }

   

}
