package api.service;

import api.dto.UserAuthDTO;
import api.dto.UserInsertDTO;
import api.dto.UserListingDTO;
import api.dto.UserValidateReturnDTO;
import api.exception.InsertionError;
import api.model.User;
import api.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserListingDTO> getAll() {
        return this.userRepository.findAll().stream().map(UserListingDTO::new).toList();
    }

    public User getById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    public User getByEmail(String email) {
        return this.userRepository.findByEmail(email).orElse(null);
    }

    public boolean auth(String username, String password) {
        Optional<User> register = userRepository.findByUsername(username);
        if (!register.isPresent()) throw new RuntimeException("User " + username + " not found!");
        return register.get().getPassword().equals(password);
    }

    public UserValidateReturnDTO validate(String username, String email) {
        Optional<User> matchingUsername = userRepository.findByUsername(username);
        Optional<User> matchingEmail = userRepository.findByEmail(email);
        return new UserValidateReturnDTO(matchingUsername.isPresent(), matchingEmail.isPresent());
    }

    @Transactional
    public User insert(User user) {
        Optional<User> register = userRepository.findByUsername(user.getUsername());
        if (register.isPresent()) throw new RuntimeException("User already registered!");
        return userRepository.save(user);
    }

    @Transactional
    public User update(UserInsertDTO user, Long id) {
        return userRepository.findById(id)
                .map(register -> {
                    register.setUsername(user.username());
                    register.setEmail(user.email());
                    register.setPassword(user.password());
                    return userRepository.save(register);
                }).orElseThrow(() -> new RuntimeException("User " + id + " not found!"));
    }

    @Transactional
    public void delete(Long id) {
        Optional<User> register = this.userRepository.findById(id);
        if (!(register.isPresent())) throw new InsertionError("User " + id + " does not exist!");
        userRepository.deleteById(id);
    }

}
