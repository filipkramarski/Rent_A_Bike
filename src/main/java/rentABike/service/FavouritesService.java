package rentABike.service;

import org.springframework.stereotype.Service;
import rentABike.model.AddToFavourites;
import rentABike.model.Rent;

import java.util.List;

public interface FavouritesService {

    List<Rent> listAll(Long cartId);
    AddToFavourites getActiveFavourites (String username);
    AddToFavourites addToFavourites(String username, Long productId);

}
