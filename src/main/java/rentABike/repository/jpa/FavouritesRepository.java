package rentABike.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rentABike.model.AddToFavourites;
import rentABike.model.User;

import java.util.Optional;

@Repository
public interface FavouritesRepository extends JpaRepository<AddToFavourites, Long> {

    Optional<AddToFavourites> findByUser(User user);
}
