package rentABike.service;

import rentABike.model.AddToFavourites;

import java.util.List;

public interface FavouritesService {

    List<AddToFavourites> listAll(Long cartId);
    AddToFavourites addToFavourites(Long productId);

}
