package foodbank.it.service;

import foodbank.it.persistence.model.Banque;
import foodbank.it.persistence.model.BanqueCount;
import foodbank.it.web.dto.BanqueOrgCountDto;
import foodbank.it.web.dto.BanqueOrgReportDto;

import java.util.List;
import java.util.Optional;


public interface IBanqueService {
    Optional<Banque> findByBankId(int bankId);
    Optional<Banque> findByBankShortName(String bankShortName);

    Banque save(Banque Banque);

    Iterable<Banque> findAll(Boolean classicBanks);
    Iterable<Banque> findByActif(short actif);
    void delete(int bankId) throws Exception;
	List<BanqueCount> reportOrgCount(Boolean hasBirbCode);
	List<BanqueOrgReportDto> reportOrgs();
	List<BanqueOrgCountDto> reportMembreCount();
	List<BanqueOrgCountDto> reportTUserCount();
}
