package rentABike.service.impl;

import org.springframework.stereotype.Service;
import rentABike.model.User;
import rentABike.model.exceptions.InvalidArgumentsException;
import rentABike.model.exceptions.PasswordsDoNotMatchException;
import rentABike.model.exceptions.UsernameAlreadyExistsException;
import rentABike.repository.jpa.UserRepository;
import rentABike.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register (String username, String password, String repeatPassword, String name, String surname) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        if (!password.equals(repeatPassword)) {
            throw new PasswordsDoNotMatchException();
        }
        if (this.userRepository.findByUsername(username).isPresent()) {
            throw new UsernameAlreadyExistsException(username);
        }
        User user = new User(username,password,name,surname);
        return userRepository.save(user);
    }
}
