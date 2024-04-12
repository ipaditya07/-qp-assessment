package Service;

import Entity.User;
import Enums.Role;
import Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserService {

    @Autowired
    UserRepository userRepository;

    public User registerUser(User user) {

        // Set default role to USER
        user.setRole(Role.USER);
        return userRepository.save(user);
    }

}
