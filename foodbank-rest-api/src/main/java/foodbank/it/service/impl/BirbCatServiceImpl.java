package foodbank.it.service.impl;

import foodbank.it.persistence.model.BirbCat;
import foodbank.it.persistence.repository.IBirbCatRepository;
import foodbank.it.service.IBirbCatService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BirbCatServiceImpl implements IBirbCatService {
    private IBirbCatRepository BirbCatRepository;

    public BirbCatServiceImpl(IBirbCatRepository BirbCatRepository) {
        this.BirbCatRepository = BirbCatRepository;

    }

    @Override
    public Optional<BirbCat> findByBirbId(int birbId) {
        return this.BirbCatRepository.findByBirbId(birbId);
    }

    @Override
    public BirbCat save(BirbCat birbCat) {
        return this.BirbCatRepository.save(birbCat);
    }

    @Override
    public List<BirbCat> findAll() {
        return (List<BirbCat>) this.BirbCatRepository.findAll();
    }

    @Override
    public void delete(int birbId) throws Exception {
        this.BirbCatRepository.deleteByBirbId(birbId);

    }
}
