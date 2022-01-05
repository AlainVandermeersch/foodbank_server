package foodbank.it.service;

import java.util.List;

import foodbank.it.web.dto.MailAddressDto;


public interface IMailService {
	 List<MailAddressDto> find(SearchMailListCriteria searchCriteria);	

}
