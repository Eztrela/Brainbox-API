package api.controller;

import api.dto.*;
import api.model.User;
import api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserListingDTO> getAll() {
        return this.userService.getAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return this.userService.getById(id);
    }

    @GetMapping("/email/{email}")
    public User getUserByEmail(@PathVariable("email") String email) {
        return this.userService.getByEmail(email);
    }

    @PostMapping
    public User insert(@RequestBody @Valid UserInsertDTO user) {
        User newUser = new User(user.username(), user.email(), user.password());
        return this.userService.insert(newUser);
    }

    @PostMapping("/auth")
    public boolean auth(@RequestBody @Valid UserAuthDTO user) {
        return this.userService.auth(user.username(), user.password());
    }

    @PostMapping("/validate")
    public UserValidateReturnDTO validate(@RequestBody @Valid UserValidateDTO user) {
        return this.userService.validate(user.username(), user.email());
    }

    @PutMapping("/{id}")
    public User update(@RequestBody @Valid UserInsertDTO user, @PathVariable("id") Long id) {
        return this.userService.update(user, id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        this.userService.delete(id);
        return "User " + id + " deleted with success.";
    }

}
