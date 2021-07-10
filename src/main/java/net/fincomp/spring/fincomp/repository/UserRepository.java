package net.fincomp.spring.fincomp.repository;

import net.fincomp.spring.fincomp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
