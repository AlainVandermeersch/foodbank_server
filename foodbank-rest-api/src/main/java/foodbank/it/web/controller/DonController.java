package foodbank.it.web.controller;

import foodbank.it.persistence.model.Don;
import foodbank.it.service.IDonService;
import foodbank.it.service.SearchDonCriteria;
import foodbank.it.web.dto.DonDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

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
    		@RequestParam(required = false) String donateurNom, @RequestParam(required = false) String donYear, 
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
        Integer donYearInteger = Optional.ofNullable(donYear).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
        
        SearchDonCriteria criteria = new SearchDonCriteria(donateurNom,donYearInteger,lienBanqueInteger);
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
        return this.convertToDto(createdDon,1);
    }
    private Don convertToEntity(DonDto dto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateDon = LocalDate.parse(dto.getDate(), formatter);
    	
    	Don myDon = new Don();
            myDon.setIdDon(dto.getIdDon());
            myDon.setAmount(dto.getAmount());
            myDon.setLienBanque(dto.getLienBanque());
            myDon.setDonateurId(dto.getDonateurId());
            myDon.setAppended((short) (dto.isAppended() ? 1 : 0));
    		myDon.setChecked((short) (dto.isChecked() ? 1 : 0));
            myDon.setDate(dateDon);
            myDon.setDateEntered(LocalDateTime.now());

        return myDon;
    }

	private DonDto convertToDto(Don entity,long totalRecords) {
		boolean booChecked= entity.getChecked() == 1;
		boolean booAppended= entity.getAppended() == 1;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");		
		String donDate = entity.getDate().format(formatter);
		DonDto dto = new DonDto();
        dto.setIdDon(entity.getIdDon());
        dto.setAmount(entity.getAmount());
        dto.setLienBanque(entity.getLienBanque());
        dto.setDonateurId(entity.getDonateurId());
        if (entity.getDateEntered() == null) {
            dto.setDateEntered("");
        }
        else {
              dto.setDateEntered(entity.getDateEntered().format(formatter));
        }
        dto.setAppended(booAppended);
        dto.setChecked(booChecked);
        dto.setDate(donDate);
        dto.setDonateurNom(entity.getDonateurNom());
        dto.setDonateurPrenom(entity.getDonateurPrenom());
        dto.setTotalAmount(entity.getTotalAmount());
        dto.setTotalRecords(totalRecords);
        return dto;
	}

}
