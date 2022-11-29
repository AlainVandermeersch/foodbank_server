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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
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
	public Collection<ClientDto> find(@RequestParam String offset, @RequestParam String rows,
			@RequestParam String sortField, @RequestParam String sortOrder,@RequestParam String actif,
			@RequestParam(required = false) String nom,@RequestParam(required = false) String prenom,
     		@RequestParam(required = false) String adresse,@RequestParam(required = false) String cp,
     		@RequestParam(required = false) String localite,@RequestParam(required = false) Boolean suspect,
			@RequestParam(required = false) Boolean duplicate,
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
		Integer actifInteger = 1;
		if (actif.equals("0")) {
			actifInteger = 0;
		}

		SearchClientCriteria criteria = new SearchClientCriteria(nom, prenom, adresse, cp,localite,
				lienBanqueInteger, lienDisInteger,actifInteger,suspect,duplicate);
		Page<Client> selectedClients = this.ClientService.findAll(criteria, pageRequest);
		long totalElements = selectedClients.getTotalElements();

		return selectedClients.stream()
				.map(client -> convertToDto(client, totalElements))
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
		
		ClientDto dto = new ClientDto(entity.getIdClient(), entity.getIdInt(), liendis, entity.getNom(), entity.getPrenom(),
				entity.getNomconj(), entity.getPrenomconj(), entity.getCivilite(), entity.getDaten(), entity.getDatenConj(), entity.getCiviliteconj(),
				entity.getAdresse(), entity.getCp(), entity.getLocalite(), entity.getPays(), entity.getEmail(), entity.getTel(), entity.getGsm(),
				entity.getConnu(), entity.getGenre(),booActif, entity.getBirb(), entity.getNatnr(), entity.getDateUpd(), entity.getRegio(),
				entity.getLCpas(), entity.getDatUpdBirb(), entity.getCritBirb(), entity.getCoeff(), entity.getNomsav(), entity.getPrenomsav(),
				entity.getGenreconj(), entity.getLbanque(), entity.getNbDep(),societe,totalRecords);
		return dto;
	}

	protected Client convertToEntity(ClientDto dto) {
		
		Organisation orgOfClient = null;

    	Optional<Organisation> org = this.OrganisationService.findByIdDis(dto.getLienDis());
    		if (org.isPresent() == true) orgOfClient = org.get() ;
		
		Client myClient = new Client(dto.getIdClient(), dto.getIdInt(), orgOfClient, dto.getLbanque(), dto.getNom(), dto.getPrenom(),
				dto.getNomconj(), dto.getPrenomconj(), dto.getCivilite(), dto.getDaten(), dto.getDatenConj(), dto.getCiviliteconj(),
				dto.getAdresse(), dto.getCp(), dto.getLocalite(), dto.getPays(), dto.getEmail(), dto.getTel(), dto.getGsm(),
				dto.getConnu(), dto.getGenre(), (short) (dto.getActif() ? 1 : 0) , dto.getBirb(), dto.getNatnr(),  dto.getRegio(),
				dto.getLCpas(), dto.getDatUpdBirb(), dto.getCritBirb(), dto.getCoeff(), dto.getNomsav(), dto.getPrenomsav(),
				dto.getGenreconj());
		if (!StringUtils.isEmpty(dto.getIdClient())) {
			myClient.setIdClient(dto.getIdClient());
		}
		return myClient;
	}
}

	
	

