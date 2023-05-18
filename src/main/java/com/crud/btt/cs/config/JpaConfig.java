package com.crud.btt.cs.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
@Configuration
public class JpaConfig {
    private final EntityManager entityManager;

    @Autowired
    public JpaConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

   @Bean
   public JPAQueryFactory jpaQueryFactory() {
       return new JPAQueryFactory(entityManager);
   }


}
