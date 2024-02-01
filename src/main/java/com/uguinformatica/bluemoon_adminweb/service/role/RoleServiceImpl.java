package com.uguinformatica.bluemoon_adminweb.service.role;

import com.uguinformatica.bluemoon_adminweb.model.Role;
import com.uguinformatica.bluemoon_adminweb.service.APIConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class RoleServiceImpl implements IRoleService {

    private final RestTemplate restTemplate;

    @Autowired
    public RoleServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<Role> getRoleByName(String name) {
        return Optional.ofNullable(restTemplate.getForObject(APIConstants.API_URL + "/role/" + name, Role.class));
    }

}
