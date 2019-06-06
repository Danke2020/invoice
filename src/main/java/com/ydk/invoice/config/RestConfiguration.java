package com.ydk.invoice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.hateoas.Identifiable;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.Type;

@Configuration
public class RestConfiguration implements RepositoryRestConfigurer {

    private final EntityManager entityManager;

    @Autowired
    public RestConfiguration(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(
                entityManager.getMetamodel().getEntities().stream()
                        .map(Type::getJavaType)
                        .filter(Identifiable.class::isAssignableFrom)
                        .toArray(Class[]::new));
    }
}