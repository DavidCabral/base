package br.com.modelo.repository;

import br.com.modelo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Long> {
    public User findByUsername(String username);
}
