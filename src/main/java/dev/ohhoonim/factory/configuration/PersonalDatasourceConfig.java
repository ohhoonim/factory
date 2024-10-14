package dev.ohhoonim.factory.configuration;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"dev.ohhoonim.factory.personalRepository", "dev.ohhoonim.factory.personalTable"},
        entityManagerFactoryRef = "personalEntityManagerFactory",
        transactionManagerRef = "personalJpaTransactionManager")
public class PersonalDatasourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari.personal")
    public HikariConfig personalHikariConfig() {
        return new HikariConfig();
    }

    @Bean(name = "personalDatasource")
    public DataSource personalDatasource() {
        return new LazyConnectionDataSourceProxy(new HikariDataSource(personalHikariConfig()));
    }

    @Bean(name = "personalEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean personalEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("personalDatasource") DataSource personalDatasource) {
        return builder.dataSource(personalDatasource)
                .packages("dev.ohhoonim.factory.personalRepository", "dev.ohhoonim.factory.personalTable")
                .persistenceUnit("personalEntityManager").properties(jpaProperties()).build();
    }

    private Map<String, Object> jpaProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.implicit_naming_strategy",
                SpringImplicitNamingStrategy.class.getName());
        props.put("hibernate.physical_naming_strategy",
                CamelCaseToUnderscoresNamingStrategy.class.getName());
        return props;
    }

    @Bean(name = "personalJpaTransactionManager")
    public PlatformTransactionManager personalJpaTransactionManager(
            @Qualifier("personalEntityManagerFactory") EntityManagerFactory personalEntityManagerFactory) {
        return new JpaTransactionManager(personalEntityManagerFactory);
    }
}
