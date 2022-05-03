package rentABike.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AlreadyInFavouritesException extends RuntimeException{

    public AlreadyInFavouritesException(Long id,String username) {
        super(String.format("Already in favourites"));
    }

}
