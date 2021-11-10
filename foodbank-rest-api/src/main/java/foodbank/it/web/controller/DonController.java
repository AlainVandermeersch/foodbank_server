package foodbank.it.web.controller;
import java.util.Collection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
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

import foodbank.it.persistence.model.Don;
import foodbank.it.service.IDonService;
import foodbank.it.service.SearchDonCriteria;
import foodbank.it.web.dto.DonDto;

@RestController

public class DonController {
	private IDonService DonService;
    
    public DonController(IDonService DonService) {
        this.DonService = DonService;
       
    }

    @GetMapping("don/{idDon}")
    public DonDto findOne(@PathVariable Integer idDon) {
        Don entity = DonService.findByIdDon(idDon)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return convertToDto(entity, 1);
    }
    

    @GetMapping("dons/")
    public Collection<DonDto> find(@RequestParam String offset, @RequestParam String rows, 
    		@RequestParam String sortField, @RequestParam String sortOrder, 
    		@RequestParam(required = false) String donateurNom, 
     		@RequestParam(required = false) String lienBanque) {
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
        
        SearchDonCriteria criteria = new SearchDonCriteria(donateurNom,lienBanqueInteger);
        Page<Don> selectedDons = this.DonService.findAll(criteria, pageRequest);
		long totalElements = selectedDons.getTotalElements();

		return selectedDons.stream()
				.map(Don -> convertToDto(Don, totalElements))
				.collect(Collectors.toList());

        
        
       
    }

   
	@PutMapping("don/{idDon}")
    public DonDto updateDon(@PathVariable("idDon") Integer idDon, @RequestBody DonDto updatedDonDto) throws Exception {
        Don donEntity = convertToEntity(updatedDonDto);
        Don savedDon = this.DonService.save(donEntity);
        savedDon.setDonateurNom(donEntity.getDonateurNom());
        savedDon.setDonateurPrenom(donEntity.getDonateurPrenom());
        return this.convertToDto(savedDon,1);
    }

    @DeleteMapping("don/{idDon}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDon(@PathVariable("idDon") Integer idDon) throws Exception {
        DonService.delete(idDon);
    }

    @PostMapping("don/")
    @ResponseStatus(HttpStatus.CREATED)
    public DonDto create(@RequestBody DonDto newDonDto) throws Exception {
        Don donEntity = convertToEntity(newDonDto);
        Don createdDon = this.DonService.save(donEntity);  
        createdDon.setDonateurNom(donEntity.getDonateurNom());
        createdDon.setDonateurPrenom(donEntity.getDonateurPrenom());
        return this.convertToDto(createdDon,1);
    }
    private Don convertToEntity(DonDto dto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateDon = LocalDate.parse(dto.getDate(), formatter);
    	
    	Don myDon = new Don( dto.getIdDon(), dto.getAmount(), dto.getLienBanque(), dto.getDonateurId(),  (short) (dto.isAppended() ? 1 : 0),
    			(short) (dto.isChecked() ? 1 : 0), dateDon,dto.getDonateurNom(),dto.getDonateurPrenom());
    	 return myDon;
    }

	private DonDto convertToDto(Don entity,long totalRecords) {
		boolean booChecked= entity.getChecked() == 1;
		boolean booAppended= entity.getAppended() == 1;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");		
		String donDate = entity.getDate().format(formatter);
		DonDto dto = new DonDto(entity.getIdDon(), entity.getAmount(), entity.getLienBanque(), entity.getDonateurId(), entity.getDateEntered(), booAppended,
				booChecked, donDate,entity.getDonateurNom(), entity.getDonateurPrenom(),totalRecords);
		return dto;
	}

}
