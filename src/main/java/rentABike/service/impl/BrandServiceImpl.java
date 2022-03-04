package rentABike.service.impl;

import org.springframework.stereotype.Service;
import rentABike.model.Brand;
import rentABike.repository.jpa.BrandRepository;
import rentABike.service.BrandService;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    public BrandServiceImpl (BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Optional<Brand> save (String name, String address) {
        return Optional.of(this.brandRepository.save(new Brand(name, address)));
    }

    @Override
    public void deleteById (Long id) {
        this.brandRepository.deleteById(id);
    }

    @Override
    public List<Brand> listBrands () {
        return brandRepository.findAll();
    }
}
