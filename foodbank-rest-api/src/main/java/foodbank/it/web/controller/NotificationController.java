package foodbank.it.web.controller;

import foodbank.it.persistence.model.Notification;
import foodbank.it.service.INotificationService;
import foodbank.it.service.SearchNotificationCriteria;
import foodbank.it.web.dto.NotificationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class NotificationController {
	 private INotificationService NotificationService;
	    
	    public NotificationController(INotificationService NotificationService) {
	        this.NotificationService = NotificationService;
	       
	    }

	    @GetMapping("notification/{notificationId}")
	    public NotificationDto findOne(@PathVariable Integer notificationId) {
	        Notification entity = NotificationService.findByNotificationId(notificationId)
	            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	        return convertToDto(entity, 1);
	    }
	    

	    @GetMapping("notifications/")
	    public Collection<NotificationDto> find(@RequestParam String offset, @RequestParam String rows, 
	    		@RequestParam(required = false) String language,@RequestParam(required = false) String admin ,
	    		@RequestParam(required = false) String bankId ,@RequestParam(required = false) String orgId) {
	    	int intOffset = Integer.parseInt(offset);
	    	int intRows = Integer.parseInt(rows);
	    	int pageNumber=intOffset/intRows; // Java throws away remainder of division
	        int pageSize = intRows;
	        Pageable pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by("creationdate").descending());
	        Integer bankIdInteger = Optional.ofNullable(bankId).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
	        Integer orgIdInteger = Optional.ofNullable(orgId).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
	        SearchNotificationCriteria criteria = new SearchNotificationCriteria(language, admin, bankIdInteger,orgIdInteger);
	        Page<Notification> selectedNotifications = this.NotificationService.findAll(criteria, pageRequest);
			long totalElements = selectedNotifications.getTotalElements();

			return selectedNotifications.stream()
					.map(Notification -> convertToDto(Notification, totalElements))
					.collect(Collectors.toList());

	        
	        
	       
	    }

	   
		@PutMapping("notification/{notificationId}")
	    public NotificationDto updateNotification(@PathVariable("notificationId") Integer notificationId, @RequestBody NotificationDto updatedNotification) throws Exception {
	        Notification NotificationEntity = convertToEntity(updatedNotification);
	        return this.convertToDto(this.NotificationService.save(NotificationEntity,false),1);
	    }

	    @DeleteMapping("notification/{notificationId}")
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    public void deleteNotification(@PathVariable("notificationId") Integer notificationId) throws Exception {
	        NotificationService.delete(notificationId);
	    }

	    @PostMapping("notification/")
	    @ResponseStatus(HttpStatus.CREATED)
	    public NotificationDto create(@RequestBody NotificationDto newNotification) throws Exception {
	        Notification entity = convertToEntity(newNotification);
	        Notification createdNotification = this.NotificationService.save(entity,true);        
	        return this.convertToDto(createdNotification,1);
	    }
	    private Notification convertToEntity(NotificationDto dto) {
	    	Notification myNotification = new Notification( dto.getNotificationId(), dto.getBankId(),dto.getOrgId(), dto.getAuthor(), dto.getSubject(), dto.getAudience(),
	    			dto.getImportance(), dto.getLanguage(), dto.getContent());
	    	 return myNotification;
	    }

		private NotificationDto convertToDto(Notification entity,long totalRecords) {
			NotificationDto dto = new NotificationDto(entity.getNotificationId(),entity.getBankId(),entity.getOrgId(),entity.getCreationdate(), entity.getAuthor(), entity.getSubject(), entity.getAudience(),
					entity.getImportance(), entity.getLanguage(), entity.getContent(),totalRecords);
			return dto;
		}
}
