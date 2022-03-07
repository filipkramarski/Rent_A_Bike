package rentABike.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import rentABike.model.Accessories;
import rentABike.model.Rent;

import java.util.Optional;

public interface AccessoriesRepository extends JpaRepository<Accessories, Long> {

    Optional<Accessories> findByName (String name);
    void deleteByName(String name);
}
