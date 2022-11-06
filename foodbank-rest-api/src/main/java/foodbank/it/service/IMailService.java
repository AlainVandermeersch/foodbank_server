package foodbank.it.service;

import foodbank.it.web.dto.MailAddressDto;

import java.util.List;


public interface IMailService {
	 List<MailAddressDto> find(SearchMailListCriteria searchCriteria);	

}
