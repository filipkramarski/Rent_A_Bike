package rentABike.service.impl;

import org.springframework.stereotype.Service;
import rentABike.model.User;
import rentABike.model.exceptions.InvalidArgumentsException;
import rentABike.model.exceptions.InvalidUserCredentialsException;
import rentABike.repository.jpa.UserRepository;
import rentABike.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username,
                password).orElseThrow(InvalidUserCredentialsException::new);
    }
}
