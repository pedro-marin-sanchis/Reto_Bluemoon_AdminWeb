package com.uguinformatica.bluemoon_adminweb.service.user;

import com.uguinformatica.bluemoon_adminweb.model.User;

import java.util.Optional;

public interface IUserService {
    Optional<User> getUserByUsername(String username);
    Optional<User> getCurrentUser();
}
