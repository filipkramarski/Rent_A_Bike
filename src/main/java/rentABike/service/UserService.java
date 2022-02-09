package rentABike.service;

import rentABike.model.User;

public interface UserService{

    User register(String username, String password, String repeatPassword, String name, String surname);

}
