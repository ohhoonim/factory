package dev.ohhoonim.factory.infra.personal.auth.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import dev.ohhoonim.factory.infra.personal.auth.repository.entity.Tokens;

public interface TokenRepository extends JpaRepository<Tokens, Long>, TokenRepositoryCustom {
    Optional<Tokens> findByToken(String token);
   
}
