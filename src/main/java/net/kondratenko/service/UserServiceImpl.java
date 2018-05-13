package net.kondratenko.service;

import net.kondratenko.model.Role;
import net.kondratenko.model.User;
import net.kondratenko.repository.RoleRepository;
import net.kondratenko.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User getById(Long id) {
        return this.userRepository.findOne(id);
    }

    @Override
    public User getByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(User user) {

        user.setEnabled(false);

        String hashedPassword = bCryptPasswordEncoder.encode( user.getPassword());
        user.setPassword(hashedPassword);

        String roleName = user.getSelectedRoleName();
        Role role = this.roleRepository.findByName(roleName);
        user.setRole(role);

        this.userRepository.save(user);
    }

    @Override
    public boolean activateUser(String token) {

        User user = this.userRepository.findByActivationToken(token);
        if (user != null) {
            user.setEnabled(true);
            user.setActivationToken(null);

            this.userRepository.save(user);
            return true;
        }

        return false;
    }

    @Override
    public boolean emailExists(String email) {
        return this.userRepository.findByEmail(email) != null;
    }


}