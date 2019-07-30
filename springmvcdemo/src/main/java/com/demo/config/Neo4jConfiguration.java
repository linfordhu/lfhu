package com.demo.config;


import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author hlf
 * @since 2019/07/16 09:37
 */
@EnableNeo4jRepositories(basePackages = "com.demo.neo4jdomain.repository")
@EnableTransactionManagement
public class Neo4jConfiguration {

    @Bean
    public SessionFactory sessionFactory() {
        // with domain entity base package(s)
        return new SessionFactory(configuration(), "com.demo.neo4jdomain");
    }

    @Bean
    public org.neo4j.ogm.config.Configuration configuration() {
        return new org.neo4j.ogm.config.Configuration.Builder()
                .uri("bolt://localhost")
                .credentials("neo4j", "123456")
                .build();
    }

    @Bean
    public Neo4jTransactionManager transactionManager() {
        return new Neo4jTransactionManager(sessionFactory());
    }
}
