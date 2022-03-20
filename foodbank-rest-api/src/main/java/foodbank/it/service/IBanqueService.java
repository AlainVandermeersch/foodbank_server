package foodbank.it.service;
import java.util.List;
import java.util.Optional;

import foodbank.it.persistence.model.Banque;
import foodbank.it.web.dto.BanqueOrgReportDto;
import foodbank.it.web.dto.BanqueCountDto;


public interface IBanqueService {
    Optional<Banque> findByBankId(int bankId);
    Optional<Banque> findByBankShortName(String bankShortName);

    Banque save(Banque Banque);

    Iterable<Banque> findAll(Boolean classicBanks);
    Iterable<Banque> findByActif(short actif);
    void delete(int bankId) throws Exception;
	List<BanqueCountDto> reportOrgCount(Boolean hasBirbCode);
	List<BanqueOrgReportDto> reportOrgs();
	List<BanqueCountDto> reportMembreCount();
	List<BanqueCountDto> reportTUserCount();
}
