package net.kondratenko.repository;

import net.kondratenko.model.Role;
import net.kondratenko.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByActivationToken(String token);

    User findByEmail(String email);
}