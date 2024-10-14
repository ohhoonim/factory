package dev.ohhoonim.factory.personalRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.ohhoonim.factory.personalTable.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
   public Optional<Users> findByEmail(String email); 
}
