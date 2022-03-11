package foodbank.it.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;

import foodbank.it.persistence.model.Population;
import foodbank.it.service.IPopulationService;
import foodbank.it.web.dto.PopulationReportDto;

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
		List<PopulationReportDto> results = entityManager.createQuery(populationQuery).getResultList();

		return results;
	}

}
