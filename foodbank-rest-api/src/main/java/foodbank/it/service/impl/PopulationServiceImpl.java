package foodbank.it.service.impl;

import foodbank.it.persistence.model.Population;
import foodbank.it.service.IPopulationService;
import foodbank.it.web.dto.PopulationReportDto;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class PopulationServiceImpl implements IPopulationService {
	private final EntityManager entityManager;
	public PopulationServiceImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<PopulationReportDto> report() {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<PopulationReportDto> populationQuery = criteriaBuilder.createQuery(PopulationReportDto.class);
		Root<Population> population = populationQuery.from(Population.class);
		List<Predicate> predicates = new ArrayList<>();
		
		Expression<Integer> expressionDate = criteriaBuilder.function("DAYOFMONTH", Integer.class, population.get("dateStat"));
		Predicate firstDayOfMonthPredicate = criteriaBuilder.equal(expressionDate, 1);
		predicates.add(firstDayOfMonthPredicate);
		// LocalDate beginDate = LocalDate.of(2022, 2, 15);
		// Predicate fromDatePredicate = criteriaBuilder.greaterThanOrEqualTo(population.get("dateStat"), beginDate);
		// predicates.add(fromDatePredicate);
		populationQuery.where(predicates.stream().toArray(Predicate[]::new));
		populationQuery.groupBy(population.get("dateStat"), population.get("lienBanque"));
		populationQuery.multiselect(population.get("dateStat"), population.get("lienBanque"),
		criteriaBuilder.sum(population.get("nFam")),
		criteriaBuilder.sum(population.get("nPers")),
		criteriaBuilder.sum(population.get("nNour")),
		criteriaBuilder.sum(population.get("nBebe")),
		criteriaBuilder.sum(population.get("nEnf")),
		criteriaBuilder.sum(population.get("nAdo")),
		criteriaBuilder.sum(population.get("n1824")),
		criteriaBuilder.sum(population.get("nSen"))
		);
		populationQuery.orderBy(criteriaBuilder.asc(population.get("dateStat")),
		  criteriaBuilder.asc(population.get("lienBanque")));
		TypedQuery<PopulationReportDto> query = entityManager.createQuery(populationQuery);
		List<PopulationReportDto> results = query.getResultList();

		return results;
	}

}
