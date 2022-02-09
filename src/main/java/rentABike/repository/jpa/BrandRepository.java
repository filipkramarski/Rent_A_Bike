package rentABike.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rentABike.model.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
}
