package rentABike.service;

import rentABike.model.User;

public interface AuthService {

    User login(String username, String password);

}
