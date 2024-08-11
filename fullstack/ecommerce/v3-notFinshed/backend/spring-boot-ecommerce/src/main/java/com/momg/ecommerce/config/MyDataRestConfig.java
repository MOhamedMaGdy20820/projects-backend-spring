package com.momg.ecommerce.config;

import com.momg.ecommerce.entity.*;
import jakarta.persistence.*;
import org.hibernate.type.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.data.rest.core.config.*;
import org.springframework.data.rest.core.mapping.*;
import org.springframework.data.rest.webmvc.config.*;
import org.springframework.http.*;
import org.springframework.web.servlet.config.annotation.*;

import jakarta.persistence.metamodel.EntityType;

import java.util.*;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {


    private EntityManager entityManager;

    @Autowired
    public MyDataRestConfig(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

        HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE, HttpMethod.PATCH};


        // disable HTTP methods for Product: PUT, POST, DELETE and PATCH
        disableHttpMethods(Product.class,config, theUnsupportedActions);

        // disable HTTP methods for ProductCategory: PUT, POST, DELETE and PATCH
        disableHttpMethods(ProductCategory.class,config, theUnsupportedActions);

        // disable HTTP methods for Country: PUT, POST, DELETE and PATCH
        disableHttpMethods(Country.class,config, theUnsupportedActions);

        // disable HTTP methods for State: PUT, POST, DELETE and PATCH
        disableHttpMethods(State.class,config, theUnsupportedActions);

        // call an internal helper method
        exposeIds(config);

    }

    private static void disableHttpMethods(Class theClass,RepositoryRestConfiguration config,
                                                          HttpMethod[] theUnsupportedActions) {

        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))           // single item
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));    // Collection
    }

    private void exposeIds(RepositoryRestConfiguration config) {
        // expose entity ids

        // - get a list of all entity classes from the entity manager
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        // - create an array of the entity types
        List<Class> entityClasses = new ArrayList<>();

        // - get the entity types for the entities
        for (EntityType tempEntityType : entities) {
               entityClasses.add(tempEntityType.getJavaType());
        }

        // - expose the entity ids for the array of entity/domain types
        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);

    }


}