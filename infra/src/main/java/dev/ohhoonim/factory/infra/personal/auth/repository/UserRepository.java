package dev.ohhoonim.factory.infra.personal.auth.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import dev.ohhoonim.factory.infra.personal.auth.repository.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
   public Optional<User> findByEmail(String email); 
}
