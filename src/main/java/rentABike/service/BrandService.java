package rentABike.service;

import rentABike.model.Brand;
import rentABike.model.Category;

import java.util.List;
import java.util.Optional;

public interface BrandService {

    Optional<Brand> save(String name, String address);
    void deleteById(Long id);
    List<Brand> listBrands();
}
