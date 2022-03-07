package rentABike.service;

import org.springframework.stereotype.Service;
import rentABike.model.Accessories;
import rentABike.model.Rent;

import java.util.List;
import java.util.Optional;

public interface AccessoriesService {

    List<Accessories> findAll();
    Optional<Accessories> findById(Long id);
    Optional<Accessories> findByName(String name);
    Optional<Accessories> save(String name, Double price, Integer quantity, Long categoryId);
    void deleteById(Long id);
}
