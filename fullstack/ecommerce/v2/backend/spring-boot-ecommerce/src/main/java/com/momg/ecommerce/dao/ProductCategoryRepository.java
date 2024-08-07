package com.momg.ecommerce.dao;

import com.momg.ecommerce.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200") // Used to allow front-end applications to interact with the back-end in a development environment
@RepositoryRestResource(collectionResourceRel = "productCategory", path = "product-category") //Providing CRUD operations through a RESTful API automatically.
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}
