package foodbank.it.service;

import foodbank.it.persistence.model.Banque;
import foodbank.it.persistence.model.BanqueCount;
import foodbank.it.web.dto.BanqueFeadReportDto;
import foodbank.it.web.dto.BanqueOrgCountDto;
import foodbank.it.web.dto.BanqueClientReportDto;

import java.util.List;
import java.util.Optional;


public interface IBanqueService {
    Optional<Banque> findByBankId(int bankId);
    Optional<Banque> findByBankShortName(String bankShortName);

    Banque save(Banque Banque);

    Iterable<Banque> findAll(Boolean classicBanks);
    Iterable<Banque> findByActif(short actif);
    void delete(int bankId) throws Exception;
	List<BanqueCount> reportOrgCount(String filter, String bankShortName);
	List<BanqueClientReportDto> reportOrgClients(String bankShortName);
	List<BanqueOrgCountDto> reportMembreCount(String bankShortName);
	List<BanqueOrgCountDto> reportTUserCount(String bankShortName);

    List<BanqueFeadReportDto> reportOrgFead(String bankShortName);
}
