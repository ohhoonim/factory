package dev.ohhoonim.factory.infra.personal.auth.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import dev.ohhoonim.factory.infra.personal.auth.repository.entity.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
   public Optional<Users> findByEmail(String email); 
}
