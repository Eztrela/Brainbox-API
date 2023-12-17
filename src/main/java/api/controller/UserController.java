package api.controller;

import api.dto.UserInsertDTO;
import api.dto.UserListingDTO;
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

    @PostMapping
    public User insert(@RequestBody @Valid UserInsertDTO user) {
        User newUser = new User(user.username(), user.email(), user.password());
        return this.userService.insert(newUser);
    }

    @PutMapping("/{id}")
    public User update(@RequestBody User user, @PathVariable("id") Long id) {
        return this.userService.update(user, id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        this.userService.delete(id);
        return "User " + id + " deleted with success.";
    }

}
