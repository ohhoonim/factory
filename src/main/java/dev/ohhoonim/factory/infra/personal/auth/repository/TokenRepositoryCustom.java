package dev.ohhoonim.factory.infra.personal.auth.repository;

import java.util.List;
import dev.ohhoonim.factory.infra.personal.auth.repository.entity.Tokens;

public interface TokenRepositoryCustom {
     List<Tokens> findAllValidTokenByUserId(String userName);
}
