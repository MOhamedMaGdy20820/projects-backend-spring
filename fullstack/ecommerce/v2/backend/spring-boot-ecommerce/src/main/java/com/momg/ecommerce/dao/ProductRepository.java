package com.momg.ecommerce.dao;

import com.momg.ecommerce.entity.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
// @RepositoryRestResource(collectionResourceRel = "Product", path = "products") // we don't need it
// if the entity the same name in data base             path == ( name of entity + s )
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product>findByCategoryId(@Param("id") Long categoryId, Pageable pageable);

    //@Query("select * from Product p where p.name like concat('%',:name ,'%')")
    Page<Product>findByNameContaining(@Param("name") String name, Pageable pageable);

}
