package org.example.dpostnov.repos;

import org.example.dpostnov.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String userName);
}
