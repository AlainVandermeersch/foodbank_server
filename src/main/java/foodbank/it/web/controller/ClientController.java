package foodbank.it.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Arrays;
import static java.util.Objects.isNull;

import javax.transaction.Transactional;

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
        return convertToDto(entity);
    }
    
    @CrossOrigin
    @GetMapping("beneficiaires/")
    public Collection<ClientDto> find( @RequestParam(required = false) String bankShortName ,@RequestParam(required = false) String lienDis) {
        Iterable<Client> selectedClients = null;
        List<ClientDto> ClientDtos = new ArrayList<>();
        if (bankShortName == null) {
        	if (lienDis == null) {
        		selectedClients = this.ClientService.findAll();
        		selectedClients.forEach(p -> ClientDtos.add(convertToDto(p)));
        	}
        	else {
        		selectedClients = this.ClientService.findByLienDis(Integer.parseInt(lienDis));
        	}
        	
        }
        else {
        	selectedClients = this.ClientService.findByBanqueObjectBankShortName(bankShortName);        	
        	
        }
        selectedClients.forEach(p -> ClientDtos.add(convertToDto(p)));
        
        return ClientDtos;
    }
    @CrossOrigin
    @PutMapping("beneficiaire/{idClient}")
    public ClientDto updateClient(@PathVariable("idClient") Integer idClient, @RequestBody ClientDto updatedClient) {
        Client ClientEntity = convertToEntity(updatedClient);
        return this.convertToDto(this.ClientService.save(ClientEntity));
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
        return this.convertToDto(Client);
    }
    protected ClientDto convertToDto(Client entity) {
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
				entity.getGenreconj(), entity.getLbanque(), bankShortName,bankName );    
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

	
	

