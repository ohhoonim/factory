package dev.ohhoonim.factory.personalRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import dev.ohhoonim.factory.personalTable.QTokens;
import dev.ohhoonim.factory.personalTable.Tokens;

public class TokenRepositoryCustomImpl implements TokenRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public TokenRepositoryCustomImpl( @Qualifier("personalJpaQueryFactory") JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Tokens> findAllValidTokenByUserId(String userName) {
        QTokens t = QTokens.tokens;

        JPAQuery<Tuple> query = queryFactory
                .select(t.id, t.token, t.tokenType, t.expired, t.revoked, t.userName)
                .from(t)
                .where( t.userName.eq(userName));

        return query.fetch()
            .stream().map(tuple -> 
                Tokens.builder()
                    .id(tuple.get(t.id))
                    .token(tuple.get(t.token))
                    .tokenType(tuple.get(t.tokenType))
                    .expired(tuple.get(t.expired))
                    .revoked(tuple.get(t.revoked))
                    .userName(tuple.get(t.userName))
                .build()
            ).toList();
    }
}
