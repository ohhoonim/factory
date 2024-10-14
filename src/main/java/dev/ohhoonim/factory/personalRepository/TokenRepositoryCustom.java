package dev.ohhoonim.factory.personalRepository;

import java.util.List;

import dev.ohhoonim.factory.personalTable.Tokens;

public interface TokenRepositoryCustom {
     List<Tokens> findAllValidTokenByUserId(String userName);
}
