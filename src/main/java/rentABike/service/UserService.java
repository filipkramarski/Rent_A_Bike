package rentABike.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import rentABike.model.Role;
import rentABike.model.User;

public interface UserService extends UserDetailsService {

    User register(String username, String password, String repeatPassword, String name, String surname, Role role);

}
