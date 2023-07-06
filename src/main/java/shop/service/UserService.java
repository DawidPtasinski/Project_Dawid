package shop.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import shop.dto.UserDto;
import shop.entity.Role;
import shop.entity.User;
import org.springframework.stereotype.Service;
import shop.repository.UserRepository;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(BCryptPasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Username doesn't exists"));
    }

    public User registerUser(UserDto userDto) {

        return registerUserWithRoles(userDto, Set.of(Role.USER));
    }

    public User registerUserWithRoles(UserDto userDto, Set<Role> roles) {
        User user = new User();
        user.setUsername(userDto.username());
        user.setPassword(passwordEncoder.encode(userDto.password().trim()));
        user.setRoles(roles);
        return userRepository.save(user);
    }

    public Optional<User> authenticateUser(String username, String password) {
        Optional<User> byUsername = userRepository.findByUsername(username);
        if (byUsername.isPresent() && passwordEncoder.matches(password, byUsername.get().getPassword())) {
            return byUsername;
        } else {
            return Optional.empty();
        }
    }

    public boolean isUsernameAvailable(String username) {
        return userRepository.findByUsername(username).isEmpty();
    }
}
