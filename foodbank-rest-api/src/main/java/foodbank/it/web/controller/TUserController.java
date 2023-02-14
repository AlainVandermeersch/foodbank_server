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
	public Collection<TUserDto> findAll(@RequestParam(required = false) String idUser, @RequestParam(required = false) String userName,
										@RequestParam(required = false) String idLanguage, @RequestParam(required = false) String idCompany,
										@RequestParam(required = false) String email, @RequestParam(required = false) String rights,
										@RequestParam(required = false) String lienDepot, @RequestParam(required = false) Boolean actif,
										@RequestParam(required = false) Boolean droit1, @RequestParam(required = false) Boolean gestMemb,
										@RequestParam(required = false) Boolean gestBen, @RequestParam(required = false) Boolean gestFead,
										@RequestParam(required = false) Boolean gestDon, @RequestParam(required = false) Boolean hasLogins,
										@RequestParam(required = false) String hasAnomalies,@RequestParam(required = false) Boolean classicBanks,
										@RequestParam(required = false) String lienBanque, @RequestParam(required = false) String idOrg,
										@RequestParam(required = false) String lienBat
			) {

		Integer lienBanqueInteger = Optional.ofNullable(lienBanque).filter(str -> !str.isEmpty()).map(Integer::parseInt)
				.orElse(null);
		Integer idOrgInteger = Optional.ofNullable(idOrg).filter(str -> !str.isEmpty()).map(Integer::parseInt)
				.orElse(null);
		Integer lienDepotInteger = Optional.ofNullable(lienDepot).filter(str -> !str.isEmpty()).map(Integer::parseInt)
				.orElse(null);
		Integer lienBatInteger = Optional.ofNullable(lienBat).filter(str -> !str.isEmpty()).map(Integer::parseInt)
				.orElse(null);

		SearchTUserCriteria criteria = new SearchTUserCriteria(idUser, userName, actif, idLanguage,
				email, rights, droit1, gestMemb, gestBen, gestFead, gestDon, lienBanqueInteger, idOrgInteger,
				lienDepotInteger, idCompany, hasLogins, hasAnomalies, classicBanks, lienBatInteger );
		List<TUser> selectedTUsers = this.TUserService.findAll(criteria);

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
			@RequestParam(required = false) String lienBanque, @RequestParam(required = false) String idOrg,
			@RequestParam(required = false) String lienBat) {
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
		Integer lienBatInteger = Optional.ofNullable(lienBat).filter(str -> !str.isEmpty()).map(Integer::parseInt)
				.orElse(null);
	
		SearchTUserCriteria criteria = new SearchTUserCriteria(idUser, userName, actif, idLanguage,
				email, rights, droit1, gestMemb, gestBen, gestFead, gestDon, lienBanqueInteger, idOrgInteger,
				lienDepotInteger, idCompany, hasLogins, hasAnomalies, classicBanks,lienBatInteger );
		Page<TUser> selectedTUsers = this.TUserService.findPaged(criteria, pageRequest);
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
		TUserDto dto = new TUserDto();
				dto.setIdUser(entity.getIdUser());
				dto.setUserName(entity.getUserName());
				dto.setIdCompany(entity.getIdCompany());
				dto.setIdOrg(entity.getIdOrg());
				dto.setIdLanguage(entity.getIdLanguage());
				dto.setLienBat(entity.getLienBat());
				dto.setActif(booActif);
				dto.setRights(caseCorrectedRights);
				dto.setPassword(entity.getPassword());
				dto.setDepot(entity.getDepot());
				dto.setDroit1(booDroit1);
				dto.setEmail(entity.getEmail());
				dto.setGestBen(booGestBen);
				dto.setGestInv(booGestInv);
				dto.setGestFead(booGestFead);
				dto.setGestAsso(booGestAsso);
				dto.setGestCpas(booGestCpas);
				dto.setGestMemb(booGestMemb);
				dto.setGestDon(booGestDon);
				dto.setLienBanque(entity.getLienBanque());
				dto.setLienCpas(entity.getLienCpas());
				dto.setSociete(entity.getSociete());
				dto.setMembreNom(entity.getMembreNom());
				dto.setMembrePrenom(entity.getMembrePrenom());
				dto.setMembreEmail(entity.getMembreEmail());
				dto.setMembreLangue(entity.getMembreLangue());
				dto.setMembreActif(booMembreActif);
				dto.setMembreBankShortname(entity.getMembreBankShortname());
				dto.setNbLogins(nbOfLogins);
				dto.setTotalRecords(totalRecords);
		return dto;
	}

	protected TUser convertToEntity(TUserDto dto) {

		TUser tUser = new TUser();
		tUser.setIdUser(dto.getIdUser());
		tUser.setUserName(dto.getUserName());
		tUser.setIdCompany(dto.getIdCompany());
		tUser.setIdOrg(dto.getIdOrg());
		tUser.setIdLanguage(dto.getIdLanguage());
		tUser.setLienBat(dto.getLienBat());
		tUser.setActif((short) (dto.getActif() ? 1 : 0));
		tUser.setRights(dto.getRights());
		tUser.setPassword(dto.getPassword());
		tUser.setDepot(dto.getDepot());
		tUser.setDroit1((short) (dto.getDroit1() ? 1 : 0));
		tUser.setEmail(dto.getEmail());
		tUser.setGestBen((short) (dto.getGestBen() ? 1 : 0));
		tUser.setGestInv((short) (dto.getGestInv() ? 1 : 0));
		tUser.setGestFead((short) (dto.getGestFead() ? 1 : 0));
		tUser.setGestAsso((short) (dto.getGestAsso() ? 1 : 0));
		tUser.setGestCpas((short) (dto.getGestCpas() ? 1 : 0));
		tUser.setGestMemb((short) (dto.getGestMemb() ? 1 : 0));
		tUser.setGestDon((short) (dto.getGestDon() ? 1 : 0));
		tUser.setLienBanque((short) dto.getLienBanque());
		tUser.setLienCpas(dto.getLienCpas());

		return tUser;
	}

}
