package rentABike.service.impl;

import org.springframework.stereotype.Service;
import rentABike.model.AddToFavourites;
import rentABike.model.Rent;
import rentABike.model.User;
import rentABike.model.exceptions.AlreadyInFavouritesException;
import rentABike.model.exceptions.BrandNotFoundException;
import rentABike.model.exceptions.UserNotFoundException;
import rentABike.repository.jpa.FavouritesRepository;
import rentABike.repository.jpa.UserRepository;
import rentABike.service.FavouritesService;
import rentABike.service.RentService;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class FavouritesServiceImpl implements FavouritesService {

    private final FavouritesRepository favouritesRepository;
    private final UserRepository userRepository;
    private final RentService rentService;

    public FavouritesServiceImpl (FavouritesRepository favouritesRepository, UserRepository userRepository, RentService rentService) {
        this.favouritesRepository = favouritesRepository;
        this.userRepository = userRepository;
        this.rentService = rentService;
    }

    @Override
    public List<Rent> listAll (Long id) {
        return this.favouritesRepository.findById(id).get().getRent();
    }

    @Override
    public AddToFavourites getActiveFavourites (String username) {
        User user = this.userRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException(username));
        return this.favouritesRepository.findByUser(user).orElseGet(() -> {

            AddToFavourites addToFavourites = new AddToFavourites(user);
            return this.favouritesRepository.save(addToFavourites);
        });
    }

    @Override
    public AddToFavourites addToFavourites (String username, Long rentId) {

        Rent rent = this.rentService.findById(rentId).orElseThrow(() -> new BrandNotFoundException(rentId));
        AddToFavourites addToFavourites = this.getActiveFavourites(username);
        if(addToFavourites.getRent().stream().filter(i -> i.getId().equals(rentId)).collect(Collectors.toList()).size() > 0)
            throw new AlreadyInFavouritesException(rentId,username);
        addToFavourites.getRent().add(rent);
        return this.favouritesRepository.save(addToFavourites);
    }
}
