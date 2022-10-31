package foodbank.it.web.controller;
import java.util.Collection;
import java.util.stream.Collectors;
import foodbank.it.persistence.model.Depot;
import foodbank.it.service.IDepotService;
import foodbank.it.service.SearchDepotCriteria;
import foodbank.it.web.dto.DepotDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;



@RestController

public class DepotController {
	
	private IDepotService DepotService;
		    
    public DepotController(IDepotService DepotService) {
        this.DepotService = DepotService;      
    }

    @GetMapping("depot/{idDepot}")
    public DepotDto findOne(@PathVariable String idDepot) {
        Depot entity = DepotService.findByIdDepot(idDepot)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return convertToDto(entity,1);
    }
    

    @GetMapping("depots/")
    public Collection<DepotDto> find(@RequestParam String offset, @RequestParam String rows, 
    		@RequestParam String sortField, @RequestParam String sortOrder, 
    		@RequestParam(required = false) Boolean actif, @RequestParam(required = false) String nom,
    		@RequestParam(required = false) String idCompany) {
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
               
        SearchDepotCriteria criteria = new SearchDepotCriteria(nom,actif,idCompany);
        Page<Depot> selectedDepots = this.DepotService.findAll(criteria, pageRequest);
		long totalElements = selectedDepots.getTotalElements();

		return selectedDepots.stream()
				.map(Depot -> convertToDto(Depot, totalElements))
				.collect(Collectors.toList());

        
        
       
    }

    @PutMapping("depot/{idDepot}")
    public DepotDto updateDepot(@PathVariable("idDepot") Integer idDepot,
                                @RequestBody DepotDto updatedDepot) {
        Depot DepotEntity = convertToEntity(updatedDepot);
        Boolean sync = updatedDepot.isSync();
        try {
           Depot returnedDepot=  this.DepotService.save(DepotEntity, sync);
            return this.convertToDto(returnedDepot,1);
        }
        catch (Exception ex) {
            String errorMsg = ex.getMessage();
            System.out.println(errorMsg);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMsg);
        }

    }

    @DeleteMapping("depot/{idDepot}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDepot(@PathVariable("idDepot") String idDepot) {

        try {
            DepotService.delete(idDepot);
        }
        catch ( DataIntegrityViolationException ex) {
            String errorMsg =  ex.getRootCause().getMessage();
            System.out.println(errorMsg);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMsg);
        }
        catch (Exception ex) {
            String errorMsg = ex.getMessage();
            System.out.println(errorMsg);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMsg);
        }


    }

    @PostMapping("depot/")
    @ResponseStatus(HttpStatus.CREATED)
    public DepotDto create(@RequestBody DepotDto newDepot) {
        Depot entity = convertToEntity(newDepot);
        try {
            Depot returnedDepot=  this.DepotService.save(entity,true); //syncing with org when creating a new depot
            return this.convertToDto(returnedDepot,1);
        }
        catch (Exception ex) {
            String errorMsg = ex.getMessage();
            System.out.println(errorMsg);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMsg);
        }
    }
    protected DepotDto convertToDto(Depot entity,long totalRecords) {
    	
    	boolean booDepPrinc= entity.getDepPrinc() == 1;
    	boolean booActif= entity.getActif() == 1;
    	boolean booDepFead= entity.getDepFead() == 1;
        String anomalies = DepotService.getAnomalies(entity);
    	
        DepotDto dto = new DepotDto(entity.getIdDepot(), entity.getNom(), entity.getAdresse(), entity.getAdresse2(), entity.getCp(), entity.getVille(),
    			entity.getIdCompany(),entity.getTelephone(), entity.getContact(), entity.getEmail(), entity.getMemo(), booDepPrinc, booActif, booDepFead,
                entity.getIpMainAddress(), entity.getLienBanque() ,anomalies,totalRecords );
        return dto;
    }

    protected Depot convertToEntity(DepotDto dto) {
    	    	    
    	Depot myDepot = new Depot( dto.getIdDepot(), dto.getNom(), dto.getAdresse(), dto.getAdresse2(), dto.getCp(), dto.getVille(),
    			dto.getIdCompany(),dto.getTelephone(), dto.getContact(), dto.getEmail(), dto.getMemo(), 
    			(short) (dto.getDepPrinc() ? 1 : 0), (short) (dto.getActif() ? 1 : 0) , (short) (dto.getDepFead() ? 1 : 0),
                dto.getIpMainAddress(), dto.getLienBanque());
    	
        if (!StringUtils.isEmpty(dto.getIdDepot())) {
            myDepot.setIdDepot(dto.getIdDepot());
        }
        return myDepot;
    }


}

