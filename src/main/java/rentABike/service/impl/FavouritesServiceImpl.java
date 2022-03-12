package rentABike.service.impl;

import rentABike.model.AddToFavourites;
import rentABike.model.Rent;
import rentABike.model.exceptions.CategoryNotFoundException;
import rentABike.repository.jpa.FavouritesRepository;
import rentABike.service.FavouritesService;
import rentABike.service.RentService;

import java.util.List;
import java.util.Optional;

public class FavouritesServiceImpl implements FavouritesService {

    private final FavouritesRepository favouritesRepository;
    private final FavouritesService favouritesService;
    private final RentService rentService;

    public FavouritesServiceImpl (FavouritesRepository favouritesRepository, FavouritesService favouritesService, RentService rentService) {
        this.favouritesRepository = favouritesRepository;
        this.favouritesService = favouritesService;
        this.rentService = rentService;
    }

    @Override
    public List<AddToFavourites> listAll (Long Id) {
        return this.favouritesService.listAll(Id);
    }

    @Override
    public AddToFavourites addToFavourites (Long rentId) {
        return null;
    }
}
