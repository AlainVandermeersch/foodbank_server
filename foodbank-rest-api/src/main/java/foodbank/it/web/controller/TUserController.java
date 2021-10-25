package foodbank.it.web.controller;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

import foodbank.it.persistence.model.Membre;
import foodbank.it.persistence.model.Organisation;
import foodbank.it.persistence.model.TUser;
import foodbank.it.service.IMembreService;
import foodbank.it.service.IOrganisationService;
import foodbank.it.service.ITUserService;
import foodbank.it.service.SearchTUserCriteria;
import foodbank.it.web.dto.TUserDto;

@RestController

public class TUserController {

    private ITUserService TUserService;
    private IOrganisationService OrganisationService;
    private IMembreService MembreService;
    
    public TUserController(ITUserService TUserService,IOrganisationService OrganisationService, IMembreService MembreService) {
        this.TUserService = TUserService;
        this.OrganisationService = OrganisationService;
        this.MembreService = MembreService;
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
    		@RequestParam(required = false) String idUser,@RequestParam(required = false) String membreNom, 
    		@RequestParam(required = false) String membrePrenom, @RequestParam(required = false) String membreLangue,
    		@RequestParam(required = false) String membreEmail, @RequestParam(required = false) String rights,
    		@RequestParam(required = false) String lienDepot ,
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
        Integer lienDepotInteger = Optional.ofNullable(lienDepot).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
        Integer membreLangueInteger = Optional.ofNullable(membreLangue).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
		SearchTUserCriteria criteria = new SearchTUserCriteria(idUser, membreNom,membrePrenom ,membreLangueInteger,membreEmail,rights, lienBanqueInteger, idOrgInteger,lienDepotInteger);
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
    	
    	String membreNom = "";
    	String membrePrenom = "";
    	String membreEmail = "";
    	Short membreLangue = 0;
    	Membre membreOfUser = entity.getMembreObject();
    	if (membreOfUser != null) {
    		membreNom = membreOfUser.getNom();
    		membrePrenom = membreOfUser.getPrenom();
    		membreEmail = membreOfUser.getBatmail();
    		membreLangue = membreOfUser.getLangue();
    		
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
        		booGestCpas, booGestMemb, booGestDon, entity.getLienBanque(), entity.getLienCpas(),entity.getSociete(),membreNom,membrePrenom,membreEmail, membreLangue, totalRecords);       
        return dto;
    }

    protected TUser convertToEntity(TUserDto dto) {
    	
    	Membre membreOfUser = null;
    	Optional<Membre> membre = this.MembreService.findByBatId(dto.getLienBat());
    	if (membre.isPresent()) membreOfUser = membre.get();
    		
        TUser tUser = new TUser(dto.getIdUser(), dto.getUserName(), dto.getIdCompany(), dto.getIdOrg(), dto.getIdLanguage(), membreOfUser, 
        		(short) (dto.getActif() ? 1 : 0) , dto.getRights(), dto.getPassword(), dto.getDepot(), (short) (dto.getDroit1() ? 1 : 0) , dto.getEmail(), 
        		(short) (dto.getGestBen() ? 1 : 0) , (short) (dto.getGestInv() ? 1 : 0) , (short) (dto.getGestFead() ? 1 : 0) , (short) (dto.getGestAsso() ? 1 : 0) ,
        		(short) (dto.getGestCpas() ? 1 : 0) , (short) (dto.getGestMemb() ? 1 : 0) ,(short) (dto.getGestDon() ? 1 : 0) , dto.getLienBanque(), dto.getLienCpas());
       
        return tUser;
    }
   
   

}
