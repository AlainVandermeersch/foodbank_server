package foodbank.it.web.controller;

import static java.util.Objects.isNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

import foodbank.it.persistence.model.Banque;
import foodbank.it.persistence.model.Client;
import foodbank.it.service.IBanqueService;
import foodbank.it.service.IClientService;
import foodbank.it.web.dto.ClientDto;

@RestController

public class ClientController {
	
	private IClientService ClientService;
	private IBanqueService BanqueService;
    
    public ClientController(IClientService ClientService, IBanqueService BanqueService) {
        this.ClientService = ClientService;
        this.BanqueService = BanqueService;
    }
    @CrossOrigin
    @GetMapping("beneficiaire/{idClient}")
    public ClientDto findOne(@PathVariable Integer idClient) {
        Client entity = ClientService.findByIdClient(idClient)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return convertToDto(entity,1);
    }
    
    @CrossOrigin
    @GetMapping("beneficiaires/")
	public Collection<ClientDto> find(@RequestParam String offset, @RequestParam String rows, 
    		@RequestParam String sortField, @RequestParam String sortOrder, 
    		@RequestParam(required = false) String searchField,@RequestParam(required = false) String searchValue,
    		@RequestParam(required = false) String bankShortName ,@RequestParam(required = false) String lienDis) {
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
        
        Page<Client> selectedClients = null;
        List<ClientDto> ClientDtos = new ArrayList<>();
        if (searchField == null) searchField = "";
        switch(searchField) {
       
        	case "nom":
        		if (bankShortName == null) {
    				if (lienDis == null) {
    					selectedClients = this.ClientService.findByNomContaining(searchValue,pageRequest);
    					long totalRecords = selectedClients.getTotalElements();
    					selectedClients.forEach(p -> ClientDtos.add(convertToDto(p, totalRecords)));
    				} else {
    					selectedClients = this.ClientService.findByLienDisAndNomContaining(Integer.parseInt(lienDis),searchValue, pageRequest);
    					long totalRecords = selectedClients.getTotalElements();
    					selectedClients.forEach(p -> ClientDtos.add(convertToDto(p, totalRecords)));
    				}

    			} else {
    				selectedClients = this.ClientService.findByBanqueObjectBankShortNameAndNomContaining(bankShortName, searchValue, pageRequest);
    				long totalRecords = selectedClients.getTotalElements();
    				selectedClients.forEach(p -> ClientDtos.add(convertToDto(p, totalRecords)));
    			}
        		break;
        	case "prenom":
        		if (bankShortName == null) {
    				if (lienDis == null) {
    					selectedClients = this.ClientService.findByPrenomContaining(searchValue,pageRequest);
    					long totalRecords = selectedClients.getTotalElements();
    					selectedClients.forEach(p -> ClientDtos.add(convertToDto(p, totalRecords)));
    				} else {
    					selectedClients = this.ClientService.findByLienDisAndPrenomContaining(Integer.parseInt(lienDis),searchValue, pageRequest);
    					long totalRecords = selectedClients.getTotalElements();
    					selectedClients.forEach(p -> ClientDtos.add(convertToDto(p, totalRecords)));
    				}

    			} else {
    				selectedClients = this.ClientService.findByBanqueObjectBankShortNameAndPrenomContaining(bankShortName, searchValue, pageRequest);
    				long totalRecords = selectedClients.getTotalElements();
    				selectedClients.forEach(p -> ClientDtos.add(convertToDto(p, totalRecords)));
    			}
        		break;
        	case "adresse":
        		if (bankShortName == null) {
    				if (lienDis == null) {
    					selectedClients = this.ClientService.findByAdresseContaining(searchValue,pageRequest);
    					long totalRecords = selectedClients.getTotalElements();
    					selectedClients.forEach(p -> ClientDtos.add(convertToDto(p, totalRecords)));
    				} else {
    					selectedClients = this.ClientService.findByLienDisAndAdresseContaining(Integer.parseInt(lienDis),searchValue, pageRequest);
    					long totalRecords = selectedClients.getTotalElements();
    					selectedClients.forEach(p -> ClientDtos.add(convertToDto(p, totalRecords)));
    				}

    			} else {
    				selectedClients = this.ClientService.findByBanqueObjectBankShortNameAndAdresseContaining(bankShortName, searchValue, pageRequest);
    				long totalRecords = selectedClients.getTotalElements();
    				selectedClients.forEach(p -> ClientDtos.add(convertToDto(p, totalRecords)));
    			}
        		break;
        	case "cp":
        		if (bankShortName == null) {
    				if (lienDis == null) {
    					selectedClients = this.ClientService.findByCpStartsWith(searchValue,pageRequest);
    					long totalRecords = selectedClients.getTotalElements();
    					selectedClients.forEach(p -> ClientDtos.add(convertToDto(p, totalRecords)));
    				} else {
    					selectedClients = this.ClientService.findByLienDisAndCpStartsWith(Integer.parseInt(lienDis),searchValue, pageRequest);
    					long totalRecords = selectedClients.getTotalElements();
    					selectedClients.forEach(p -> ClientDtos.add(convertToDto(p, totalRecords)));
    				}

    			} else {
    				selectedClients = this.ClientService.findByBanqueObjectBankShortNameAndCpStartsWith(bankShortName, searchValue, pageRequest);
    				long totalRecords = selectedClients.getTotalElements();
    				selectedClients.forEach(p -> ClientDtos.add(convertToDto(p, totalRecords)));
    			}
        		break;
        	case "localite":
        		if (bankShortName == null) {
    				if (lienDis == null) {
    					selectedClients = this.ClientService.findByLocaliteContaining(searchValue,pageRequest);
    					long totalRecords = selectedClients.getTotalElements();
    					selectedClients.forEach(p -> ClientDtos.add(convertToDto(p, totalRecords)));
    				} else {
    					selectedClients = this.ClientService.findByLienDisAndLocaliteContaining(Integer.parseInt(lienDis),searchValue, pageRequest);
    					long totalRecords = selectedClients.getTotalElements();
    					selectedClients.forEach(p -> ClientDtos.add(convertToDto(p, totalRecords)));
    				}

    			} else {
    				selectedClients = this.ClientService.findByBanqueObjectBankShortNameAndLocaliteContaining(bankShortName, searchValue, pageRequest);
    				long totalRecords = selectedClients.getTotalElements();
    				selectedClients.forEach(p -> ClientDtos.add(convertToDto(p, totalRecords)));
    			}
        		break;
        		
        	default:
        	
        		if (bankShortName == null) {
				if (lienDis == null) {
					selectedClients = this.ClientService.findAll(pageRequest);
					long totalRecords = selectedClients.getTotalElements();
					selectedClients.forEach(p -> ClientDtos.add(convertToDto(p, totalRecords)));
				} else {
					selectedClients = this.ClientService.findByLienDis(Integer.parseInt(lienDis), pageRequest);
					long totalRecords = selectedClients.getTotalElements();
					selectedClients.forEach(p -> ClientDtos.add(convertToDto(p, totalRecords)));
				}

			} else {
				selectedClients = this.ClientService.findByBanqueObjectBankShortName(bankShortName, pageRequest);
				long totalRecords = selectedClients.getTotalElements();
				selectedClients.forEach(p -> ClientDtos.add(convertToDto(p, totalRecords)));
			}
        }
       return ClientDtos;
    }
    @CrossOrigin
    @PutMapping("beneficiaire/{idClient}")
    public ClientDto updateClient(@PathVariable("idClient") Integer idClient, @RequestBody ClientDto updatedClient) {
        Client ClientEntity = convertToEntity(updatedClient);
        return this.convertToDto(this.ClientService.save(ClientEntity),1);
    }
    @CrossOrigin
    @DeleteMapping("beneficiaire/{idClient}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable("idClient") Integer idClient) {
        ClientService.delete(idClient);
    }
    @CrossOrigin
    @PostMapping("beneficiaire/")
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDto create(@RequestBody ClientDto newClient) {
        Client entity = convertToEntity(newClient);
        // Alain todo later entity.setDateCreated(LocalDate.now());
        Client Client = this.ClientService.save(entity);        
        return this.convertToDto(Client,1);
    }
    protected ClientDto convertToDto(Client entity,long totalRecords) {
    	String bankShortName = "";
    	String bankName = "";
    	Banque banqueObject = entity.getBanqueObject();
    	if ( ! isNull(banqueObject)) {
    		bankShortName = banqueObject.getBankShortName();
    		bankName = banqueObject.getBankName();
    	} 
    	
        ClientDto dto = new ClientDto(entity.getIdClient(),entity.getIdInt(), entity.getLienDis(), entity.getNom(), entity.getPrenom(),
				entity.getNomconj(), entity.getPrenomconj(), entity.getCivilite(), entity.getDaten(),  entity.getDatenConj(), entity.getCiviliteconj(),
				entity.getAdresse(), entity.getCp(), entity.getLocalite(), entity.getPays(), entity.getEmail(), entity.getTel(),entity.getGsm(), 
				entity.getConnu(), entity.getGenre(), entity.getActif(), entity.getBirb(), entity.getNatnr(), entity.getDateUpd(), entity.getRegio(),
				entity.getLCpas(), entity.getDatUpdBirb(), entity.getCritBirb(), entity.getCoeff(), entity.getNomsav(), entity.getPrenomsav(),
				entity.getGenreconj(), entity.getLbanque(), bankShortName,bankName,totalRecords );    
        return dto;
    }

    protected Client convertToEntity(ClientDto dto) {
    	Banque banqueObject = this.BanqueService.findByBankId(dto.getLbanque()).get();
    	    
    	Client myClient = new Client( dto.getIdClient(),dto.getIdInt(), dto.getLienDis(),dto.getLbanque(), dto.getNom(), dto.getPrenom(),
				dto.getNomconj(), dto.getPrenomconj(), dto.getCivilite(), dto.getDaten(),  dto.getDatenConj(), dto.getCiviliteconj(),
				dto.getAdresse(), dto.getCp(), dto.getLocalite(), dto.getPays(), dto.getEmail(), dto.getTel(),dto.getGsm(), 
				dto.getConnu(), dto.getGenre(), dto.getActif(), dto.getBirb(), dto.getNatnr(), dto.getDateUpd(), dto.getRegio(),
				dto.getLCpas(), dto.getDatUpdBirb(), dto.getCritBirb(), dto.getCoeff(), dto.getNomsav(), dto.getPrenomsav(),
				dto.getGenreconj(),  banqueObject);       
        if (!StringUtils.isEmpty(dto.getIdClient())) {
            myClient.setIdClient(dto.getIdClient());
        }
        return myClient;
    }
}

	
	

