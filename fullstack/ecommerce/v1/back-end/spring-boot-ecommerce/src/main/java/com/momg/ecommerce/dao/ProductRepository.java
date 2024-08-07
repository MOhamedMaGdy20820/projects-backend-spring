package com.momg.ecommerce.dao;

import com.momg.ecommerce.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
// @RepositoryRestResource(collectionResourceRel = "Product", path = "products") // we don't need it
// if the entity the same name in data base             path == ( name of entity + s )
public interface ProductRepository extends JpaRepository<Product, Long> {


}
