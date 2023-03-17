package foodbank.it.service.impl;

import foodbank.it.persistence.model.CodePostal;
import foodbank.it.persistence.model.Cpas;
import foodbank.it.persistence.repository.ICodePostalRepository;
import foodbank.it.service.ICodePostalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CodePostalServiceImpl implements ICodePostalService {
    private final ICodePostalRepository CodePostalRepository;

    public CodePostalServiceImpl(ICodePostalRepository CodePostalRepository) {
        this.CodePostalRepository = CodePostalRepository;
    }
    @Override
    public Optional<CodePostal> findByZipCode(int zipCode) {
        return this.CodePostalRepository.findByZipCode(zipCode);
    }

    @Override
    public Iterable<CodePostal> findByLCpas(int lcpas) {
        return this.CodePostalRepository.findBylCpas(lcpas);
    }

    @Override
    public void deleteByZipCode(int zipCode) {
        this.CodePostalRepository.deleteByZipCode(zipCode);
    }

    @Override
    public CodePostal save(CodePostal newCodePostal) {
       return this.CodePostalRepository.save(newCodePostal);
    }

    @Override
    public void delete(Integer zipcode) {
        this.CodePostalRepository.deleteByZipCode(zipcode);
    }

    @Override
    public Page<CodePostal> findAll(Pageable pageRequest) {
        return this.CodePostalRepository.findAll(pageRequest);
    }
}
