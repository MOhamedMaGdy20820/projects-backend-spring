package com.momg.ecommerce.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;

import java.math.*;
import java.util.*;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sku;
    private String name;
    private String description;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "image_url")
    private String imageUrl;

    private boolean active;

    @Column(name = "units_in_stock")
    private int unitsInStock;

    @Column(name = "date_created")
    @CreationTimestamp
    private Date dateCreated;

    @Column(name = "last_updated")
    @UpdateTimestamp
    private Date lastUpdated;

    @ManyToOne
    @JoinColumn (name = "category_id" , nullable = false)
    private ProductCategory category;


}
