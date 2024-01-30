package com.uguinformatica.bluemoon_adminweb.service.user;

import com.uguinformatica.bluemoon_adminweb.model.AppUser;
import com.uguinformatica.bluemoon_adminweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<AppUser> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<AppUser> getUserByUsername(String username) { return userRepository.findByUsername(username); }

    @Override
    public Optional<AppUser> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return getUserByUsername(username);
    }

    @Override
    public AppUser createUser(AppUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public AppUser updateUser(Long id, AppUser updatedUser) {
        Optional<AppUser> existingUser = userRepository.findById(id);

        if (existingUser.isPresent()) {
            AppUser userToUpdate = existingUser.get();
            userToUpdate.setUsername(updatedUser.getUsername());
            userToUpdate.setName(updatedUser.getName());
            userToUpdate.setLastName(updatedUser.getLastName());
            userToUpdate.setEmail(updatedUser.getEmail());
            userToUpdate.setPhoneNumber(updatedUser.getPhoneNumber());
            userToUpdate.setOrders(updatedUser.getOrders());
            userToUpdate.setRolesAssociated(updatedUser.getRolesAssociated());
            userToUpdate.setPassword(bCryptPasswordEncoder.encode(updatedUser.getPassword()));
            return userRepository.save(userToUpdate);
        } else {
            return null;
        }
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}