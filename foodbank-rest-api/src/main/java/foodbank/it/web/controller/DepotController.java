package foodbank.it.web.controller;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import foodbank.it.persistence.model.Depot;
import foodbank.it.service.IDepotService;
import foodbank.it.service.SearchDepotCriteria;
import foodbank.it.web.dto.DepotDto;
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
    		@RequestParam(required = false) String nom,@RequestParam(required = false) String lienBanque) {
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
        
        SearchDepotCriteria criteria = new SearchDepotCriteria(nom,lienBanqueInteger);
        Page<Depot> selectedDepots = this.DepotService.findAll(criteria, pageRequest);
		long totalElements = selectedDepots.getTotalElements();

		return selectedDepots.stream()
				.map(Depot -> convertToDto(Depot, totalElements))
				.collect(Collectors.toList());

        
        
       
    }

    @PutMapping("depot/{idDepot}")
    public DepotDto updateDepot(@PathVariable("idDepot") Integer idDepot, @RequestBody DepotDto updatedDepot) {
        Depot DepotEntity = convertToEntity(updatedDepot);
        return this.convertToDto(this.DepotService.save(DepotEntity),1);
    }

    @DeleteMapping("depot/{idDepot}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDepot(@PathVariable("idDepot") String idDepot) {
        DepotService.delete(idDepot);
    }

    @PostMapping("depot/")
    @ResponseStatus(HttpStatus.CREATED)
    public DepotDto create(@RequestBody DepotDto newDepot) {
        Depot entity = convertToEntity(newDepot);
        // Alain todo later entity.setDateCreated(LocalDate.now());
        Depot Depot = this.DepotService.save(entity);        
        return this.convertToDto(Depot,1);
    }
    protected DepotDto convertToDto(Depot entity,long totalRecords) {
    	
    	boolean booDepPrinc= entity.getDepPrinc() == 1;
    	boolean booActif= entity.getActif() == 1;
    	boolean booDepFead= entity.getDepFead() == 1;
    	
        DepotDto dto = new DepotDto(entity.getIdDepot(), entity.getNom(), entity.getAdresse(), entity.getAdresse2(), entity.getCp(), entity.getVille(),
    			entity.getTelephone(), entity.getContact(), entity.getEmail(), entity.getMemo(), booDepPrinc, booActif, booDepFead,
    			entity.getLienBanque() ,totalRecords );    
        return dto;
    }

    protected Depot convertToEntity(DepotDto dto) {
    	    	    
    	Depot myDepot = new Depot( dto.getIdDepot(), dto.getNom(), dto.getAdresse(), dto.getAdresse2(), dto.getCp(), dto.getVille(),
    			dto.getTelephone(), dto.getContact(), dto.getEmail(), dto.getMemo(), 
    			(short) (dto.getDepPrinc() ? 1 : 0), (short) (dto.getActif() ? 1 : 0) , (short) (dto.getDepFead() ? 1 : 0),dto.getLienBanque());  
    	
        if (!StringUtils.isEmpty(dto.getIdDepot())) {
            myDepot.setIdDepot(dto.getIdDepot());
        }
        return myDepot;
    }


}

