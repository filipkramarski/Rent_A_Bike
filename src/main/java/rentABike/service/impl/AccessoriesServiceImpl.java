package rentABike.service.impl;

import org.springframework.stereotype.Service;
import rentABike.model.Accessories;
import rentABike.model.Brand;
import rentABike.model.Category;
import rentABike.model.Rent;
import rentABike.model.exceptions.BrandNotFoundException;
import rentABike.model.exceptions.CategoryNotFoundException;
import rentABike.repository.jpa.AccessoriesRepository;
import rentABike.repository.jpa.BrandRepository;
import rentABike.repository.jpa.CategoryRepository;
import rentABike.service.AccessoriesService;

import java.util.List;
import java.util.Optional;

@Service
public class AccessoriesServiceImpl implements AccessoriesService {

    private final AccessoriesRepository accessoriesRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;

    public AccessoriesServiceImpl (AccessoriesRepository accessoriesRepository, CategoryRepository categoryRepository, BrandRepository brandRepository) {
        this.accessoriesRepository = accessoriesRepository;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
    }

    @Override
    public List<Accessories> findAll () {
        return this.accessoriesRepository.findAll();
    }

    @Override
    public Optional<Accessories> findById (Long id) {
        return this.accessoriesRepository.findById(id);
    }

    @Override
    public Optional<Accessories> findByName (String name) {
        return this.accessoriesRepository.findByName(name);
    }

    @Override
    public Optional<Accessories> save (String name, Double price, Integer quantity, Long categoryId) {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId));

        this.accessoriesRepository.deleteByName(name);

        return Optional.of(this.accessoriesRepository.save(new Accessories(name,price,quantity,category)));
    }

    @Override
    public void deleteById (Long id) {

    }
}
