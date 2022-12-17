package foodbank.it.web.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import foodbank.it.persistence.model.TUser;
import foodbank.it.service.ITUserService;
import foodbank.it.service.SearchTUserCriteria;
import foodbank.it.web.dto.TUserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController

public class TUserController {

	private ITUserService TUserService;
	private static final BCrypt.Hasher HASHER = BCrypt.with(BCrypt.Version.VERSION_2Y);
	public static final int HASH_COST = 10;
	public TUserController(ITUserService TUserService) {
		this.TUserService = TUserService;		
	}

	//

	@GetMapping(value = "user/{idUser}")
	public TUserDto findOne(@PathVariable String idUser) {
		TUser entity = TUserService.findByIdUser(idUser)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return convertToDto(entity, (long) 1);
	}

	@GetMapping("usersall/")
	public Collection<TUserDto> findAll(@RequestParam(required = false) String lienBanque,
			@RequestParam(required = false) String idOrg,
			@RequestParam(required = false) String lienBat
			) {
		List<TUser> selectedTUsers;
		Short lienBanqueShort = Optional.ofNullable(lienBanque).filter(str -> !str.isEmpty()).map(Short::parseShort)
				.orElse(null);

		Integer idOrgInteger = Optional.ofNullable(idOrg).filter(str -> !str.isEmpty()).map(Integer::parseInt)
				.orElse(null);
		Integer lienBatInteger = Optional.ofNullable(lienBat).filter(str -> !str.isEmpty()).map(Integer::parseInt)
				.orElse(null);
		if (lienBat != null) {
			selectedTUsers = (List<TUser>) this.TUserService.findByLienBat(lienBatInteger);
		} 
		else if (idOrg != null) {
			selectedTUsers = (List<TUser>) this.TUserService.findByIdOrg(idOrgInteger);
		} 
		else if (lienBanque != null) {
				selectedTUsers = (List<TUser>) this.TUserService.findByLienBanque(lienBanqueShort);
			}
		else {
			selectedTUsers = (List<TUser>) this.TUserService.findAll();
		}
		long nbOfSelectedTusers = selectedTUsers.size();

		return selectedTUsers.stream().map(tuser -> convertToDto(tuser, nbOfSelectedTusers))
				.collect(Collectors.toList());
	}

	@GetMapping("users/")
	public Collection<TUserDto> find(@RequestParam String offset, @RequestParam String rows,
			@RequestParam String sortField, @RequestParam String sortOrder,
			@RequestParam(required = false) String idUser, @RequestParam(required = false) String userName,
			@RequestParam(required = false) String idLanguage, @RequestParam(required = false) String idCompany,
			@RequestParam(required = false) String email, @RequestParam(required = false) String rights,
			@RequestParam(required = false) String lienDepot, @RequestParam(required = false) Boolean actif,
			@RequestParam(required = false) Boolean droit1, @RequestParam(required = false) Boolean gestMemb,
			@RequestParam(required = false) Boolean gestBen, @RequestParam(required = false) Boolean gestFead,
			@RequestParam(required = false) Boolean gestDon, @RequestParam(required = false) Boolean hasLogins,
			@RequestParam(required = false) String hasAnomalies,@RequestParam(required = false) Boolean classicBanks,
			@RequestParam(required = false) String lienBanque, @RequestParam(required = false) String idOrg) {
		int intOffset = Integer.parseInt(offset);
		int intRows = Integer.parseInt(rows);
		int pageNumber = intOffset / intRows; // Java throws away remainder of division
		int pageSize = intRows;
		Pageable pageRequest = null;

		if (sortOrder.equals("1")) {
			pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortField).ascending());
		} else {
			pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortField).descending());
		}

		Integer lienBanqueInteger = Optional.ofNullable(lienBanque).filter(str -> !str.isEmpty()).map(Integer::parseInt)
				.orElse(null);
		Integer idOrgInteger = Optional.ofNullable(idOrg).filter(str -> !str.isEmpty()).map(Integer::parseInt)
				.orElse(null);
		Integer lienDepotInteger = Optional.ofNullable(lienDepot).filter(str -> !str.isEmpty()).map(Integer::parseInt)
				.orElse(null);
	
		SearchTUserCriteria criteria = new SearchTUserCriteria(idUser, userName, actif, idLanguage,
				email, rights, droit1, gestMemb, gestBen, gestFead, gestDon, lienBanqueInteger, idOrgInteger,
				lienDepotInteger, idCompany, hasLogins, hasAnomalies, classicBanks );
		Page<TUser> selectedTUsers = this.TUserService.findAll(criteria, pageRequest);
		long totalElements = selectedTUsers.getTotalElements();

		return selectedTUsers.stream().map(TUser -> convertToDto(TUser, totalElements)).collect(Collectors.toList());
	}

	@PutMapping("user/{idUser}")
	public TUserDto updateTUser(@PathVariable("idUser") String idUser, @RequestBody TUserDto updatedTUser) {
		TUser TUserEntity = convertToEntity(updatedTUser);
		boolean booCreateMode = false;
		try {
			TUser tuser = this.TUserService.save(TUserEntity, booCreateMode);
			return this.convertToDto(tuser, (long) 1);
		} catch (Exception ex) {
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
		String password = newTUser.getPassword();
		String hashedPassword = HASHER.hashToString(HASH_COST, password.toCharArray());
		entity.setPassword(hashedPassword);
		boolean booCreateMode = true;
		try {
			TUser tuser = this.TUserService.save(entity, booCreateMode);
			return this.convertToDto(tuser, (long) 1);
		} catch (Exception ex) {
			String errorMsg = ex.getMessage();
			System.out.println(errorMsg);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMsg);
		}
	}

	protected TUserDto convertToDto(TUser entity, long totalRecords) {

		boolean booActif = entity.getActif() == 1;
		boolean booMembreActif = entity.getMembreActif() == 1;
		boolean booDroit1 = entity.getDroit1() == 1;
		boolean booGestBen = entity.getGestBen() == 1;
		boolean booGestInv = entity.getGestInv() == 1;
		boolean booGestFead = entity.getGestFead() == 1;
		boolean booGestAsso = entity.getGestAsso() == 1;
		boolean booGestCpas = entity.getGestCpas() == 1;
		boolean booGestMemb = entity.getGestMemb() == 1;
		boolean booGestDon = entity.getGestDon() == 1;
		long nbOfLogins = entity.getNbLogins();
		String caseCorrectedRights = entity.getRights(); // if not found in list later, keep original
		String[] officialRights = { "admin", "Admin_Banq", "Admin_CPAS" ,"Admin_FEAD", "Admin_EXT", "Admin_FBBA" , 
				"Asso",    "Bank",    "Bank_FBBA" };

		for (String str : officialRights) {
			if (str.equalsIgnoreCase(entity.getRights())) {
				caseCorrectedRights = str;
				break;
			}

		}

		// field LienDepot is correct , field depot is erratic
		TUserDto dto = new TUserDto(entity.getIdUser(), entity.getUserName(), entity.getIdCompany(), entity.getIdOrg(),
				entity.getIdLanguage(), entity.getLienBat(), booActif, caseCorrectedRights, entity.getPassword(),
				entity.getLienDepot(), booDroit1, entity.getEmail(), booGestBen, booGestInv, booGestFead, booGestAsso,
				booGestCpas, booGestMemb, booGestDon, entity.getLienBanque(), entity.getLienCpas(), entity.getSociete(),
				entity.getMembreNom(), entity.getMembrePrenom(), entity.getMembreEmail(), entity.getMembreLangue(),booMembreActif,
				entity.getMembreBankShortname(), nbOfLogins, totalRecords);
		return dto;
	}

	protected TUser convertToEntity(TUserDto dto) {
       // feed erratic field depot from field liendepot derived from idDis
		TUser tUser = new TUser(dto.getIdUser(), dto.getUserName(), dto.getIdCompany(), dto.getIdOrg(),
				dto.getIdLanguage(), dto.getLienBat(), (short) (dto.getActif() ? 1 : 0), dto.getRights(),
				dto.getPassword(), dto.getLienDepot(), (short) (dto.getDroit1() ? 1 : 0), dto.getEmail(),
				(short) (dto.getGestBen() ? 1 : 0), (short) (dto.getGestInv() ? 1 : 0),
				(short) (dto.getGestFead() ? 1 : 0), (short) (dto.getGestAsso() ? 1 : 0),
				(short) (dto.getGestCpas() ? 1 : 0), (short) (dto.getGestMemb() ? 1 : 0),
				(short) (dto.getGestDon() ? 1 : 0), (short) dto.getLienBanque(), dto.getLienCpas());

		return tUser;
	}

}
