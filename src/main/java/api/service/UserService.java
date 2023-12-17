package api.service;

import api.dto.UserListingDTO;
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

    @Transactional
    public User replace(User user) {
        User register = userRepository.findByUsername(user.getUsername());
        if (register == null) throw new RuntimeException("User already registered!");
        return userRepository.save(user);
    }

    @Transactional
    public User replace(User user, Long id) {
        return userRepository.findById(id)
                .map(register -> {
                    register.setUsername(user.getUsername());
                    register.setEmail(user.getEmail());
                    return userRepository.save(register);
                })
                .orElseGet(() -> {
                    user.setId(id);
                    return userRepository.save(user);
                });
    }

    @Transactional
    public void delete(Long id) {
        Optional<User> register = this.userRepository.findById(id);
        if (!(register.isPresent())) throw new InsertionError("User " + id + " does not exist!");
        userRepository.deleteById(id);
    }

}
