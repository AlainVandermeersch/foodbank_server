package foodbank.it.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Service;

import foodbank.it.persistence.model.Notification;
import foodbank.it.persistence.repository.INotificationRepository;
import foodbank.it.service.INotificationService;
import foodbank.it.service.SearchNotificationCriteria;
@Service
public class NotificationServiceImpl implements INotificationService {
	private INotificationRepository NotificationRepository;
	private final EntityManager entityManager;

	
	public NotificationServiceImpl(INotificationRepository NotificationRepository, EntityManager entityManager) {
        this.NotificationRepository = NotificationRepository;
        this.entityManager = entityManager;
        
    }

	@Override
	public Optional<Notification> findByNotificationId(int notificationId) {
		
		return NotificationRepository.findByNotificationId(notificationId);
	}

	@Override
	public Notification save(Notification Notification,boolean booCreateMode) {
		return NotificationRepository.save(Notification);
	}

	@Override
	public Page<Notification> findAll(SearchNotificationCriteria searchCriteria, Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Notification> notificationQuery = criteriaBuilder.createQuery(Notification.class);
		Root<Notification> notification = notificationQuery.from(Notification.class);

		List<Predicate> predicates = new ArrayList<>();

		String language = searchCriteria.getLanguage();
		String admin = searchCriteria.getAdmin();
		Integer bankId = searchCriteria.getBankId();
		Integer orgId = searchCriteria.getOrgId();
		List<String> audienceList = null;

		if (language != null ) {			

			Predicate languagePredicate = criteriaBuilder.equal(notification.get("language"), language);
			predicates.add(languagePredicate);
		}
		if (orgId != null ) {			

			Predicate orgIdPredicate = criteriaBuilder.equal(notification.get("orgId"), orgId);
			predicates.add(orgIdPredicate);
			if (admin != null ) {	
				audienceList = Arrays.asList("general","org_admins","mybank_orgadmin","mybank_org","myorg","myorgadmin"); 			
			}
			else {
				audienceList = Arrays.asList("general", "mybank_org","myorg");
			}
		}
		else if (bankId != null ) {			

			Predicate bankIdPredicate = criteriaBuilder.equal(notification.get("bankId"), bankId);
			predicates.add(bankIdPredicate);
		
			if (admin != null ) {	
				audienceList = Arrays.asList("general", "bank_admins", "bank_users", "mybank_only","mybank_orgadmin","mybank_org","mybank_all"); 			
			}
			else {
				audienceList = Arrays.asList("general", "bank_users", "mybank_only","mybank_all");
			}
		}
		// no audience filter for admin
		
		if (audienceList != null)
		{
			Expression<String> parentExpression = notification.get("audience");
			Predicate audiencePredicate = parentExpression.in(audienceList);			
			predicates.add(audiencePredicate);
		}
		notificationQuery.where(predicates.stream().toArray(Predicate[]::new));
		notificationQuery.orderBy(QueryUtils.toOrders(pageable.getSort(), notification, criteriaBuilder));

		TypedQuery<Notification> query = entityManager.createQuery(notificationQuery);
		query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
		query.setMaxResults(pageable.getPageSize());

		CriteriaQuery<Long> totalCriteriaQuery = criteriaBuilder.createQuery(Long.class);
		totalCriteriaQuery.where(predicates.stream().toArray(Predicate[]::new));
		totalCriteriaQuery.select(criteriaBuilder.count(totalCriteriaQuery.from(Notification.class)));
		TypedQuery<Long> countQuery = entityManager.createQuery(totalCriteriaQuery);
		long countResult = countQuery.getSingleResult();

		List<Notification> resultList = query.getResultList();
		return new PageImpl<>(resultList, pageable, countResult);
	}

	@Override
	@Transactional
	public void delete(int notificationId) throws Exception {
		NotificationRepository.deleteByNotificationId(notificationId);
		
	}

	

}
