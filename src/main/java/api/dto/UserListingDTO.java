package api.dto;

import api.model.User;

public record UserListingDTO(Long id, String nome, String email) {

    public UserListingDTO(User user) {
        this(user.getId(), user.getUsername(), user.getEmail());
    }
}