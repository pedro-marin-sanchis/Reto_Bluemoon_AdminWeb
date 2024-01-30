package com.uguinformatica.bluemoon_adminweb.service.role;

import com.uguinformatica.bluemoon_adminweb.model.Role;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> getRoleByName(String name);
}
