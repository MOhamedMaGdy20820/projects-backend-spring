package com.momg.ecommerce.dao;

import com.momg.ecommerce.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "countries", path = "countries")
public interface CountryRepository extends JpaRepository<Country, Integer> {
}
