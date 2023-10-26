package dev.ohhoonim.factory.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Configuration
public class QueryDslConfig {

    @PersistenceContext(unitName = "businessEntityManager")
    private EntityManager businessJpaTransactionManager;
    @PersistenceContext(unitName = "personalEntityManager")
    private EntityManager personalJpaTransactionManager;

    @Bean(name="businessJpaQueryFactory")
    public JPAQueryFactory businessJpaQueryFactory() {
        return new JPAQueryFactory(businessJpaTransactionManager);
    }

    @Bean(name="personalJpaQueryFactory")
    public JPAQueryFactory personalJpaQueryFactory() {
        return new JPAQueryFactory(personalJpaTransactionManager);
    }
}
