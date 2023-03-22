package foodbank.it.web.controller;

import foodbank.it.persistence.model.Client;
import foodbank.it.persistence.model.Organisation;
import foodbank.it.service.IClientService;
import foodbank.it.service.IOrganisationService;
import foodbank.it.service.SearchClientCriteria;
import foodbank.it.web.dto.ClientDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;




@RestController

public class ClientController {

	private IClientService ClientService;
	private IOrganisationService OrganisationService;

	public ClientController(IClientService ClientService, IOrganisationService OrganisationService) {
		this.ClientService = ClientService;	
		this.OrganisationService = OrganisationService;
	}


	@GetMapping("beneficiaire/{idClient}")
	public ClientDto findOne(@PathVariable Integer idClient) {
		Client entity = ClientService.findByIdClient(idClient)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return convertToDto(entity, 1);
	}


	@GetMapping("beneficiaires/")
	public Collection<ClientDto> findPaged(@RequestParam String offset, @RequestParam String rows,
			@RequestParam String sortField, @RequestParam String sortOrder,@RequestParam String actif,
			@RequestParam(required = false) String nom,@RequestParam(required = false) String prenom,
     		@RequestParam(required = false) String adresse,@RequestParam(required = false) String cp, @RequestParam(required = false) String lienCpas,
     		@RequestParam(required = false) String localite,@RequestParam(required = false) Boolean coeff,
			@RequestParam(required = false) String daten,@RequestParam(required = false) String duplicate,
			@RequestParam(required = false) String birb,
     		@RequestParam(required = false) String lienBanque, @RequestParam(required = false) String lienDis) {
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
		Integer lienBanqueInteger = Optional.ofNullable(lienBanque).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
		Integer lienDisInteger = Optional.ofNullable(lienDis).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
		Integer birbInteger = Optional.ofNullable(birb).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
		Integer actifInteger = 1;
		if (actif.equals("0")) {
			actifInteger = 0;
		}
		Integer lienCpasInteger = Optional.ofNullable(lienCpas).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
		SearchClientCriteria criteria = new SearchClientCriteria();
		criteria.setNom(nom);
		criteria.setPrenom(prenom);
		criteria.setAdresse(adresse);
		criteria.setCp(cp);
		criteria.setLocalite(localite);
		criteria.setLienBanque(lienBanqueInteger);
		criteria.setLienDis(lienDisInteger);
		criteria.setLienCpas(lienCpasInteger);
		criteria.setBirb(birbInteger);
		criteria.setActif(actifInteger);
		criteria.setCoeff(coeff);
		criteria.setDaten(daten);
		criteria.setDuplicate(duplicate);
		Page<Client> selectedClients = this.ClientService.findPaged(criteria, pageRequest);
		long totalElements = selectedClients.getTotalElements();

		return selectedClients.stream()
				.map(client -> convertToDto(client, totalElements))
				.collect(Collectors.toList());
	}

	@GetMapping("beneficiairesall/")
	public Collection<ClientDto> findAll(@RequestParam String actif,
										 @RequestParam(required = false) String nom,@RequestParam(required = false) String prenom,
										 @RequestParam(required = false) String adresse,@RequestParam(required = false) String cp,
										 @RequestParam(required = false) String localite,@RequestParam(required = false) Boolean coeff,
										 @RequestParam(required = false) String daten,@RequestParam(required = false) String duplicate,
										 @RequestParam(required = false) String lienBanque, @RequestParam(required = false) String lienDis,
										 @RequestParam(required = false) String birb,@RequestParam(required = false) String lienCpas) {

		Integer lienBanqueInteger = Optional.ofNullable(lienBanque).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
		Integer lienDisInteger = Optional.ofNullable(lienDis).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
		Integer lienCpasInteger = Optional.ofNullable(lienCpas).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
		Integer birbInteger = Optional.ofNullable(birb).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
		Integer actifInteger = 1;
		if (actif.equals("0")) {
			actifInteger = 0;
		}
		SearchClientCriteria criteria = new SearchClientCriteria();
		criteria.setNom(nom);
		criteria.setPrenom(prenom);
		criteria.setAdresse(adresse);
		criteria.setCp(cp);
		criteria.setLocalite(localite);
		criteria.setLienBanque(lienBanqueInteger);
		criteria.setLienDis(lienDisInteger);
		criteria.setLienCpas(lienCpasInteger);
		criteria.setActif(actifInteger);
		criteria.setBirb(birbInteger);
		criteria.setCoeff(coeff);
		criteria.setDaten(daten);
		criteria.setDuplicate(duplicate);
		List<Client> selectedClients = this.ClientService.findAll(criteria);
		return selectedClients.stream()
				.map(Client -> convertToDto(Client, selectedClients.size()))
				.collect(Collectors.toList());
	}


	@PutMapping("beneficiaire/{idClient}")
	public ClientDto updateClient(@PathVariable("idClient") Integer idClient, @RequestBody ClientDto updatedClient) {
		Client ClientEntity = convertToEntity(updatedClient);
		return this.convertToDto(this.ClientService.save(ClientEntity), 1);
	}


	@DeleteMapping("beneficiaire/{idClient}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteClient(@PathVariable("idClient") Integer idClient) {
		ClientService.delete(idClient);
	}


	@PostMapping("beneficiaire/")
	@ResponseStatus(HttpStatus.CREATED)
	public ClientDto create(@RequestBody ClientDto newClient) {
		Client entity = convertToEntity(newClient);
		// Alain todo later entity.setDateCreated(LocalDate.now());
		Client Client = this.ClientService.save(entity);
		return this.convertToDto(Client, 1);
	}

	protected ClientDto convertToDto(Client entity, long totalRecords) {
		String societe ="";
    	Integer liendis = 0;
    	Organisation orgOfClient = entity.getOrganisationObject();
    	if (orgOfClient != null) {
    		liendis = orgOfClient.getIdDis();
    		societe = orgOfClient.getSociete();

    	}
		boolean booActif = entity.getActif() == 1;
		
		ClientDto dto = new ClientDto();
		String dtoDateUpd = "";
		if (entity.getDateUpd() != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			dtoDateUpd = entity.getDateUpd().format(formatter);
		}
		dto.setIdClient(entity.getIdClient());
		dto.setIdInt(entity.getIdInt());
		dto.setLienDis(liendis);
		dto.setNom(entity.getNom());
		dto.setPrenom(entity.getPrenom());
		dto.setNomconj(entity.getNomconj());
		dto.setPrenomconj(entity.getPrenomconj());
		dto.setCivilite(entity.getCivilite());
		dto.setDaten(entity.getDaten());
		dto.setDatenConj(entity.getDatenConj());
		dto.setCiviliteconj(entity.getCiviliteconj());
		dto.setAdresse(entity.getAdresse());
		dto.setCp(entity.getCp());
		dto.setLocalite(entity.getLocalite());
		dto.setPays(entity.getPays());
		dto.setEmail(entity.getEmail());
		dto.setTel(entity.getTel());
		dto.setGsm(entity.getGsm());
		dto.setConnu(entity.getConnu());
		dto.setGenre(entity.getGenre());
		dto.setActif(booActif);
		dto.setBirb(entity.getBirb());
		dto.setNatnr(entity.getNatnr());
		dto.setDateUpd(dtoDateUpd);
		dto.setRegio(entity.getRegio());
		dto.setLCpas(entity.getLCpas());
		dto.setDatUpdBirb(entity.getDatUpdBirb());
		dto.setCritBirb(entity.getCritBirb());
		dto.setCoeff(entity.getCoeff());
		dto.setNomsav(entity.getNomsav());
		dto.setPrenomsav(entity.getPrenomsav());
		dto.setGenreconj(entity.getGenreconj());
		dto.setLbanque(entity.getLbanque());
		long nbDep = entity.getNbDep();
		if(entity.getNomconj() != null && !entity.getNomconj().isEmpty()) {
			nbDep = nbDep + 1;
		}
		dto.setNbDep(nbDep);
		dto.setSociete(societe);
		dto.setTotalRecords(totalRecords);
		return dto;
	}

	protected Client convertToEntity(ClientDto dto) {
		
		Organisation orgOfClient = null;

    	Optional<Organisation> org = this.OrganisationService.findByIdDis(dto.getLienDis());
    		if (org.isPresent()) orgOfClient = org.get() ;
		
		Client myClient = new Client();
		 myClient.setIdClient(dto.getIdClient());
		 myClient.setIdInt(dto.getIdInt());
		 myClient.setDateUpd(LocalDateTime.now()); // do not use dateUpd from DTO we need to update the time
		 myClient.setOrganisationObject(orgOfClient);
		 myClient.setLbanque(dto.getLbanque());
		 myClient.setNom(dto.getNom());
		 myClient.setPrenom(dto.getPrenom());
		 myClient.setNomconj(dto.getNomconj());
		 myClient.setPrenomconj(dto.getPrenomconj());
		 myClient.setCivilite(dto.getCivilite());
		 myClient.setDaten(dto.getDaten());
		 myClient.setDatenConj(dto.getDatenConj());
		 myClient.setCiviliteconj(dto.getCiviliteconj());
		 myClient.setAdresse(dto.getAdresse());
		 myClient.setCp(dto.getCp());
		 myClient.setLocalite(dto.getLocalite());
		 myClient.setPays(dto.getPays());
		 myClient.setEmail(dto.getEmail());
		 myClient.setTel(dto.getTel());
		 myClient.setGsm(dto.getGsm());
		 myClient.setConnu(dto.getConnu());
		 myClient.setGenre(dto.getGenre());
		 myClient.setActif((short) (dto.getActif() ? 1 : 0) );
		 myClient.setBirb(dto.getBirb());
		 myClient.setNatnr(dto.getNatnr());
		 myClient.setRegio(dto.getRegio());
		 myClient.setLCpas(dto.getLCpas());
		 myClient.setDatUpdBirb(dto.getDatUpdBirb());
		 myClient.setCritBirb(dto.getCritBirb());
		 myClient.setCoeff(dto.getCoeff());
		 myClient.setNomsav(dto.getNomsav());
		 myClient.setPrenomsav(dto.getPrenomsav());
		 myClient.setGenreconj(dto.getGenreconj());

		return myClient;
	}
}

	
	

