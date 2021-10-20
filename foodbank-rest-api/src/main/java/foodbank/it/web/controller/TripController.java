package foodbank.it.web.controller;

import foodbank.it.persistence.model.Membre;
import foodbank.it.persistence.model.Trip;
import foodbank.it.service.ITripService;
import foodbank.it.service.IMembreService;
import foodbank.it.service.SearchTripCriteria;
import foodbank.it.web.dto.TripDto;
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

public class TripController {
    
    private ITripService TripService;
    private IMembreService MembreService;
    
    public TripController(ITripService TripService, IMembreService MembreService) {
        this.TripService = TripService;
        this.MembreService = MembreService;
       
    }

    @GetMapping("trip/{tripId}")
    public TripDto findOne(@PathVariable Integer tripId) {
        Trip entity = TripService.findByTripId(tripId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return convertToDto(entity,1);
    }
   

    @GetMapping("trips/")
    public Collection<TripDto> find( @RequestParam String offset, @RequestParam String rows, 
    		@RequestParam(required = false) String batId,@RequestParam(required = false) String membreNom )  {
    	int intOffset = Integer.parseInt(offset);
    	int intRows = Integer.parseInt(rows);
    	int pageNumber=intOffset/intRows; // Java throws away remainder of division
        int pageSize = intRows;
        Pageable pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by("tripDate").descending());
       
      	Integer batIdInteger = Optional.ofNullable(batId).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
        SearchTripCriteria criteria = new SearchTripCriteria(batIdInteger,membreNom);
		Page<Trip> selectedTrips = this.TripService.findAll(criteria, pageRequest);
		long totalElements = selectedTrips.getTotalElements();

		return selectedTrips.stream()
				.map(Trip -> convertToDto(Trip, totalElements))
				.collect(Collectors.toList());
		
		
       
    }

    @PutMapping("trip/{tripId}")
    public TripDto updateTrip(@PathVariable("tripId") Integer tripId, @RequestBody TripDto updatedTrip) {
        Trip TripEntity = convertToEntity(updatedTrip);
        return this.convertToDto(this.TripService.save(TripEntity),1);
    }

    @DeleteMapping("trip/{tripId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrip(@PathVariable("tripId") Integer tripId) {
        TripService.delete(tripId);
    }

    @PostMapping("trip/")
    @ResponseStatus(HttpStatus.CREATED)
    public TripDto create(@RequestBody TripDto newTrip) {
        Trip entity = convertToEntity(newTrip);
        Trip Trip = this.TripService.save(entity);        
        return this.convertToDto(Trip,1);
    }
    protected TripDto convertToDto(Trip entity,long totalRecords) {
    	
    	String membreNom = "";
    	Membre membreOfTrip = entity.getMembreObject();
    	if (membreOfTrip != null) {
    		membreNom = membreOfTrip.getNom() + ' ' + membreOfTrip.getPrenom() ;
    	}
    	boolean booActif= entity.getActif() == 1;
    	
        TripDto dto = new TripDto(entity.getTripId(), entity.getTripDate(), entity.getTripDepart(), entity.getTripArrivee(), entity.getTripKm(),
        		entity.getBatnr(),entity.getDateEnreg(),booActif, membreNom,totalRecords);       
        return dto;
    }

    protected Trip convertToEntity(TripDto dto) {
    	Membre membreOfTrip = null;
    	Optional<Membre> membre = this.MembreService.findByBatId(dto.getBatId());
    	if (membre.isPresent()) membreOfTrip = membre.get();
        Trip trip = new Trip( dto.getTripId(), dto.getTripDate(), dto.getTripDepart(), dto.getTripArrivee(), dto.getTripKm(),
        		(short) (dto.getActif() ? 1 : 0) ,membreOfTrip);       
        if (!StringUtils.isEmpty(dto.getTripId())) {
            trip.setTripId(dto.getTripId());
        }
        return trip;
    }
}
